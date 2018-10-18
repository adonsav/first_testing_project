package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;

public class TestBase {

    private static AppiumServer appium = new AppiumServer();
    public AppiumDriver<?> driver = null;

    @BeforeSuite
    public void appiumInitialization() {

        System.out.println("Before Suite annotation is running.\n");
        appium.startServer();

        try {

            Runtime.getRuntime().exec("./executors/launchHub.sh");
            Runtime.getRuntime().exec("./executors/launchNode1.sh");
            Runtime.getRuntime().exec("./executors/launchNode2.sh");

            System.out
                    .println("======================================================================================");
            System.out
                    .println("                           HUB and NODES Started ");
            System.out
                    .println("====================================================================================== \n");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @BeforeTest(alwaysRun = true)
    @Parameters({ "platformName", "udid", "platformVersion" })
    public void setDeviceCapabilities(String platformName, String udid, String platformVersion) throws Exception {

        System.out.println("\nBefore Test annotation is running.\n");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);

        switch (platformName.toLowerCase()) {
            case "ios":
                System.out.println("Apple SUCKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;

            case "android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "gr.androiddev.taxibeat");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.taxibeat.passenger.presentation.components.activities.SplashActivity");
                capabilities.setCapability("autoGrantPermissions", "true");
                capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");


                driver = new AndroidDriver<MobileElement>(
                    new URL("http://127.0.0.1:" + appium.getAppiumServerPort() + "/wd/hub"), capabilities);
                break;

         default:
             throw new Exception("Platform not supported! Check if you set ios or android on the parameter.");

        }
    }

    @AfterSuite(alwaysRun = true)
    public void appiumTermination() {

        System.out.println("\nAfter Suite annotation is running.\n");

        System.out.println("    Closing application.\n");
        driver.closeApp();

        System.out.println("    Quitting Appium driver.\n");
        driver.quit();

        System.out.println("    Stopping Appium server.\n");
        appium.stopServer();

    }

}
