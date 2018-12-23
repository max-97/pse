package de.sswis.view.model;

import java.util.List;

/**
 * Initialisierungs Daten, die alle nötigen Parameter zum Erzeugen einer {@code Initialization} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMInitialization {

    private String name;
    private List<VMGroup> groups;
    private int agentCount;

    /**
     * @return
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }


    /**
     * @return
     */
    public String getToolTipText() {    return ""; }



}


