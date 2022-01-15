package core.listeners;

import core.ui.WindowImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private int actionId;

    public ButtonListener(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowImpl.ACTION_ID = actionId;
    }
}
