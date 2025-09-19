package com.optum.rxcomet.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Interactions {
	
	public static WebDriver driver;
    public WaitUtils wait;

    //Interactions() constructor creates a WaitUtils object. 
    //WaitUtils is initialized with the same WebDriver.
    public Interactions(){
        this.wait =  new WaitUtils(this.driver);    
    }
    
    public void launchBrowser(String browserName) throws MalformedURLException {
        Properties prop = JavaUtils.readPropertiesFile(
                FilePathUtils.getResourceDirectoryPath() + "/EnvironmentDetails.properties");
        switch (browserName.toLowerCase()) {
            case "chrome":
            	//Creates an object to customize how Chrome browser should behave when launched.
            	//You can use this to run Chrome in headless mode, disable extensions, or set window size.
                ChromeOptions coptions = new ChromeOptions();
                
                //Automatically downloads and sets up the correct version of ChromeDriver for your system.
                WebDriverManager.chromedriver().setup();
                
                //this would run Chrome without a GUI (headless mode).
                //coptions.addArguments("--headless");
                
                //In CI/CD environments the sandbox can cause Chrome to crash or fail to start.
                //Disabling it helps Chrome run in restricted environments
                coptions.addArguments("--no-sandbox");
                
                //While running Selenium tests in headless mode(CI/CD) without a physical GPU. 
                //If you don’t disable GPU, Chrome might crash or fail to render pages properly.
                coptions.addArguments("--disable-gpu");
                
                //Starts a new Chrome browser session using the options defined above with no sandbox and GPU disabled. 
                driver = new ChromeDriver(coptions);
                break;
            case "edge":
                EdgeOptions options = new EdgeOptions();
               // options.addArguments("--headless");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(options);
                break;

            case "grid":
                ChromeOptions chromeOptions= new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://10.28.8.73:4444/wd/hub/"),chromeOptions);
                break;

            default:
                throw new RuntimeException("we are not supporting " + browserName + " browser");
        }
        driver.get(prop.getProperty("Stage_Env"));   //This will give the value of Test_Env key from properties file
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

	public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    public String getText(WebElement ele) {
            return  ele.getText();
    }
    public void hover(WebElement ele) {
        String text = "";
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).perform();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    
    public void pressRightArrow() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    
    public void pressLeftArrow() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_LEFT).build().perform();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    
    public void clearAndType(WebElement element, String valueToType) {
        try {
            wait.waitForElementToBeDisplayed(element);
            element.clear();
            element.sendKeys(valueToType);
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    public List<WebElement> getElementsById(String id){
        return getElements("id", id);
    }
    public List<WebElement> getElementsByXpath(String xpath){
        return getElements("xpath", xpath);
    }
    public List<WebElement> getElementsByName(String name){
        return getElements("name", name);
    }
    public List<WebElement> getElementsByCssSelector(String cssSelector){
        return getElements("cssSelector", cssSelector);
    }
    public List<WebElement> getElementsByClass(String className){
        return getElements("class", className);
    }

    public List<WebElement> getElementsById(WebElement parent, String id){
        return getElements(parent, "id", id);
    }
    public List<WebElement> getElementsByXpath(WebElement parent, String xpath){
        return getElements(parent, "xpath", xpath);
    }
    public List<WebElement> getElementsByName(WebElement parent, String name){
        return getElements(parent, "name", name);
    }
    public List<WebElement> getElementsByCssSelector(WebElement parent, String cssSelector){
        return getElements(parent, "cssSelector", cssSelector);
    }
    public List<WebElement> getElementsByClass(WebElement parent, String className){
        return getElements(parent, "class", className);
    }

    public WebElement getElementById(String id){
        return getElement("id", id);
    }
    public WebElement getElementByXpath(String xpath){
        return getElement("xpath", xpath);
    }
    public WebElement getElementByName(String name){
        return getElement("name", name);
    }
    public WebElement getElementByCssSelector(String cssSelector){
        return getElement("cssSelector", cssSelector);
    }
    public WebElement getElementByClass(String className) {
        return getElement("class", className);
    }


    public WebElement getElementById(WebElement parent, String id){
        return getElement(parent, "id", id);
    }
    public WebElement getElementByXpath(WebElement parent, String xpath){
        return getElement(parent, "xpath", xpath);
    }
    public WebElement getElementByName(WebElement parent, String name){
        return getElement(parent, "name", name);
    }
    public WebElement getElementByCssSelector(WebElement parent, String cssSelector){
        return getElement(parent, "cssSelector", cssSelector);
    }
    public WebElement getElementByClass(WebElement parent, String className) {
        return getElement(parent, "class", className);
    }

    public List<String> getText(List<WebElement> elements){
        List<String> texts =  new ArrayList<>();
        for(WebElement element : elements){
            texts.add(element.getText());
        }
        return texts;
    }

    private List<WebElement> getElements(String locatorTye, String locator) {
        switch (locatorTye) {
            case "id":
                return driver.findElements(By.id(locator));
            case "cssSelector":
                return driver.findElements(By.cssSelector(locator));
            case "xpath":
                return driver.findElements(By.xpath(locator));
            case "name":
                return driver.findElements(By.name(locator));
            case "class":
                return driver.findElements(By.className(locator));
            default:
                throw new RuntimeException("No element found with " +
                        "locator type"+locatorTye+ "and locator value "+locator);

        }
    }
    private WebElement getElement(WebElement parent , String locatorTye, String locator) {
        switch (locatorTye) {
            case "id":
                return parent.findElement(By.id(locator));
            case "cssSelector":
                return parent.findElement(By.cssSelector(locator));
            case "xpath":
                return parent.findElement(By.xpath(locator));
            case "name":
                return parent.findElement(By.name(locator));
            case "class":
                return parent.findElement(By.className(locator));
            default:
                throw new RuntimeException("No element found with " +
                        "locator type"+locatorTye+ "and locator value "+locator);

        }
    }
    private List<WebElement> getElements(WebElement parent, String locatorTye, String locator) {
        switch (locatorTye) {
            case "id":
                return parent.findElements(By.id(locator));
            case "cssSelector":
                return parent.findElements(By.cssSelector(locator));
            case "xpath":
                return parent.findElements(By.xpath(locator));
            case "name":
                return parent.findElements(By.name(locator));
            case "class":
                return parent.findElements(By.className(locator));
            default:
                throw new RuntimeException("No element found with " +
                        "locator type"+locatorTye+ "and locator value "+locator);

        }
    }
    private WebElement getElement(String locatorTye, String locator) {
        switch (locatorTye) {
            case "id":
                return driver.findElement(By.id(locator));
            case "cssSelector":
                return driver.findElement(By.cssSelector(locator));
            case "xpath":
                return driver.findElement(By.xpath(locator));
            case "name":
                return driver.findElement(By.name(locator));
            case "class":
                return driver.findElement(By.className(locator));
            default:
                throw new RuntimeException("No element found with " +
                        "locator type"+locatorTye+ "and locator value "+locator);

        }
    }
    public void quitDriver(){
        driver.quit();
    }

    public WebDriver getDriver(){
        return this.driver;
    }
}
