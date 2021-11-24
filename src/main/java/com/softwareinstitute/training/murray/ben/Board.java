package com.softwareinstitute.training.murray.ben;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
    private final int height;
    private final int width;
    private final int mineAmount;
    private String[][] gameBackBoard;
    private String[][] gameFrontBoard;
    private String[][] gameDoneBoard;
    private ArrayList<Integer> mines;
    private int[][] minePositions;
    String mineSymbol = ".";
    String hiddenSymbol = "-";
    String clearSymbol = "0";
    boolean dead = false;
    boolean done = false;

    public Board(int height, int width, int mineAmount) {
        this.height = height;
        this.width = width;
        this.mineAmount = mineAmount;

        generateBackBoard();
    }


    //GENERATE DONE BOARD
    private void generateDoneBoard() {
        this.gameDoneBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameDoneBoard[i][j] = gameBackBoard[i][j];
            }
        }

        for (int i = 0; i < mineAmount; i++) {
            gameDoneBoard[minePositions[0][i]][minePositions[1][i]] = hiddenSymbol;
        }
    }


    //PRINT DONE BOARD
    public void printDoneBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("  " + gameDoneBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //GENERATE BACK BOARD
    private void generateBackBoard() {
        generateClearBackBoard();
        //printBackBoard();
        generateMines();
        placeBackBoardMines();
        //printBackBoard();
        addInitialValues();
        //printMinePositions();
        printBackBoard();
        generateDoneBoard();
        //printDoneBoard();
    }


    //GENERATE CLEAR BACK BOARD
    private void generateClearBackBoard() {
        this.gameBackBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBackBoard[i][j] = clearSymbol;
            }
        }
    }


    //PRINT BACK BOARD
    public void printBackBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("  " + gameBackBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //GENERATE MINES
    private void generateMines() {
        ArrayList<Integer> numbers = new ArrayList<>();
        this.mines = new ArrayList<>();

        for (int i = 0; i < height * width; i++) {
            numbers.add(i);
        }
        //System.out.println(numbers);
        //System.out.println();
        Collections.shuffle(numbers);
        for (int j = 0; j < mineAmount; j++) {
            mines.add(numbers.get(j));
        }
        //System.out.println(mines);
        //System.out.println();
    }


    //PLACE BACK BOARD MINES
    private void placeBackBoardMines() {
        this.minePositions = new int[2][mineAmount];

        for (int i = 0; i < mineAmount; i++) {
            int mine = (int) mines.get(i);

            int mineRow = (int) Math.floor(mine / width);
            int mineColumn = mine % width;

            minePositions[0][i] = mineRow;
            minePositions[1][i] = mineColumn;

            gameBackBoard[mineRow][mineColumn] = mineSymbol;
        }
    }


    //PRINT MINE POSITIONS
    public void printMinePositions() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < mineAmount; j++) {
                System.out.print("  " + minePositions[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //ADD INITIAL VALUES
    private void addInitialValues() {
        for (int tileRow = 0; tileRow < height; tileRow++) {
            for (int tileColumn = 0; tileColumn < width; tileColumn++) {

                String tileValue = gameBackBoard[tileRow][tileColumn];
                int setTileValue = 0;

                if (!tileValue.equals(mineSymbol)) {
                    for (int i = tileRow - 1; i <= tileRow + 1; i++) {
                        for (int j = tileColumn - 1; j <= tileColumn + 1; j++) {
                            try {
                                if (gameBackBoard[i][j].equals(mineSymbol)) {
                                    setTileValue++;
                                    gameBackBoard[tileRow][tileColumn] = Integer.toString(setTileValue);
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                }
            }
        }
    }


    //GENERATE FRONT BOARD
    public void generateFrontBoard() {
        generateClearFrontBoard();
    }


    //GENERATE CLEAR FRONT BOARD
    private void generateClearFrontBoard() {
        this.gameFrontBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameFrontBoard[i][j] = hiddenSymbol;
            }
        }
    }


    //PRINT FRONT BOARD
    public void printFrontBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("  " + gameFrontBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //EVALUATE SELECTED TILE
    public void evaluateTile(int selectedRow, int selectedColumn) {
        if (gameBackBoard[selectedRow][selectedColumn].equals(mineSymbol)) {
            this.dead = true;
        } else if (gameBackBoard[selectedRow][selectedColumn].equals(clearSymbol)) {

            gameFrontBoard[selectedRow][selectedColumn] = gameBackBoard[selectedRow][selectedColumn];
            String[][] oldGameFrontBoard = new String[height][width];

            while (!Arrays.deepEquals(oldGameFrontBoard, gameFrontBoard)) {


                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        oldGameFrontBoard[i][j] = gameFrontBoard[i][j];
                    }
                }




                for (int tileRow = 0; tileRow < height; tileRow++) {
                    for (int tileColumn = 0; tileColumn < width; tileColumn++) {

                        String frontTileValue = gameFrontBoard[tileRow][tileColumn];

                        if (frontTileValue.equals(clearSymbol)) {
                            for (int i = tileRow - 1; i <= tileRow + 1; i++) {
                                for (int j = tileColumn - 1; j <= tileColumn + 1; j++) {
                                    try {
                                        gameFrontBoard[i][j] = gameBackBoard[i][j];

                                    } catch (ArrayIndexOutOfBoundsException ignored) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            gameFrontBoard[selectedRow][selectedColumn] = gameBackBoard[selectedRow][selectedColumn];
        }

        if (Arrays.deepEquals(gameFrontBoard, gameDoneBoard)) {
            this.done = true;
        }

    }


    //RETURN IF DEAD
    public boolean isDead() {
        return dead;
    }

    public boolean isDone() {
        return done;
    }
}
