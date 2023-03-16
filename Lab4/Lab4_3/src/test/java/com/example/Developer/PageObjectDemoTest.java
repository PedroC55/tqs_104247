package com.example.Developer;

import com.example.Page.DeveloperApplyPage;
import com.example.Page.HomePage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class PageObjectDemoTest {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use FF Driver
        driver = new ChromeDriver();
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);

        //Check if page is opened
        Assert.assertTrue(home.isPageOpened());

        home.clickOnFreelancerApplyButton();

        //Create object of DeveloperApplyPage
        DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

        //Check if page is opened
        Assert.assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setDeveloper_email("dejan@toptal.com");
        applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
        applyPage.setDeveloper_password("password123");
        applyPage.setDeveloper_password_confirmation("password123");

        //Click on join
        //applyPage.clickOnJoin();
    }

    @AfterEach
    public void close(){
        driver.close();
    }
}