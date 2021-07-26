package BlocageRapportEtCclTechnique;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
	
public class TestRapportEtCclTechnique {
	public static String caseId;
	
	public void blocageDocCas1(WebDriver driver,String dossier,String rapport) throws InterruptedException, MalformedURLException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Find the access to the expert card
		Thread.sleep(5000);
		String xpExpert="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[3]/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_case___0121t0000015djoaa2___full___view___recordlayout2/force-record-layout-block/slot/force-record-layout-section[6]/div/div/div/slot/force-record-layout-row[3]/slot/force-record-layout-item[2]/div/div/div[2]/span/slot[1]/slot/force-lookup/div/force-hoverable-link/div/a";
		WebElement Expert=driver.findElement(By.xpath(xpExpert));
		js.executeScript("arguments[0].scrollIntoView()", Expert);
		driver.findElement(By.xpath(xpExpert)).click();
		
		//Access to poste BCA
		String xpPoste="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-personnel_-b-c-a_-page_d_enregistrement___-personnel-b-c-a__c___-v-i-e-w/forcegenerated-flexipage_personnel_bca_page_d_enregistrement_personnelbca__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[2]/slot/slot/flexipage-component2/slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list/lst-list-view-manager-header/div/div[1]/div[1]/div/div/h2/a";
		WebElement PosteBCA= driver.findElement(By.xpath(xpPoste));
		js.executeScript("arguments[0].scrollIntoView()", PosteBCA);
		driver.findElement(By.xpath(xpPoste)).click();
		
		//know if the expert is still working for BCA
		String xpdateExpert="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[1]/td[5]/span/span";
		String dateExpert=driver.findElement(By.xpath(xpdateExpert)).getText();
		
		// Get the current date
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		String Date = dtf.format(Calendar.getInstance().getTime());
			
		
		//Go on Editique
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Editique']")).click();
		
