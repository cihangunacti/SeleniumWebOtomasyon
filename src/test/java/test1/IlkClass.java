package test1;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
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
import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IlkClass {

    @Test
    public void test01() throws IOException, CsvException,InterruptedException{

        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(170));
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
        Thread.sleep(2000);
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
        String gelenindirimli_fiyat=gelenindirimlifiyat.substring(0,5);
        String yeni_indirimlifiyat= gelenindirimli_fiyat.replace(".","");
       //System.out.println(yeni_indirimlifiyat);
        Long indirimli_fiyat=Long.parseLong(yeni_indirimlifiyat);
        WebElement sepet_eskifiyat=driver.findElement(By.xpath("//span[@class='cartItem__price -old -labelPrice']"));
        String geleneskifiyat=sepet_eskifiyat.getText();
        String geleneskifiyat_=geleneskifiyat.substring(0,5);
        String yeni_eskifiyat=geleneskifiyat_.replace(".","");
        Long eskifiyat=Long.parseLong(yeni_eskifiyat);
        //System.out.println(eskifiyat);
        WebElement sepet_beden=driver.findElement(By.xpath("//span[@class='cartItem__attrValue']"));
        String beden_k=sepet_beden.getText();

        Assert.assertTrue(bedenkontrol.contains(beden_k)&& fiyat_kontrol.contains(fiyat_kontrol));
        Assert.assertTrue(eskifiyat>indirimli_fiyat);


        //DEVAM ET
        WebElement devam_et =driver.findElement(By.xpath("//button[@class='continueButton n-button large block text-center -primary']"));
        devam_et.click();

        //Giriş yap butonunun geldiği kontrol edilir.
        WebElement girisyap=driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement eposta=driver.findElement(By.xpath("//input[@id='n-input-email']"));
        WebElement sifre=driver.findElement(By.xpath("//input[@type='password']"));
        String path="src/test/java/test1/csvtest.csv";
        Reader reader =new FileReader(path);
        CSVReader csvReader =new CSVReader(reader);
        //List<String[]> data = csvReader.readAll();
        String [] csvData;
        while ((csvData=csvReader.readNext()) !=null){
            String mail_adresi = csvData[0];
            String sifre_=csvData[1];

            eposta.sendKeys(mail_adresi);
            sifre.sendKeys(sifre_);

        }
        Assert.assertTrue(girisyap.isDisplayed());
        WebElement logo=driver.findElement(By.xpath("//a[@class='headerCheckout__logo']"));
        logo.click();
        WebElement anasayfa_sepet=driver.findElement(By.xpath("//button[@class='header__basketTrigger js-basket-trigger -desktop']"));
        anasayfa_sepet.click();
        Integer uruncikarma=0;
        WebElement uruncikart=driver.findElement(By.xpath("//div[@class='header__basketProductBtn header__basketModal -remove']"));
        uruncikart.click();
        WebElement cikart=driver.findElement(By.xpath("//button[@class='btn -black o-removeCartModal__button']"));
        cikart.click();
        uruncikarma++;
        Assert.assertTrue(uruncikarma==1);



    }
}
