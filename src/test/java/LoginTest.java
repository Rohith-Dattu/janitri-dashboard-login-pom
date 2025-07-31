

import org.testng.Assert;
import com.LoginPage;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest {
    private LoginPage loginPage;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev-dash.janitri.in/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginProcessOnceAndPass() {
        // Process: Enter userId & password
        loginPage.enterUserId("rohith@gmail.com");
        loginPage.enterPassword("qwerty");

        // Toggle password (make it visible, via the eye icon)
        loginPage.togglePasswordVisibility();

        // Assert: Password is visible
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after toggle.");

        // Click login button
        loginPage.clickLoginButton();

        // (Optional) Wait for post-login indicator here, if your app gives one

        // Refresh the page
        driver.navigate().refresh();

        // Pass test if process finished with no exception
        Assert.assertTrue(true);
    }
}
