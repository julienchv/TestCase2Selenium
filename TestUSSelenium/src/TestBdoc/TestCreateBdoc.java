package TestBdoc;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCreateBdoc {
	public static String caseId;
	
	public void CreateBdoc(WebDriver driver,String dossier) throws InterruptedException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Go on Editique
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Editique']")).click();
		
		//Find 938 document
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='enter-search']")).sendKeys("938");
		Thread.sleep(2000);
		
		//click on the link
		String xpLink938="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2[1]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[1]/slot/c-lwc010_-afficher-liste-modele-editique/article/div[2]/ul/li/article/div/div[1]/h3/a";
		driver.findElement(By.xpath(xpLink938)).click();
		Thread.sleep(1000);
		
		//choose actor
		driver.findElement(By.xpath("//input[@name='Acteur']")).click();
		String xpactor ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2[1]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc014_generer-documents/section/div/div/lightning-combobox/div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[1]";
		driver.findElement(By.xpath(xpactor)).click();
		
		//Generate the document
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Générer']")).click();
		driver.findElement(By.xpath("//body[@class='desktop']")).sendKeys(Keys.CONTROL+""+Keys.TAB);
		Thread.sleep(10000);
		ArrayList<String>tab=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));			
		Thread.sleep(3000);
		
	}
}
