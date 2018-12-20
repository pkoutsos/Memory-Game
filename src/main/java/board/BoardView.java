package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoardView extends JComponent implements ActionListener {

    private int rows;
    private int cols;
    private static final Color backgroundColor = new Color(200, 226, 206);

    private board.Card cardA;
    private board.Card cardB;

    private int matches;

    private board.Board board;
    private board.CardView cardView;

    public BoardView(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        init();

    }

    public void init(){

        removeAll();

        board = new board.Board(rows, cols);
        board.createBoard();

        matches = 0;

        cardView = new board.CardView(board);

        setLayout(new GridLayout(rows, cols, 10, 10));
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                add(board.getBoard()[x][y]);
                board.getBoard()[x][y].addActionListener(this);
            }
        }
        displayBoard();

    }
        public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        }


    public void displayBoard() {

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                cardView.assignCards(x, y);
            }
        }
    }



    private board.Card getClickedCard(Point point) {

        return board.getBoard()[point.x][point.y];
    }

    private Point getCardLocation(board.Card card) {
        Point p = new Point();

        for (int column = 0; column < rows; column++) {
            for (int row = 0; row < cols; row++) {
                if (board.getBoard()[column][row] == card) {
                    p.setLocation(column, row);
                    return p;
                }
            }
        }
        return null;
    }

    private void flipCard(int x, int y) {
        board.getBoard()[x][y].setViewing(true);
        displayBoard();
    }

    private void hideCard(int x, int y) {
        board.getBoard()[x][y].setHidden(true);
        board.getBoard()[x][y].setViewing(false);
    }
    private void matchCard(int x, int y) {
        board.getBoard()[x][y].setHidden(false);
        board.getBoard()[x][y].setViewing(false);
        board.getBoard()[x][y].setMatched(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point pointA = getCardLocation((board.Card)e.getSource());
        Point pointB;
       if(cardA == null){
           cardA = getClickedCard(pointA);
           pointA = getCardLocation(cardA);
           flipCard(pointA.x, pointA.y );

       }
       if(cardA != null){
           pointB = getCardLocation((board.Card)e.getSource());
           cardB = getClickedCard(pointB);
           pointB = getCardLocation(cardB);
            if(!cardA.equals(cardB)){
                flipCard(pointB.x, pointB.y );
             
                delay();
                pointA = getCardLocation(cardA);
                if(cardA.getName().equals(cardB.getName())){
                    matchCard(pointA.x, pointA.y);
                    matchCard(pointB.x, pointB.y);
                    matches++;
                }
                hideCard(pointA.x, pointA.y);
                hideCard(pointB.x, pointB.y);
                cardA = null;
                cardB = null;
            }

            checkTotalMatches();

       }
    }

    private void delay() {

        Action displayBoardAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                displayBoard();
            }
        };

        Timer timer = new Timer(2500, displayBoardAction);
        timer.setRepeats(false);
        timer.start();
    }

    public void checkTotalMatches() {
        if (isWon()) {
            delay();
            endGameQuery();
        }
    }

    public void endGameQuery() {

        int userAnswer;

        userAnswer = JOptionPane.showConfirmDialog(null,
                "Would you like to play again?",
                "You have a great memory.",
                JOptionPane.YES_NO_OPTION);

        if(userAnswer == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Thank you for gameViewing!");
            System.exit(0);
        } else if(userAnswer == JOptionPane.YES_OPTION) {
            resetBoard();
        }

    }


    private void resetBoard() {
        init();
    }

    public boolean isWon(){

        return matches == board.getMatches();
    }
    
    public void resizeBoard(int size) {
        rows = size;
        cols = size;
        
        resetBoard();
        
    }
}




