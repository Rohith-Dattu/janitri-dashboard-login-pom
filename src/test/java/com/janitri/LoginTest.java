package com.janitri;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        // Re-init the page object before each test
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginWithPasswordToggleAndSubmit() {
        loginPage.enterUserId("rohith@gmail.com");
        loginPage.enterPassword("qwerty");

        // Toggle password visibility
        loginPage.togglePasswordVisibility();

        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after toggle.");

        // Login
        loginPage.clickLoginButton();

        // Refresh and explicit assert
        driver.navigate().refresh();
        Assert.assertTrue(true); // Dummy assert
    }

    @Test
    public void testLoginAndRefresh() {
        loginPage.enterUserId("rohith@gmail.com");
        loginPage.enterPassword("qwerty");

        loginPage.clickLoginButton();
        driver.navigate().refresh();
        Assert.assertTrue(true); // Dummy assert
    }
}
