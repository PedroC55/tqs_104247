package com.example.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    //Page URL
    private static String PAGE_URL="https://www.toptal.com";

    //Locators

    @FindBy( tagName = "h1")
    WebElement heading;

    //Apply as Developer Button
    @FindBy(how = How.LINK_TEXT, using = "Apply as a Freelancer")
    private WebElement freelancerApplyButton;

    //Constructor
    public HomePage(WebDriver driver){
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Hire the Top 3% of Freelance Talent®");
    }

    public void clickOnFreelancerApplyButton(){
        freelancerApplyButton.click();
    }
}