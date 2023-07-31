package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


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
    @Test
    public void testComboBox() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select comboBox = new Select(element);
        comboBox.selectByIndex(7);
        Assert.assertEquals("Doutorado", comboBox.getFirstSelectedOption().getText());
        driver.quit();
    }
    @Test
    public void testListComboBox() {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select comboBox = new Select(element);
        List<WebElement> options = comboBox.getOptions();
        boolean val = false;
        for (WebElement option: options) {
            if (option.getText().equals("Superior")) {
                val = true;
                break;
            }
        }
        Assert.assertTrue(val);
        Assert.assertEquals(8, options.size());
        driver.quit();
    }
}