package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * in Hauptfenster der Benutzeroberfläche.
 *
 * @author Sophie Bräuniger
 */
public class MainView implements AbstractMainView {


    private List<VMConfiguration> configurations;


    private JButton startButton;
    private JButton showResultButton;
    private JTree ConfigurationTree;
    private JButton saveResultButton;
    private JPanel TitelPanel;
    private JPanel ContentPanel;
    private JScrollPane ConfigurationScrollPane;
    private JPanel MainPanel;
    private JLabel ChooseLabel;
    private JLabel TitleLabel;




    @Override
    public void addConfiguration(VMConfiguration configuration) {
    }

    @Override
    public void removeConfiguration(String configurationName) {
    }

    @Override
    public void addResult(String NameConfiguration, VMResult result) {
    }

    @Override
    public void setSimulationFinished(String NameConfiguration) {
    }

    @Override
    public VMConfiguration getSelected() {
        return null;
    }

    @Override
    public void addStartButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addStopActionListener(ActionListener listener) {

    }

    @Override
    public void addShowButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addNewGameMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addNewCombiStrategyMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addNewStrategyMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addNewInitMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addNewConfigMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addManageGameMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addManageCombiStrategyMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addManageStrategyMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addManageInitMenuActionListener(ActionListener listener) {

    }

    @Override
    public void addManageConfigMenuActionListener(ActionListener listener) {

    }

    @Override
    public void update() {
    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here


    }

}
