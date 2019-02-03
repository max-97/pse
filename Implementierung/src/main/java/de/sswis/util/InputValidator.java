package de.sswis.util;

/**
 * Utility-Klasse, um das Format einer Benutzereingabe zu überprüfen.
 * Es werden Methoden angeboten, die auf Gültigkeit von Namen, Beschreibungen, Zahlenwerten und Scharen von Zahlenwerten prüfen.
 *
 * @author Simon Hügel
 */
public class InputValidator {

    /**
     * Prüft, ob der entgegengenommene String ein gültiger Name ist.
     * Gültig bedeutet, dass die Eingabe ausschließlich alphanumerische Zeichen beinhaltet und die maximale Länge für Namen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein gültiger Name ist, {@code false} sonst
     */
    public static boolean isLegalName(String str) {

        return (str.matches("\\w{1,35}"));
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Beschreibung ist.
     * Gültig bedeutet, dass ausschließlich Zeichen des Unicode-Zeichensatzes beinhaltet und die maximale Länge für Beschreibungen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Beschreibung ist, {@code false} sonst
     */
    public static boolean isLegalDescription(String str) {

        return (str.length() <= 200);
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

    public static boolean isInterval(String str) {

        String[] parts = str.split("-");

        if (!(parts.length == 2
                && isSingleValue(parts[0].trim()) && isSingleValue(parts[1].trim()))) return false;

        int start = Integer.parseInt(parts[0].trim());
        int end = Integer.parseInt(parts[1].trim());

        return (start < end);
    }

    public static boolean isIntervalPlusSingleValues(String str) {

        String[] parts = str.split(",");

        for (String part : parts) {
            if (!(isSingleValue(part.trim()) || isInterval(part.trim()))) return false;
        }
        return true;
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

}
