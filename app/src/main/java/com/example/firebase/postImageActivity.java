package com.example.firebase;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class postImageActivity extends AppCompatActivity {
    private Button postCont;
    private Button homePage;
    private ImageView SecondImageView;
    private Uri imageUri;
    private EditText PostDescription;
    private EditText PostCaption;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_image);
        homePage = findViewById(R.id.btHomePage);
        postCont = findViewById(R.id.postBtn);
        SecondImageView = findViewById(R.id.SecondImageView);
        PostDescription = findViewById(R.id.postDescription);
        PostCaption = findViewById(R.id.captionPost);


        SecondImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryInt = new Intent();
                galleryInt.setAction(Intent.ACTION_GET_CONTENT);
                galleryInt.setType("image/*");
                startActivityForResult(galleryInt,2);
            }
        });
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(postImageActivity.this,HomePageActivity.class);
                startActivity(intent);
//
            }
        });
        postCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //method for posting the contents to database called posts.
               if (imageUri!=null){
                   postContent(imageUri);
               }
            }
        });
    }

    private void postContent( Uri uri) {
        StorageReference fileRef = FirebaseStorage.getInstance().getReference("Profile"+getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        PostedCont postedCont = new PostedCont(uri.toString(),PostDescription.getText().toString(),PostCaption.getText().toString());
                        FirebaseUser myUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(myUser!=null){
                            String uid= myUser.getUid();
                            DatabaseReference root = FirebaseDatabase.getInstance().getReference("post");
                            String key = root.push().getKey();
                            root.child(key).setValue(postedCont).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(postImageActivity.this,"UPLOADING SUCCESS!!",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(postImageActivity.this,GalleryActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(postImageActivity.this,"UPLOADING FAILED!!",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            Toast.makeText(postImageActivity.this,"Please Login First!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(postImageActivity.this,"UPLOADING FAILED!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode == RESULT_OK &&data!=null){
            imageUri = data.getData();
            SecondImageView.setImageURI(imageUri);
        }
    }
}