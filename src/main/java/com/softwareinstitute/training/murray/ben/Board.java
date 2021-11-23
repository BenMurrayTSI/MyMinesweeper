package com.softwareinstitute.training.murray.ben;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private int height;
    private int width;
    private int mineAmount;
    private String[][] gameBoard;
    private ArrayList mines;
    private int[][] minePositions;

    public Board(int height, int width, int mineAmount) {
        this.height = height;
        this.width = width;
        this.mineAmount = mineAmount;

        generate();
    }

    private void generate() {
        generateBoard();
        generateMines();
        placeMines();
        addValues();
        printMinePositions();
        printBackBoard();
    }

    //GENERATE BOARD
    private void generateBoard() {
        this.gameBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j] = "0";
            }
        }
        printBackBoard();
    }



    //PRINT BACK BOARD
    private void printBackBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("  " + gameBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }



    //GENERATE MINES
    private void generateMines() {
        ArrayList numbers = new ArrayList();
        this.mines = new ArrayList();

        for (int i = 0; i < height * width; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);
        System.out.println();
        Collections.shuffle(numbers);
        for (int j = 0; j < mineAmount; j++) {
            mines.add(numbers.get(j));
        }
        System.out.println(mines);
        System.out.println();
    }



    //PLACE MINES
    private void placeMines() {
        this.minePositions = new int[2][mineAmount];

        for (int i = 0; i < mineAmount; i++) {
            int mine = (int) mines.get(i);

            int mineRow = (int) Math.floor(mine / width);
            int mineColumn = mine % width;

            minePositions[0][i] = mineRow;
            minePositions[1][i] = mineColumn;

            gameBoard[mineRow][mineColumn] = "X";
        }
        printBackBoard();
    }



    //PRINT MINE POSITIONS
    private void printMinePositions() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < mineAmount; j++) {
                System.out.printf("  " + minePositions[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //ADD VALUES
    private void addValues() {
        for (int k = 0; k < height; k++) {
            for (int l = 0; l < width; l++) {

                int tileRow = k;
                int tileColumn = l;
                String tileValue = gameBoard[tileRow][tileColumn];
                int setTileValue = 0;

                if (tileValue == "X") {

                } else {
                    for (int i = tileRow - 1; i <= tileRow + 1; i++) {
                        for (int j = tileColumn - 1; j <= tileColumn + 1; j++) {
                            try {
                                if (gameBoard[i][j] == "X") {
                                    setTileValue++;
                                    gameBoard[tileRow][tileColumn] = Integer.toString(setTileValue);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }
}
