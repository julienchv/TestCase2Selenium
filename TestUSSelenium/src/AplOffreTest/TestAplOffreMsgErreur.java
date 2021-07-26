package AplOffreTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestAplOffreMsgErreur {
	public static String caseId;
	
	public void AplOfrTestNominal(WebDriver driver, String act, String platform,String dossier) throws InterruptedException, MalformedURLException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
						
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = AplOffreMsgErreur.baseUrl + "/lightning/r/" + caseId + "/related/AppelsOffre__r/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it		
		driver.get(linkListMessage);
		
		//Scroll to load all the element
		Thread.sleep(2000);
		js.executeScript("document.body.style.zoom = '75%'");
		WebElement scrollBar= driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]"));	
		for(int i=0;i<=50;i++) {
			scrollBar.sendKeys(Keys.PAGE_DOWN);
		}
		
		List <WebElement> list =driver.findElements(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr"));		
		
		// Get the current date
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		String Date = dtf.format(Calendar.getInstance().getTime());
		
		//AO canvas 
		String elements="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";			
		String xpdate="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";
		int aoabandonnej=0;	//count the number of AO leave today
		int aoencours=0; //count the number of AO in progress
		int aotraitej=0; //count the number of AO treated today
		for(int i=1;i<=list.size();i++) {			
			String status =driver.findElement(By.xpath(elements+i+"]/td[3]/span/span")).getText();
			String dateAO =driver.findElement(By.xpath(xpdate+i+"]/th/span/a")).getAttribute("title").split(" ")[2];
			switch(status) {
			case "En attente","Publié":
				 aoencours+=1;
				break;
			
			case "Traité","Annulé","Clôturé":
				if(dateAO.equals(Date)) {
					aotraitej+=1;
				}
				break;
			case "Abandonné":
				if(dateAO.equals(Date)) {
					aoabandonnej+=1;
				}
				break;		
			}
		}		
		if(aoencours!=0) {
			System.out.println("Le test ne peut pas être lancé car un AO à un status publié ou en attente");
		}
		else if(aotraitej!=0) {
			System.out.println("Le test ne peut pas être lancé car un AO a été traité aujourd'hui");
		}
		else {
			if(aoabandonnej!=0) {
				
				//New demande
				Thread.sleep(3000);
				js.executeScript("document.body.style.zoom = '100%'");
				driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
				
				// complete the different fields
				driver.findElement(By.xpath("//textarea[@name='DegatsApparents__c']")).sendKeys("Dégat-Test");
				driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
				
				//Select the code of the car registration document
				Thread.sleep(1000);
				WebElement cardreg= driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']"));
				Actions action=new Actions(driver);
				action.moveToElement(cardreg).click().perform();		
				
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.DOWN);
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.RETURN);
				
				//save
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				//Go to the frame request for proposals
				Thread.sleep(5000);
				String goframe="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/a";
				driver.findElement(By.xpath(goframe)).click();
													
				//checking the status
				Thread.sleep(1000);
				js.executeScript("document.body.style.zoom = '40%'");
				String xpsts="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[16]/slot/record_flexipage-record-field/div/div/div[2]/span/slot[1]/slot/lightning-formatted-text";
				String status=driver.findElement(By.xpath(xpsts)).getText();
				System.out.println(status);	
				System.out.println("Validation du test");
			}
			else {
				
				//New demande
				Thread.sleep(5000);
				js.executeScript("document.body.style.zoom = '100%'");
				driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
				
				// complete the different fields
				driver.findElement(By.xpath("//textarea[@name='DegatsApparents__c']")).sendKeys("Dégat-Test");
				driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
				
				//Select the code of the car registration document
				Thread.sleep(1000);
				WebElement cardreg= driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']"));
				Actions action=new Actions(driver);
				action.moveToElement(cardreg).click().perform();		
				
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.DOWN);
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.RETURN);
				
				//save
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				//Go to the frame request for proposals
				Thread.sleep(5000);
				String goframe="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/a";
				driver.findElement(By.xpath(goframe)).click();
				
				//leave the request
				WebElement leave=driver.findElement(By.xpath("//button[@name='B']"));
				action.moveToElement(leave).click().perform();
				driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[@title='OK']")).click();
				
				//Create an other one
				Thread.sleep(1000);
				String xpcanvasAO="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[3]/a";
				driver.findElement(By.xpath(xpcanvasAO)).click();
				
				//New demande
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
				
				// complete the different fields
				driver.findElement(By.xpath("//textarea[@name='DegatsApparents__c']")).sendKeys("Dégat-Test");
				driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
				
				//Select the code of the car registration document
				Thread.sleep(1000);
				 cardreg= driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']"));
				action.moveToElement(cardreg).click().perform();		
				
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.DOWN);
				driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.RETURN);
				
				//save
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				System.out.println("Test validé");
			}
		}
		System.out.println("fin de test");
	}
	
	
	public void AplOfrReg(WebDriver driver, String act, String platform,String dossier) throws InterruptedException, MalformedURLException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
								
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = AplOffreMsgErreur.baseUrl + "/lightning/r/" + caseId + "/related/AppelsOffre__r/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it		
		driver.get(linkListMessage);
				
		//Scroll to load all the element
		Thread.sleep(2000);
		js.executeScript("document.body.style.zoom = '75%'");
		WebElement scrollBar= driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]"));	
		for(int i=0;i<=50;i++) {
		scrollBar.sendKeys(Keys.PAGE_DOWN);
		}
				
		List <WebElement> list =driver.findElements(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr"));		
				
		// Get the current date
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		String Date = dtf.format(Calendar.getInstance().getTime());
				
		//AO canvas 
		String elements="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";			
		String xpdate="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";
		int aojourj=0; //count the number of AO treated today
		for(int i=1;i<=list.size();i++) {			
			String status =driver.findElement(By.xpath(elements+i+"]/td[3]/span/span")).getText();
			String dateAO =driver.findElement(By.xpath(xpdate+i+"]/th/span/a")).getAttribute("title").split(" ")[2];
			switch(status) {
			
			case "En attente","Publié":
				 aojourj+=1;
				break;					
			case "Traité","Annulé","Clôturé":
				if(dateAO.equals(Date)) {
					aojourj+=1;
				}
				break;					
			}
		}
		if(aojourj!=0) {
			
			//New demande
			Thread.sleep(3000);
			js.executeScript("document.body.style.zoom = '100%'");
			driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
			Thread.sleep(3000);
			if(existsElement("/html/body/div[6]/div/div/div/div/div/span",driver)) {
				System.out.println("Test validé, on ne peut pas créer de deuxième AO");
			}
			else {
				System.out.println("Test non validé");
			}
		}
		else {
			//New demande
			Thread.sleep(3000);
			js.executeScript("document.body.style.zoom = '100%'");
			driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
			
			// complete the different fields
			driver.findElement(By.xpath("//textarea[@name='DegatsApparents__c']")).sendKeys("Dégat-Test");
			driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
			
			//Select the code of the car registration document
			Thread.sleep(1000);
			WebElement cardreg= driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']"));
			Actions action=new Actions(driver);
			action.moveToElement(cardreg).click().perform();		
			
			driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.DOWN);
			driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.RETURN);
			
			//save
			driver.findElement(By.xpath("//button[@type='submit']")).click();			
			Thread.sleep(3000);
		}	
	}
	
	public boolean existsElement(String xpath,WebDriver driver) {
	    try {
	        driver.findElement(By.xpath(xpath));
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}

}