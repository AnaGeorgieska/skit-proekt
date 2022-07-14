package mk.ukim.finki.bazi_proekt.avio_kompanija.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PatnikPage extends AbstractPage{

    private static WebElement name;
    private static WebElement surename;
    private static WebElement passportNumber;
    private static WebElement country;
    private static WebElement seat;
    private static WebElement submit;

    public PatnikPage(WebDriver driver) {
        super(driver);
    }

    public static PatnikPage patnikPage(WebDriver driver) {
        get(driver, "/patnik");
        name.sendKeys("Ana");
        surename.sendKeys("Georgieska");
        passportNumber.sendKeys("MK2244");
        country.sendKeys("Macedonia");
        seat.sendKeys("1");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        System.out.println(driver.getCurrentUrl() + "->/patnik");
        return PageFactory.initElements(driver, PatnikPage.class);
    }

}
