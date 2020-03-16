package com.codenation.decrypt.integration;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "integrationClient", url = "${codenation.url}")
public interface IntegrationClient {

    @RequestMapping(method = RequestMethod.GET, value = "${codenation.url.generate.data}", produces = "application/json")
    JsonNode requestData() throws Exception;

    @RequestMapping(method = RequestMethod.POST, value = "${codenation.url.submit.solution}", consumes = "multipart/form-data", produces = "application/json")
    JsonNode submitSolution(@Param("file") MultipartFile file) throws Exception;
}
