package test1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;

public class IlkClass {

    @Test
    public void test01() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://www.network.com.tr/");
        //cookies cikarsa kabul et butonuna basin
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
        // webelement'i locator yöntemi Unique olmak zorundda
        //1.ci yöntem id ile bulma
        //WebElement aramaKutusu=driver.findElement(By.id("search"));
        //2.ci yöntem name ile bulma
        //WebElement aramaKutusu2=driver.findElement(By.name("searchKey"));
        //3.cü yöntem class ile bulma genelde Unique olmaz
        //WebElement aramaKutusu3=driver.findElement(By.className("o-header__searchInput--input form-control header__searchInput header__searchAreaInput"));
        // sadece linkler için geçerili yöntem
        //WebElement aramaKutusu3=driver.findElement(By.linkText("Textin tamamı"));
        // en güçlü locator'dir ve tüm webElementleri unique olarak belirleyebilir.
        //tagName[@attributelismi='attributeValue']
        //input[@name='email']
        WebElement aramaKutusu=driver.findElement(By.id("search"));
        aramaKutusu.sendKeys("ceket");
        aramaKutusu.submit();
        WebElement sonucYaziElementi= driver.findElement(By.xpath("//div[@class='resultInfo']"));
        System.out.println(sonucYaziElementi.getText());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);",element);
        js.executeScript("window.scrollBy(0,17500)");
        driver.findElement(By.xpath("//button[@class='button -secondary -sm relative']")).click();
        WebElement hover=driver.findElement(By.xpath("//div[@data-product-id='112624']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(hover).perform();

        WebElement beden=driver.findElement(By.xpath("//label[@extcode='1073542007']"));
        actions.moveToElement(beden).perform();
        beden.click();
        String bedenkontrol=beden.getText();
        System.out.println(bedenkontrol);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement sepet =driver.findElement(By.xpath("//a[@class='button -primary header__basket--checkout header__basketModal -checkout']"));
        WebElement fiyat=driver.findElement(By.xpath("//span[@class='a-product__price -salePrice']"));
        String fiyat_kontrol=fiyat.getText();
        System.out.println(fiyat_kontrol);
        sepet.click();

        //KONTROLLER
        WebElement sepet_indirimlifiyat=driver.findElement(By.xpath("//span[@class='cartItem__price -sale']"));
        String gelenindirimlifiyat=sepet_indirimlifiyat.getText();
        Long indirimli_fiyat=Long.parseLong(gelenindirimlifiyat);
        WebElement sepet_eskifiyat=driver.findElement(By.xpath("//span[@class='cartItem__price -old -labelPrice']"));
        String geleneskifiyat=sepet_eskifiyat.getText();
        Long eskifiyat=Long.parseLong(geleneskifiyat);
        WebElement sepet_beden=driver.findElement(By.xpath("//span[@class='cartItem__attrValue']"));
        String beden_k=sepet_beden.getText();

        Assert.assertTrue(bedenkontrol.contains(beden_k)&& fiyat_kontrol.contains(fiyat_kontrol));
        Assert.assertTrue(eskifiyat>indirimli_fiyat);



    }
}
