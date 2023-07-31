package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestingFiles {
    @Test
    public void testTextField() {

        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        driver.findElement(By.name("elementosForm:nome")).sendKeys("Testing");
        Assert.assertEquals("Testing",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }
    @Test
    public void testTextArea() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        driver.findElement(By.name("elementosForm:sugestoes")).sendKeys("Testing");
        Assert.assertEquals("Testing", driver.findElement(By.name("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }
    @Test
    public void testRadioButton() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("elementosForm:sexo:" + i)).click();
            Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:" + i)).isSelected());
        }
        driver.quit();
    }
    @Test
    public void testCheckbox() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).click();
            Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).isSelected());
        }
        driver.quit();
    }
}