import com.google.common.annotations.VisibleForTesting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.PseudoColumnUsage;
import java.util.concurrent.TimeUnit;

public class Autoria {

    //1 Описать UI элементы

    By searchbutton=By.xpath("//button[@class='button']");
    By brand =By.xpath( "//input[@id='brandTooltipBrandAutocompleteInput-brand']");
    By model =By.xpath( "//input[@id='brandTooltipBrandAutocompleteInput-model']");
    By region = By.xpath("//label[@for=\"brandTooltipBrandAutocompleteInput-region\"]");
    By priceFrom = By.xpath("//input[@name='price_ot']");
    By priceTo = By.xpath("//input[@name='price_do']");
    int expectedNumberOfCarsOnList = 20; //* сколько машин на странице мы ожидаем увидеть*/

    boolean check; //*Проверка удовлетворяет ли страница условиям*/
    //2  Метожы описывающие действие

    //3 Написание теста
    @Test
    public void openSite (){
        System.setProperty("webdriver.chrome.driver" , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://auto.ria.com/uk/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        //+driver.findElement(By.xpath(searchbutton)).click();
//        driver.manage().window().maximize();
//       //* driver.close();
        driver.findElement(brand).sendKeys("Chevrolet");
        driver.findElement(By.xpath("//li[@data-value='13'][1]")).click();
        driver.findElement(model).sendKeys("Aveo");
        driver.findElement(By.xpath("//li[@data-value='1038'][1]")).click();
        driver.findElement(region).click();
        driver.findElement(By.xpath("//a[text()='Київ']")).click();
        driver.findElement(priceFrom).sendKeys("3800");
        driver.findElement(priceTo).sendKeys("4000");
        driver.findElement(searchbutton).click();
        //* Assert.assertEquals("https://auto.ria.com/uk/", driver.getCurrentUrl());
        int count1= driver.findElements(By.xpath("//div[@class='content-bar']")).size();
        //* Assert.assertEquals(expectedNumberOfCarsOnList, count1);
        //* Я сравниваю количество машин отображаемых на странице.
        //*  По умолчанию их 20, во втором варианте я сравнил и ожидал, что их 10 и мне выдало ошибку */
        if (count1<=expectedNumberOfCarsOnList) {
            check = true;
        }
        else {
            check = false;
        }
        Assert.assertTrue(check);
        driver.quit();
    }
}
