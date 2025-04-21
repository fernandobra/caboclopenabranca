package com.umbandanet.caboclopenabranca.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String data, String base64Key) throws Exception {
        byte[] key = decodeAndValidateKey(base64Key);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, String base64Key) throws Exception {
        byte[] key = decodeAndValidateKey(base64Key);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }

    private static byte[] decodeAndValidateKey(String base64Key) {
        byte[] key = Base64.getDecoder().decode(base64Key);
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("O comprimento da chave AES deve ser de 16, 24 ou 32 bytes.");
        }
        return key;
    }
}
