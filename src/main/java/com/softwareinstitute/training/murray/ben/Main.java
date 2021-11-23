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

        Board gameBoard = new Board(height, width, mineAmount);



    }
}
