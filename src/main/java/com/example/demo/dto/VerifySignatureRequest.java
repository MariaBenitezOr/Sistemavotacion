package com.example.demo.dto;


public class VerifySignatureRequest {

    private String data;
    private String signature;

    public String getData() {
        return data;
    }

    public String getSignature() {
        return signature;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
