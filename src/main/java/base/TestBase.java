package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URL;

public class TestBase {

    private static AppiumServer appium = new AppiumServer();
    private static AppiumDriver<?> driver;

    @BeforeSuite
    public void appiumInitialization() throws Exception {

        System.out.println("Mpike sthn Before Suite");
        appium.startServer();
        setDeviceCapabilities("android", "emulator-5554", "7.1.1");

        try {

            Runtime.getRuntime().exec("scripts/launchGrid.sh");
            System.out
                    .println("======================================================================================");
            System.out.println("                                    HUB Started ");
            System.out
                    .println("======================================================================================");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @BeforeTest(alwaysRun = true)
    //@Parameters({ "platformName", "udid", "platformVersion" })
    public void setDeviceCapabilities(String platformName, String udid, String platformVersion) throws Exception {

        System.out.println("Mpike sthn Before Test");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platformName.toLowerCase().equals("ios")) {

            System.out.println("Apple SUCKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        } else if (platformName.toLowerCase().equals("android")) {

            capabilities.setCapability(MobileCapabilityType.UDID, "asggsbdfs");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0.1");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "test");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.appium.android.apis.ApiDemos");

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        } else {

            throw new Exception("Platform not supported! Check if you set ios or android on the parameter.");
        }

    }

    @AfterSuite
    public void appiumTermination() {

        System.out.println("Mpike sthn After Suite");
        driver.quit();
        appium.stopServer();

    }

}