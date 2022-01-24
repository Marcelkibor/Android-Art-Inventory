package com.example.firebase;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
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

public class myProfile extends AppCompatActivity {
private Button uploadBtn;
private ImageView imageView;
private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        uploadBtn = findViewById(R.id.profilePost);
        imageView = findViewById(R.id.profileImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryInt = new Intent();
                galleryInt.setAction(Intent.ACTION_GET_CONTENT);
                galleryInt.setType("image/*");
                startActivityForResult(galleryInt,2);
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri!=null){
                postContent(imageUri);
                }
                else{
                    Toast.makeText(myProfile.this,"SELECTION ERROR!!",Toast.LENGTH_SHORT).show();
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
                        Model model = new Model(uri.toString());
                        FirebaseUser myUser = FirebaseAuth.getInstance().getCurrentUser();
                      if(myUser!=null){
                          String uid= myUser.getUid();
                          DatabaseReference root = FirebaseDatabase.getInstance().getReference("users");
                          root.child(uid).child("profile").setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {
                                  Toast.makeText(myProfile.this,"SUCCESS!",Toast.LENGTH_SHORT).show();
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  Toast.makeText(myProfile.this,"UPLOADING FAILED!!",Toast.LENGTH_SHORT).show();
                              }
                          });

                    }
                      else{
                          Toast.makeText(myProfile.this,"Please Login First!!",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(myProfile.this,"UPLOADING FAILED!!",Toast.LENGTH_SHORT).show();
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
            imageView.setImageURI(imageUri);
        }
    }
}




//
//}
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    imageView.setImageURI(selectedImageUri);
//                }
//            }
//        }
//    }
//}
//
//        uploadBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(imageUri!=null){
//                    uploadToFirebase(imageUri);
//                }else{
//                    Toast.makeText(myProfile.this,"SELECTION ERROR!!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
;

//    private void uploadToFirebase(Uri uri) {
//        StorageReference fileRef = FirebaseStorage.getInstance().getReference("images").child(System.currentTimeMillis()+"."+getFileExtension(uri));
//        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    Model model = new Model(uri.toString());
//                    String modelId = root.push().getKey();
//                    assert modelId != null;
//                    root.child(modelId).setValue(model);
//                    Toast.makeText(myProfile.this,"UPLOAD SUCCESSFULLY!!",Toast.LENGTH_SHORT).show();
//
//                }
//            });
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(myProfile.this,"POSTING ERROR",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//    private String getFileExtension(Uri mUri) {
//        ContentResolver cr = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return  mime.getExtensionFromMimeType(cr.getType(mUri));
//    }


