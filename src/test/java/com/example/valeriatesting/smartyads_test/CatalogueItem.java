package com.example.valeriatesting.smartyads_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogueItem extends BasePageObject {
    
    @FindBy(css="a[data-eventlabel='Product name']")
    private WebElement linkTitle;

    public CatalogueItem(WebDriver driver) {
        super(driver);
    }

    
    public void goToProductPage() {
        linkTitle.click();
    }
    
    public String getTitle() {
        return linkTitle.getText();
    }

}
