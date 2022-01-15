package core.ui;

import core.factories.SheepFactory;
import core.factories.interfaces.Factory;
import core.listeners.AddSheepListener;
import core.listeners.ButtonListener;
import core.listeners.SheerListener;
import core.ui.interfaces.Window;
import entities.sheep.interfaces.Sheep;
import repositories.SheepRepository;
import repositories.interfaces.Repository;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class WindowImpl extends JPanel implements Window{
    public static int ACTION_ID;

    public static Sheep SHEEP_BUFFER;
    public static int WOOL_BUFFER;

    private int totalWool;

    private JLabel timeLabel;
    private JLabel woolLabel;

    private TextArea textArea;

    public static int time;

    private Repository<Sheep> sheepRepository;
    private JFrame window;

    private JFrame sheepBar;
    private JFrame timeBar;
    private JFrame woolBar;

    public WindowImpl() {
        sheepRepository = new SheepRepository();

        totalWool = 0;
        time = 0;

        ACTION_ID = 0;

        SHEEP_BUFFER = null;
        WOOL_BUFFER = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(ACTION_ID != 0) {
            handle(ACTION_ID);

            ACTION_ID = 0;
            SHEEP_BUFFER = null;
        }

        if(SHEEP_BUFFER != null) {
            System.out.println("SHEEP ADDED TO REPOSITORY!!!");

            sheepRepository.add(SHEEP_BUFFER);

            SHEEP_BUFFER = null;

            saveSheepDatabase();
        }

        if(WOOL_BUFFER != 0) {
            System.out.println("WOOD ADDED TO STORAGE");

            totalWool += WOOL_BUFFER;

            WOOL_BUFFER = 0;

            setWool();
        }

        timeLabel.setText("TIME [day]: " + time);
        woolLabel.setText("WOOL [kg]: " + totalWool);

        String data = "";

        for (Sheep entity : sheepRepository.getEntities()) {
            data += "Id: ";
            data += entity.getId();
            data += " Class: ";
            data += entity.getClass().getSimpleName();
            data += " Can Sheer: " + (time % entity.getPeriod() == 0 ? "true" : "false");
            data += "\n";
        }

        textArea.setText(data);

        repaint();
    }

    @Override
    public void show(String name, int sizeX, int sizeY, boolean canResize, boolean exitOperationStopProcess) {
        getTick();
        getWool();

        loadSheepDatabase();

        window = new JFrame(name);

        window.setSize(sizeX, sizeY);

        window.setResizable(canResize);

        if(exitOperationStopProcess) {
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

        timeLabel = new JLabel("TIME [day]: ");
        woolLabel = new JLabel("WOOL [kg]: ");

        textArea = new TextArea();

        sheepBar = new JFrame("Sheep");
        sheepBar.setSize(500, 100);

        JPanel sheepPanel = new JPanel();

        timeBar = new JFrame("Time");
        timeBar.setSize(500, 100);

        JPanel timePanel = new JPanel();

        woolBar = new JFrame("Wool");
        woolBar.setSize(500, 100);

        JPanel woolPanel = new JPanel();

        JButton addSheepBTN = new JButton("Add Sheep");
        addSheepBTN.addActionListener(new ButtonListener(1));

        JButton sheerSheepBTN = new JButton("Sheer Sheep");
        sheerSheepBTN.addActionListener(new ButtonListener(2));

        JButton tickButton = new JButton("Tick");
        tickButton.addActionListener(new ButtonListener(3));

        JButton clearTimeData = new JButton("Clear Time Data");
        clearTimeData.addActionListener(new ButtonListener(4));

        JButton clearDatabase = new JButton("Clear Sheep Database");
        clearDatabase.addActionListener(new ButtonListener(5));

        JButton sellWool = new JButton("Sell");
        sellWool.addActionListener(new ButtonListener(6));

        sheepPanel.add(addSheepBTN);
        sheepPanel.add(sheerSheepBTN);
        timePanel.add(tickButton);
        timePanel.add(clearTimeData);
        sheepPanel.add(clearDatabase);
        woolPanel.add(sellWool);

        sheepBar.add(sheepPanel);
        timeBar.add(timePanel);
        woolBar.add(woolPanel);

        JButton sheepButton = new JButton("Sheep");
        sheepButton.addActionListener(new ButtonListener(7));

        JButton timeButton = new JButton("Time");
        timeButton.addActionListener(new ButtonListener(8));

        JButton woolButton = new JButton("Wool");
        woolButton.addActionListener(new ButtonListener(9));

        this.add(sheepButton);
        this.add(timeButton);
        this.add(woolButton);

        JToolBar textBar = new JToolBar();
        textBar.setName("Database");

        textBar.add(textArea);

        this.add(textBar);

        this.add(timeLabel);
        this.add(woolLabel);

        window.add(this);

        window.setVisible(true);
    }

    private JFrame addSheepFrame() {
        JFrame sheepFrame = new JFrame("Add Sheep");

        sheepFrame.setSize(500, 500);
        sheepFrame.setResizable(false);

        sheepFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JTextField sheepModel = new JTextField(10);
        JTextField sheepId = new JTextField(10);

        JButton createSheep = new JButton("Add Sheep");

        createSheep.addActionListener(new AddSheepListener(sheepModel, sheepId));

        JLabel textModel = new JLabel("Sheep Model: ");
        JLabel textId = new JLabel("Sheep Id: ");

        panel.add(textModel);
        panel.add(sheepModel);

        panel.add(textId);
        panel.add(sheepId);

        panel.add(createSheep);

        sheepFrame.add(panel);

        return sheepFrame;
    }

    private JFrame sheerSheepFrame() {
        JFrame sheerSheepFrame = new JFrame("Sheer Sheep");

        sheerSheepFrame.setSize(500, 500);

        sheerSheepFrame.setResizable(false);

        sheerSheepFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JTextField idField = new JTextField(10);

        JButton sheerSheep = new JButton("Sheer Sheep");
        sheerSheep.addActionListener(new SheerListener(sheepRepository, idField,
                time));

        JLabel textId = new JLabel("Sheep Id: ");

        panel.add(textId);
        panel.add(idField);

        panel.add(sheerSheep);

        sheerSheepFrame.add(panel);

        return sheerSheepFrame;
    }

    @Override
    public void handle(int id) {
        System.out.println(id);

        switch (id) {
            case 1:
                JFrame addSheepFrame = addSheepFrame();

                addSheepFrame.setVisible(true);

                break;

            case 2:
                JFrame sheerSheepFrame = sheerSheepFrame();

                sheerSheepFrame.setVisible(true);

                break;

            case 3:
                time ++;
                setTick();

                ACTION_ID = 0;

                break;

            case 4:
                time = 0;
                setTick();

                ACTION_ID = 0;

                break;

            case 5:
                sheepRepository.getEntities().clear();

                saveSheepDatabase();

                break;

            case 6:
                totalWool = 0;

                setWool();

                break;
            case 7:
                sheepBar.setVisible(true);

                break;

            case 8:
                timeBar.setVisible(true);

                break;

            case 9:
                woolBar.setVisible(true);

                break;
        }
    }

    private void getTick() {
        try {
            File file = new File("data/time.txt");

            Scanner fileScanner = new Scanner(file);

            time = Integer.parseInt(fileScanner.nextLine());
        }catch (Exception exception) {

        }
    }

    private void setTick(){
        try {
            File file = new File("data/time.txt");

            FileWriter writer = new FileWriter(file);

            writer.write(String.valueOf(time));

            writer.close();
        }catch (Exception exception) {

        }
    }

    private void getWool() {
        try {
            File file = new File("data/storage.txt");

            Scanner fileScanner = new Scanner(file);

            totalWool = Integer.parseInt(fileScanner.nextLine());
        }catch (Exception exception) {

        }
    }

    private void setWool(){
        try {
            File file = new File("data/storage.txt");

            FileWriter writer = new FileWriter(file);

            writer.write(String.valueOf(totalWool));

            writer.close();
        }catch (Exception exception) {

        }
    }

    private void saveSheepDatabase() {
        try {
            File file = new File("data/entities.txt");

            FileWriter writer = new FileWriter(file);

            for (Sheep entity : sheepRepository.getEntities()) {
                writer.write(entity.getClass().getSimpleName() + " " + entity.getId() + "\n");
            }

            writer.close();
        }catch (Exception exception) {

        }
    }

    private void loadSheepDatabase() {
        try{
            File file = new File("data/entities.txt");

            Scanner database = new Scanner(file);

            while(database.hasNextLine()) {
                String line = database.nextLine();

                String[] content = line.split(" ");

                Factory<Sheep> sheepFactory = new SheepFactory();

                sheepRepository.add(sheepFactory.create(content));
            }
        }catch (Exception exception) {

        }
    }
}
