package de.sswis.view;

/**
 * Ein Fenster der Benutzeroberfläche.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractView {

    /**
     *Aktualisiert alle Komponenten.
     */
    void update();

    /**
     * Macht dieses Fenster sichtbar.
     */
    void show();

    /**
     * Schließt dieses Fenster.
     */
    void close();

}
