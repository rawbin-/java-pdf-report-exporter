import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.print.PrintOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class SeleniumDemo {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(options);

        driver.get("http://www.baidu.com");
        driver.manage().addCookie(new Cookie("xxxtoken", "tokenvalue", ".baidu.com", "/", new Date(2023, 2, 2)));

        PrintsPage printsPage = (PrintsPage) driver;
        Pdf pdf = printsPage.print(new PrintOptions());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("selenium-out.pdf"));
            fileOutputStream.write(Base64.getDecoder().decode(pdf.getContent()));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}