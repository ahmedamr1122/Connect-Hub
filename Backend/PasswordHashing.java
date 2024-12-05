/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ahmed
 */
public class PasswordHashing {

    public String hashedPass(String passowrd) {
        try {
            //Chooses Algorithm SHA-256
            MessageDigest transform = MessageDigest.getInstance("SHA-256");

            //apply algorithm and return list of bytes
            byte[] hashed = transform.digest(passowrd.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hashed) {
                hex.append(String.format("%02x", b)); //convert each byte into two hexadecimal chars
            }
            String hashedPassword = hex.toString();
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
