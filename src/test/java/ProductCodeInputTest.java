import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductCodeInputTest {
    private static WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void testDriverConnection(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver.get("https://www.valentino.com/en-ca");
    }

    @org.testng.annotations.Test
    public void productCodeInputTest() throws InterruptedException {
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"home\"]/div[1]/header/nav[1]/ul/li[3]/button"));

//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        Thread.sleep(4000);
        searchButton.click();
        String codeOfProductStr = "VW0T0S12ZFRLC8";
        WebElement inputLine = driver.findElement(By.id("textSearch"));
        System.out.println(inputLine.getText());
        Thread.sleep(4000);
        inputLine.sendKeys(codeOfProductStr);
        Thread.sleep(2000);
        WebElement product = driver.findElement(By.xpath("//*[@id=\"panelSearch\"]/div[2]/div[3]/form/div[3]/div/ul/li/a"));
        Thread.sleep(2000);
        product.click();

        WebElement codeOfProduct = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[4]/div[6]/div/div[3]/p"));

        String mydata = codeOfProduct.getAttribute("innerHTML");
        Pattern pattern = Pattern.compile("[A-Z0-9]{14}");
        Matcher matcher = pattern.matcher(mydata);

        String res = "";

        res = matcher.find() ? matcher.group() : null;

        Assert.assertEquals(res, codeOfProductStr);
    }

    @AfterTest
    public void exit(){
        driver.quit();
    }
}

