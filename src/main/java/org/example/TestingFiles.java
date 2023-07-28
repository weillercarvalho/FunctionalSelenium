package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestingFiles {
    @Test
    public void test() {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
//        driver.manage().window().setSize(new Dimension(1200,765));
//        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}