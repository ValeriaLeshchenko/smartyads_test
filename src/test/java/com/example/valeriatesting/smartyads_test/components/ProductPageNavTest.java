package com.example.valeriatesting.smartyads_test.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.valeriatesting.smartyads_test.*;

public class ProductPageNavTest extends BaseTest {
    
    @Test
    public void checkPricesNumber() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        dismissToolbar();
        WebElement navContext = driver.findElement(By.xpath("//div[contains(@class,'product-page-nav')]"));
        SearchContextElementLocatorFactory navLocatorFactory = new SearchContextElementLocatorFactory(navContext);
        ProductPageNav productPageNav = new ProductPageNav(driver);
        PageFactory.initElements(navLocatorFactory, productPageNav);
        int pricesCount = productPageNav.getPricesCount();
        ProductPrices pricesTab = productPageNav.openPrices();
        Assert.assertEquals(pricesTab.getPriceCount(),
                pricesCount,
                "Prices count in prices tab not displayed correctly");
    }
    
    @Test
    public void checkDiscussionRating() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        dismissToolbar();
        WebElement navContext = driver.findElement(By.xpath("//div[contains(@class,'product-page-nav')]"));
        SearchContextElementLocatorFactory navLocatorFactory = new SearchContextElementLocatorFactory(navContext);
        ProductPageNav productPageNav = new ProductPageNav(driver);
        PageFactory.initElements(navLocatorFactory, productPageNav);
        ProductDiscussion discussionTab = productPageNav.openDiscussion();
        Assert.assertEquals(discussionTab.getDisplayedAverageRating(),
                discussionTab.countAverageRating(),
                "Rating in discussion tab not displayed correctly");
    }
    
    @Test
    public void viewFullProductProperties() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        dismissToolbar();
        WebElement navContext = driver.findElement(By.xpath("//div[contains(@class,'product-page-nav')]"));
        SearchContextElementLocatorFactory navLocatorFactory = new SearchContextElementLocatorFactory(navContext);
        ProductPageNav productPageNav = new ProductPageNav(driver);
        PageFactory.initElements(navLocatorFactory, productPageNav);
        ProductDescription descriptionTab = productPageNav.openDescription();
        descriptionTab.selectFullProps();
        Assert.assertTrue(descriptionTab.isFullPropsVisible());
    }
}
