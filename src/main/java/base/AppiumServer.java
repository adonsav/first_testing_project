package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServer {

    private AppiumDriverLocalService appiumDriverLocalService;
    private AppiumServiceBuilder appiumServiceBuilder;

    public void startServer() {

        // Build the Appium service
        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumServiceBuilder.usingAnyFreePort();
        appiumServiceBuilder.usingDriverExecutable(new File("/usr/local/bin/node"));
        appiumServiceBuilder.withAppiumJS(new File("node_modules/appium/build/lib/main.js"));
        appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        // Start the server with the builder
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();
        System.out.println("======================================================================================");
        System.out.println("                 Appium Server started " + appiumDriverLocalService.getUrl());
        System.out.println("====================================================================================== \n");

    }

    public void stopServer() {

        appiumDriverLocalService.stop();
        System.out.println("======================================================================================");
        System.out.println("                             Appium Server stopped                                    ");
        System.out.println("======================================================================================");

    }

    public int getAppiumServerPort() {

        return appiumDriverLocalService.getUrl().getPort();

    }



}
