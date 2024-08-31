package info.getCaseDetails;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import info.getCountryDetails.*;

public class GetCaseDetails extends GetCountryDetails
{
	
	HashMap<String, ArrayList<String>> details = new HashMap<String, ArrayList<String>>();
	//Getting data from 'www.worldometers.info' website to calculate covid affected and mortality percentage
	public HashMap<String, ArrayList<String>> caseDetails(String[] allCountries)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Tech\\Supporting Files\\Selenium Drivers\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Navigation navigator = driver.navigate();
		navigator.to("https://www.worldometers.info/coronavirus/#countries");
		
		for(String individualCountries : allCountries)
		{
			ArrayList <String> countDetails = new ArrayList <String>();
			
			WebElement searchField = driver.findElement(By.xpath("(//input[@class='form-control input-sm'])[1]"));
			searchField.sendKeys(individualCountries);
			
			String population = driver.findElement(By.xpath("((//table[@id='main_table_countries_today']/tbody/tr)/td[15])[1]/a")).getText();
			countDetails.add(population.replace(",", ""));
			String totalCase = driver.findElement(By.xpath("((//table[@id='main_table_countries_today']/tbody/tr)/td[3])[1]")).getText();
			countDetails.add(totalCase.replace(",", ""));
			String totalDeadth = driver.findElement(By.xpath("((//table[@id='main_table_countries_today']/tbody/tr)/td[5])[1]")).getText();
			countDetails.add(totalDeadth.replace(",", ""));
			
			details.put(individualCountries, countDetails);
			navigator.refresh();

		}
		driver.quit();
		return details;
	}
}
