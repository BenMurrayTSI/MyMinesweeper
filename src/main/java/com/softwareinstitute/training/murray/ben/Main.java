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



    }
}
