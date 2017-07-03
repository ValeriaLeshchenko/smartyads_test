package com.example.valeriatesting.smartyads_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Catalogue extends BasePageObject {
    private List<CatalogueItem> catalogueItems;
    
    @FindBy(xpath="id('catalogue')/div/div[contains(@class, 'gd-item')]")
    private List<WebElement> catalogueElements;
    
    @FindBy(xpath="id('catalogue')//div[@data-id='hl-paginator']//a[@title='Последняя']")
    private WebElement lastPageLink;

    public Catalogue(WebDriver driver) {
        super(driver);
        catalogueItems = new ArrayList<CatalogueItem>();
    }
    
    public List<WebElement> getCatalogueElements() {
        return catalogueElements;
    }
    
    private boolean isPaginatorDisplayed() {
        List<WebElement> elements = driver.findElements(By.xpath("id('catalogue')"
                + "//div[@data-id='hl-paginator']"));
        return elements.size() > 0; 
    }
    
    private int getPageCount() {
        return Integer.parseInt(driver.findElement(By.xpath("id('catalogue')//div[@data-id='hl-paginator']"
                + "//span//a[@data-eventcategory='Pages Catalog'][last()]")).getText());
    }
    
    public void setItems() {
        List<WebElement> catalogueElements = driver.findElements(By.xpath("id('catalogue')/div/div[contains(@class, 'gd-item')]"));
        for (WebElement catalogueElement : catalogueElements) {
            SearchContextElementLocatorFactory elementLocatorFactory = new SearchContextElementLocatorFactory(catalogueElement);
            CatalogueItem item = new CatalogueItem(driver);
            PageFactory.initElements(elementLocatorFactory, item);
            catalogueItems.add(item);
        }
    }
    
    
    public CatalogueItem getRandomItem() {
        int index = new Random().nextInt(catalogueItems.size());
        return catalogueItems.get(index);
    }
    
    public int getItemsLength() {
        if (isPaginatorDisplayed()) {
            lastPageLink.click();
            return (getPageCount() - 1) * 24 + catalogueItems.size();
        }
        return catalogueItems.size();
    }

}
