package com.example;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class HeadlessBrowserTest {

    WebDriver driver;
  
    @BeforeEach
    public void setUp() { driver = new ChromeDriver(); }
  
    @AfterEach
    public void tearDown() { driver.quit(); }
  
  
    // test found online
    @Test
    void testWithHeadless(HtmlUnitDriver driver) {
        driver.get("http://www.seleniumhq.org/");
        assertThat(driver.getTitle(), containsString("Selenium"));
    }
}