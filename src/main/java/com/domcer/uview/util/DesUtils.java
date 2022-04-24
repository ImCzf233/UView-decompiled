/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtils {
    public static byte[] encrypt(String key, byte[] input) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(1, desKey);
        return Base64.getEncoder().encode(ecipher.doFinal(input));
    }

    public static byte[] decrypt(String key, byte[] input) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher dcipher = Cipher.getInstance("DES");
        dcipher.init(2, desKey);
        return dcipher.doFinal(Base64.getDecoder().decode(input));
    }
}

