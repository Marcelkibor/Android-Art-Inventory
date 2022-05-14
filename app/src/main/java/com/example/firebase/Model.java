package com.example.firebase;

public class Model {
    private String imageUri;
    private String imageCapt;
    private String itemPrice;
    private String itemAge;
    private String futureValue;
    private String itemRate;
    private String margin;
    public Model(){

    }
    public Model(String imageUri, String imageCapt,String itemPrice,String itemAge,String futureValue, String itemRate, String margin){
      this.imageUri = imageUri;
      this.imageCapt = imageCapt;
      this.itemPrice = itemPrice;
      this.itemAge = itemAge;
      this.futureValue = futureValue;
      this.itemRate = itemRate;
      this.margin = margin;
    }
    public String getImageUri() {
        return imageUri;
    }
    public String getImageCapt() {
        return imageCapt;
    }
    public String getFutureValue() {
        return futureValue;
    }
    public String getItemRate() {
        return itemRate;
    }
    public String getMargin() {
        return margin;
    }
    public String getItemPrice() {
        return itemPrice;
    }
    public String getItemAge() {
        return itemAge;
    }

    public void setImageUri(String imageUrl,String imageUri, String imageCapt,String itemPrice,String itemAge,String futureValue, String itemRate, String margin) {
        this.imageUri = imageUrl;
        this.imageCapt = imageCapt;
        this.itemPrice = itemPrice;
        this.itemAge = itemAge;
        this.futureValue = futureValue;
        this.itemRate = itemRate;
        this.margin = margin;

    }
}
