package ui;

//sourced from CPSC 210 Edx

import network.ReadWebPage;
import weather.TodayWeather;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Gui extends JFrame implements ActionListener {
    private JLabel label1;
    private JTextField field;
    private ArrayList<String> clothesRecommendations;
    private TodayWeather todayWeather;
    private ReadWebPage readWebPage;
    private String temperature;
    private String greatDay = "./data/greatDay.wav";
    private String jazz = "./data/jazz.wav";
    private Gui prevGui;
    private Clip clip;
    private int roundCounter = 0;


    public Gui(String title, String prompt) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Enter");
        btn.setFont(new Font(Font.SERIF,  Font.PLAIN, 15));
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        btn.setBackground(Color.BLACK); //sourced from: https://stackoverflow.com/questions/4172940/how-to-set-background-color-of-a-button-in-java-gui
        btn.setForeground(Color.DARK_GRAY); //sourced from: https://stackoverflow.com/questions/4172940/how-to-set-background-color-of-a-button-in-java-gui
        label1 = new JLabel();
        label1.setForeground(Color.DARK_GRAY);
        label1.setFont(new Font(Font.SERIF,  Font.PLAIN, 15));
        field = new JTextField(32);
        field.setForeground(Color.DARK_GRAY);
        field.setFont(new Font(Font.SERIF,  Font.PLAIN, 15));
        field.setText(prompt);
        add(field);
        add(btn);
        add(label1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY); //sourced from: https://stackoverflow.com/questions/1081486/setting-background-color-for-the-jframe
        createBorder();
    }

    //MODIFIES: this
    //EFFECTS: produces visual of clothing recommendation when enter button is pressed
    public void actionPerformed(ActionEvent e) {
        clothesRecommendations = new ArrayList<>();
        readWebPage = new ReadWebPage();
        stopMusic();
        getTemperature();
        todayWeather = new TodayWeather(temperature);
        clothesRecommendations = todayWeather.getClothesRecommendation();
        if (e.getActionCommand().equals("myButton")) {
            roundCounter++;
            if (field.getText().equals("yes")) {
                label1.setText(displayString(temperature));
                playMusic(greatDay);

            } else {
                label1.setText("No recommendation needed. Start off your day right with some smooth jazz!");
                playMusic(jazz);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: updates temperature variable with weather data
    public void getTemperature() {
        try {
            temperature = readWebPage.gatherWeather();
        } catch (Exception ee) {
            System.out.println("My custom error message.");
        }
    }


    //EFFECTS: produces sound effect when button is pressed
    public void playMusic(String soundName) {
        try {
            AudioInputStream audioInStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInStream);
            clip.start();
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
    }

    //MODIFIES: this
    //EFFECTS: stops music when enter button is pressed
    public void stopMusic() {
        if (roundCounter != 0) {
            clip.stop();
            prevGui = this;
        }

    }

    //EFFECTS: return string to display to user
    public String displayString(String temp) {
        return "<html>Today it is " + temp + " in Vancouver. <br><br>"
                + "My clothing recommendation is:<br> "
                + clothesRecommendations.get(0) + " with "
                + clothesRecommendations.get(1)
                + "<br><br>Hit Enter for a different recommendation.";
    }

    //MODIFIES: this
    //EFFECTS: create border for window display
    public void createBorder() { //sourced from HTML Demo (provided on CPSC 210 Edx)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(String.valueOf(java.time.LocalDate.now())), //sourced from: https://www.javatpoint.com/java-get-current-date
                BorderFactory.createEmptyBorder(20,20,20,20)));
        rightPanel.add(label1);
        rightPanel.setFont(new Font(Font.SERIF,  Font.PLAIN, 15));
        rightPanel.setForeground(Color.DARK_GRAY);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);
    }

}
