package test1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverGetMethodlari {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        //1-driver.get("url") --> yazdiğimiz url'e gider
        driver.get("https://www.network.com.tr/");

        //2.driver.getTitle() --> İçinde olduğu sayfanın baslığını dömdürür.
        System.out.println("sayfa title:"+driver.getTitle());

        //3- driver driver.getCurrentUrl() --> içinde olduğu sayfanın URL'ini döndürür.
        System.out.println(driver.getCurrentUrl());

        //4-driver.getPageSource(); -->icinde olduğu sayfanın kaynak kodlarını döndürür.
        System.out.println("====================================================================");
        System.out.println(driver.getPageSource());//arka planda calisan sayfa kodlarını yazdırır.
        System.out.println("====================================================================");

        //5-driver.getWindowHandle() --> icinde olduğu sayfanin UNIQUE has kodunu döndürür.
        System.out.println(driver.getWindowHandle());

        //6-driver.getWindowsHandles() --> driver çalısırken acilan tum sayfaların UNIQUE has kodunu döndürür.



    }
}
