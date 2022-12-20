package com.example.weatherforecast.controller;

import com.example.weatherforecast.view.ViewFactory;

public class BaseController {

    private ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName(){
        return fxmlName;
    }
}