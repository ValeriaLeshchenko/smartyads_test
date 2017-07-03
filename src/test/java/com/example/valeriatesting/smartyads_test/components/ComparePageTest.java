package com.example.valeriatesting.smartyads_test.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.valeriatesting.smartyads_test.BaseTest;

public class ComparePageTest extends BaseTest {
    
    @Test
    public void openWithNoProducts() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/cmp/");
        List<WebElement> list = driver.findElements(By.xpath(String.format("//*[contains(text(),'%s')]", "Ошибка")));
        Assert.assertTrue(list.size() > 0, "Error message not found");
    }
    
    @Test
    public void openWithMaximumNumberOfProducts() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/cmp/?s=9978668-10348706-9344287-2929892-3275800-5262498-728037-659207-6635457-7076909");
        List<WebElement> list = driver.findElements(By.xpath("id('slider_face_card')//div[contains(@class, 'item-compare')]"));
        Assert.assertTrue(list.size() == 10, "Expected to find 10 elements selected for comparison, but something went wrong");
    }
    
    @Test
    public void openWithTooManyProducts() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/cmp/?s=9978668-10348706-9344287-2929892-3275800-5262498-728037-659207-6635457-10348705-7076909");
        List<WebElement> list = driver.findElements(By.xpath(String.format("//*[contains(text(),'%s')]", "недопустимое количество товаров")));
        Assert.assertTrue(list.size() > 0, "Error message not found");
    }
    
    @Test
    public void removeProductFromComparison() {
        driver.get("http://hotline.ua/computer/nastolnye-kompyutery/cmp/?s=9978668-10348706-9344287");
        List<WebElement> productList = driver.findElements(By.xpath("id('slider_face_card')//a[contains(@class, 'txt-br')]"));
        List<String> modelNames = new ArrayList<String>();
        for (WebElement element : productList) {
            modelNames.add(element.getText());
        }
        driver.findElement(By.xpath("(id('slider_face_card')//span[contains(@class, 'close-x')])[1]")).click();
        List<WebElement> updatedList = driver.findElements(By.xpath("id('slider_face_card')//a[contains(@class, 'txt-br')]"));
        List<String> updatedModelNames = new ArrayList<String>();
        for (WebElement element : updatedList) {
            updatedModelNames.add(element.getText());
        }
        Assert.assertEquals(updatedList.size(), 2, "Expected to find 2 products for comparison "
                + "after removing one, but something went wrong");
        Assert.assertFalse(updatedModelNames.contains(modelNames.get(0)));
        Assert.assertTrue(updatedModelNames.containsAll(modelNames.subList(1, 3)));
    }
}
