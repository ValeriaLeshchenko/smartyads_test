package com.example.valeriatesting.smartyads_test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPrices extends BasePageObject {
    
    @FindBy(xpath="//div[contains(@class,'price-line-shop')]")
    List<WebElement> priceLines;

    public ProductPrices(WebDriver driver) {
        super(driver);
    }
    
    public int getPriceCount() {
        return priceLines.size();
    }
}
