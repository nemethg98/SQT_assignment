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


class ProfilePage extends PageBase
{
    private By settingsBy = By.xpath("//div[@class='second-level-menu']//a[contains(@href, 'settings')]");
    private By socialBy = By.xpath("//div[@class='second-level-menu']//a[contains(@href, 'social')]");
    private By firstNameBy = By.xpath("//div[@class='userbox']//form[@id='settings-form']//input[@name='firstName']");
    private By lastNameBy = By.xpath("//div[@class='userbox']//form[@id='settings-form']//input[@name='lastName']");
    private By saveButtonBy = By.xpath("//div[@class='userbox']//form[@id='settings-form']//input[@value='Save changes']");
    
    public ProfilePage(WebDriver driver)
    {
        super(driver);
    }
    
    public void setSocialSettings(String firstName, String lastName)
    {
		goToSocial();
        
        WebElement firstNameField = waitAndReturnElement(firstNameBy);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        
        WebElement lastNameField = waitAndReturnElement(lastNameBy);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        
        WebElement saveButton = waitAndReturnElement(saveButtonBy);
        saveButton.click();
	}
	
	public String getFirstName()
	{
		return waitAndReturnElement(firstNameBy).getAttribute("value");
	}
	
	public String getLastName()
	{
		return waitAndReturnElement(lastNameBy).getAttribute("value");
	}
	
	public void goToSocial()
	{
		WebElement settings = waitAndReturnElement(settingsBy);
        settings.click();
		WebElement social = waitAndReturnElement(socialBy);
        social.click();
	}
}
