package com.codenation.decrypt.service;

import com.codenation.decrypt.DTO.Request;
import com.codenation.decrypt.DTO.Response;
import com.codenation.decrypt.domain.Alphabet;
import com.codenation.decrypt.domain.Roman;
import com.codenation.decrypt.integration.IntegrationClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class DecryptService {

    @Autowired
    private IntegrationClient integrationClient;

    @Autowired
    private ObjectMapper objectMapper;

    public void doDecrypt() {
        try {
            Request request = requestIntegrationData();
            Integer range = request.getIndex();

            String decryptedText = shiftEncryptedString(request.getEncryptedText(), range);
            String encryptedSha = encryptInSha1(decryptedText);

            completeRequest(request, decryptedText, encryptedSha);

            final File answerFile = createJsonFile(request);
            final Response response = answerIntegration(answerFile);

            if (!response.getScore().equals("100")) {
                throw new RuntimeException("Unsuccessful - Rate: " + response.getScore());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Request requestIntegrationData() throws Exception {
        JsonNode json = integrationClient.requestData();
        return objectMapper.treeToValue(json, Request.class);
    }

    private Response answerIntegration(final File answer) throws Exception {
        FileInputStream input = new FileInputStream(answer);
        MultipartFile multipartFile = new MockMultipartFile("answer",
                answer.getName(), "text/plain", IOUtils.toByteArray(input));
        JsonNode json = integrationClient.submitSolution(multipartFile);
        return objectMapper.treeToValue(json, Response.class);
    }

    public String shiftEncryptedString(final String encryptedString, final Integer range) {
        final Alphabet roman = Roman.getInstance();
        StringBuilder response = new StringBuilder();
        char[] encryptedArray = encryptedString.toCharArray();
        for (char c : encryptedArray) {
            String decryptedString = String.valueOf(c);
            Integer index = roman.getIndex(c);
            if (index != null) {
                Integer newIndex = index - range;
                Integer decryptIndex = newIndex < 0 ? roman.getAlphabetSize() + newIndex : newIndex;
                decryptedString = roman.getLetter(decryptIndex);
            }
            response.append(decryptedString);
        }
        return response.toString();
    }

    public String encryptInSha1(final String decryptedText) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(decryptedText.getBytes(StandardCharsets.UTF_8));
        return String.format("%040x", new BigInteger(1, digest.digest()));
    }

    private void completeRequest(Request request, final String decryptedText, final String encryptedSha) {
        request.setDecryptedTextText(decryptedText);
        request.setCryptoResume(encryptedSha);
    }

    private File createJsonFile(final Request request) throws Exception {
        File file = new File("answer.json");
        FileWriter fileWriter = new FileWriter("answer.json", false);
        fileWriter.write(objectMapper.writeValueAsString(request));
        fileWriter.flush();
        fileWriter.close();
        return file;
    }
}
