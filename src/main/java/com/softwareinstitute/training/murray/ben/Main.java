package com.softwareinstitute.training.murray.ben;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //INPUTS
        Scanner scan = new Scanner(System.in);
        System.out.print("Height: ");
        int height = scan.nextInt();

        System.out.print("Width: ");
        int width = scan.nextInt();

        System.out.print("Number Of Mines: ");
        int mineAmount = scan.nextInt();
        System.out.println();



        //CREATE BOARD
        String[][] gameBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j] = "0";
            }
        }



        //PRINT BOARD
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("  " + gameBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();



        //CREATE MINES
        ArrayList numbers = new ArrayList();
        ArrayList mines = new ArrayList();
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



        //PLACE MINES
        int[][] minePositions = new int[2][mineAmount];
        for (int i = 0; i < mineAmount; i++) {
            int mine = (int) mines.get(i);

            int mineRow = (int) Math.floor(mine/width);
            int mineColumn = mine % width;

            minePositions[0][i] = mineRow;
            minePositions[1][i] = mineColumn;

            gameBoard[mineRow][mineColumn] = "X";
        }



        //PRINT MINE POSITIONS
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < mineAmount; j++) {
                System.out.printf("  " + minePositions[i][j]);
            }
            System.out.println();
        }
        System.out.println();



        //PRINT BOARD
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("  " + gameBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();



        //ADD VALUES
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



        //PRINT BOARD
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("  " + gameBoard[i][j]);
            }
            System.out.println();
        }



//        //ADD VALUES
//
//        int tileRow = 0;
//        int tileColumn = 0;
//
//        String tileValue = gameBoard[tileRow][tileColumn];
//        int setTileValue = 0;
//        String newTileValue;
//
//        if (tileValue == "X") {
//            newTileValue = "X";
//
//        } else {
//            for (int i = tileRow - 1; i <= tileRow + 1; i++) {
//                for (int j = tileColumn - 1; j <= tileColumn + 1; j++) {
//                    try {
//                        if (gameBoard[i][j] == "X") {
//                            setTileValue++;
//                        }
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        continue;
//                    }
//                }
//            }
//            newTileValue = Integer.toString(setTileValue);
//        }
//        System.out.println(newTileValue);


    }
}
