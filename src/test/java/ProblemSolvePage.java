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

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

class ProblemSolvePage extends PageBase
{
	private By sortBy = By.xpath("//div[@class='datatable']//table[@class='problems']//a[contains(@href, 'BY_SOLVED_DESC')]");
	private By searchBy = By.xpath("//div[@class='datatable']//div[contains(text(), 'Problems')]//img");
	private By searchFieldBy = By.xpath("//div[@class='datatable']//div[contains(text(), 'Problems')]//input");
	private By problemIdBy = By.xpath("//div[@class='datatable']//table[@class='problems']//a[contains(text(), 'Watermelon')]");
	private By problemNameBy = By.xpath("//div[@class='problem-statement']//div[@class='title']");
	
	public ProblemSolvePage(WebDriver driver)
    {
        super(driver);
    }
    
    public void openWatermelonProblem()
    {
		WebElement sort = waitAndReturnElement(sortBy);
        sort.click();
        
		WebElement search = waitAndReturnElement(searchBy);
        search.click();
        
        WebElement searchField = waitAndReturnElement(searchFieldBy);
        searchField.clear();
        searchField.sendKeys("Watermelon");
        
        WebElement problemLink = waitAndReturnElement(problemIdBy);
        problemLink.click();
	}
	
	public String getProblemName()
	{
		return waitAndReturnElement(problemNameBy).getText();
	}
	
	public void uploadFile()
	{
		prepareUploadedFile();
	}
	
	private void prepareUploadedFile()
	{
		String code = "";
		String line = "";
		try
		{
			String filenamew = "resource/program.cpp";
			FileWriter fw = new FileWriter(filenamew, false);
			
			line = "//" + java.time.LocalTime.now().toString();
			
			fw.write(line);
			
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("Error occured when file was uploaded");
		}
	}
}
