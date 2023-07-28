package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestingFiles {
    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
    }
}