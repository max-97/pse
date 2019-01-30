package de.sswis.view.model;

import java.util.HashMap;
import java.util.List;

/**
 * Konfigurations Daten, die alle nötigen Parameter zum Erzeugen einer {@code Configuration} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMConfiguration {


    private String name;

    private String game;
    private String init;
    private String adaptationAlg;
    private String pairingAlg;
    private String rankingAlg;
    private String rounds;
    private String cycles;
    private String adaptationProbability;
    private List<String> strategies;

    private VMResult result;

    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: mehrere variable Parameter, illegale Eingaben.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Zeigt ob die Konfiguration variable Parameter enthält.
     * @return true wenn es genau einen variablen Parameter gibt, sonst false
     */
    public boolean isMultiConfiguration() { return false; }

    /**
     *Zeigt ob Ergebnisse einer Simulation vorliegen, die mit dieser Konfiguration ausgeführt wurde.
     *
     * @return true wenn es Ergebnisse von Simulationen mit dieser Konfiguration gibt, sonst false
     */
    public boolean hasResult () {
        //TODO: implement me
        return false;
    }

    /**
     * Gibt eine String der wichtige Informationen zu dieser Konfiguration zusammenfasst.
     * @return String enthält Kurzbeschreibung der Konfiguration
     */
    public String getToolTipText() {    return ""; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public String getAdaptationAlg() {
        return adaptationAlg;
    }

    public void setAdaptationAlg(String adaptationAlg) {
        this.adaptationAlg = adaptationAlg;
    }

    public String getPairingAlg() {
        return pairingAlg;
    }

    public void setPairingAlg(String pairingAlg) {
        this.pairingAlg = pairingAlg;
    }

    public String getRankingAlg() {
        return rankingAlg;
    }

    public void setRankingAlg(String rankingAlg) {
        this.rankingAlg = rankingAlg;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public String getCycles() {
        return cycles;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public String getAdaptationProbability() {
        return adaptationProbability;
    }

    public void setAdaptationProbability(String adaptationProbability) {
        this.adaptationProbability = adaptationProbability;
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<String> strategies) {
        this.strategies = strategies;
    }

    public void addStrategy(String strategy) {
        this.strategies.add(strategy);
    }

    public void removeStrategy(String strategy) {
        this.strategies.remove(strategy);
    }

    public VMResult getResult() {
        return result;
    }

    public void setResult(VMResult result) {
        this.result = result;
    }

    public HashMap<String, Object> getAdaptationParameters() {
        return null;
    }

    public HashMap<String, Object> getPairingParameters() {
        return null;
    }

    public HashMap<String, Object> getRankingParameters() {
        return null;
    }
}
