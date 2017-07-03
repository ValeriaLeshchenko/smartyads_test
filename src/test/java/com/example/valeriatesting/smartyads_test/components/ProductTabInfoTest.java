package com.example.valeriatesting.smartyads_test.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.valeriatesting.smartyads_test.BaseTest;
import com.example.valeriatesting.smartyads_test.ProductTopInfo;
import com.example.valeriatesting.smartyads_test.SearchContextElementLocatorFactory;

public class ProductTabInfoTest extends BaseTest {

    @Test
    public void playVideo() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        WebElement topInfoContext = driver.findElement(By.xpath("//body"));
        SearchContextElementLocatorFactory infoLocatorFactory = new SearchContextElementLocatorFactory(topInfoContext);
        ProductTopInfo topInfo = new ProductTopInfo(driver);
        PageFactory.initElements(infoLocatorFactory, topInfo);
        topInfo.openImageGallery();
        driver.findElement(By.xpath("//span[@class='prev']")).click();
        driver.findElement(By.id("video_main")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("id('video_main')/iframe")));
        List<WebElement> videoElements = driver.findElements(By.xpath("//div[contains(@class,'playing-mode')]"));
        Assert.assertTrue(videoElements.size() > 0, "Failed to play video for the product");
        driver.switchTo().defaultContent();
    }

    @Test
    public void clickPricesToDisplayPricesTab() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        WebElement topInfoContext = driver.findElement(By.xpath("//body"));
        SearchContextElementLocatorFactory infoLocatorFactory = new SearchContextElementLocatorFactory(topInfoContext);
        ProductTopInfo topInfo = new ProductTopInfo(driver);
        PageFactory.initElements(infoLocatorFactory, topInfo);
        topInfo.clickPrices();
        List<WebElement> priceTabElements = driver.findElements(By.xpath("//div[@data-id='prices']"));
        Assert.assertTrue(priceTabElements.size() > 0 && priceTabElements.get(0).isDisplayed(),
                "Prices tab not open");
    }

    @Test
    public void clickReviewsToDisplayDiscussionTab() {
        driver.get("http://hotline.ua/computer-nastolnye-kompyutery/apple-mac-mini-2014-mgem2ll/");
        WebElement topInfoContext = driver.findElement(By.xpath("//body"));
        SearchContextElementLocatorFactory infoLocatorFactory = new SearchContextElementLocatorFactory(topInfoContext);
        ProductTopInfo topInfo = new ProductTopInfo(driver);
        PageFactory.initElements(infoLocatorFactory, topInfo);
        topInfo.clickDiscussion();
        List<WebElement> priceTabElements = driver.findElements(By.xpath("//div[@data-id='discussion']"));
        Assert.assertTrue(priceTabElements.size() > 0 && priceTabElements.get(0).isDisplayed(),
                "Discussion tab not open");
    }
}
