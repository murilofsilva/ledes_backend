package com.ledes.project.business;

import com.ledes.project.config.EncryptionConfig;
import com.ledes.project.utls.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final EncryptionConfig encryptionConfig;

    @Autowired
    public EncryptionService(EncryptionConfig encryptionConfig) {
        this.encryptionConfig = encryptionConfig;
    }

    public String encrypt(String value) {
        return CryptoUtil.encrypt(encryptionConfig.getEncryptionKey(), value);
    }

    public String decrypt(String encryptedValue) {
        return CryptoUtil.decrypt(encryptionConfig.getEncryptionKey(), encryptedValue);
    }
}
