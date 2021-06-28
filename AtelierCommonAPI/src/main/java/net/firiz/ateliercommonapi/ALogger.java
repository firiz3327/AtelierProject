package net.firiz.ateliercommonapi;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ALogger {

    private ALogger() {
    }

    private final static Logger LOGGER = AtelierCommonAPI.getPlugin().getLogger();

    public static void log(Object message) {
        LOGGER.log(Level.INFO, message.toString());
    }

}
