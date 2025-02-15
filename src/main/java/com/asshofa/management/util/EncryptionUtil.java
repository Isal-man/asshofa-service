package com.asshofa.management.util;

import com.asshofa.management.exception.custom.BadRequestException;
import com.asshofa.management.model.response.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public final class EncryptionUtil {

    private static final String ALGORITHM = "AES/GCM/NoPadding"; // Ubah algoritma ke AES-GCM
    private static final SecretKey secretKey;
    private static final byte[] IV = new byte[12];

    private static final Logger logger = LogManager.getLogger(EncryptionUtil.class);

    private EncryptionUtil() {
        // restriction
    }

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(IV);
        } catch (Exception e) {
            logger.error("Error in initializing SecretKey", e);
            throw new BadRequestException(ResponseMessage.SOMETHING_WENT_WRONG);
        }
    }

    public static String encrypt(Short id) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, IV);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] encrypted = cipher.doFinal(String.valueOf(id).getBytes());
            return Base64.getUrlEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            logger.error("Error in encrypting", e);
            throw new BadRequestException(ResponseMessage.SOMETHING_WENT_WRONG);
        }
    }

    public static Short decrypt(String id) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, IV);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] decrypted = cipher.doFinal(Base64.getUrlDecoder().decode(id));
            return Short.parseShort(new String(decrypted));
        } catch (Exception e) {
            logger.error("Error in decrypting", e);
            throw new BadRequestException(ResponseMessage.SOMETHING_WENT_WRONG);
        }
    }
}
