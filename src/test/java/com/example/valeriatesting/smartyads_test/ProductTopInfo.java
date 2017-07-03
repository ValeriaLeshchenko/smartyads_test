package com.example.valeriatesting.smartyads_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductTopInfo extends BasePageObject {
    
    @FindBy(id="cmp-popup-button")
    private WebElement cmpPopupButton;
    
    @FindBy(xpath="id('shadePopup')")
    private WebElement shadePopup;
    
    @FindBy(xpath="//button[contains(text(),'Сравнить')]")
    private WebElement compareButton;
    
    @FindBy(xpath="//div[contains(@class,'block-img-gall')]")
    private WebElement imageGallery;
    
    @FindBy(xpath="//a[contains(.,'Всего предложений')]")
    private WebElement pricesLink;
    
    @FindBy(xpath="//a[contains(.,'отзыв')]")
    private WebElement discussionLink;

    public ProductTopInfo(WebDriver driver) {
        super(driver);
    }
    
    public void openComparePopup() {
        cmpPopupButton.click();
    }
    
    public void closeComparePopup() {
        WebElement closeLink = cmpPopupButton.findElement(By.xpath("//a[@data-modal='close']"));
        if (closeLink.isDisplayed()) { closeLink.click(); }
    }
    
    public boolean isComparePopupDisplayed() {
        return shadePopup.isDisplayed();
    }
    
    public void openComparePage() {
        compareButton.click();
    }
    
    public ProductTopInfo openImageGallery() {
        imageGallery.click();
        return this;
    }
    
    public void clickPrices() {
        pricesLink.click();
    }
    
    public void clickDiscussion() {
        discussionLink.click();
    }
    
}
