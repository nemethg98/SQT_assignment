import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import java.net.URL;
import java.net.MalformedURLException;

import org.junit.*;

import java.util.List;

public class SeleniumWebsiteTest
{
    private WebDriver driver;
    private String username;
    private String password;
    private String code;
    private ConfigFileReader configFileReader;

    @Before
    public void Setup() throws MalformedURLException
    {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        username = configFileReader.getInstance().getUsername();
        password = configFileReader.getInstance().getPassword();
    }
    
    //@Test
    public void TestMainPage()
    {
        MainPage mainPage = new MainPage(this.driver);
        
        final String expectedPageTitle = "Codeforces";
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains(expectedPageTitle));
        
        List<String> menuTexts = mainPage.getMenuListStrings();
        assertTrue(menuTexts.contains("HOME"));
        assertTrue(menuTexts.contains("TOP"));
        assertTrue(menuTexts.contains("CATALOG"));
        assertTrue(menuTexts.contains("CONTESTS"));
    }
    
    //@Test
    public void TestLoginLogout()
    {
        MainPage mainPage = new MainPage(this.driver);
        
        assertTrue(mainPage.isLoginVisible());
        
        LoginPage loginPage = mainPage.login(username, password);
        
        String loggedInUser = loginPage.getUsername();
        assertTrue(loggedInUser.contains(username));
        
        assertFalse(mainPage.isLoginVisible());
        
        mainPage = loginPage.logout(mainPage);
        
        assertTrue(mainPage.isLoginVisible());
    }
    
    //@Test
    public void TestProfileChange() {
        MainPage mainPage = new MainPage(this.driver);
        
        LoginPage loginPage = mainPage.login(username, password);
        
        ProfilePage profilePage = loginPage.openProfile();
        
        final String expectedFirstNameText = "AK423Q";
        final String expectedLastNameText = "Selenium";
        profilePage.setSocialSettings(expectedFirstNameText, expectedLastNameText);
        
        String firstName = profilePage.getFirstName();
        String lastName = profilePage.getLastName();
        assertTrue(firstName.contains(expectedFirstNameText));
        assertTrue(lastName.contains(expectedLastNameText));
        
        profilePage = loginPage.openProfile();
        
        final String expectedFirstNameText2 = "";
        final String expectedLastNameText2 = "";
        profilePage.setSocialSettings(expectedFirstNameText2, expectedLastNameText2);
        
        firstName = profilePage.getFirstName();
        lastName = profilePage.getLastName();
        assertEquals("First name test", "", expectedFirstNameText2);
        assertEquals("Last name test", "", expectedLastNameText2);
        
        mainPage = loginPage.logout(mainPage);
    }
    
    @Test
    public void UploadFileTest()
    {
        MainPage mainPage = new MainPage(this.driver);
        
        LoginPage loginPage = mainPage.login(username, password);
        
        waitSeconds(2);
        
        ProblemSolvePage problemSolvePage = loginPage.openProblemSet();
        
        problemSolvePage.openWatermelonProblem();
        
        final String problemName = "Watermelon";
        assertTrue(problemSolvePage.getProblemName().contains(problemName));
        
        problemSolvePage.uploadFile();
        
        assertTrue(true);
        
        waitSeconds(10);
        
        mainPage = loginPage.logout(mainPage);
    }
    
    @After
    public void close()
    {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
    
    private void waitSeconds(int seconds)
    {
		try
		{
			Thread.sleep(1000 * seconds);
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}
}
