package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class HmacService {

    @Value("${app.hmac.secret}")
    private String secret;

    public String generateSignature(String data) {

        try {

            Mac mac = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKeySpec =
                    new SecretKeySpec(secret.getBytes(), "HmacSHA256");

            mac.init(secretKeySpec);

            byte[] hmacBytes = mac.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(hmacBytes);

        } catch (Exception ex) {
            throw new RuntimeException("Error generando firma HMAC");
        }
    }

    public boolean verifySignature(String data, String signature) {

        String generatedSignature = generateSignature(data);

        return generatedSignature.equals(signature);
    }

}
