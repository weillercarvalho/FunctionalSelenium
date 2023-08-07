package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestingDSL {
    private final WebDriver driver;

    public TestingDSL(WebDriver driver) {
        this.driver = driver;
    }

    public void writeText(String element, String text) {
        driver.findElement(By.id(element)).sendKeys(text);
    }
    public String getText(String element) {
        return driver.findElement(By.id(element)).getAttribute("value");
    }
    public void clickBox(String element, int i) {
        driver.findElement(By.id(element + i)).click();
    }
    public boolean radioBox(String element, int i) {
        return driver.findElement(By.id(element + i)).isSelected();
    }
    public void selectCombo(String string, int num) {
        WebElement element = driver.findElement(By.id(string));
        Select comboBox = new Select(element);
        comboBox.selectByIndex(num);
    }
    public String getOptionCombo(String string) {
        WebElement element = driver.findElement(By.id(string));
        Select comboBox = new Select(element);
        return comboBox.getFirstSelectedOption().getText();
    }
 }
