package US2583;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestTauxPT {
	public static String caseId;
	
	public void CalculTauxPT(WebDriver driver,String dossier) throws InterruptedException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Open Perte totale
		driver.findElement(By.xpath("//a[text()='Perte totale']")).click();
		
		//Open process and value		
		String xpProcessAndValue="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/h3/button";
		driver.findElement(By.xpath(xpProcessAndValue)).click();
		driver.findElement(By.xpath(xpProcessAndValue)).click();
		driver.findElement(By.xpath(xpProcessAndValue)).click();
		Thread.sleep(5000);
		
		//Get the vrade amount
		String xpvrade="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[5]/div[1]/div/div/div";
		WebElement locvrade=driver.findElement(By.xpath(xpvrade));
		js.executeScript("arguments[0].scrollIntoView()", locvrade);
		String svrade=driver.findElement(By.xpath(xpvrade)).getText();
		Thread.sleep(1000);
		
		if(svrade.equals("")) {
			System.out.println("Le taux PT ne peut pas être calculé");
		}
		else {
			//convert string in integer
			double vrade=Double.valueOf(svrade);
		
			//Get the reparation amount
			String xprep="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[10]/div[1]/div/div/div";
			WebElement locrep=driver.findElement(By.xpath(xprep));
			js.executeScript("arguments[0].scrollIntoView()", locrep);
			String srep=driver.findElement(By.xpath(xprep)).getText();
			Thread.sleep(1000);
			
			if(srep.equals("")) {
				System.out.println("Erreur");
			}
			else {
				//convert string in integer
				double rep=Double.valueOf(srep);
				
				//Get the TVA vrade
				String xpTVAvrade="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[5]/div[2]/div/div/div";
				WebElement weTVAvrade=driver.findElement(By.xpath(xpTVAvrade));
				js.executeScript("arguments[0].scrollIntoView()", weTVAvrade);
				String TVAvrade=driver.findElement(By.xpath(xpTVAvrade)).getText();
							
				//Get the TVA reparation
				String xpTVArep="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[10]/div[2]/div/div/div";
				WebElement weTVArep=driver.findElement(By.xpath(xpTVArep));
				js.executeScript("arguments[0].scrollIntoView()", weTVArep);
				String TVArep=driver.findElement(By.xpath(xpTVArep)).getText();
				
				//format number
				DecimalFormat f = new DecimalFormat("0.00");
				
				if(TVArep.equals("Net")||TVAvrade.equals("Net")||TVAvrade.equals("")||TVArep.equals("")) {
					System.out.println("Position TVA Net ou non renseignée");
				}
				else {
					if((TVAvrade.equals("TTC")&&TVAvrade.equals(TVArep))||(TVAvrade.equals("HT")&&TVAvrade.equals(TVArep))) {
						
						//Calculate taux PT						
						double dtauxPT=(rep/vrade)*100;
						if(dtauxPT>=999) {
							dtauxPT=999;
						}
						String tauxPT=f.format(dtauxPT);
						String xptauxPT="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[9]/div[2]/div/div/div";
						String sfTauxPT=f.format(Double.valueOf(driver.findElement(By.xpath(xptauxPT)).getText()));
						
						if(sfTauxPT.equals(tauxPT)) {
							System.out.println("Test Valide : le taux PT trouvé est bien le même que sur le dossier");
						}
						else {
							System.out.println("Test Invalide : le taux PT trouvé n'est pas le même que sur le dossier ");
						}
					}
					else{
						if(TVAvrade.equals("TTC")&&TVArep.equals("HT")){
							vrade=vrade/1.2;
							double dtauxPT=(rep/vrade)*100;
							if(dtauxPT>=999) {
								dtauxPT=999;
							}
							String tauxPT=f.format(dtauxPT);
							String xptauxPT="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[9]/div[2]/div/div/div";
							String sfTauxPT=f.format(Double.valueOf(driver.findElement(By.xpath(xptauxPT)).getText()));
							
							if(sfTauxPT.equals(tauxPT)) {
								System.out.println("Test Valide : le taux PT trouvé est bien le même que sur le dossier");
							}
							else {
								System.out.println("Test Invalide : le taux PT trouvé n'est pas le même que sur le dossier ");
							}
							
						}
						else if (TVAvrade.equals("HT")&&TVArep.equals("TTC")) {
							rep=rep/1.2;
							double dtauxPT=(rep/vrade)*100;
							if(dtauxPT>=999) {
								dtauxPT=999;
							}
							String tauxPT=f.format(dtauxPT);
							String xptauxPT="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[9]/div[2]/div/div/div";
							String sfTauxPT=f.format(Double.valueOf(driver.findElement(By.xpath(xptauxPT)).getText()));
							
							if(sfTauxPT.equals(tauxPT)) {
								System.out.println("Test Valide : le taux PT trouvé est bien le même que sur le dossier");
							}
							else {
								System.out.println("Test Invalide : le taux PT trouvé n'est pas le même que sur le dossier ");
							}
						}					
					}
				}				
			}
		}				
	}
}

