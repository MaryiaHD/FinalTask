import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testUserRegistration() {
        driver.get("http://shop.pragmatic.bg");
        WebElement myAccount = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        driver.findElement(By.id("input-firstname")).sendKeys("Maria");
        driver.findElement(By.id("input-lastname")).sendKeys("Hadzhiivanova");

        WebElement emailInputField = driver.findElement(By.id("input-email"));
        String prefix = RandomStringUtils.randomAlphanumeric(7);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String emailAddress = prefix + "@" + sufix + ".bg";
        emailInputField.sendKeys(emailAddress);


        driver.findElement(By.id("input-telephone")).sendKeys("0123456789");
        driver.findElement(By.id("input-password")).sendKeys("password123");
        driver.findElement(By.id("input-confirm")).sendKeys("password123");

        WebElement privacyPolicy = driver.findElement(By.xpath("//*[@id='content']/form/div/div/input[1]"));
        privacyPolicy.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input.btn-primary"));
        continueButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertEquals(successMessage.getText(), "Your Account Has Been Created!", "Registration failed!");


    }
}
