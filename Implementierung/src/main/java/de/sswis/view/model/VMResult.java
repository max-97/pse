package de.sswis.view.model;

/**
 *Ein Ergebnis zum Speichern von Daten von abgeschlossenen Simulationen.
 *Berechnet Daten zum Anzeigen von Diagrammen vor.
 *
 * @author Sophie Bräuniger
 */
public class VMResult {

    private String name;
    private VMConfiguration vmConfig;

    /*
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VMConfiguration getVmConfig() {
        return vmConfig;
    }

    public void setVmConfig(VMConfiguration vmConfig) {
        this.vmConfig = vmConfig;
    }
}
