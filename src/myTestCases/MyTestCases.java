package myTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters{
	
	@BeforeTest
	public void mySetup() {

		ConfigurationToAccess();
	}

	@Test(priority = 1)

	public void CheckTheDefaultLanguageIsEnglish() {

		String ActualLanaguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(ActualLanaguage, ExpectedEnglishLanaguage);
	}

	@Test(priority = 2)

	public void CheckTheDefaultCurrency() {

		String ActualCurrency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)

	public void CheckTheMobileNumber() {

		String ActualMobileNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualMobileNumber, ExpectedMobileNumber);

	}

	@Test(priority = 4)

	public void CheckQitafLogo() throws InterruptedException {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		WebElement TheContainerDiv = TheFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"));
		WebElement QitafLogo = TheContainerDiv.findElement(By.tagName("svg"));

		boolean ActaualQitafLogoDisplay = QitafLogo.isDisplayed();

		Assert.assertEquals(ActaualQitafLogoDisplay, ExpectedQitafLogoDisplay);

	}

	@Test(priority = 5)

	public void CheckHotelTabIsNotSelected() throws InterruptedException {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValueforHotelTab = HotelTab.getDomAttribute("aria-selected");

		Assert.assertEquals(ActualValueforHotelTab, ExpectedValueForHotelTab);

	}

	@Test(priority = 6)

	public void CheckDepatureDate() throws InterruptedException {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));

		String ActualDepatureDate = dates.get(0).getText().replaceFirst("^0", "");

		Assert.assertEquals(ActualDepatureDate, Tomorrow);

	}

	@Test(priority = 7)

	public void CheckReturnDate() throws InterruptedException {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));

		String ActualReturnDate = dates.get(1).getText().replaceFirst("^0", "");

		Assert.assertEquals(ActualReturnDate, DayAfterTomorrow);

	}

	@Test(priority = 8)

	public void RandomlyChangeWebSiteLanguage() throws InterruptedException {

		

		driver.get(Websites[RandomIndexForTheWebSite]);


		WebElement headerforTheLanagauge = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement SearchCityInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		if (headerforTheLanagauge.getText().equals("العربية")) {
			String ActualLanaguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

			Assert.assertEquals(ActualLanaguage, ExpectedEnglishLanaguage);

			SearchCityInput.sendKeys(EnglishCities[RandomEnglishCity]);

			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

			ListOfCities.findElements(By.tagName("li")).get(1).click();

		}

		else {

			String ActualLanaguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

			Assert.assertEquals(ActualLanaguage, ExpectedArabicLanaguage);
			SearchCityInput.sendKeys(ArabicCities[RandomArabicCity]);
			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

			ListOfCities.findElements(By.tagName("li")).get(1).click();

		}

		WebElement NumberOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select mySelector = new Select(NumberOfVistor);

		int RandomIndex = rand.nextInt(2);

		mySelector.selectByIndex(RandomIndex);

		WebElement SearchButton = driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk"));
		SearchButton.click();

		Thread.sleep(25000);

		WebElement Results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		System.out.println(Results.getText() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		;

		boolean ActualResult = Results.getText().contains("stays") || Results.getText().contains("مكان");

		Assert.assertEquals(ActualResult, ExpectedResults);

	}

}


