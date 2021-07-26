package TestBdoc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateBdoc {
	public static WebDriver driver;
	public static String baseUrl;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// connection to salesforce
				System.setProperty("webdriver.chrome.driver","D:\\TestSelenium\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
				ResourceBundle bundle = ResourceBundle.getBundle("TestBdoc.Bdoc");		
				String environnement = bundle.getString("environnement");
				driver.get("https://bcaexpertise"+environnement+".my.salesforce.com/");
				driver.manage().window().maximize();
				driver.findElement(By.xpath("//button[@class='button mb24 secondary wide']")).click();
				String connexion=bundle.getString("connexion");
									
				if(connexion.equals("maison")) {				
					//house connection
					String username =bundle.getString("username");
					String password =bundle.getString("password");
					driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(username);
					driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
					driver.findElement(By.xpath("//span[@id='submitButton']")).click();
				}
									
				// Set Base URL then use it for the access
				URL currentUrl = new URL(driver.getCurrentUrl());				
				baseUrl = currentUrl.getProtocol() + "://" + currentUrl.getHost();
				
				//Create Bdoc				
				String numDossier =bundle.getString("num�roDossier");
				TestCreateBdoc bdoc=new TestCreateBdoc();
				bdoc.CreateBdoc(driver, numDossier);
				
						

	}

}