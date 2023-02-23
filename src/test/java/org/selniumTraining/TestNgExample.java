package org.selniumTraining;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v106.domsnapshot.model.StringIndex;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestNgExample {

    WebDriver driver;
    @BeforeMethod
    void launchDriver(){
        System.setProperty("webdriver.chrome.driver",
                "C:\\SeleniumProject\\SeleniumProject\\src\\test\\resource\\chromedriver.exe");
         driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
         driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1000));
        driver.get("http://the-internet.herokuapp.com/");
    }

    @Test(priority = 0)
    public void verifyTitleOfPage(){
        String actualTitle =driver.findElement(By.className("heading")).getText();
        Assert.assertEquals(actualTitle,"Welcome to the-internet");
    }

    @Test(priority = 1)
    public void verifyABtesting(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='A/B Testing']")));

        driver.findElement(By.xpath("//*[text()='A/B Testing']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='content']//h3"))));
       String actualABTestingHeader =driver.findElement(By.xpath("//*[@id='content']//h3")).getText();
       Assert.assertEquals(actualABTestingHeader,"A/B Test Variation 1");
    }

    @Test(priority = 2)
    public void selectDropDown() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dropdown")));

        driver.findElement(By.linkText("Dropdown")).click();

        Select s = new Select(driver.findElement(By.id("dropdown")));
        Thread.sleep(1000);
        s.selectByIndex(1);
        s.selectByVisibleText("Option 2");
        s.selectByValue("1");
    }

    @Test(priority = 3)
    public void selectCHeckBox(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkboxes")));

        driver.findElement(By.linkText("Checkboxes")).click();
       List<WebElement> webelemets= driver.findElements(By.xpath("//*[@id='checkboxes']/input"));
       for(WebElement element:webelemets){

               String val=element.getAttribute("checked");
               System.out.println(val);
               if(val==null){
                   element.click();
               }
       }
    }
@Test
    public void mouseActions(){
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Drag and Drop")));

    driver.findElement(By.linkText("Drag and Drop")).click();
        Actions action = new Actions(driver);
        action.dragAndDrop(driver.findElement(By.id("column-a")),driver.findElement(By.id("column-b"))).perform();

    action.contextClick(driver.findElement(By.id("hot-spot"))).build().perform();
    }
    @Test
    public void rightClick(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Context Menu")));

        driver.findElement(By.linkText("Context Menu")).click();
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("hot-spot"))).build().perform();

    }

    @Test
    public void tableHandle(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Challenging DOM")));
        driver.findElement(By.linkText("Challenging DOM")).click();
        String  str="Iuvaret1";
        driver.findElement(By.xpath("//*[text()='"+str+"']/..//td/a[text()='edit']")).click();
    }


    @Test
    public void iframeExample(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Frames")));

        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("iFrame")).click();
       driver.switchTo().frame("mce_0_ifr");
       String text= driver.findElement(By.id("tinymce")).getText();

    }

    @Test(dataProvider = "authitication")
    public void multipleWindow(String firstName,String lastName,String age){
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(age);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Multiple Windows")));

        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.linkText("Click Here")).click();
        String currentWindow =driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for(String window:allWindows){
            if(window!=currentWindow){
                driver.switchTo().window(window);
            }
        }
       String newWindowText= driver.findElement(By.xpath("//*[@class='example']/h3")).getText();

    }

    @Test
    public  void alert(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("JavaScript Alerts")));

        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
      driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
      driver.switchTo().alert().dismiss();
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
      driver.switchTo().alert().sendKeys("a");
      driver.switchTo().alert().accept();
    }

    @DataProvider(name="authitication")
        public static Object[][] crediential(){
            return new Object[][] {{"naveen","goyal","31"},{"fatime","Roksana","30"},{"sanket","abc","32"}};
        }





    @AfterMethod
    void quitDriver(){
        driver.quit();         //all the chrome driver tab and window will close
        //driver.close();         //only the current window nd tab will close
    }



}
