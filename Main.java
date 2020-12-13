package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] gameField = new char[3][3];

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j] = ' ';
            }
        }

        do {

            printGameField(gameField);
            int columnCh = 0;
            int rowCh = 0;
            int rowN = 0;
            boolean flag = true;

            do {
                do {
                    System.out.print("Enter the coordinates: ");
                    try {
                        columnCh = scanner.nextInt();
                        rowCh = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("You should enter numbers!");
                        flag = false;
                        scanner.nextLine();
                        continue;
                    }
                    flag = true;
                } while (!flag);


                if (columnCh > 3 || columnCh < 1 || rowCh > 3 || rowCh < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = false;
                } else {
                    if (rowCh == 3) {
                        rowN = 1;
                    } else if (rowCh == 1) {
                        rowN = 3;
                    } else {
                        rowN = 2;
                    }

                    if (gameField[rowN - 1][columnCh - 1] != 'X' && gameField[rowN - 1][columnCh - 1] != 'O') {
                        gameField[rowN - 1][columnCh - 1] = 'X';
                        flag = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        flag = false;
                    }
                }

            } while (!flag);

            printGameField(gameField);

            if (countNumberOfEmpty(gameField) == 0)
                break;

            if (xHasThreeInARow(gameField))
                break;


            do {

                do {
                    System.out.print("Enter the coordinates: ");
                    try {
                        columnCh = scanner.nextInt();
                        rowCh = scanner.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println("You should enter numbers!");
                        flag = false;
                        scanner.nextLine();
                        continue;
                    }
                    flag = true;
                } while (!flag);

                if (columnCh > 3 || columnCh < 1 || rowCh > 3 || rowCh < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = false;
                } else {
                    if (rowCh == 3) {
                        rowN = 1;
                    } else if (rowCh == 1) {
                        rowN = 3;
                    } else {
                        rowN = 2;
                    }

                    if (gameField[rowN - 1][columnCh - 1] != 'X' && gameField[rowN - 1][columnCh - 1] != 'O') {
                        gameField[rowN - 1][columnCh - 1] = 'O';
                        flag = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        flag = false;
                    }

                }

            } while (!flag);

            printGameField(gameField);

            if (oHasThreeInARow(gameField))
                break;


        } while (countNumberOfEmpty(gameField) > 0);

        showResult(gameField);

    }

    public static void showMoves(char[][] gameField) {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[i][j] != 'X' && gameField[i][j] != 'O') {
                    System.out.print(" ");
                } else {
                    System.out.print(gameField[i][j]);
                }
            }
        }
        System.out.println();
    }


    public static void printGameField(char[][] gameField) {
        System.out.println("--------");
        for (char[] chars : gameField) {
            System.out.print("| ");
            for (int j = 0; j < gameField.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.print("| \n");
        }
        System.out.println("--------");
    }

    public static void showResult(char[][] gameField) {
        if (!isValid(gameField)) {
            System.out.println("Impossible");
        } else if (oHasThreeInARow(gameField) && !xHasThreeInARow(gameField)) {
            System.out.println("O wins");
        } else if (xHasThreeInARow(gameField) && !oHasThreeInARow(gameField)) {
            System.out.println("X wins");
        } else if (xHasThreeInARow(gameField) && oHasThreeInARow(gameField)) {
            System.out.println("Impossible");
        } else if (!xHasThreeInARow(gameField) && !oHasThreeInARow(gameField) && countNumberOfEmpty(gameField) == 0) {
            System.out.println("Draw");
        } else if (!xHasThreeInARow(gameField) && !oHasThreeInARow(gameField) && countNumberOfEmpty(gameField) != 0) {
            System.out.println("Game not finished");
        }
    }

    public static boolean isValid(char[][] gameField) {
        int xN = 0;
        int oN = 0;
        for (char[] ch : gameField) {
            for (int i = 0; i < gameField.length; i++) {
                if (ch[i] == 'O') {
                    oN++;
                } else if (ch[i] == 'X') {
                    xN++;
                }
            }
        }
        return Math.abs(xN - oN) > 1 ? false : true;
    }

    public static int countNumberOfEmpty(char[][] gameFiled) {
        int emptyField = 0;
        for (int i = 0; i < gameFiled.length; i++) {
            for (int j = 0; j < gameFiled.length; j++) {
                if (gameFiled[i][j] != 'X' && gameFiled[i][j] != 'O') {
                    emptyField++;
                }
            }
        }
        return emptyField;
    }

    public static boolean oHasThreeInARow(char[][] gameField) {
        return (gameField[0][0] == 'O' && gameField[0][1] == 'O' && gameField[0][2] == 'O') ||
                (gameField[1][0] == 'O' && gameField[1][1] == 'O' && gameField[1][2] == 'O') ||
                (gameField[2][0] == 'O' && gameField[2][1] == 'O' && gameField[2][2] == 'O') ||
                (gameField[0][0] == 'O' && gameField[1][0] == 'O' && gameField[2][0] == 'O') ||
                (gameField[0][1] == 'O' && gameField[1][1] == 'O' && gameField[2][1] == 'O') ||
                (gameField[0][2] == 'O' && gameField[1][2] == 'O' && gameField[2][2] == 'O') ||
                (gameField[0][0] == 'O' && gameField[1][1] == 'O' && gameField[2][2] == 'O') ||
                (gameField[0][2] == 'O' && gameField[1][1] == 'O' && gameField[2][0] == 'O');
    }

    public static boolean xHasThreeInARow(char[][] gameField) {
        return (gameField[0][0] == 'X' && gameField[0][1] == 'X' && gameField[0][2] == 'X') ||
                (gameField[1][0] == 'X' && gameField[1][1] == 'X' && gameField[1][2] == 'X') ||
                (gameField[2][0] == 'X' && gameField[2][1] == 'X' && gameField[2][2] == 'X') ||
                (gameField[0][0] == 'X' && gameField[1][0] == 'X' && gameField[2][0] == 'X') ||
                (gameField[0][1] == 'X' && gameField[1][1] == 'X' && gameField[2][1] == 'X') ||
                (gameField[0][2] == 'X' && gameField[1][2] == 'X' && gameField[2][2] == 'X') ||
                (gameField[0][0] == 'X' && gameField[1][1] == 'X' && gameField[2][2] == 'X') ||
                (gameField[0][2] == 'X' && gameField[1][1] == 'X' && gameField[2][0] == 'X');
    }

}

