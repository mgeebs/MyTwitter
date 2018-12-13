/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author michaelhedrick
 */
public class PwHashingUtil {

  public String hashPassword(String passwordToHash, byte[] salt){
    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt);
      byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for(int i=0; i< bytes.length ;i++){
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e){
      e.printStackTrace();
    }
    return generatedPassword;
  }

  public boolean checkPasswordHash(String hashedPw, String attempt, byte[] salt){
    String generatedHash = hashPassword(attempt, salt);
    return hashedPw.equals(generatedHash);
  }
}