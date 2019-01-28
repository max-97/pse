package de.sswis.controller.handlers;

import de.sswis.view.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeHandler implements ActionListener {

    private AbstractView view;

    public ChangeHandler(AbstractView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.view.update();
    }
}
