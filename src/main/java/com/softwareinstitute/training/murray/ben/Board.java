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
    boolean dead = false;
    boolean done = false;

    //SYMBOLS
    String mineSymbol = BRIGHT_WHITE + BLACK_BACKGROUND + " X " + RESET;
    String hiddenSymbol = BLACK_BACKGROUND_BRIGHT + " ? " + RESET;
    String clearSymbol = BLACK_BACKGROUND + " - " + RESET;
    String flagSymbol = BLACK_BACKGROUND_BRIGHT + BRIGHT_YELLOW + " F " + RESET;
    String oneSymbol = BRIGHT_CYAN + BLACK_BACKGROUND + " 1 " + RESET;
    String twoSymbol = GREEN + BLACK_BACKGROUND + " 2 " + RESET;
    String threeSymbol = RED + BLACK_BACKGROUND + " 3 " + RESET;
    String fourSymbol = BRIGHT_BLUE + BLACK_BACKGROUND + " 4 " + RESET;
    String fiveSymbol = BRIGHT_PURPLE + BLACK_BACKGROUND + " 5 " + RESET;
    String sixSymbol = CYAN + BLACK_BACKGROUND + " 6 " + RESET;
    String sevenSymbol = YELLOW + BLACK_BACKGROUND + " 7 " + RESET;
    String eightSymbol = BRIGHT_GREEN + BLACK_BACKGROUND + " 8 " + RESET;


    //COLOURS
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String WHITE_BACKGROUND = "\033[47m";
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[100m";
    public static final String BLACK_BACKGROUND = "\033[40m";


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
                gameDoneBoard[i][j] = gameFrontBoard[i][j];
            }
        }

        for (int i = 0; i < mineAmount; i++) {
            gameDoneBoard[minePositions[0][i]][minePositions[1][i]] = mineSymbol;
        }
    }


    //PRINT DONE BOARD
    public void printDoneBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(gameDoneBoard[i][j]);
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
                System.out.print(gameBackBoard[i][j]);
            }
            System.out.print(" |" + (i+1));
            System.out.println();

            if (i == height - 1) {
                System.out.print(" ");
                for (int j = 0; j < width; j++) {
                    System.out.print("___");
                }
                System.out.println();

                int widthPrintRows = String.valueOf(width).length();
                for (int k = 0; k < widthPrintRows; k++) {
                    boolean zero = true;
                    for (int j = 0; j < width; j++) {
                        int value = (int) (Math.floor((j + 1)/(Math.pow(10,(widthPrintRows-k-1)))) % 10);
                        if (value == 0 && zero) {
                            System.out.print("   ");
                        } else {
                            System.out.print("  " + value);
                            zero = false;
                        }

                    }
                    System.out.println();
                }


            }
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

        Collections.shuffle(numbers);
        for (int j = 0; j < mineAmount; j++) {
            mines.add(numbers.get(j));
        }
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
                System.out.print(minePositions[i][j]);
            }
            System.out.print(" |" + (i+1));
            System.out.println();

            if (i == height - 1) {
                System.out.print(" ");
                for (int j = 0; j < width; j++) {
                    System.out.print("___");
                }
                System.out.println();

                int widthPrintRows = String.valueOf(width).length();
                for (int k = 0; k < widthPrintRows; k++) {
                    boolean zero = true;
                    for (int j = 0; j < width; j++) {
                        int value = (int) (Math.floor((j + 1)/(Math.pow(10,(widthPrintRows-k-1)))) % 10);
                        if (value == 0 && zero) {
                            System.out.print("   ");
                        } else {
                            System.out.print("  " + value);
                            zero = false;
                        }

                    }
                    System.out.println();
                }


            }
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

                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }

                    String colouredSetTileValue = Integer.toString(setTileValue);

                    switch (setTileValue) {
                        case 0 -> colouredSetTileValue = clearSymbol;
                        case 1 -> colouredSetTileValue = oneSymbol;
                        case 2 -> colouredSetTileValue = twoSymbol;
                        case 3 -> colouredSetTileValue = threeSymbol;
                        case 4 -> colouredSetTileValue = fourSymbol;
                        case 5 -> colouredSetTileValue = fiveSymbol;
                        case 6 -> colouredSetTileValue = sixSymbol;
                        case 7 -> colouredSetTileValue = sevenSymbol;
                        case 8 -> colouredSetTileValue = eightSymbol;
                    }



                    gameBackBoard[tileRow][tileColumn] = colouredSetTileValue;
                }
            }
        }
    }


    //GENERATE FRONT BOARD
    public void generateFrontBoard() {
        generateClearFrontBoard();
        generateDoneBoard();
        //printDoneBoard();
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
                System.out.print(gameFrontBoard[i][j]);
            }
            System.out.print(" |" + (i+1));
            System.out.println();

            if (i == height - 1) {
                System.out.print(" ");
                for (int j = 0; j < width; j++) {
                    System.out.print("___");
                }
                System.out.println();

                int widthPrintRows = String.valueOf(width).length();
                for (int k = 0; k < widthPrintRows; k++) {
                    boolean zero = true;
                    for (int j = 0; j < width; j++) {
                        int value = (int) (Math.floor((j + 1)/(Math.pow(10,(widthPrintRows-k-1)))) % 10);
                        if (value == 0 && zero) {
                            System.out.print("   ");
                        } else {
                            System.out.print("  " + value);
                            zero = false;
                        }

                    }
                    System.out.println();
                }


            }
        }
        System.out.println();
    }


    //EVALUATE SELECTED TILE
    public void evaluateTile(int selectedRow, int selectedColumn) {
        String tileFrontValue = gameFrontBoard[selectedRow][selectedColumn];
        String tileBackValue = gameBackBoard[selectedRow][selectedColumn];

        if (tileFrontValue.equals(flagSymbol)) {
            System.out.println("This tile is flagged.");
        } else {
            if (tileBackValue.equals(mineSymbol)) {
                this.dead = true;
            } else if (tileBackValue.equals(clearSymbol)) {
                gameFrontBoard[selectedRow][selectedColumn] = tileBackValue;
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
            generateDoneBoard();
            if (Arrays.deepEquals(gameBackBoard, gameDoneBoard)) {
                this.done = true;
            }
        }

    }

    public void flagTile(int selectedRow, int selectedColumn) {
        String tileValue = gameFrontBoard[selectedRow][selectedColumn];

        if (tileValue.equals(hiddenSymbol)) {
            this.gameFrontBoard[selectedRow][selectedColumn] = flagSymbol;
        }

        if (tileValue.equals(flagSymbol)) {
            this.gameFrontBoard[selectedRow][selectedColumn] = hiddenSymbol;
        }

    }


    //RETURN IF DEAD
    public boolean isDead() {
        return dead;
    }


    //RETURN IF DONE
    public boolean isDone() {
        return done;
    }
}
