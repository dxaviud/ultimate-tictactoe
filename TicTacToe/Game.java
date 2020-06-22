

import java.util.Scanner;
import Marks.*;
import javax.swing.JFrame;

public class TicTacToeGame {

    private TicTacToeBigBoard bigGameBoard;
    private int turn;
    private String currentPlayer;
    private TicTacToeBoard currentBoard;

    public TicTacToeGame() {
        bigGameBoard = new TicTacToeBigBoard();
        turn = 1;
        currentPlayer = "X";
        currentBoard = null;
    }

    public void runGame() {
        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Ultimate TicTacToe");
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true) {

            displayBoard();

            System.out.println("Turn " + turn + ": " + currentPlayer + " plays.");

            if (turn == 1 || currentBoard.boardIsFull()) {
                pickBoard();
            }

            playerMakesMark();

            switchCurrentPlayer();

            turn++;

            if (bigGameBoard.gameOver()) {
                displayBoard();
                break;
            }
        }

        //game over message
        System.out.println("\n");
        System.out.println("Game over. Winner: " + bigGameBoard.getWinner());
    }

    public void switchCurrentPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public void makeXMark() {
        makeGeneralMark(new XMark());
    }

    public void makeOMark() {
        makeGeneralMark(new OMark());
    }

    public void pickBoard() {
        int pick = -1;
        int[] possibleInputs = {1,2,3,4,5,6,7,8,9};
        System.out.println("possibleInputs: " + getStringArray(possibleInputs));
        while(true) {
            pick = getPlayerInput("Enter board number: ", possibleInputs);
            if (bigGameBoard.getBoard(pick).boardIsFull()) {
                System.out.println("That board is full.");
            } else {
                break;
            }
            
        }
        currentBoard = bigGameBoard.getBoard(pick);
    }

    public void makeGeneralMark(Mark mark) {
        int rowInd = -1;
        int colInd = -1;
        int[] possibleInputs = {0,1,2};
        System.out.println("Possible inputs: " + getStringArray(possibleInputs));
        while(true) {
            rowInd = getPlayerInput("Enter row index: ", possibleInputs);
            colInd = getPlayerInput("Enter col index: ", possibleInputs);
            if (currentBoard.getBox(rowInd, colInd) instanceof NoMark) {
                break;
            } else {
                System.out.println("Must pick box that is not already marked.");
            }
        }
        currentBoard.setBox(rowInd, colInd, mark);
    }

    public int getPlayerInput(String prompt, int[] possibleInputs) {
        Scanner in = new Scanner(System.in);
        int input = -1;
        while(true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(in.next());
                if (!linearSearch(possibleInputs, input)) {
                    System.out.println("Possible inputs: " + getStringArray(possibleInputs));
                    continue;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT");
            }
        }
    }

    private String getStringArray(int[] array) {
        String returnString = "";
        for (int num : array) {
            returnString += num + " ";
        }
        return returnString;
    }

    private boolean linearSearch(int[] array, int toFind) {
        for (int num : array) {
            if (toFind == num) {
                return true;
            }
        }
        return false;
    }

    public void playerMakesMark() {
        if (turn % 2 == 0) {
            makeOMark();
        } else {
            makeXMark();
        }
    }

    public void displayBoard() {
        bigGameBoard.display();
    }
}