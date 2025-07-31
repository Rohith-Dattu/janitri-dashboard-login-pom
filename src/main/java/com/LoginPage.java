// package and imports remain the same
package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By userIdInput = By.xpath("//*[@id='formEmail']");
    private By passwordInput = By.xpath("//*[@id='formPassword']");
    private By loginButton = By.xpath("//*[@id='root']/div/div/div[2]/div/div/form/button");
    private By errorMessage = By.cssSelector(".error-message");
    private By toggleEyeIcon = By.xpath("//*[@id='root']/div/div/div[2]/div/div/form/div[2]/img");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUserId(String userId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput));
        WebElement userField = driver.findElement(userIdInput);
        userField.clear();
        userField.sendKeys(userId);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement passField = driver.findElement(passwordInput);
        passField.clear();
        passField.sendKeys(password);
    }

    public boolean isLoginButtonDisabled() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        WebElement button = driver.findElement(loginButton);
        return !button.isEnabled();
    }

    public boolean isPasswordMasked() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement password = driver.findElement(passwordInput);
        String typeAttr = password.getAttribute("type");
        return typeAttr != null && typeAttr.equalsIgnoreCase("password");
    }

    public void togglePasswordVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(toggleEyeIcon));
        driver.findElement(toggleEyeIcon).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        List<WebElement> errors = driver.findElements(errorMessage);
        if (!errors.isEmpty()) {
            return errors.get(0).getText();
        }
        return "";
    }

    public boolean areElementsPresent() {
        return !driver.findElements(userIdInput).isEmpty() &&
               !driver.findElements(passwordInput).isEmpty() &&
               !driver.findElements(loginButton).isEmpty();
    }
}
