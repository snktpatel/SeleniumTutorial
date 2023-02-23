package org.selniumTraining;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchDriver {

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\SeleniumProject\\SeleniumProject\\src\\test\\resource\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("naveen");
        driver.findElement(By.className("lnXdpd")).click();
        driver.findElement(By.className("gNO89b")).click();
    }
}



