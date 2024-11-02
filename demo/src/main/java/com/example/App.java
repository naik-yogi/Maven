package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/5.9.2");
        driver.quit();
    }
}
