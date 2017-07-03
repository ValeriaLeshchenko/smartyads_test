package com.example.valeriatesting.smartyads_test;

import org.openqa.selenium.WebDriver;

//TODO set as abstract
public abstract class BasePageObject {
    protected WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }
}
