package com.epam.training.gohome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Adam_Belak on 11/17/2015.
 */
public class GoHome {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoHome.class);

    private static final String GOHOME_END = "gohome.end";
    private static final String GOHOME_FORMAT = "gohome.format";
    private static final String GOHOME_PROPERTIES = "/gohome.properties";

    private Locale locale;
    private ResourceBundle bundle;
    private Properties properties;

    public GoHome() throws IOException {
        this(Locale.getDefault());
    }

    public GoHome(Locale locale) throws IOException {
        this.locale = locale;
        this.bundle = PropertyResourceBundle.getBundle("messages", this.locale);
        this.properties = new Properties();
        this.properties.load(GoHome.class.getResourceAsStream(GOHOME_PROPERTIES));
    }

    public void timeTo() {
        LOGGER.trace("Calculating time...");
        LocalTime now = LocalTime.now().withNano(0).withSecond(0);
        LOGGER.trace("from: {}", now.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getProperty(GOHOME_FORMAT));
        LocalTime end = LocalTime.parse(properties.getProperty(GOHOME_END), formatter);
        LOGGER.trace("  to: {}", end.toString());
        printMessage(difference(now, end));
    }

    private LocalTime difference(LocalTime now, LocalTime end) {
        LOGGER.trace("Calculating difference...");
        LocalTime timeLeft = end.minusHours(now.getHour()).minusMinutes(now.getMinute());
        LOGGER.trace("left: {}", timeLeft.toString());
        return timeLeft;
    }

    private void printMessage(LocalTime time) {
        LOGGER.trace("Printing message...");
        MessageFormat messageForm = new MessageFormat("");
        messageForm.setLocale(locale);

        String and = (time.getHour() > 0 && time.getMinute() > 0) ? bundle.getString("and") : "";
        messageForm.applyPattern(bundle.getString("pattern"));
        messageForm.setFormats(new FormatGenerator(bundle).getFormats());

        Object[] messageArguments = {time.getHour(), and, time.getMinute()};
        String result = messageForm.format(messageArguments);
        LOGGER.info(result);
    }

}
