package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char[][] state = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        printGameGrid(state);
        processUserInput(state);
    }

    private static void printGameGrid(char[][] state) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", state[0][0], state[0][1], state[0][2]);
        System.out.printf("| %c %c %c |%n", state[1][0], state[1][1], state[1][2]);
        System.out.printf("| %c %c %c |%n", state[2][0], state[2][1], state[2][2]);
        System.out.println("---------");
    }

    private static void processUserInput(char[][] state) {

        Scanner scanner = new Scanner(System.in);
        boolean playerX = true;
        while (true) {
            try {
                int inputRow = scanner.nextInt();
                int inputCol = scanner.nextInt();
                if (inputRow > 0 && inputRow < 4 && inputCol > 0 && inputCol < 4) {
                    inputRow--;
                    inputCol--;
                    if (state[inputRow][inputCol] == ' ') {
                        state[inputRow][inputCol] = playerX ? 'X' : 'O';
                        printGameGrid(state);
                        playerX = !playerX;
                        if (gameFinished(state)){
                            break;
                        }
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }

    private static boolean gameFinished(char[][] state) {

        if (!isPossibleState(state)) {
            System.out.println("Impossible");
            return false;
        } else if (hasRow('X', state)) {
            System.out.println("X wins");
            return true;
        } else if (hasRow('O', state)) {
            System.out.println("O wins");
            return true;
        } else if (hasEmptyCells(state)) {
//            System.out.println("Game not finished");
            return false;
        } else {
            System.out.println("Draw");
            return true;
        }
    }

    private static boolean hasEmptyCells(char[][] state) {

        for (char[] row: state) {
            for (char field: row) {
                if(field == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPossibleState(char[][] state) {

        int diff = 0;
        for (char[] row: state) {
            for (char field: row) {
                diff += field == 'X' ? 1 : field == 'O' ? - 1 : 0;
            }
        }

        if (Math.abs(diff) > 1) {
            return false;
        } else {
            return !(hasRow('X', state) && hasRow('O', state));
        }
    }

    private static boolean hasRow(char player, char[][] state) {

        return (state[0][0] == player && state[0][1] == player && state[0][2] == player) ||
            (state[1][0] == player && state[1][1] == player && state[1][2] == player) ||
            (state[2][0] == player && state[2][1] == player && state[2][2] == player) ||
            (state[0][0] == player && state[1][0] == player && state[2][0] == player) ||
            (state[0][1] == player && state[1][1] == player && state[2][1] == player) ||
            (state[0][2] == player && state[1][2] == player && state[2][2] == player) ||
            (state[0][0] == player && state[1][1] == player && state[2][2] == player) ||
            (state[0][2] == player && state[1][1] == player && state[2][0] == player);
    }
}
