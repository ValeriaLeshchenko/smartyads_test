package com.example.valeriatesting.smartyads_test.integration;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.valeriatesting.smartyads_test.BaseTest;
import com.example.valeriatesting.smartyads_test.Catalogue;
import com.example.valeriatesting.smartyads_test.CatalogueItem;
import com.example.valeriatesting.smartyads_test.FiltersPanel;
import com.example.valeriatesting.smartyads_test.ProductDescription;
import com.example.valeriatesting.smartyads_test.ProductPageNav;
import com.example.valeriatesting.smartyads_test.ProductTopInfo;
import com.example.valeriatesting.smartyads_test.SearchContextElementLocatorFactory;

public class ComputersIT extends BaseTest {

    @Test
    public void applyFilter() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        String selectedFilter = filtersPanel.chooseRandomItemFromCategory("Процессор");
        Assert.assertTrue(filtersPanel.isFilterApplied(selectedFilter),
                "Failed to apply filtering by processor");
    }

    @Test
    public void selectItem() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        WebElement elementContext = driver.findElement(By.xpath("id('catalogue')"));
        SearchContextElementLocatorFactory elementLocatorFactory = new SearchContextElementLocatorFactory(elementContext);
        Catalogue catalogue = new Catalogue(driver);
        PageFactory.initElements(elementLocatorFactory, catalogue);
        catalogue.setItems();
        CatalogueItem item = catalogue.getRandomItem();
        String itemTitle = item.getTitle();
        item.goToProductPage();
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains(itemTitle),
                "Item description not found");
    }

    @Test
    public void applyFilterAndSelectItem() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        String selectedFilter = filtersPanel.chooseRandomItemFromCategory("Процессор");
        WebElement elementContext = driver.findElement(By.xpath("id('catalogue')"));
        SearchContextElementLocatorFactory elementLocatorFactory = new SearchContextElementLocatorFactory(elementContext);
        Catalogue catalogue = new Catalogue(driver);
        PageFactory.initElements(elementLocatorFactory, catalogue);
        catalogue.setItems();
        CatalogueItem item = catalogue.getRandomItem();
        item.goToProductPage();
        WebElement navContext = driver.findElement(By.xpath("//div[contains(@class,'product-page-nav')]"));
        SearchContextElementLocatorFactory navLocatorFactory = new SearchContextElementLocatorFactory(navContext);
        ProductPageNav productPageNav = new ProductPageNav(driver);
        PageFactory.initElements(navLocatorFactory, productPageNav);
        dismissAd();
        ProductDescription descriptionTab = productPageNav.openDescription();
        String itemProcessorName = descriptionTab.getProcessorName();
        Assert.assertTrue(matchProcessorNameToFilter(itemProcessorName, selectedFilter));
    }

    @Test
    public void addItemToCompare() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        filtersPanel.chooseRandomItemFromCategory("Процессор");
        WebElement elementContext = driver.findElement(By.xpath("id('catalogue')"));
        SearchContextElementLocatorFactory elementLocatorFactory = new SearchContextElementLocatorFactory(elementContext);
        Catalogue catalogue = new Catalogue(driver);
        PageFactory.initElements(elementLocatorFactory, catalogue);
        catalogue.setItems();
        CatalogueItem item = catalogue.getRandomItem();
        item.goToProductPage();
        WebElement topInfoContext = driver.findElement(By.xpath("//div[contains(@class, 'product-info-top')]"));
        SearchContextElementLocatorFactory infoLocatorFactory = new SearchContextElementLocatorFactory(topInfoContext);
        ProductTopInfo topInfo = new ProductTopInfo(driver);
        PageFactory.initElements(infoLocatorFactory, topInfo);
        topInfo.openComparePopup();
        Assert.assertTrue(topInfo.isComparePopupDisplayed());
        topInfo.closeComparePopup();

        driver.navigate().back();

        WebElement anotherCatalogueContext = driver.findElement(By.xpath("id('catalogue')"));
        SearchContextElementLocatorFactory anotherLocatorFactory = new SearchContextElementLocatorFactory(anotherCatalogueContext);
        Catalogue anotherCatalogue = new Catalogue(driver);
        PageFactory.initElements(anotherLocatorFactory, anotherCatalogue);
        anotherCatalogue.setItems();
        CatalogueItem anotherItem = anotherCatalogue.getRandomItem();
        anotherItem.goToProductPage();
        WebElement topInfoContextn = driver.findElement(By.xpath("//div[contains(@class, 'product-info-top')]"));
        SearchContextElementLocatorFactory infoLocatorFactoryn = new SearchContextElementLocatorFactory(topInfoContextn);
        ProductTopInfo topInfon = new ProductTopInfo(driver);
        PageFactory.initElements(infoLocatorFactoryn, topInfon);
        topInfon.openComparePopup();
        Assert.assertFalse(topInfon.isComparePopupDisplayed());
        topInfon.openComparePage();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.findElementByTagName("h1").getText(), "Сравнение моделей",
                "Heading for item compare page not found");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }


    private boolean matchProcessorNameToFilter(String itemProcessorName, String selectedFilter) {
        itemProcessorName = itemProcessorName.trim();
        selectedFilter = selectedFilter.trim();
        if (selectedFilter == "Intel Xeon E3-xxxx/E5-xxxx") {
            return itemProcessorName.contains("Intel Xeon E3") ||
                    itemProcessorName.contains("Intel Xeon E5");
        } else if (selectedFilter == "AMD Phenom II X4 и X6, AMD FX") {
            return itemProcessorName.contains("AMD Phenom II X4") ||
                    itemProcessorName.contains("AMD Phenom II X6") ||
                    itemProcessorName.contains("AMD FX");
        } else if (selectedFilter == "AMD Athlon II X3 и X4, A6, A8, A10") {
            return itemProcessorName.contains("AMD Athlon II X3") ||
                    itemProcessorName.contains("AMD Athlon II X4") ||
                    itemProcessorName.contains("AMD A6") ||
                    itemProcessorName.contains("AMD A8") ||
                    itemProcessorName.contains("AMD A10");
        } else if (selectedFilter == "AMD Athlon II X2, A4") {
            return itemProcessorName.contains("AMD Athlon II X2") ||
                    itemProcessorName.contains("AMD A4");
        } else if (selectedFilter == "AMD E-xxx, С-xx") {
            return itemProcessorName.contains("AMD E-") ||
                    itemProcessorName.contains("AMD С-");
        } else if (selectedFilter == "другой Intel") {
            return itemProcessorName.contains("Intel");
        } else if (selectedFilter == "другой AMD") {
            return itemProcessorName.contains("AMD");
        } else if (selectedFilter == "другой") {
            return !(itemProcessorName.contains("AMD")) && !(itemProcessorName.contains("Intel"));
        } else if (selectedFilter.endsWith("ххх")) {
            return itemProcessorName.contains(selectedFilter.substring(0, selectedFilter.length() - 3));
        } else {
            return itemProcessorName.contains(selectedFilter);
        }
    }
}
