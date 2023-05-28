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
    
    private By addFileBy = By.xpath("//div[@id='sidebar']//div[contains(text(), 'Submit')]/ancestor::div[position() = 1]//form[@class='submitForm']//input[@name='sourceFile']");
    
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
        
        WebElement addFile = waitAndReturnElement(addFileBy);
        
        //String filePath = "/home/gaben/Dokumentumok/sqt/selenium-docker-sandbox/tests/resource/program.cpp";
        String filePath = System.getProperty("user.dir") + "/resource/program.cpp";
        //~ JavascriptExecutor jsx = (JavascriptExecutor) driver;
        //~ jsx.executeScript("document.getElementsByName('sourceFile').value='" + filePath + "';");
        
        addFile.sendKeys(filePath);
    }
    
    private void prepareUploadedFile()
    {
        String code = "";
        String line = "";
        BufferedReader reader;
        try
        {
            String filenamew = "resource/program.cpp";
            FileWriter fw = new FileWriter(filenamew, false);
            
            String filenamer = "resource/programbase.cpp";
            FileReader fr = new FileReader(filenamer);
            reader = new BufferedReader(fr);
            
            line = reader.readLine();
            
            while (line != null)
            {
                fw.write(line + "\n");
                line = reader.readLine();
            }
            
            line = "//" + java.time.LocalDateTime.now().toString();
            
            fw.write(line);
            
            fr.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error occured when file was uploaded");
        }
    }
}
