package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;


public class TestingFiles {
    private WebDriver driver;
    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
    }
    @After
    public void dropDown() {
        driver.quit();
    }
    @Test
    public void testTextField() {
        driver.findElement(By.name("elementosForm:nome")).sendKeys("Testing");
        Assert.assertEquals("Testing",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }
    @Test
    public void testTextArea() {
        driver.findElement(By.name("elementosForm:sugestoes")).sendKeys("Testing");
        Assert.assertEquals("Testing", driver.findElement(By.name("elementosForm:sugestoes")).getAttribute("value"));
    }
    @Test
    public void testRadioButton() {
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("elementosForm:sexo:" + i)).click();
            Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:" + i)).isSelected());
        }
    }
    @Test
    public void testCheckbox() {
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).click();
            Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).isSelected());
        }
    }
    @Test
    public void testComboBox() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select comboBox = new Select(element);
        comboBox.selectByIndex(7);
        Assert.assertEquals("Doutorado", comboBox.getFirstSelectedOption().getText());
    }
    @Test
    public void testListComboBox() {
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
    }
    @Test
    public void testListMultCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select comboBox = new Select(element);
        List<WebElement> options = comboBox.getOptions();
        comboBox.selectByVisibleText("Karate");
        List<WebElement> allOptions = comboBox.getAllSelectedOptions();
        boolean val = true;
        for (WebElement option : options) {
            if (option.getText().equals("Karate")) {
                val = false;
                break;
            }
        }
        Assert.assertEquals(1, allOptions.size());
        Assert.assertFalse(val);
    }
    @Test
    public void testClickMessage() {
        WebElement element = driver.findElement(By.id("buttonSimple"));
        element.click();
        Assert.assertEquals("Obrigado!", element.getAttribute("value"));
    }

    @Test
    public void testLink() {
        WebElement element = driver.findElement(By.linkText("Voltar"));
        element.click();
        String element1 = driver.findElement(By.id("resultado")).getText();
        Assert.assertEquals("Voltou!", element1);
    }

    @Test
    public void searchText() {
        List<WebElement> element = driver.findElements(By.tagName("a"));
        boolean val = false;
        for (WebElement links: element) {
            if (links.getText().equals("Curso de Testes Funcionais automatizados com Selenium Webdriver")) {
                val = true;
                break;
            }
        }
        Assert.assertTrue(val);
    }
    @Test
    public void testAlert() {
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String value = alert.getText();
        Assert.assertEquals("Alert Simples", value);
        alert.accept();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(value);
    }
    @Test
    public void confirmRejectAlert() {
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();
        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
    }
    @Test
    public void testPrompt() {
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        String value = "1";
        alert.sendKeys(value);
        alert.accept();
        Assert.assertEquals("Era " + value + "?", alert.getText());
        int num = 0;
        while (num != 2) {
            alert.accept();
            num++;
        }
    }
    @Test
    public void testSignUp() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Weiller");
        Assert.assertEquals("Weiller", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Carvalho");
        Assert.assertEquals("Carvalho", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertEquals("M", driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value"));
        int num = 0;
        while (num != 2) {
            driver.findElement(By.id("elementosForm:sexo:" + num)).click();
            if (num == 0) {
                Assert.assertEquals("M", driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value"));
            }
            else {
                Assert.assertEquals("F", driver.findElement(By.id("elementosForm:sexo:1")).getAttribute("value"));
            }
            num++;
        }
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).click();
            Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:" + i)).isSelected());
        }
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Assert.assertEquals("Nome: " + driver.findElement(By.id("elementosForm:nome")).getAttribute("value"), driver.findElement(By.id("descNome")).getText());
        Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Carvalho"));
    }
    @Test
    public void testFrame() {
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String value = alert.getText();
        alert.accept();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(value);
        Assert.assertEquals(value, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }
    @Test
    public void testWindow() {
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Test");
        driver.close();
        driver.switchTo().window("");
    }
    @Test
    public void testUnknownWindow() {
        driver.findElement(By.id("buttonPopUpHard")).click();
        Set<String> values = driver.getWindowHandles();
        for (String value : values) {
            if (!value.equals(driver.getWindowHandle())) {
                driver.switchTo().window(value);
                break;
            }
        }
        driver.findElement(By.tagName("textarea")).sendKeys("Test");
        for (String value : values) {
            if (!value.equals(driver.getWindowHandle())) {
                driver.switchTo().window(value);
                break;
            }
        }
        driver.findElement(By.tagName("textarea")).sendKeys("Finished test");
    }
}