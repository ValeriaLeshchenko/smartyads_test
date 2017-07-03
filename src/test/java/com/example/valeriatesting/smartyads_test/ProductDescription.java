package com.example.valeriatesting.smartyads_test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDescription extends BasePageObject {
    
    @FindBy(xpath="id('short-props-list')//th[contains(string(), 'Тип процессора')]/following-sibling::td")
    private WebElement processorDataCell;

    public ProductDescription(WebDriver driver) {
        super(driver);
    }
    
    public String getProcessorName() {
        return processorDataCell.getText();
    }

    public void selectFullProps() {
        driver.findElement(By.xpath("//div[contains(@class,'opis')]//span[contains(string(), 'подробные')]")).click();
    }
    
    public boolean isFullPropsVisible() {
        List<WebElement> props = driver.findElements(By.xpath("id('full-props-list')"));
        return props.size() > 0;
    }
   
}
