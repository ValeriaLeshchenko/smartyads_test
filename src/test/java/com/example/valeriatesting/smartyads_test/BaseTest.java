package com.example.valeriatesting.smartyads_test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public abstract class BaseTest {
  protected ChromeDriver driver;
  public WebDriverWait wait;

  @BeforeClass(alwaysRun=true)
  public void setUp() throws Exception {
    setupChromeDriver();
    driver.manage().timeouts().
      implicitlyWait(3, TimeUnit.SECONDS);
  }

  @AfterClass(alwaysRun=true)
  public void tearDown() throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  protected void setupChromeDriver() {
    ChromeDriverManager.getInstance().setup();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--disable-web-security");
    options.addArguments("--no-proxy-server");

    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("credentials_enable_service", false);
    prefs.put("profile.password_manager_enabled", false);

    options.setExperimentalOption("prefs", prefs);

    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

    driver = new ChromeDriver(capabilities);
  }

  protected void dismissToolbar() {
      WebElement toolbarHideLink = driver.findElementByXPath("//div[contains(@class, 'toolbar-user')]//a[contains(@class, 'hide')]");
      if (toolbarHideLink.isDisplayed()) { toolbarHideLink.click(); }
  }

  protected void dismissAd() {
      if (driver.findElement(By.tagName("iframe")).isDisplayed()) {
          driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
          List<WebElement> closeElement = driver.findElements(By.xpath("id('close')"));
          if (closeElement.size() > 0) {
              closeElement.get(0).click();
          }
          driver.switchTo().defaultContent();
      };
  }
}
