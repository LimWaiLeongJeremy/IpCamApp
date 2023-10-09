package com.ipcam.backend.controllers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class VideoProxyController {

    @GetMapping("/proxy-video")
    public ResponseEntity<byte[]> proxyVideo() throws IOException {
        // Use HttpClient to fetch the HTTP video content
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://example.com/original-video-url");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // Read the content and return it as a response
        byte[] videoData = EntityUtils.toByteArray(response.getEntity());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(videoData, headers, HttpStatus.OK);
    }
}
