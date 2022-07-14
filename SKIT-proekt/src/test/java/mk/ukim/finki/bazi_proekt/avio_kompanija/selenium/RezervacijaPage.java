package mk.ukim.finki.bazi_proekt.avio_kompanija.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RezervacijaPage extends AbstractPage{

    private WebElement submit;

    public RezervacijaPage(WebDriver driver) {
        super(driver);
    }

    public static RezervacijaPage rez(WebDriver driver) {
        get(driver, "/rezervacija");
        System.out.println(driver.getCurrentUrl() + "->/rezervacija");
        return PageFactory.initElements(driver, RezervacijaPage.class);
    }

    public HomePage rezervacijaSubmit(WebDriver driver) {
        driver.findElement(By.cssSelector(".btn-success")).click();
        System.out.println(driver.getCurrentUrl() + "->/");
        return PageFactory.initElements(driver, HomePage.class);
    }
}
