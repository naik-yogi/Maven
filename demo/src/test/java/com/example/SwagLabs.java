package com.example;

import org.checkerframework.checker.units.qual.t;
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

//@Listeners(MyListenerClass.class)
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

    @Test(description = "Clicking on cart button", priority = 3)
    public void navigateToCart() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

    }

    @Test(description = "Clicing on check", priority = 4)
    public void checkOut() {
        driver.findElement(By.id("checkout")).click();

    }

    @Test(description = "Entering checkout infos", priority = 5)
    public void yourInfo() {
        driver.findElement(By.id("first-name")).sendKeys("Yogesh");
        driver.findElement(By.id("last-name")).sendKeys("Naik");
        driver.findElement(By.id("postal-code")).sendKeys("581324");
        driver.findElement(By.id("continue")).click();

    }

    @Test(description = "Fetcging ordr dtails", priority = 6)
    public void orderDetails() {
        WebElement totalValue = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
        System.out.println("Tota order value is: " + totalValue.getText());
        driver.findElement(By.id("finish")).click();
    }

    @AfterTest(description = "Closes the browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
