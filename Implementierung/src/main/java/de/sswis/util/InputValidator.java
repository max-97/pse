package de.sswis.util;

/**
 * Utility-Klasse, um das Format einer Benutzereingabe zu überprüfen.
 * Es werden Methoden angeboten, die auf Gültigkeit von Namen, Beschreibungen, Zahlenwerten und Scharen von Zahlenwerten prüfen.
 *
 * @author Simon Hügel
 */
public class InputValidator {

    public static String ILLEGAL_INPUT_MSG = "Bitte überprüfen Sie Ihre Eingabe!";

    /**
     * Prüft, ob der entgegengenommene String ein gültiger Name ist.
     * Gültig bedeutet, dass die Eingabe ausschließlich alphanumerische Zeichen beinhaltet und die maximale Länge für Namen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein gültiger Name ist, {@code false} sonst
     */
    public static boolean isLegalName(String str) {
        return str.matches("^(?! )[a-zA-Z0-9 ]{1,35}(?<! )$");
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Beschreibung ist.
     * Gültig bedeutet, dass ausschließlich Zeichen des Unicode-Zeichensatzes beinhaltet und die maximale Länge für Beschreibungen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Beschreibung ist, {@code false} sonst
     */
    public static boolean isLegalDescription(String str) {

        return (str.length() <= 300);
    }

    /**
     * Prüft, ob der entgegengenommene String ein einfacher Zahlenwert ist.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein einfacher Zahlenwert ist, {@code false} sonst
     */
    public static boolean isSingleValue(String str) {

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String str) {

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isInterval(String str) {

        String[] parts = str.split("-");

        return parts.length == 2
                && isSingleValue(parts[0].trim()) && isSingleValue(parts[1].trim());
    }

    public static boolean isIntervalPlusSingleValues(String str) {

        String[] parts = str.split(",");

        for (String part : parts) {
            if (!(isSingleValue(part.trim()) || isInterval(part.trim()))) return false;
        }
        return true;
    }

    public static boolean isLegalAgentCount(String str) {
        return isSingleValue(str) ? (Integer.parseInt(str) % 2 == 0) && (Integer.parseInt(str) > 0) :
                isFamilyOfValues(str);
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Schar von Zahlenwerten ist.
     * Gültig bedeutet, dass die Eingabe in der Form Startwert - Endwert - Schrittweite stattgefunden hat.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Schar von Zahlenwerten ist, {@code false} sonst
     */
    public static boolean isFamilyOfValues(String str) {

        String[] parts = str.split("-");

        if (!(parts.length == 3 && isSingleValue(parts[0].trim())
                && isSingleValue(parts[1].trim()) && isSingleValue(parts[2].trim()))) return false;

        int start = Integer.parseInt(parts[0].trim());
        int end = Integer.parseInt(parts[1].trim());
        int step = Integer.parseInt(parts[2].trim());

        return (start != end) && (step > 0);
    }

    public static boolean isFamilyOfPercentages(String str) {

        String[] parts = str.split("-");

        if (!(parts.length == 3 && isPercentage(parts[0].trim())
                && isPercentage(parts[1].trim()) && isPercentage(parts[2].trim()))) return false;

        int start = Integer.parseInt(parts[0].replace("%","").trim());
        int end = Integer.parseInt(parts[1].replace("%","").trim());

        return start != end;
    }

    public static boolean isSingelOrMultiplePercantage(String input) {
        if (input.contains("-")) {
            // Variabler Parameter
            String[] parts = input.split(" - ");
            if (!(parts.length == 3))
                return false;
            if ( !(isSingleValue(parts[0]) && isSingleValue(parts[1]) && isSingleValue(parts[2])) )
                return false;
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            int step = Integer.parseInt(parts[2]);

            return start != end && start >= 0 && end >= 0 && start <= 100 && end <= 100 && step > 0 && step <= 100;
        } else {
            // Einfacher Parameter
            if (!isSingleValue(input)) return false;
            int percentage = Integer.parseInt(input);
            return percentage >= 0 && percentage <= 100;
        }
    }

    public static boolean isPercentage(String str) {

        if (!(str.contains("%"))) return false;
        String percentage = str.replace("%","").trim();

        return isInPercentageRange(percentage);
    }

    public static boolean isInPercentageRange(String str) {

        if (!isSingleValue(str.trim())) return false;
        int percentage = Integer.parseInt(str.trim());

        return (percentage > 0) && (percentage < 101);
    }

    public static boolean isProbability(String input) {
        if (!isDouble(input))
            return false;
        double value = Double.parseDouble(input);
        return 0 <= value && value <= 1;
    }

    public static boolean isVariableProbability(String input) {
        String[] parts = input.split("-");
        if(parts.length != 3) return false;
        double start;
        double end;
        double step;
        try {
            start = Double.parseDouble(parts[0]);
            end = Double.parseDouble(parts[1]);
            step = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return start != end && start >= 0 && start <= 1 && end >= 0 && end <= 1 && step > 0 && step <= 1;
    }

    public static boolean containsFamilyOfValues(String str) {

        String[] parts = str.split(",");

        for (String part : parts) {
            if (isFamilyOfValues(part.trim())) return true;
        }

        return false;
    }

    public static boolean isCorrectParameterInput(String label, Object input) {
        return !((String) input).trim().matches(""); //TODO: implementation
    }
}
