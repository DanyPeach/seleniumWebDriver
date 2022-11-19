import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.valentino.com/en-ca");
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"home\"]/div[1]/header/nav[1]/ul/li[3]/button"));

//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        Thread.sleep(4000);
        searchButton.click();
        String codeOfProductStr = "VW0T0S12ZFRLC8";
        WebElement inputLine = driver.findElement(By.id("textSearch"));
        System.out.println(inputLine.getText());
        Thread.sleep(4000);
        inputLine.sendKeys(codeOfProductStr);
        Thread.sleep(1000);
        WebElement product = driver.findElement(By.xpath("//*[@id=\"panelSearch\"]/div[2]/div[3]/form/div[3]/div/ul/li/a"));
        Thread.sleep(1000);
        product.click();

        WebElement codeOfProduct = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[4]/div[6]/div/div[3]/p"));

        String mydata = codeOfProduct.getAttribute("innerHTML");
        Pattern pattern = Pattern.compile("[A-Z0-9]{14}");
        Matcher matcher = pattern.matcher(mydata);
        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(Objects.equals(matcher.group(), codeOfProductStr));
        }

        driver.quit();
    }
}

