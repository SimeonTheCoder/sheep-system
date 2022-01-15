package core.listeners;

import core.ui.WindowImpl;
import entities.sheep.interfaces.Sheep;
import repositories.interfaces.Repository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SheerListener implements ActionListener {
    private Repository<Sheep> sheepRepository;

    private JTextField sheepId;

    private int time;

    public SheerListener(Repository<Sheep> sheepRepository, JTextField sheepId, int time) {
        this.sheepRepository = sheepRepository;

        this.sheepId = sheepId;

        this.time = time;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Sheep sheep = sheepRepository.getById(sheepId.getText());

        int collected = sheep.getWool(time);

        WindowImpl.WOOL_BUFFER = collected;
    }
}
