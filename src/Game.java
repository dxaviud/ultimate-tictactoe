package src;

import ultimatetictactoe.boards.*;
import ultimatetictactoe.marks.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Game {

    private GlobalBoard globalBoard;
    private int turn;
    private String currentPlayer;
    private LocalBoard currentBoard;
    private Mark clickedMark;

    public Game() {
        globalBoard = new GlobalBoard();
        turn = 1;
        currentPlayer = "X";
        currentBoard = null;
    }

    public void runGame() {

        final int WIDTH = 576;
        final int HEIGHT = 576;

        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Ultimate TicTacToe");
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        GUIBoard gui = new GUIBoard(WIDTH, HEIGHT, this);
        gui.setSize(WIDTH, HEIGHT);
        

        class MakeMarkListener implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				
			}
	
			public void mousePressed(MouseEvent e) {
                int xClick = e.getX();
                int yClick = e.getY();
                setClickedMark(xClick, yClick);
			}
		
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
	
			public void mouseExited(MouseEvent e) {
                
            }

            private void setClickedMark(int xClick, int yClick) {
                for (int x = 0; x < globalBoard.width; x++) {
                    for (int y = 0; y < globalBoard.length; y++) {
                        if (turn == 1 || globalBoard.getLocalBoard(y,x) == currentBoard || currentBoard.boardIsFull()) {
                            for (int x2 = 0; x2 < globalBoard.width; x2++) {
                                for (int y2 = 0; y2 < globalBoard.length; y2++) {
                                    int xMin = x*WIDTH/3 + x2*WIDTH/9;
                                    int xMax = x*WIDTH/3 + x2*WIDTH/9 + WIDTH/9;
                                    int yMin = y*HEIGHT/3 + y2*HEIGHT/9;
                                    int yMax = y*HEIGHT/3 + y2*HEIGHT/9 + HEIGHT/9;
                                    if (xClick >= xMin && xClick <= xMax && yClick >= yMin && yClick <= yMax) {
                                        if (!(globalBoard.getLocalBoard(y, x).getBox(y2, x2) instanceof NoMark)) {
                                            clickedMark = null;
                                            System.out.println("null");
                                        } else {
                                            clickedMark = globalBoard.getLocalBoard(y, x).getBox(y2, x2);
                                            currentBoard = globalBoard.getLocalBoard(y, x);
                                            System.out.println("clicked mark: " + clickedMark);
                                        }
                                        return;
                                    }
                                }
                            }
                        } 
                        
                    }
                }
            }
        }
        gui.addMouseListener(new MakeMarkListener());
        gui.setVisible(true);

        gameFrame.add(gui);
        gameFrame.pack();

        while(true) {

            displayBoard();
            gui.repaint();

            System.out.println("Turn " + turn + ": " + currentPlayer + " plays.");

            // if (turn == 1 || currentBoard.boardIsFull()) {
            //     pickBoard();
            // }

            playerMakesMark();

            globalBoard.updateLocalBoards();

            switchCurrentPlayer();

            turn++;
            
            if (globalBoard.gameOver()) {
                displayBoard();
                gui.repaint();
                break;
            }
        }

        System.out.println("\n");
        System.out.println("Game over. Winner: " + globalBoard.getWinner());
    }


    private void switchCurrentPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    private void makeXMark() {
        makeGeneralMark(new XMark(clickedMark.getRowIndex(), clickedMark.getColIndex()));
    }

    private void makeOMark() {
        makeGeneralMark(new OMark(clickedMark.getRowIndex(), clickedMark.getColIndex()));
    }

    // public void pickBoard() {
    //     int rowInd = -1;
    //     int colInd = -1;
    //     int[] possibleInputs = {0,1,2};
    //     System.out.println("Pick a local board using row and column. Possible inputs: " + getStringArray(possibleInputs));
    //     while(true) {
    //         rowInd = getPlayerInput("Enter row index: ", possibleInputs);
    //         colInd = getPlayerInput("Enter col index: ", possibleInputs);
    //         if (!globalBoard.getLocalBoard(rowInd, colInd).boardIsFull()) {
    //             break;
    //         } else {
    //             System.out.println("Must pick board that is not full.");
    //         }
    //     }
    //     if (turn > 1) {
    //         currentBoard.setIsNotCurrentBoard();
    //     }
    //     currentBoard = globalBoard.getLocalBoard(rowInd, colInd);
    //     currentBoard.setIsCurrentBoard();
    // }

    // public void makeGeneralMark(Mark mark) {
    //     int rowInd = -1;
    //     int colInd = -1;
    //     int[] possibleInputs = {0,1,2};
    //     System.out.println("Pick a box using row and column. Possible inputs: " + getStringArray(possibleInputs));
    //     while(true) {
    //         rowInd = getPlayerInput("Enter row index: ", possibleInputs);
    //         colInd = getPlayerInput("Enter col index: ", possibleInputs);
    //         if (currentBoard.getBox(rowInd, colInd) instanceof NoMark) {
    //             break;
    //         } else {
    //             System.out.println("Must pick box that is not already marked.");
    //         }
    //     }
    //     currentBoard.setBox(rowInd, colInd, mark);
    //     currentBoard.setIsNotCurrentBoard();
    //     currentBoard = globalBoard.getLocalBoard(rowInd, colInd);
    //     currentBoard.setIsCurrentBoard();
    // }

    private void makeGeneralMark(Mark mark) {
        
        int row = clickedMark.getRowIndex();
        int col = clickedMark.getColIndex();
        currentBoard.setBox(row, col, mark);
        currentBoard.setIsNotCurrentBoard();
        currentBoard = globalBoard.getLocalBoard(row, col);
        currentBoard.setIsCurrentBoard();
        clickedMark = null;
    }

    // public int getPlayerInput(String prompt, int[] possibleInputs) {
    //     Scanner in = new Scanner(System.in);
    //     int input = -1;
    //     while(true) {
    //         try {
    //             System.out.print(prompt);
    //             input = Integer.parseInt(in.next());
    //             if (!linearSearch(possibleInputs, input)) {
    //                 System.out.println("Possible inputs: " + getStringArray(possibleInputs));
    //                 continue;
    //             } else {
    //                 return input;
    //             }
    //         } catch (NumberFormatException e) {
    //             System.out.println("INVALID INPUT");
    //         }
    //     }
    // }

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

    private void playerMakesMark() {
        while(clickedMark == null) {
            System.out.println("waiting for click");
        }
        if (turn % 2 == 0) {
            makeOMark();
        } else {
            makeXMark();
        }
    }

    private void displayBoard() {
        globalBoard.display();
    }

    public GlobalBoard getGlobalBoard() {
        return globalBoard;
    }
}
