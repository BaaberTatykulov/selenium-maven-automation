package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// Set up chrome driver path
		WebDriverManager.chromedriver().setup();
		// invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		// fullcreen
		driver.manage().window().maximize();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/*
		 * Step 1. Launch browser and navigate to https://dice.com Expected: dice home
		 * page should be displayed
		 * 
		 */

		// url variable
		String url = "https://dice.com";
		// open webSite
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage succesufulle loaded");
		} else {
			System.out.println("Step FAIL. Dice homepage did not load succesfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not load succesfully");
		}

		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = "60659";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		// ensure count is more than 0
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			System.out.println("Step PASS: Keyword : " + keyword + " search reterned " 
		+ countResult + " results in " + location);
					
		}else {
			System.out.println("Step PASS: Keyword : " + keyword + " search reterned " 
					+ countResult + " results in " + location);
		}
		
		driver.close();
		System.out.println("Test completed -  "+ LocalDateTime.now());
	}

}
