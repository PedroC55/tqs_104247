package com.example;
import static io.github.bonigarcia.seljup.BrowserType.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class DockerBrowserTest {

    @Test
    void testWithDockerOpera(@DockerBrowser(type = OPERA) RemoteWebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(), containsString("Selenium-Jupiter"));
    }

    @Test
    void testWithDockerEdge(@DockerBrowser(type = EDGE) RemoteWebDriver driver) {
        driver.get("https://www.microsoft.com/");
        assertThat(driver.getTitle(), containsString("Microsoft"));
    }

}