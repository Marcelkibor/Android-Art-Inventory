package com.example.firebase;
public class PostedCont {
    String imageUri;
    String imageDesc;
    String imageCapt;

    PostedCont() {}
    public PostedCont(String imageUri, String imageDesc, String imageCapt) {
        this.imageUri = imageUri;
        this.imageDesc = imageDesc;
        this.imageCapt = imageCapt;

    }
    public String getImageUri() {
        return imageUri;
    }
    public String getImageDesc() {
        return imageDesc;
    }
    public String getImageCapt() {
        return imageCapt;
    }
}
