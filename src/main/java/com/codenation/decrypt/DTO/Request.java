package com.codenation.decrypt.DTO;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Request {

    private Integer index;
    private String token;
    private String encryptedText;
    private String decryptedTextText;
    private String cryptoResume;

    public Integer getIndex() {
        return index;
    }

    @JsonSetter("numero_casas")
    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getToken() {
        return token;
    }

    @JsonSetter("token")
    public void setToken(String token) {
        this.token = token;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    @JsonSetter("cifrado")
    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    public String getDecryptedTextText() {
        return decryptedTextText;
    }

    @JsonSetter("decifrado")
    public void setDecryptedTextText(String decryptedTextText) {
        this.decryptedTextText = decryptedTextText;
    }

    public String getCryptoResume() {
        return cryptoResume;
    }

    @JsonSetter("resumo_criptografico")
    public void setCryptoResume(String cryptoResume) {
        this.cryptoResume = cryptoResume;
    }
}
