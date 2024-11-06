package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public static void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.flipkart.com/");
        String currentUrl=driver.getCurrentUrl();
        if(currentUrl.contains("flipkart")){
            WebElement search=driver.findElement(By.xpath("//input[@class='Pke_EE']"));
            Wrappers.search(driver,search, "Washing Machine");
            WebElement menuBar=driver.findElement(By.xpath("//div[text()='Popularity']"));
            menuBar.click();
            List<WebElement> ratingList=driver.findElements(By.xpath("//div[@class='_75nlfW']//div[@class='XQDdHH']"));
            int cnt=Wrappers.rating(ratingList,4);
            System.out.println("Count of Items rating less than 4 : "+cnt);
        }
        
    }

    @Test
    public static void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        WebElement secondSearchBox=driver.findElement(By.xpath("//input[@class='zDPmFV']"));
        Wrappers.search(driver,secondSearchBox, "iPhone");
        Thread.sleep(3000);
        
    }

    @Test
    public static void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        List<WebElement> discountPer=driver.findElements(By.xpath("//div[@class='UkUFwK']"));
        for(WebElement dis : discountPer){
        String number=dis.getText().replaceAll("[^0-9]", "");
        int discount=Integer.parseInt(number);

        if(discount>17){
            WebElement title = dis.findElement(By.xpath("../../../preceding-sibling::div/div"));
            System.out.println("Title : "+title.getText()+"Discount percentage : "+discount+"%");
        }
        
        }
        
    }

    @Test
    public static void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        WebElement secondSearchBox=driver.findElement(By.xpath("//input[@class='zDPmFV']"));
        Wrappers.search(driver,secondSearchBox, "Coffee Mug");
        Thread.sleep(2000);
        
        List<WebElement> reviewString=driver.findElements(By.xpath("//span[@class='Wphh3N' and normalize-space(text()) != '']"));
        System.out.println("Total reviews found: " + reviewString.size());
        List<Integer> numbers=new ArrayList<>();
        
Map<Integer, WebElement> reviewElementMap = new HashMap<>();

    // Iterate through review elements to extract and store review counts
    for (WebElement review : reviewString) {
        String text = review.getText().replaceAll("[^0-9]", "");
        int count = Integer.parseInt(text);
        numbers.add(count);
        reviewElementMap.put(count, review);
    }

    
    Collections.sort(numbers);
    Collections.reverse(numbers);

    
    for (int i = 0; i < Math.min(5, numbers.size()); i++) {
        int reviewCount = numbers.get(i);
        WebElement reviewElement = reviewElementMap.get(reviewCount);

        
        WebElement titleElement = reviewElement.findElement(By.xpath("ancestor::div[contains(@class, 'slAVV4')]//a[@class='wjcEIp']"));
        WebElement imageElement = reviewElement.findElement(By.xpath("ancestor::div[contains(@class, 'slAVV4')]//img"));
        WebElement starElement = reviewElement.findElement(By.xpath("ancestor::div[contains(@class, 'slAVV4')]//div[@class='XQDdHH']"));


        // Print the title and image URL
        System.out.println("Title: " + titleElement.getText());
        System.out.println("Image URL: " + imageElement.getAttribute("src"));
        System.out.println("Review Count: " + reviewCount);

        String starText = starElement.getText().replaceAll("[^0-9.]", ""); 
        double starRating = Double.parseDouble(starText);
        
        // Check if star rating is 4 or higher
        if (starRating >= 4.0) {
            System.out.println("Star Rating: " + starRating);
        }
    }
        
    }
       
        
    

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}