		//Find CTP, P1 or P26
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='enter-search']")).sendKeys(rapport);
		Thread.sleep(2000);

		if(dateExpert.compareTo(Date)>=0 ||dateExpert.equals("")) {
			
			//Click to print the error message			
			driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
			
			//Generate a CTP
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@title='Générer']")).click();
			driver.findElement(By.xpath("//body[@class='desktop']")).sendKeys(Keys.CONTROL+""+Keys.TAB);
			Thread.sleep(10000);
			ArrayList<String>tab=new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));			
			Thread.sleep(3000);
			String xpP26CTP="/html/body/my-app/bdoc-top-bar/div/div/bdoc-layout-main/header/nav/bdoc-toolbar/div/div[1]/bdoc-label/label";					
			String xpElement="/html/body/my-app/bdoc-top-bar/div/div/bdoc-layout-main/header/nav/bdoc-toolbar/div/div[1]/bdoc-label/label/text()";								
								
			if(existsElement(xpElement,driver)||existsElement(xpP26CTP,driver)) {
				System.out.println("Test validé, Le document a bien été crée");
			}
			else {
				System.out.println("Test non validé, Le document n'a pas été crée");
			}
		}
		else {
			//Click to print the error message			
			driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
			
			if(existsElement("//div[text()='Attention']",driver)) {				
				System.out.println("Test validé message d'erreur: "+driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span")).getText());
			}
			else {
				System.out.println("Test invalide, message non affiché");
			}
		}
	}
	
	public void blocageDocCas2(WebDriver driver,String dossier,String rapport) throws InterruptedException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Find the access to the expert card
		Thread.sleep(5000);
		String xpExpert="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[3]/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_case___0121t0000015djoaa2___full___view___recordlayout2/force-record-layout-block/slot/force-record-layout-section[6]/div/div/div/slot/force-record-layout-row[3]/slot/force-record-layout-item[2]/div/div/div[2]/span/slot[1]/slot/force-lookup/div/force-hoverable-link/div/a";
		WebElement Expert=driver.findElement(By.xpath(xpExpert));
		js.executeScript("arguments[0].scrollIntoView()", Expert);
		driver.findElement(By.xpath(xpExpert)).click();
				
		//Access to poste BCA
		String xpPoste="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-personnel_-b-c-a_-page_d_enregistrement___-personnel-b-c-a__c___-v-i-e-w/forcegenerated-flexipage_personnel_bca_page_d_enregistrement_personnelbca__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[2]/slot/slot/flexipage-component2/slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list/lst-list-view-manager-header/div/div[1]/div[1]/div/div/h2/a";
		WebElement PosteBCA= driver.findElement(By.xpath(xpPoste));
		js.executeScript("arguments[0].scrollIntoView()", PosteBCA);
		driver.findElement(By.xpath(xpPoste)).click();
		
		//Get the job of the expert
		String xpjob="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr";
		String mainJob=driver.findElement(By.xpath(xpjob)).getAttribute("title");
		
		//Know if it's his main job 
		String table="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr";
		String functionJob="Non";
		int i=1;
		while(functionJob.equals("Non")) {
			functionJob=driver.findElement(By.xpath(table+"["+i+"]/td[4]/span/span")).getText();
			mainJob=driver.findElement(By.xpath(xpjob+"["+i+"]/th/span/a")).getAttribute("title");
			i+=1;
		}
		System.out.println(mainJob);
		//Go on Editique
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Editique']")).click();
	
		//Find CTP, P1 or P26
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='enter-search']")).sendKeys(rapport);
		Thread.sleep(2000);
		
		if(mainJob.equals("Expert Formation Terrain")){
								
			//Click to print the error message			
			driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
			
			if(existsElement("//div[text()='Attention']",driver)) {
				System.out.println("Test validé message d'erreur: "+driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span")).getText());
			}
			else {
				System.out.println("Test invalide, message non affiché");
			}
		}
		else {
			System.out.println("L'expert n'est pas en formation tout terrain");
		}
	}
	
	public void blocageDocCas3(WebDriver driver,String dossier,String rapport) throws InterruptedException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Go on Editique
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Editique']")).click();
				
		//Find CTP, P1 or P26
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='enter-search']")).sendKeys(rapport);		
				
		//Find the access to the expert card
		Thread.sleep(2000);
		String xpExpert="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[3]/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_case___0121t0000015djoaa2___full___view___recordlayout2/force-record-layout-block/slot/force-record-layout-section[6]/div/div/div/slot/force-record-layout-row[3]/slot/force-record-layout-item[2]/div/div/div[2]/span/slot[1]/slot/force-lookup/div/force-hoverable-link/div/a";
		WebElement Expert=driver.findElement(By.xpath(xpExpert));
		js.executeScript("arguments[0].scrollIntoView()", Expert);
		String expertName=driver.findElement(By.xpath(xpExpert)).getText();
		
		//Open expertise data
		WebElement dataValuation= driver.findElement(By.xpath("//a[text()='Données Expertise']"));
		js.executeScript("arguments[0].scrollIntoView()", dataValuation);
		driver.findElement(By.xpath("//a[text()='Données Expertise']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Change frame and open exam
		String xpiframe="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[4]/slot/flexipage-component2/slot/c-lwc004_-consulter-donnees-expertise/iframe";
		WebElement iframe=driver.findElement(By.xpath(xpiframe));
		driver.switchTo().frame(iframe);	
		Thread.sleep(2000);				
		driver.findElement(By.xpath("/html/body/div/div/div[1]/h3/button")).click();
		Thread.sleep(3000);
			
		String xpexam="/html/body/div/div/div[1]/div/div/div/div[1]/table/tbody/tr[";
		List<WebElement> exam=driver.findElements(By.xpath("/html/body/div/div/div[1]/div/div/div/div[1]/table/tbody/tr"));		
		if(exam.size()==0) {
			System.out.println("L'expert n'a pas fait d'exam, l'erreur doit s'afficher");
			
			//Select the report	
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
			
			if(existsElement("//div[text()='Attention']",driver)) {
				System.out.println("Test validé message d'erreur: "+driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span")).getText());
			}
			else {
				System.out.println("Test invalide, message non affiché");
			}
		}
		else {	
			
			for(int i=1;i<=exam.size();i++) {
				String matchExpertName=driver.findElement(By.xpath(xpexam+i+"]/td[2]/div")).getText();
				if(matchExpertName.equals(expertName)) {
					System.out.println("L'expert à déjà fait un examen");
					
					//Select the report	
					driver.switchTo().defaultContent();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
					
					//Generate a CTP
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[@title='Générer']")).click();
					driver.findElement(By.xpath("//body[@class='desktop']")).sendKeys(Keys.CONTROL+""+Keys.TAB);
					Thread.sleep(13000);
					ArrayList<String>tab=new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tab.get(1));			
					Thread.sleep(2000);
					String xpP26CTP="/html/body/my-app/bdoc-top-bar/div/div/bdoc-layout-main/header/nav/bdoc-toolbar/div/div[1]/bdoc-label";					
					String xpElement="/html/body/my-app/bdoc-top-bar/div/div/bdoc-layout-main/header/nav/bdoc-toolbar/div/div[1]/bdoc-label/label/text()";								
									
					if(existsElement(xpP26CTP,driver)||existsElement(xpElement,driver)) {
						System.out.println("Test validé, Le document a bien été crée");
					}			
					else {
						System.out.println("Test invalide");
					}
				System.exit(0);
				}				
			}
			
			//Select the report	
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@data-modele='"+rapport+"']")).click();
						
			//Display error message					
			if(existsElement("/html/body/div[6]/div/div/div/div/div/span",driver)) {
				System.out.println("Test Validé: "+driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span")).getText());
			}			
			else {
				System.out.println("Test invalide");
			}
			
		}
	}
	
	public boolean existsElement(String xpath,WebDriver driver) {
	    try {
	        driver.findElement(By.xpath(xpath));
	    }
	    catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
}
