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
    private TestingDSL dsl;
    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/components.html");
        dsl = new TestingDSL(driver);
    }
    @After
    public void dropDown() {
        driver.quit();
    }
    @Test
    public void testTextField() {
        dsl.writeText("elementosForm:nome","Testing");
        Assert.assertEquals("Testing",dsl.getText("elementosForm:nome"));
    }
    @Test
    public void testTextArea() {
        dsl.writeText("elementosForm:sugestoes","Testing");
        Assert.assertEquals("Testing", dsl.getText("elementosForm:sugestoes"));
    }
    @Test
    public void testRadioButton() {
        for (int i = 0; i < 2; i++) {
            dsl.clickBox("elementosForm:sexo:", i);
            Assert.assertTrue(dsl.radioBox("elementosForm:sexo:", i));
        }
    }
    @Test
    public void testCheckbox() {
        for (int i = 0; i < 4; i++) {
            dsl.clickBox("elementosForm:comidaFavorita:", i);
            Assert.assertTrue(dsl.radioBox("elementosForm:comidaFavorita:", i));
        }
    }
    @Test
    public void testComboBox() {
        dsl.selectCombo("elementosForm:escolaridade", 7);
        Assert.assertEquals("Doutorado", dsl.getOptionCombo("elementosForm:escolaridade"));
    }
    @Test
    public void testListComboBox() {
        WebElement element = dsl.findById("elementosForm:escolaridade");
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
        WebElement element = dsl.findById("elementosForm:esportes");
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
        WebElement element = dsl.findById("buttonSimple");
        element.click();
        Assert.assertEquals("Obrigado!", element.getAttribute("value"));
    }

    @Test
    public void testLink() {
        WebElement element = dsl.findByLinkText("Voltar");
        element.click();
        String element1 = dsl.findById("resultado").getText();
        Assert.assertEquals("Voltou!", element1);
    }

    @Test
    public void searchText() {
        List<WebElement> element = dsl.findByTagName("a");
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
        dsl.findById("alert").click();
        Alert alert = dsl.switchAlert();
        String value = alert.getText();
        Assert.assertEquals("Alert Simples", value);
        alert.accept();
        dsl.switchBack();
        dsl.writeText("elementosForm:nome", value);
    }
    @Test
    public void confirmRejectAlert() {
        dsl.findById("confirm").click();
        Alert alert = dsl.switchAlert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();
        dsl.findById("confirm").click();
        alert = dsl.switchAlert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado", alert.getText());
        alert.accept();
        dsl.switchBack();
    }
    @Test
    public void testPrompt() {
        dsl.findById("prompt").click();
        Alert alert = dsl.switchAlert();
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
        dsl.writeText("elementosForm:nome", "Weiller");
        Assert.assertEquals("Weiller", dsl.getText("elementosForm:nome"));
        dsl.writeText("elementosForm:sobrenome", "Carvalho");
        Assert.assertEquals("Carvalho", dsl.getText("elementosForm:sobrenome"));
        dsl.findById("elementosForm:sexo:0").click();
        Assert.assertEquals("M", dsl.getText("elementosForm:sexo:0"));
        int num = 0;
        while (num != 2) {
            dsl.clickBox("elementosForm:sexo:", num);
            if (num == 0) {
                Assert.assertEquals("M", dsl.getText("elementosForm:sexo:0"));
            }
            else {
                Assert.assertEquals("F", dsl.getText("elementosForm:sexo:1"));
            }
            num++;
        }
        for (int i = 0; i < 3; i++) {
            dsl.clickBox("elementosForm:comidaFavorita:", i);
            Assert.assertTrue(dsl.radioBox("elementosForm:comidaFavorita:", i));
        }
        dsl.findById("elementosForm:cadastrar").click();
        Assert.assertEquals("Nome: " + dsl.getText("elementosForm:nome"), dsl.findById("descNome").getText());
        Assert.assertTrue(dsl.findById("descSobrenome").getText().endsWith("Carvalho"));
    }
    @Test
    public void testFrame() {
        dsl.switchFrame("frame1");
        dsl.findById("frameButton").click();
        Alert alert = dsl.switchAlert();
        String value = alert.getText();
        alert.accept();
        dsl.switchBack();
        dsl.writeText("elementosForm:nome", value);
        Assert.assertEquals(value, dsl.getText("elementosForm:nome"));
    }
    @Test
    public void testWindow() {
        dsl.findById("buttonPopUpEasy").click();
        dsl.switchWindow("Popup");
        dsl.writeTextTagname("textarea", "Test");
        dsl.close();
        dsl.switchWindow("");
    }
    @Test
    public void testUnknownWindow() {
        dsl.findById("buttonPopUpHard").click();
        Set<String> values = dsl.windowHandleAll();
        for (String value : values) {
            if (!value.equals(dsl.windowHandle())) {
                dsl.switchWindow(value);
                break;
            }
        }
        dsl.writeTextTagname("textarea", "Test");
        for (String value : values) {
            if (!value.equals(dsl.windowHandle())) {
                dsl.switchWindow(value);
                break;
            }
        }
        dsl.writeTextTagname("textarea", "Finished test");
    }
}