package com.asshofa.management.util;

import com.asshofa.management.exception.custom.BadRequestException;
import com.asshofa.management.model.response.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public final class EncryptionUtil {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final SecretKey secretKey;
    private static final byte[] IV;

    private static final Logger logger = LogManager.getLogger(EncryptionUtil.class);

    private static final String BASE64_SECRET_KEY = "YXNzaGhvZmEtbWFuYWdlbWVudC1rZXktMTIzNDU2Nzg=";
    private static final String BASE64_IV = "YXNzaG9mYWl2MTI=";

    private EncryptionUtil() {
    }

    static {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(BASE64_SECRET_KEY);
            secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            IV = Base64.getDecoder().decode(BASE64_IV);
        } catch (Exception e) {
            logger.error("Error initializing encryption parameters", e);
            throw new BadRequestException(ResponseMessage.SOMETHING_WENT_WRONG);
        }
    }

    public static String encrypt(Short id) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, IV);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] encrypted = cipher.doFinal(String.valueOf(id).getBytes());
            return BaseSixTwoUtil.encode(encrypted);
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
            byte[] decrypted = cipher.doFinal(BaseSixTwoUtil.decode(id));
            return Short.parseShort(new String(decrypted));
        } catch (Exception e) {
            logger.error("Error in decrypting", e);
            throw new BadRequestException(ResponseMessage.SOMETHING_WENT_WRONG);
        }
    }
}

