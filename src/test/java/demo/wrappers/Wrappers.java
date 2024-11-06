package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void search(WebDriver driver, WebElement element, String str) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Keys.CONTROL + "a");
    element.sendKeys(Keys.DELETE); 
        element.sendKeys(str);
        element.sendKeys(Keys.ENTER);
    }

    public static int rating(List<WebElement> rate,int num){
        int count=0;
        try {
            for(WebElement val : rate){
                Double r=Double.valueOf(val.getText());
                if(r<=num){
                    count ++;
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }

    
}
