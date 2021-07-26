package BlocageRapportEtCclTechnique;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RapportEtCclTechnique {
	public static WebDriver driver;
	public static String baseUrl;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// connection to salesforce
		System.setProperty("webdriver.chrome.driver","D:\\TestSelenium\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		ResourceBundle bundle = ResourceBundle.getBundle("BlocageRapportEtCclTechnique.BlocageRapport");		
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
		
		TestRapportEtCclTechnique crsp= new TestRapportEtCclTechnique();
		String numDossier =bundle.getString("numéroDossier");
		String rapport=bundle.getString("rapport");
		//crsp.blocageDocCas1(driver, numDossier,rapport);
		//crsp.blocageDocCas2(driver, numDossier, rapport);
		crsp.blocageDocCas3(driver, numDossier, rapport);
	}

}
