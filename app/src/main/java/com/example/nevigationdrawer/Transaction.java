package com.example.nevigationdrawer;

import android.widget.ImageView;

public class Transaction {
    private String amount, contactNumber ;
    private ImageView imageTransactin;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ImageView getImageTransactin() {
        return imageTransactin;
    }

    public void setImageTransactin(ImageView imageTransactin) {
        this.imageTransactin = imageTransactin;
    }
}
