package com.example.valeriatesting.smartyads_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPageNav extends BasePageObject {
    
    @FindBy(xpath="//a[contains(string(), 'О товаре')]")
    private WebElement descriptionLink;
    
    @FindBy(xpath="//a[contains(string(), 'Все предложения')]")
    private WebElement pricesLink;
    
    @FindBy(xpath="//a[contains(string(), 'Отзывы и вопросы')]")
    private WebElement discussionLink;
    
    @FindBy(xpath="//div[@data-id='descr']")
    private WebElement descriptionContent;
    
    @FindBy(xpath="//div[@data-id='prices']")
    private WebElement pricesContent;
    
    @FindBy(xpath="//div[@data-id='discussion']")
    private WebElement discussionContent;
    
    public ProductPageNav(WebDriver driver) {
        super(driver);
    }
    
    public ProductDescription openDescription() {
        descriptionLink.click();
        SearchContextElementLocatorFactory descriptionLocatorFactory = new SearchContextElementLocatorFactory(descriptionContent);
        ProductDescription description = new ProductDescription(driver);
        PageFactory.initElements(descriptionLocatorFactory, description);
        return description;
    }
    
    public ProductPrices openPrices() {
        pricesLink.click();
        SearchContextElementLocatorFactory pricesLocatorFactory = new SearchContextElementLocatorFactory(pricesContent);
        ProductPrices prices = new ProductPrices(driver);
        PageFactory.initElements(pricesLocatorFactory, prices);
        return prices;
    }
    
    public int getPricesCount() {
        String pricesLinkText = pricesLink.getText();
        return Integer.parseInt(pricesLinkText.substring(
                pricesLinkText.lastIndexOf("(")+1, pricesLinkText.lastIndexOf(")")));
    }
    
    public ProductDiscussion openDiscussion() {
        discussionLink.click();
        SearchContextElementLocatorFactory discussionLocatorFactory = new SearchContextElementLocatorFactory(discussionContent);
        ProductDiscussion discussion = new ProductDiscussion(driver);
        PageFactory.initElements(discussionLocatorFactory, discussion);
        return discussion;
    }
}
