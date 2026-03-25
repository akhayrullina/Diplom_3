package utils.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static utils.config.BaseURL.BASE_URL;

public class DriverFactory {
    private WebDriver driver;
    private final String YANDEX_BROWSER_PATH = "C:\\Users\\akhayrullina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
//    private final String YANDEX_DRIVER_PATH = "C:\\drivers\\Yandex\\yandexdriver.exe";
    private final String YANDEX_DRIVER_PATH = "src/test/resources/yandexdriver.exe";

    public void initDriver() {
        if("yandex".equals(System.getProperty("browser"))) {
            setupYandex();
        } else {
            setupChrome();
        }
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setupBaseUrl();
    }

    public void setupYandex() {
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
//        WebDriverManager.chromedriver().driverVersion("114.0.7559.981").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER_PATH);
//        options.addArguments("--start-maximized");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev0shm-isage");
        driver = new ChromeDriver(options);
        setupBaseUrl();
    }

    public void setupBaseUrl() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
