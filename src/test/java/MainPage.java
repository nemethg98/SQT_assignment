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

import java.util.List;
import java.util.ArrayList;


class MainPage extends PageBase
{

    private String mainUrl = "https://www.codeforces.com";
    private By usernameFieldBy = By.xpath("//form[@id='enterForm']//input[@id='handleOrEmail']");
    private By passwordFieldBy = By.xpath("//form[@id='enterForm']//input[@id='password']");
    private By loginButtonBy = By.xpath("//form[@id='enterForm']//input[@class='submit']");
    private By lbBy = By.xpath("//div[@id='header']//div[@class='lang-chooser']//a[contains(@href, 'enter')]");
    private By langChooserBy = By.xpath("//div[@id='header']//div[@class='lang-chooser']");
    private By menuListBy = By.xpath("//div[@class='menu-list-container']//li");
        
    public MainPage(WebDriver driver)
    {
        super(driver);
        this.driver.get(mainUrl);
    }
    
    public LoginPage login(String username, String password)
    {
        this.driver.get("https://codeforces.com/enter?back=%2F");
        
        WebElement usernameField = waitAndReturnElement(usernameFieldBy);
        usernameField.sendKeys(username);
        
        WebElement passwordField = waitAndReturnElement(passwordFieldBy);
        passwordField.sendKeys(password);
        
        WebElement loginButton = waitAndReturnElement(loginButtonBy);
        loginButton.click();
        
        return new LoginPage(this.driver);
    }
    
    public List<String> getMenuListStrings()
    {
        List<WebElement> menuListElements = waitAndReturnElements(menuListBy);
        
        List<String> menuListStrings = new ArrayList<String>();
        
        menuListElements.forEach( (elem) -> menuListStrings.add(elem.getText()) );
        
        return menuListStrings;
    }
    
    public boolean isLoginVisible()
    {
        WebElement lb = waitAndReturnElement(langChooserBy);
        return lb.getText().contains("Enter");
    }
}
