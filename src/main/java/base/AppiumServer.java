package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

    private AppiumDriverLocalService appiumServer;
    private AppiumServiceBuilder appiumBuilder;

    public void startServer() {

        // Build the Appium service
        appiumBuilder = new AppiumServiceBuilder();
        appiumBuilder.withIPAddress("127.0.0.1");
        appiumBuilder.usingAnyFreePort(); // .usingPort(4723);
        // appiumBuilder.usingDriverExecutable(new
        // File("/home/linuxbrew/.linuxbrew/bin/node"));
        // appiumBuilder.withAppiumJS(new
        // File("/home/linuxbrew/.linuxbrew/lib/node_modules/appium/build/lib/main.js"));
        appiumBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        // Start the server with the builder
        appiumServer = AppiumDriverLocalService.buildService(appiumBuilder);
        appiumServer.start();
        System.out.println("======================================================================================");
        System.out.println("                 Appium Server started " + appiumServer.getUrl());
        System.out.println("======================================================================================");

    }

    public void stopServer() {

        appiumServer.stop();
        System.out.println("======================================================================================");
        System.out.println("                             Appium Server stopped                                    ");
        System.out.println("======================================================================================");

    }

}
