package com.example.valeriatesting.smartyads_test.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.valeriatesting.smartyads_test.*;

public class FiltersPanelTest extends BaseTest {
    
    @Test
    public void applyTwoFiltersAndRemoveOneOfThem() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        String firstFilter = filtersPanel.chooseRandomItemFromCategory("Процессор");
        String secondFilter = filtersPanel.chooseRandomItemFromCategory("Производитель");
        filtersPanel.removeFilter("Процессор", firstFilter);
        Assert.assertFalse(filtersPanel.isFilterApplied(firstFilter));
        Assert.assertTrue(filtersPanel.isFilterApplied(secondFilter));
    }
    
    @Test
    public void clickRemoveAllFilters() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        filtersPanel.chooseRandomItemFromCategory("Процессор");
        filtersPanel.chooseRandomItemFromCategory("Производитель");
        filtersPanel.removeAllFilters();
        Assert.assertFalse(filtersPanel.hasAppliedFilters());
    }
    
    @Test
    public void checkDisplayedProductsCount() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/");
        dismissToolbar();
        FiltersPanel filtersPanel = PageFactory.initElements(driver, FiltersPanel.class);
        
        int itemCount = filtersPanel.getItemCount("Встроенный оптический накопитель", "Blu-ray");
        filtersPanel.applyFilter("Встроенный оптический накопитель", "Blu-ray");
        
        WebElement elementContext = driver.findElement(By.xpath("id('catalogue')"));
        SearchContextElementLocatorFactory elementLocatorFactory = new SearchContextElementLocatorFactory(elementContext);
        Catalogue catalogue = new Catalogue(driver);
        PageFactory.initElements(elementLocatorFactory, catalogue);
        catalogue.setItems();
        Assert.assertEquals(catalogue.getItemsLength(),
                itemCount,
                "Product count for filter is not displayed correctly");
    }
}
