package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class TestingDSL {
    private final WebDriver driver;

    public TestingDSL(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement findById(String element) {
        return driver.findElement(By.id(element));
    }
    public WebElement findByLinkText(String element) {
        return driver.findElement(By.linkText(element));
    }
    public List<WebElement> findByTagName(String element) {
        return driver.findElements(By.tagName(element));
    }
    public void writeText(String element, String text) {
        driver.findElement(By.id(element)).sendKeys(text);
    }
    public void writeTextTagname(String element, String text) {
        driver.findElement(By.tagName(element)).sendKeys(text);
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
    public Alert switchAlert() {
        return driver.switchTo().alert();
    }
    public Object switchFrame(String element) { return driver.switchTo().frame(element);}
    public Object switchWindow(String element) { return driver.switchTo().window(element);}
    public void close() {driver.close();}
    public void switchBack() {
        driver.switchTo().defaultContent();
    }
    public String windowHandle() {return driver.getWindowHandle();}
    public Set<String> windowHandleAll() {return driver.getWindowHandles();}
    public String getOptionCombo(String string) {
        WebElement element = driver.findElement(By.id(string));
        Select comboBox = new Select(element);
        return comboBox.getFirstSelectedOption().getText();
    }
 }
