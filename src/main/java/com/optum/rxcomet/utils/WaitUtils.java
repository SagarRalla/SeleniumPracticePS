package com.optum.rxcomet.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
        WebDriver driver;
        private Duration defaultWaitTime = Duration.ofSeconds(60);

        public WaitUtils(WebDriver driver) {
            this.driver = driver;
        }

        public void waitForElementPresent(By locator) {
            webDriverWait()
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        public List<WebElement> waitForElementsPresent(By locator) {
            return webDriverWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
         }

        public WebElement waitForElementToBeDisplayed(WebElement element) {
            return webDriverWait()
                    .until(ExpectedConditions.visibilityOf(element));
        }

        public List<WebElement> waitForElementsToBeDisplayed(List<WebElement> elements) {
        return webDriverWait()
                .until(ExpectedConditions.visibilityOfAllElements(elements));
        }

        public void waitForElementClickable(WebElement element) {
            webDriverWait()
                    .until(ExpectedConditions.elementToBeClickable(element));
        }
        private WebDriverWait webDriverWait() {
            return new WebDriverWait(driver, defaultWaitTime);
        }
}
