package com.softwareinstitute.training.murray.ben;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int k = 1;
        int j = 1;
        int i = 1;

        //INPUTS
        Scanner scan = new Scanner(System.in);
        System.out.print("Height: ");
        int height = scan.nextInt();

        System.out.print("Width: ");
        int width = scan.nextInt();

        System.out.print("Number Of Mines: ");
        int mineAmount = scan.nextInt();
        System.out.println();

        Board gameBoard = new Board(height, width, mineAmount);
        gameBoard.generateFrontBoard();



        if (height*width == mineAmount) {
            while (k == 1) {
                gameBoard.printFrontBoard();

                System.out.print("Row: ");
                int nullRow = scan.nextInt()-1;

                System.out.print("Column: ");
                int nullColumn = scan.nextInt()-1;

                gameBoard.generateFullBackBoard(height, width);

                gameBoard.printBackBoard();
                System.out.println("You Died!");
                j = 0;
                i = 0;
                k = 0;
            }
        }



        while (j == 1) {
            gameBoard.printFrontBoard();

            System.out.print("Row: ");
            int initialRow = scan.nextInt()-1;

            System.out.print("Column: ");
            int initialColumn = scan.nextInt()-1;

            gameBoard.isBoardAllowed(initialRow, initialColumn);

            gameBoard.generateDoneBoard();
            gameBoard.generateBackBoard();
            gameBoard.printBackBoard();

            gameBoard.evaluateTile(initialRow, initialColumn);
            j = 0;

            if (gameBoard.isDone()) {
                gameBoard.printBackBoard();
                System.out.println("You Won!");
                j = 0;
                i = 0;
            }
        }



        while (i == 1) {
            gameBoard.printFrontBoard();

            System.out.print("Reveal (r) or Flag (f)? ");
            String option = scan.next();

            System.out.print("Row: ");
            int selectedRow = scan.nextInt()-1;

            System.out.print("Column: ");
            int selectedColumn = scan.nextInt()-1;

            System.out.println();

            if (option.equals("r")) {
                gameBoard.evaluateTile(selectedRow, selectedColumn);
            } else {
                gameBoard.flagTile(selectedRow, selectedColumn);
            }

            if (gameBoard.isDead()) {
                gameBoard.printBackBoard();
                System.out.println("You Died!");
                i = 0;
            }

            if (gameBoard.isDone()) {
                gameBoard.printBackBoard();
                System.out.println("You Won!");
                i = 0;
            }
        }
    }
}
