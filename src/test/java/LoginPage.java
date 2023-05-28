import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class LoginPage extends PageBase
{
    private By logoutButtonBy = By.xpath("//div[@id='header']//div[@class='lang-chooser']//a[contains(@href, 'logout')]");
    private By profileButtonBy = By.xpath("//div[@id='header']//div[@class='lang-chooser']//a[contains(@href, 'profile')]");
    private By problemSetBy = By.xpath("//div[@class='menu-list-container']//a[contains(@href, 'problemset')]");
    
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    
    public MainPage logout(MainPage mainPage)
    {
        WebElement logoutButton = waitAndReturnElement(logoutButtonBy);
        logoutButton.click();
        
        return mainPage;
    }
    
    public String getUsername()
    {
        WebElement profileButton = waitAndReturnElement(profileButtonBy);
        return profileButton.getText();
    }
    
    public ProfilePage openProfile()
    {
        WebElement profileButton = waitAndReturnElement(profileButtonBy);
        profileButton.click();
        
        return new ProfilePage(this.driver);
    }
    
    public ProblemSolvePage openProblemSet()
    {
        WebElement problemSetButton = waitAndReturnElement(problemSetBy);
        problemSetButton.click();
        
        return new ProblemSolvePage(driver);
    }
}
