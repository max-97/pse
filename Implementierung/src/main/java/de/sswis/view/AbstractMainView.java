package de.sswis.view;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;


/**
 *Ein Hauptfenster der Benutzeroberfläche.
 *
 * @author Sophie Bräuniger
 *
 */
public interface AbstractMainView {

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
     *Aktualisiert alle Komponenten.
     */
    void update();



}

