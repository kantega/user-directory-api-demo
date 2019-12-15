package no.kantega.cloud.util;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern validUuidPattern = Pattern.compile("[0-9a-fA-F]{8}-(?:[0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}");

    private ValidationUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isValidUuid(String s) {
        return s != null && validUuidPattern.matcher(s).matches();
    }
}
