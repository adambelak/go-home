package com.epam.training.gohome;

import java.text.ChoiceFormat;
import java.text.Format;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by Adam_Belak on 11/18/2015.
 */
public class FormatGenerator {

    private static final Logger LOGGER = Logger.getLogger(FormatGenerator.class.getName());
    private static final double[] LIMITS = {0, 1, 2};

    private ResourceBundle bundle;

    protected FormatGenerator(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Format[] getFormats() {
        LOGGER.finest("Generating formats...");
        return new Format[]{getHourChoiceFormat(), null, getMinuteChoiceFormat()};
    }

    private ChoiceFormat getHourChoiceFormat() {
        LOGGER.finest("Generating hour choice format...");
        String[] hourLimitStrings = {
                bundle.getString("noHours"),
                bundle.getString("oneHour"),
                bundle.getString("multipleHours")
        };
        return new ChoiceFormat(LIMITS, hourLimitStrings);
    }

    private ChoiceFormat getMinuteChoiceFormat() {
        LOGGER.finest("Generating minute choice format...");
        String[] minuteLimitStrings = {
                bundle.getString("noMinutes"),
                bundle.getString("oneMinute"),
                bundle.getString("multipleMinutes")
        };
        return new ChoiceFormat(LIMITS, minuteLimitStrings);
    }

}
