package com.epam.training.gohome;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Adam_Belak on 11/17/2015.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        new GoHome().timeTo();
        new GoHome(new Locale("hu")).timeTo();
    }

}
