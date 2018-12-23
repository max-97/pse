package de.sswis.view.model;

/**
 * Utility-Klasse, um das Format einer Benutzereingabe zu überprüfen.
 * Es werden Methoden angeboten, die auf Gültigkeit von Namen, Beschreibungen, Zahlenwerten und Scharen von Zahlenwerten prüfen.
 *
 * @author Simon Hügel
 */
public class VMUtil {

    /**
     * Die für einen Namen maximale zulässige Anzahl an Zeichen.
     */
    public static final short MAX_NAME_LENGHT = 70;

    /**
     * Die für eine Beschreibung maximale zulässige Anzahl an Zeichen.
     */
    public static final short MAX_DESCRIPTION_LENGHT = 250;

    /**
     * Prüft, ob der entgegengenommene String ein gültiger Name ist.
     * Gültig bedeutet, dass die Eingabe ausschließlich alphanumerische Zeichen beinhaltet und die maximale Länge für Namen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein gültiger Name ist, {@code false} sonst
     */
    public boolean isLegalName(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Beschreibung ist.
     * Gültig bedeutet, dass ausschließlich Zeichen des Unicode-Zeichensatzes beinhaltet und die maximale Länge für Beschreibungen nicht überschritten wurde.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Beschreibung ist, {@code false} sonst
     */
    public boolean isLegalDescription(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String ein einfacher Zahlenwert ist.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str ein einfacher Zahlenwert ist, {@code false} sonst
     */
    public boolean isSingleValue(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Schar von Zahlenwerten ist.
     * Gültig bedeutet, dass die Eingabe in der Form Startwert - Endwert - Schrittweite stattgefunden hat.
     *
     * @param str der entgegengenommene String
     * @return {@code true}, wenn str eine gültige Schar von Zahlenwerten ist, {@code false} sonst
     */
    public boolean isFamilyOfValues(String str) {
        return false;
    }

}
