package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BuciExeption extends RuntimeException {
    public BuciExeption(String message){super (message);}
}
