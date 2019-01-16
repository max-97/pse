package de.sswis.util;

/**
 * Utility-Klasse, um das Format einer Benutzereingabe zu überprüfen.
 * Es werden Methoden angeboten, die auf Gültigkeit von Namen, Beschreibungen, Zahlenwerten und Scharen von Zahlenwerten prüfen.
 *
 * @author Simon Hügel
 */
public class InputValidator {

    /**
     * Die für einen Namen maximale zulässige Anzahl an Zeichen.
     */
    public static final short MAX_NAME_LENGTH = 70;

    /**
     * Die für eine Beschreibung maximale zulässige Anzahl an Zeichen.
     */
    public static final short MAX_DESCRIPTION_LENGTH = 250;

    /**
     * Prüft, ob der entgegengenommene String ein gültiger Name ist.
     * Gültig bedeutet, dass die Eingabe ausschließlich alphanumerische Zeichen beinhaltet und die maximale Länge für Namen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein gültiger Name ist, {@code false} sonst
     */
    public boolean isLegalName(String str) {

        return (str.length() <= MAX_NAME_LENGTH) && (str.length() != 0) && str.matches("[A-Za-z0-9]");
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Beschreibung ist.
     * Gültig bedeutet, dass ausschließlich Zeichen des Unicode-Zeichensatzes beinhaltet und die maximale Länge für Beschreibungen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Beschreibung ist, {@code false} sonst
     */
    public boolean isLegalDescription(String str) {

        return (str.length() <= MAX_DESCRIPTION_LENGTH) && (str.length() != 0);
    }

    /**
     * Prüft, ob der entgegengenommene String ein einfacher Zahlenwert ist.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein einfacher Zahlenwert ist, {@code false} sonst
     */
    public boolean isSingleValue(String str) {

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
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
    public boolean isFamilyOfValues(String str) {

        String[] strings = str.split(" - ");

        if (!(strings.length == 3 &&
                isSingleValue(strings[0]) && isSingleValue(strings[1]) && isSingleValue(strings[2]))) return false;

        int start = Integer.parseInt(strings[0]);
        int end = Integer.parseInt(strings[1]);
        int step = Integer.parseInt(strings[2]);

        if (step == 0) return (start == end);

        return (step > 0) ? (start <= end) : (start >= end);
    }

}
