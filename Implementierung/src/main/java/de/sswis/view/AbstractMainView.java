package de.sswis.view;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;
import java.util.Collection;


/**
 *Ein Hauptfenster der Benutzeroberfläche.
 *
 * @author Sophie Bräuniger
 *
 */
public interface AbstractMainView extends AbstractView {

    /**
     * Fügt eine vom Nutzer erstellte Konfiguration hinzu.
     * @param configuration die Benutzereingaben zur Konfiguration
     */
    void addConfiguration(VMConfiguration configuration);

    /**
     * Entfernt die vom Nutzer erstellte Konfiguration mit dem übergegebenen Namen.
     * @param configurationName Name der Konfiguration
     */
    void removeConfiguration(String configurationName);

    /**
     * Fügt ein Ergebnis zu einer Konfiguration hinzu.
     * @param NameConfiguration Name der Konfiguration.
     * @param result Ergebnis
     */
    void  addResult(String NameConfiguration, VMResult result);

    /**
     * Wird aufgerufen wenn eine Simulation beendet wurde.
     * @param NameConfiguration Name der Konfiguration, mit der die beendete Simulation ausgeführt wurde
     */
    void setSimulationFinished (String NameConfiguration);

    /**
     * Gibt eine {@code VMConfiguration} zurück, die in der Liste ausgewählt ist.
     *
     * @return die in der Liste ausgewählt Konfiguration
     */
    VMConfiguration getSelected();

    /**
     * Fügt ein ActionListener zum Button Simulation starten hinzu.
     * @param listener ActionListener
     */
    void addStartButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Kontextmenüpunkt Simulation abbrechen hinzu.
     * @param listener ActionListener
     */
    void addStopActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Ergebnisse anzeigen hinzu.
     * @param listener ActionListener
     */
    void addShowButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein Actionlistener zum Button Ergebnisse speichern hinzu.
     * @param listener ActionListener
     */
    void addSaveButtonActionlistener(ActionListener listener);



    /**
     * Fügt ein ActionListener zum Menüpunkt neues Stufenspiel hinzu.
     * @param listener ActionListener
     */
    void addNewGameMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt neue kombinierte Strategie hinzu.
     * @param listener ActionListener
     */
    void addNewCombiStrategyMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt neue gemischte Strategie hinzu.
     * @param listener ActionListener
     */
    void addNewStrategyMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt neue Initialisierung hinzu.
     * @param listener ActionListener
     */
    void addNewInitMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt neue Konfiguration hinzu.
     * @param listener ActionListener
     */
    void addNewConfigMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt Stufenspiele verwalten hinzu.
     * @param listener ActionListener
     */
    void addManageGameMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt kombinierte Strategien verwalten hinzu.
     * @param listener ActionListener
     */
    void addManageCombiStrategyMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt gemischte Strategien verwalten hinzu.
     * @param listener ActionListener
     */
    void addManageStrategyMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt Initialisierungen verwalten hinzu.
     * @param listener ActionListener
     */
    void addManageInitMenuActionListener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Menüpunkt Konfigurationen verwalten hinzu.
     * @param listener ActionListener
     */
    void addManageConfigMenuActionListener(ActionListener listener);

    Collection<VMResult> getResults();




}

