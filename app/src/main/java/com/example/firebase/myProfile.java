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

            private void postContent(Uri imageUri) {
            }
        });
    }
}
