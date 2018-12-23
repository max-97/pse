package de.sswis.util;

/**
 * Utility-Klasse um das Format einer Benutzereingabe zu überprüfen.
 *
 * @author Simon Hügel
 */
public class InputValidator {

    /**
     * Prüft, ob der entgegengenommene String ein gültiger Name ist.
     *
     * @param str der entgegengenommene String
     * @return der Wahrheitswert von "str ist ein gültiger Name"
     */
    public boolean isLegalName(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Beschreibung ist.
     *
     * @param str der entgegengenommene String
     * @return der Wahrheitswert von "str ist eine gültige Beschreibung"
     */
    public boolean isLegalDescription(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String ein gültiger einfacher Zahlenwert ist.
     *
     * @param str der entgegengenommene String
     * @return der Wahrheitswert von "str ist ein gültiger einfacher Zahlenwert"
     */
    public boolean isSingleValue(String str) {
        return false;
    }

    /**
     * Prüft, ob der entgegengenommene String eine gültige Schar von Zahlenwerten ist.
     * Gültig bedeutet in diesem Fall, dass die Eingabe in der Form Startwert - Endwert - Schrittweite stattgefunden hat.
     *
     * @param str der entgegengenommene String
     * @return der Wahrheitswert von "str ist eine gültige Schar von Zahlenwerten"
     */
    public boolean isFamilyOfValues(String str) {
        return false;
    }

}
