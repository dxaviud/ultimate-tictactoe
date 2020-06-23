

import java.util.Scanner;
import Marks.*;
import javax.swing.JFrame;

public class Game {

    private GlobalBoard globalBoard;
    private int turn;
    private String currentPlayer;
    private LocalBoard currentBoard;

    public Game() {
        globalBoard = new GlobalBoard();
        turn = 1;
        currentPlayer = "X";
        currentBoard = null;
    }

    public void runGame() {
        JFrame GameFrame = new JFrame();
        GameFrame.setTitle("Ultimate TicTacToe");
        GameFrame.setResizable(false);
        GameFrame.setVisible(true);
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true) {

            displayBoard();

            System.out.println("Turn " + turn + ": " + currentPlayer + " plays.");

            if (turn == 1 || currentBoard.boardIsFull()) {
                pickBoard();
            }

            playerMakesMark();

            globalBoard.updateLocalBoards();

            switchCurrentPlayer();

            turn++;
            
            if (globalBoard.gameOver()) {
                displayBoard();
                break;
            }
        }

        //Game over message
        System.out.println("\n");
        System.out.println("Game over. Winner: " + globalBoard.getWinner());
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
        int rowInd = -1;
        int colInd = -1;
        int[] possibleInputs = {0,1,2};
        System.out.println("Pick a local board using row and column. Possible inputs: " + getStringArray(possibleInputs));
        while(true) {
            rowInd = getPlayerInput("Enter row index: ", possibleInputs);
            colInd = getPlayerInput("Enter col index: ", possibleInputs);
            if (!globalBoard.getLocalBoard(rowInd, colInd).boardIsFull()) {
                break;
            } else {
                System.out.println("Must pick board that is not full.");
            }
        }
        if (turn > 1) {
            currentBoard.setIsNotCurrentBoard();
        }
        currentBoard = globalBoard.getLocalBoard(rowInd, colInd);
        currentBoard.setIsCurrentBoard();
    }

    public void makeGeneralMark(Mark mark) {
        int rowInd = -1;
        int colInd = -1;
        int[] possibleInputs = {0,1,2};
        System.out.println("Pick a box using row and column. Possible inputs: " + getStringArray(possibleInputs));
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
        currentBoard.setIsNotCurrentBoard();
        currentBoard = globalBoard.getLocalBoard(rowInd, colInd);
        currentBoard.setIsCurrentBoard();
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
        globalBoard.display();
    }
}