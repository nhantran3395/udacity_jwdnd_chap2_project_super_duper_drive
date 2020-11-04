package com.udacity.jwdnd.course1.cloudstorage.UI.Page.PageUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtil {

    private WebDriver driver;

    public PageUtil(WebDriver driver){
        this.driver = driver;
    }

    public void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
