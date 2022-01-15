package core.listeners;

import core.factories.SheepFactory;
import core.factories.interfaces.Factory;
import core.ui.WindowImpl;
import entities.sheep.interfaces.Sheep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSheepListener implements ActionListener {
    private JTextField modelField;
    private JTextField idField;

    public AddSheepListener(JTextField modelField, JTextField idField) {
        this.modelField = modelField;
        this.idField = idField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Factory<Sheep> factory = new SheepFactory();

        String[] input = new String[2];

        input[0] = "Sheep" + modelField.getText();
        input[1] = idField.getText();

        System.out.println(input[1]);

        WindowImpl.SHEEP_BUFFER = factory.create(input);

        System.out.println("SHEEP CREATED!!!!");
        System.out.println(WindowImpl.SHEEP_BUFFER);
    }
}
