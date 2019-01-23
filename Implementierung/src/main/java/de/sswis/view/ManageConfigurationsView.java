package de.sswis.view;

import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *Ein Fenster zum Verwalten von Konfigurationen.
 *
 * @author Sophie Bräuniger
 */
public class ManageConfigurationsView implements AbstractManageConfigurationsView {

    private List<VMConfiguration> vmConfigurations;
    private AbstractMainView parentView;


    @Override
    public void addConfiguration(VMConfiguration configuration) {

    }

    @Override
    public void removeConfiguration(String configName) {

    }

    @Override
    public void addNewConfigurationButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addEditConfigurationButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addDeleteConfigurationButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public VMConfiguration getSelectedVM() {
        return null;
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

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }
}
