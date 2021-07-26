
package NouvActeur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCreateActor {
	public static String caseId;
	
	@SuppressWarnings("null")
	public void CreateActor(WebDriver driver,String dossier) throws InterruptedException {
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		Thread.sleep(3000);
		
		//Open acteur dossier
		js.executeScript("document.body.style.zoom = '45%'");
		Thread.sleep(2000);
		String xpActeurDoss="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2[1]/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[1]/slot/flexipage-component2[3]/slot/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list/lst-list-view-manager-header/div/div[1]/div[1]/div/div/h2/a";
		WebElement acteurDoss=driver.findElement(By.xpath(xpActeurDoss));
		js.executeScript("document.body.style.zoom = '100%'");
		js.executeScript("arguments[0].scrollIntoView()", acteurDoss);
		driver.findElement(By.xpath(xpActeurDoss)).click();
		
		//scroll to see all the actors
		WebElement scrollBar=driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]"));
		for(int i=0;i<=50;i++) {
			scrollBar.sendKeys(Keys.PAGE_DOWN);
		}
		
		//Get all the role
		String xpActor="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr";
		List<WebElement> actor=driver.findElements(By.xpath(xpActor));
		ArrayList<String> actorRole = new ArrayList<String>();
		int i=1;
			while(i<=actor.size()) {
				actorRole.add(driver.findElement(By.xpath(xpActor+"["+i+"]/td[2]/span/span")).getText());
				i+=1;
			}
			
			for(int j=0;j<actorRole.size()-1;j++) {
				for(int k=j+1;k<actorRole.size();k++) {
					if(actorRole.get(j).equals(actorRole.get(k))) {						
						System.out.println("Test Invalide: Il y a 2 rôles similaires");
						System.exit(0);
					}																						
				}
			}
			System.out.println("Il n'y a pas 2 rôles similaires");
			
			//Create a new actor
			String xpNouveau="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[1]/div[1]/div[2]/ul/li/a";
			driver.findElement(By.xpath(xpNouveau)).click();
			
			//choose an account
			driver.findElement(By.xpath("//input[@placeholder='Recherchez dans les Comptes...']")).sendKeys("BCA services");
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/records-related-list-lwc-detail-panel-wrapper/records-record-layout-event-broker/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_acteursdossier__c___012000000000000aaa___full___create___recordlayout2/force-record-layout-block/slot/force-record-layout-section/div/div/div/slot/force-record-layout-row[1]/slot/force-record-layout-item[1]/div/span/slot/slot/force-record-layout-lookup/lightning-lookup/lightning-lookup-desktop/lightning-grouped-combobox/div[1]/div/lightning-base-combobox/div/div[2]/ul/li[1]/lightning-base-combobox-item")).click();
			
			//Choose a role
			String xpRole="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/records-related-list-lwc-detail-panel-wrapper/records-record-layout-event-broker/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_acteursdossier__c___012000000000000aaa___full___create___recordlayout2/force-record-layout-block/slot/force-record-layout-section/div/div/div/slot/force-record-layout-row[2]/slot/force-record-layout-item[1]/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[1]/input";
			WebElement role=driver.findElement(By.xpath(xpRole));
			js.executeScript("arguments[0].scrollIntoView()",role);
			driver.findElement(By.xpath(xpRole)).click();			
			String assuré="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/records-related-list-lwc-detail-panel-wrapper/records-record-layout-event-broker/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-lwc-record-layout/forcegenerated-detailpanel_acteursdossier__c___012000000000000aaa___full___create___recordlayout2/force-record-layout-block/slot/force-record-layout-section/div/div/div/slot/force-record-layout-row[2]/slot/force-record-layout-item[1]/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[3]";
			driver.findElement(By.xpath(assuré)).click();
			
			//Try to save
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
			Thread.sleep(10000);
			
			//Check if the error is present
			if(existsElement("/html/body/div[4]/div[2]/div/div[1]/div[1]/force-record-edit-error-header/header/div/div[2]/h2",driver)) {
				String msgError=driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/force-record-edit-error-header/header/div/div[2]/h2")).getText();
				System.out.println("Test validé message d'erreur: "+msgError);
			}
			else {
				System.out.println("Test Invalide");
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
