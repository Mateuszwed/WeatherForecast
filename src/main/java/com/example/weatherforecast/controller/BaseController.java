package com.example.weatherforecast.controller;
public class BaseController {

    private String fxmlName;

    public BaseController(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    public String getFxmlName(){
        return fxmlName;
    }
}