package com.midas.nuevatienda.integration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

public class TestController {

    protected final String BASE_PATH = "/";

    protected final Faker faker = new Faker();

    @Autowired
    protected ObjectMapper objectMapper;
}
