package com.example.valeriatesting.smartyads_test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDiscussion extends BasePageObject {
    
    @FindBy(xpath="//div[contains(@class, 'rate-text') and contains(string(), 'Общая')]/following-sibling::div[contains(@class, 'b-rate')]")
    private WebElement averageMark;
    
    @FindBy(xpath="//div[@data-id='discussion']//div[contains(@class, 'page-product-rate') and contains(string(),'Оценка')]//div[contains(@class, 'b-rate')]")
    private List<WebElement> ratings;
    
    public ProductDiscussion(WebDriver driver) {
        super(driver);
    }
    
    public Double getDisplayedAverageRating() {
        return Double.parseDouble(averageMark.getText());
    }
    
    public double countAverageRating() {
        List<Double> ratingMarks = new ArrayList<Double>();
        for (WebElement rating : ratings) {
            ratingMarks.add(Double.parseDouble(rating.getText()));
        }
        Double sum = 0.0;
        for (Double mark : ratingMarks) {
            sum += mark;
        }
        return sum / ratingMarks.size();
    }
}
