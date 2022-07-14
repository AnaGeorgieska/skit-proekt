package mk.ukim.finki.bazi_proekt.avio_kompanija.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractPage
{
    @FindBy(id = "rezervacija")
    private WebElement rezervacii;
    @FindBy(id = "line")
    private WebElement line;
    @FindBy(id = "from")
    private WebElement from;
    @FindBy(id = "to")
    private WebElement to;

    private WebElement submit;
    private WebElement letOption;
    private WebElement submitLet;


    public HomePage(WebDriver driver) {
        super(driver);
    }
    public static HomePage to(WebDriver driver) {
        get(driver, "");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, HomePage.class);
    }

    public HomePage FromAndTo(WebDriver driver) {
        driver.findElement(By.id("to")).click();
        {
            WebElement dropdown = driver.findElement(By.id("to"));
            dropdown.findElement(By.xpath("//option[. = 'Skopje']")).click();
        }
        driver.findElement(By.id("from")).click();
        {
            WebElement dropdown = driver.findElement(By.id("from"));
            dropdown.findElement(By.xpath("//option[. = 'Ohrid']")).click();
        }
        this.submit.click();
        System.out.println(driver.getCurrentUrl() + "->/dest");
        return PageFactory.initElements(driver, HomePage.class);
    }

    public PatnikPage izbiranjeLet(WebDriver driver) {
        this.letOption.sendKeys("1");
        this.submitLet.click();
        System.out.println(driver.getCurrentUrl() + "->/let");
        return PageFactory.initElements(driver, PatnikPage.class);
    }

}
