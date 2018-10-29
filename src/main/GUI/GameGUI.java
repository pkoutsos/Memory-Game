package main.GUI;

import main.CardsBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameGUI extends JFrame {

    private JPanel gridPanel = new JPanel();
    private GameView gameView = new GameView();
    private int rows = 6;
    private int cols = 6;

    public GameGUI() {
        gameView.setRows(rows);
        gameView.setCols(cols);
        setTitle("MEMORY GAME");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(340, 90);

        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        gridPanel.setBorder(border);
        gridPanel.setLayout(new BorderLayout(2, 1));

        gridPanel.add(gameView, BorderLayout.CENTER);

        add(gridPanel);
    }

    public static void main(String[] args) {
        new GameGUI().setVisible(true);
    }
}
