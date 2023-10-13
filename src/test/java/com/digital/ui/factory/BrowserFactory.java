package com.digital.ui.factory;

import com.digital.util.CommonUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * AuthorName: Murali Krishnan Nagaraj AuthorEmail: muralikrishnan.nagaraj@albertsons.com Summary :
 * Browser setup configs CreatedDate : Nov-04-2018
 */

//Test Commit for validation
public class BrowserFactory {
    public static final String USER_DIR = "user.dir";
    public static final String DOWNLOADED_FILES_FOLDER = System.getProperty("user.dir") + System.getProperty("file.separator") + "downloadfiles";
    static BrowserMobProxy proxy;

    public static BrowserMobProxy getProxy() {
        return proxy;
    }

    public static WebDriver getBrowser(String browserName) throws IOException {
        WebDriver driver = null;
        String rwdDevice = System.getProperty(CommonUtilities.RWDDEVICE);

        if (browserName.equalsIgnoreCase("firefox")) {
            if (driver == null) {
                System.setProperty(
                        GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.gecko.driver"));
                if (Boolean.parseBoolean(System.getProperty("analytics"))) {
                    // create proxy
                    proxy = new BrowserMobProxyServer();
                    proxy.start(0);
                    // get the Selenium proxy object
                    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
                    // configure it as a desired capability
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("acceptInsecureCerts", true);
                    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
                    // start the browser up
                    //driver = new FirefoxDriver(capabilities);
                    driver = new FirefoxDriver();
                    // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
                    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT);
                    proxy.newHar("test");
                    driver.manage().window().maximize();
                    System.out.println("Running on Firefox with proxy");
                } else {
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().window().maximize();
                    System.out.println("Running on Firefox");
                }
            }
        } else if (browserName.equalsIgnoreCase("ffheadless")) {
            if (driver == null) {
                System.setProperty(
                        GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.gecko.driver"));
                if (Boolean.parseBoolean(System.getProperty("analytics"))) {
                    // create proxy
                    proxy = new BrowserMobProxyServer();
                    proxy.start(0);
                    // get the Selenium proxy object
                    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
                    // configure it as a desired capability
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("headless", true);
                    capabilities.setCapability("acceptInsecureCerts", true);
                    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-headless");
                    firefoxOptions.merge(capabilities);
                    // start the browser up
                    driver = new FirefoxDriver(firefoxOptions);
                    // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
                    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT);
                    proxy.newHar("test");
                    driver.manage().window().maximize();
                    System.out.println("Running on Firefox Headless with proxy");
                } else {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().maximize();
                    System.out.println("Running on Firefox Headless");
                }
            }
        } else if (browserName.equalsIgnoreCase("ie")) {
            if (driver == null) {
                System.setProperty(
                        InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.ie.driver"));
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.destructivelyEnsureCleanSession();
                driver = new InternetExplorerDriver(ieOptions);
                driver.manage().window().maximize();
                System.out.println("Running on IE");
            }
        } else if (browserName.equalsIgnoreCase("edge")) {
            if (driver == null) {
                System.setProperty(
                        EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.edge.driver"));
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("InPrivate", true);
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                System.out.println("Running on Edge");
            }
        } else if (browserName.equalsIgnoreCase("chrome")) {
            if (driver == null) {
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
                System.setProperty("webdriver.chrome.whitelistedIps", "");
                System.setProperty(
                        ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.chrome.driver"));
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1366,768");
//            options.addArguments("auto-open-devtools-for-tabs");
                System.out.println("Running on Chrome");
                //DesiredCapabilities caps = DesiredCapabilities.chrome();
                //ChromeOptions chromeOptions = new ChromeOptions();

                options.addArguments("--remote-allow-origins=*");
                //LoggingPreferences logPrefs = new LoggingPreferences();
                HashMap<String, Object> chromePref = new HashMap<String, Object>();
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.default_directory", DOWNLOADED_FILES_FOLDER);
                options.setExperimentalOption("prefs", chromePref);
                if (System.getProperty("os.name").contains("Mac")) {
                    System.out.println("Running on " + System.getProperty("os.name"));
                    //logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                    //options.setCapability("goog:loggingPrefs", logPrefs);
                    driver = new ChromeDriver(options);
                } else {
                    System.out.println("Running on " + System.getProperty("os.name"));
                    //logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
                    options.setCapability(ChromeOptions.CAPABILITY, options);
                    //options.merge(options);
                    //chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                    driver = new ChromeDriver(options);
                }
                //driver.manage().window().maximize();
            }
        } else if (browserName.equalsIgnoreCase("jsChrome")) {
            if (driver == null) {
                System.out.println("JS CHROME Driver ");
                ChromeOptions chromeOptions = new ChromeOptions();
                System.setProperty(
                        ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.chrome.driver"));
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                //driver.manage().window().setSize(new Dimension(1500, 900));
                System.out.println("Running on jsChrome");
            }
        } else if (browserName.equalsIgnoreCase("chromeheadless")) {
            if (driver == null) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
                chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1400,600");
                HashMap<String, Object> chromePref = new HashMap<String, Object>();
                System.setProperty(
                        ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.chrome.driver"));
                if (System.getProperty("os.name").contains("Mac")) {
                    System.out.println("Running on " + System.getProperty("os.name"));
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    System.out.println("Running on " + System.getProperty("os.name"));
                    //ChromeOptions chromeOptions1 = new ChromeOptions();
                    chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    //chromeOptions.merge(chromeOptions);
                    driver = new ChromeDriver(chromeOptions);
                }
                driver.manage().window().setSize(new Dimension(1440,900));
                driver.manage().window().maximize();
                System.out.println("Running on Chromeheadless");
            }
        } else if (browserName.equalsIgnoreCase("safari")) {
            if (driver == null) {
                driver = new SafariDriver();
                driver.manage().window().maximize();
            }
        } else if (browserName.equalsIgnoreCase("headless")) {
            if (driver == null) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("Platform", Platform.ANY);
                System.setProperty(
                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        System.getProperty("phantomjs.binary.path"));
                driver = new PhantomJSDriver(capabilities);
            }
            if (browserName.equalsIgnoreCase("firefox")) {
                if (driver == null) {
                    System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.gecko.driver"));
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                }
            } else if (browserName.equalsIgnoreCase("ie")) {
                if (driver == null) {
                    System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.ie.driver"));
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.destructivelyEnsureCleanSession();
                    driver = new InternetExplorerDriver(ieOptions);
                    driver.manage().window().maximize();
                }
            } else if (browserName.equalsIgnoreCase("edge")) {
                if (driver == null) {
                    System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.edge.driver"));
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("InPrivate", true);
                    driver = new EdgeDriver(edgeOptions);
                    driver.manage().window().maximize();
                }
            } else if (browserName.equalsIgnoreCase("chrome")) {
                if (driver == null) {
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.chrome.driver"));
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    options.addArguments("--remote-allow-origins=*");
                    LoggingPreferences logPrefs = new LoggingPreferences();
                    logPrefs.enable(LogType.BROWSER, Level.ALL);
                    //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability(ChromeOptions.CAPABILITY, options);
                    chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
					/*
					Map<String, String> mobileEmulation = new HashMap<>();

					mobileEmulation.put("deviceName", "Nexus 5");

					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

					driver = new ChromeDriver(chromeOptions);
					*/
                }
            } else if (browserName.equalsIgnoreCase("jsChrome")) {
                if (driver == null) {
                    System.out.println(" JSCHrome driver Driver ");
                    System.setProperty("webdriver.chrome.whitelistedIps", "");
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.chrome.driver"));
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();

                }
            } else if (browserName.equalsIgnoreCase("chromeheadless")) {
                if (driver == null) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("window-size=1400x600");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--proxy-server='direct://'");
                    chromeOptions.addArguments("--proxy-bypass-list=*");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("start-maximized");
                    System.setProperty("webdriver.chrome.whitelistedIps", "");
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("webdriver.chrome.driver"));
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().setSize(new Dimension(1440, 900));
                }
            } else if (browserName.equalsIgnoreCase("safari")) {
                if (driver == null) {
                    driver = new SafariDriver();
                }
            } else if (browserName.equalsIgnoreCase("headless")) {
                if (driver == null) {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("Platform", Platform.ANY);
                    System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("phantomjs.binary.path"));
                    driver = new PhantomJSDriver(capabilities);
                }

            } else if (browserName.equalsIgnoreCase("chromeandroid")) {
                // ******This coded added to test Android Real device******//
                if (driver == null) {
                    System.out.println("Driver is null");
                    System.setProperty("webdriver.chrome.whitelistedIps", "");
                    System.setProperty(
                            ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                            System.getProperty("webdriver.chrome.driver"));
                    AppiumDriver appiumdriver = null;
                    try {
                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
                        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, rwdDevice);
                        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                        URL url = new URL("http://10.10.10.10:4723/wd/hub");
                        appiumdriver = new AppiumDriver(url, capabilities);
                        System.out.println("Running on Chrome chromeandroid");
                    } catch (Exception e) {
                        System.out.println("Exception:" + e.toString());
                    }
                    return appiumdriver;
                }
            } else if (browserName.equalsIgnoreCase("chromeIOS")) {
                // ******This coded added to test Android Real device******//
                if (driver == null) {
                    System.out.println("Driver is null");
                    System.setProperty("webdriver.chrome.whitelistedIps", "");
                    System.setProperty(
                            ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                            System.getProperty("webdriver.chrome.driver"));
                    AppiumDriver appiumdriver = null;
                    try {
                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
                        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
                        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                        URL url = new URL("http://10.10.10.10:4723/wd/hub");
                        appiumdriver = new AppiumDriver(url, capabilities);
                        System.out.println("Running on Chrome IOS");
                    } catch (Exception e) {
                        System.out.println("Exception:" + e.toString());
                    }
                    return appiumdriver;
                }
            }
        } else if (browserName.equalsIgnoreCase("chromespoofer")) {

            String deviceName = null;

            if (rwdDevice.equalsIgnoreCase("empty")) {
                deviceName = "iPhone 6 Plus";
            } else {
                deviceName = rwdDevice;
                System.out.println("Selected device: " + deviceName);
            }
            if (driver == null) {
                System.setProperty("webdriver.chrome.whitelistedIps", "");
                System.setProperty(
                        ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.chrome.driver"));
                ChromeOptions capabilities = new ChromeOptions();
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", deviceName);
                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                capabilities.addArguments("--remote-allow-origins=*");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                driver = new ChromeDriver(capabilities);
                System.out.println("Running on Chrome Spoofer:");

            }
        } else if (browserName.equalsIgnoreCase("chromespooferheadless")) {

            String deviceName = null;

            if (rwdDevice.equalsIgnoreCase("empty")) {
                deviceName = "iPhone 6 Plus";
            } else {
                deviceName = rwdDevice;
                System.out.println("Selected device: " + deviceName);
            }
            if (driver == null) {
                ChromeOptions chromeOptions = new ChromeOptions();
                System.setProperty("webdriver.chrome.whitelistedIps", "");
                System.setProperty(
                        ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                        System.getProperty("webdriver.chrome.driver"));
                ChromeOptions capabilities;
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", deviceName);
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                chromeOptions.addArguments("--headless");
                //.addArguments("--remote-allow-origins=*")
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--proxy-server='direct://'");
                chromeOptions.addArguments("--proxy-bypass-list=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=800,600");
                capabilities = new ChromeOptions();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                driver = new ChromeDriver(capabilities);
                System.out.println("Running on Chrome Spoofer:");
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}