package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
public class VerifySignatureRequest {

    @NotBlank(message = "La data es obligatoria")
    private String data;

    @NotBlank(message = "La firma es obligatoria")
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
