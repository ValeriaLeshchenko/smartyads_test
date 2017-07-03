package com.example.valeriatesting.smartyads_test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FiltersPanel extends BasePageObject {

    public FiltersPanel(WebDriver driver) {
        super(driver);
    }
    
    public String chooseRandomItemFromCategory(String name) {
        By locator = By.xpath(String.format("id('filters')/div[contains(string(), '%s') "
                + "and contains(@class, 'f-title')]/following-sibling::div[1]//div[contains(@class, 'f-item')]", 
                name));
        List<WebElement> elements = driver.findElements(locator);
        int index = new Random().nextInt(elements.size());
        WebElement item = elements.get(index).findElement(By.tagName("a"));
        String itemText = item.getText();
        item.click();
        return itemText;
    }
    
    public String getRandomItemNameFromCategory(String name) {
        By locator = By.xpath(String.format("id('filters')/div[contains(string(), '%s') "
                + "and contains(@class, 'f-title')]/following-sibling::div[1]//div[contains(@class, 'f-item')]", 
                name));
        List<WebElement> elements = driver.findElements(locator);
        int index = new Random().nextInt(elements.size());
        WebElement item = elements.get(index).findElement(By.tagName("a"));
        return item.getText();
    }
    
    public int getItemCount(String category, String name) {
        By locator = By.xpath(String.format("id('filters')/div[contains(string(), '%s') "
                + "and contains(@class, 'f-title')]/following-sibling::div[1]//div[contains(@class, 'f-item')]"
                + "/a[contains(string(), '%s')]/parent::div", 
                category,
                name));
        String elementText = driver.findElement(locator).getText();
        return Integer.parseInt(elementText.substring(elementText.lastIndexOf("(") + 1, elementText.lastIndexOf(")")));
    }
    
    public boolean isFilterApplied(String name){
        By locator = By.xpath(String.format("id('filters')//div[contains(string(), 'Вы выбрали')]"
                + "//a[contains(text(), '%s')]", name));
        return driver.findElements(locator).size() == 1;
    }
    
    public void removeFilter(String category, String name){
        By locator = By.xpath(String.format("id('filters')//div[contains(string(), 'Вы выбрали')]"
                + "//div[contains(string(), '%s')]//following-sibling::div//a[contains(text(),"
                + "'%s')]", category, name));
        
        List<WebElement> filters = driver.findElements(locator);
        if (filters.size() == 1) {
            filters.get(0).click();
        }
    }
    
    public void removeAllFilters() {
        driver.findElement(By.xpath("id('filters')//span[contains(string(), 'Убрать все фильтры')]")).click();
    }
    
    public boolean hasAppliedFilters() {
        return driver.findElements(By.xpath("id('filters')//div[contains(string(), 'Вы выбрали')]")).size() > 0;
    }

    public void applyFilter(String category, String itemName) {
        By locator = By.xpath(String.format("id('filters')/div[contains(string(), '%s') "
                + "and contains(@class, 'f-title')]/following-sibling::div[1]//div[contains(@class, 'f-item')]"
                + "/a[contains(string(), '%s')]", 
                category,
                itemName));
        driver.findElement(locator).click();
    }
}
