package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners
public class SwagLabs {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(SwagLabs.class);

    @BeforeTest(description = "Setting up the broswer and navigating to the site")
    public void loginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // driver.quit();
    }

    @Test(description = "Logging in by entering valid username and password", priority = 1)
    public void performLogin() {
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButon = driver.findElement(By.xpath("//input[@id='login-button']"));
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        submitButon.click();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "Login was succesful");
        // logger.info("User Successfully logged In");
        // System.out.println("Successfully logged in");
    }

    @Test(description = "Adding a product to cart", dependsOnMethods = "performLogin", priority = 2)
    public void addToCart() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @AfterTest(description = "Closes the browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
