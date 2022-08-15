//package com.dibimbing.assignment3.controller;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class TestingLoginController {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void restTemplateLogin() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("username", "admin@mail.com");
//        map.add("password", "password");
//        map.add("grant_type", "password");
//        map.add("client_id", "my-client-web");
//        map.add("client_secret", "password");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        // call api lain : konsep microservice. setiap fitur memiliki service masing2
//        // cara call dengan mengunakan rest template, untuk testing bisa junit dll
//        //String url = "http://localhost:9092/api/oauth/token";
//        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        System.out.println("url=" + url);
//        System.out.println("response  =" + response.getBody());
//    }
//}