package ultimatetictactoe.boards;

import ultimatetictactoe.Game;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

// import Marks.*;

public class GUIBoard extends Canvas {
    
    private final int WIDTH;
    private final int HEIGHT;
    private Game game;

    public GUIBoard(int width, int height, Game game) {
        WIDTH = width;
        HEIGHT = height;
        this.game = game;
    }
    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        BasicStroke thinStroke = new BasicStroke(1);
        BasicStroke thickStroke = new BasicStroke(5);

        //drawing the board lines
        for (int x = WIDTH/9; x < WIDTH; x += WIDTH/9  ) {
            if (x % (WIDTH/3) == 0) {
                g.setStroke(thickStroke);
            } else {
                g.setStroke(thinStroke);
            }
            g.drawLine(x, 0, x, HEIGHT);
        }
        for (int y = HEIGHT/9; y < HEIGHT; y += HEIGHT/9) {
            if (y % (HEIGHT/3) == 0) {
                g.setStroke(thickStroke);
            } else {
                g.setStroke(thinStroke);
            }
            g.drawLine(0, y, WIDTH, y);
        }

        //draw the Xs and Os
        GlobalBoard board = game.getGlobalBoard();
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int x2 = 0; x2 < board.width; x2++) {
                    for (int y2 = 0; y2 < board.length; y2++) { //small Xs and Os
                        String markType2 = board.getLocalBoard(y, x).getBox(y2,x2).toString();
                        if (markType2.equals("X")) {
                            drawX(g, x*WIDTH/3+x2*WIDTH/9, y*HEIGHT/3+y2*HEIGHT/9, WIDTH/9, Color.BLUE, 3);
                        } else if (markType2.equals("O")) {
                            drawO(g, x*WIDTH/3+x2*WIDTH/9, y*HEIGHT/3+y2*HEIGHT/9, WIDTH/9, Color.RED, 3);
                        }

                    }
                }
                //big Xs and Os
                String markType = board.getLocalBoard(y,x).getWinner();
                int alpha = 100;
                if (markType.equals("X")) {
                    drawX(g, x*WIDTH/3, y*HEIGHT/3, WIDTH/3, new Color(0,0,255,alpha),10);
                } else if (markType.equals("O")) {
                    drawO(g, x*WIDTH/3, y*HEIGHT/3, WIDTH/3, new Color(255,0,0,alpha), 10);
                }
            }
        }

        if (game.getGlobalBoard().gameOver()) {
            g.setColor(new Color(0,0,0, 200));
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Game over. Winner: " + game.getGlobalBoard().getWinner(), 50, 200);
        }
        

    
    }
    private void drawX(Graphics2D g, int x, int y, int size, Color color, int thickness) {
        g.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        g.drawLine(x+3, y+3, x+size-3, y+size-3);
        g.drawLine(x+size-3, y+3, x+3, y+size-3);
        g.setColor(Color.BLACK);
    }

    private void drawO(Graphics2D g, int x, int y, int size, Color color, int thickness) {
        g.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        g.drawOval(x+3, y+3, size-6, size-6);
        g.setColor(Color.BLACK);
    }

    
}