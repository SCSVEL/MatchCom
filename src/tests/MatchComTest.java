package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class MatchComTest {
  WebDriver driver;
  WebDriverWait wait;
  Actions actions;
	
	
  @BeforeTest
  public void beforeTest() {
//	  driver = new FirefoxDriver();
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\EclipseWorkSpace\\SeleniumJARs\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  wait = new WebDriverWait(driver, 10);
	  actions = new Actions(driver);
	  driver.manage().window().maximize();
  }

  @Test(enabled=true)
  public void registerForMatchCom() throws InterruptedException {
	  //Navigate to registration page
	  driver.get("http://www.match.com/cpx/en-us/match/registration/indexpage");
	  
	  //Register
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("genderGenderSeek")));
	  WebElement temp = driver.findElement(By.id("genderGenderSeek"));
	  Select genderSeek = new Select(temp);
	  genderSeek.selectByVisibleText("Man seeking a Woman");
	  
	  driver.findElement(By.name("postalCode")).sendKeys("21117");
	  driver.findElement(By.xpath("//button[text()='View Singles']")).click();
	  
	  String emailID = "twew32@gmail.com";    //DATA
	  wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
	  driver.findElement(By.name("email")).sendKeys(emailID);
	  driver.findElement(By.xpath("//button[text()='Continue']")).click();
	  
	  wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
	  driver.findElement(By.name("password")).sendKeys("test123");
	  
	  temp = driver.findElement(By.id("birthMonth"));
	  Select birthMonth = new Select(temp);
	  birthMonth.selectByVisibleText("Feb"); 
	  
	  temp = driver.findElement(By.id("birthDay"));
	  Select birthDay = new Select(temp);
	  birthDay.selectByVisibleText("2");
	  
	  temp = driver.findElement(By.id("birthYear"));
	  Select birthYear = new Select(temp);
	  birthYear.selectByVisibleText("1990");
	  
	  driver.findElement(By.xpath("//fieldset[@class='active']//button[text()='Continue']")).click();
	  
	  String userID = "twew325";  //DATA
	  wait.until(ExpectedConditions.elementToBeClickable(By.name("handle")));
	  driver.findElement(By.name("handle")).sendKeys(userID);  //////
	  driver.findElement(By.xpath("//fieldset[@class='active']//button[text()='Continue']")).click();
	  Thread.sleep(5000);
	  
	  //Navigate to home page
	  driver.navigate().to("http://www.match.com/home/mymatch.aspx");
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='welcomeMsg']/a")));
	  String user = driver.findElement(By.xpath("//span[@class='welcomeMsg']/a")).getText();
	  if(user.contains(userID))
		  System.out.println("Successfully logged in with registered user");
	  
	  
	  //SEARCH
	  driver.findElement(By.name("dist")).clear();
	  driver.findElement(By.name("dist")).sendKeys("100");
	  driver.findElement(By.xpath("//form[@id='quick-search']//button[text()=' Search Now ']")).click();
	  
	  //Add to Favorite
	  String addedUser = driver.findElement(By.xpath("//div[@class='results ']//dd[@class='card-bio '][1]/a")).getText();
	  driver.findElement(By.xpath("//div[@class='results ']//a[text()='save'][1]")).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'has been added to your favorites')]")));
	  System.out.println("User added successfully to Favorites: " + addedUser);	  	    	
 	  
	  //LOGOUT
	  WebElement toElement = driver.findElement(By.xpath("//div[@id='header-nav']//li[contains(@class,'menu-Logout')]"));	  
	  actions.moveToElement(toElement).build().perform(); 
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Sign Out ']")).click();
	  
	  //Login
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
	  driver.findElement(By.id("email")).sendKeys(emailID);
	  driver.findElement(By.id("password")).sendKeys("test123");
	  driver.findElement(By.xpath("//button[text()='Sign in now »']")).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='welcomeMsg']/a")));
	  
	  //GoTo Favorites page	  	  
	  toElement = driver.findElement(By.xpath("//div[@id='header-nav']//li[contains(@class,'menu-Connections')]"));
	  actions.moveToElement(toElement).build().perform(); 
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='My Favorites ']")).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-cnt-fave")));  
	  
	  
	  //Verify if added
	  String actualUserAdded = driver.findElement(By.xpath("//div[@class='card']//div[@class='card-body'][1]/h3/a")).getText();
	  if(actualUserAdded.equalsIgnoreCase(addedUser))
		  System.out.println("Added User is available under My Favorites: " + actualUserAdded);
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
