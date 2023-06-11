package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private  static int size = 5;
    private static char[][] board = new char[size][size];

    private static int turn = 0;

    private static ArrayList<Character> symb = new ArrayList<Character>(Arrays.asList('0','X'));

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<ArrayList<Integer>> checkWinPositions = new ArrayList<>(new ArrayList<>(3));

    public static void main(String[] args) {
        initGame();
        System.out.println("Welcome to TicTacToe Game!");
        runGame();
    }

    public static void initGame(){
        initBoard();
        initWinPositions();
    }

    public static void initWinPositions(){
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(1,2,3)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(4,5,6)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(7,8,9)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(1,5,9)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(7,5,3)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(7,4,1)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(8,5,2)));
        checkWinPositions.add(new ArrayList<Integer>(Arrays.asList(9,6,3)));
    }

    public static void initBoard(){
        for (int i=0;i<size;i++){
            for (int j = 0; j<size;j++){
                if (i%2==0){
                    if (j == 1 || j ==3)
                        board[i][j] = '|';
                    else
                        board[i][j] = ' ';
                }
                else{
                    if (j % 2 == 0)
                        board[i][j] = '-';
                    else
                        board[i][j] = '+';
                }
            }
        }
    }

    public static void printBoard(){
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    public static void putChar(){
        System.out.println("Enter your placement(1-9):");
        int place = scanner.nextInt() -1;
        if (place < 0 || place > 8)
        {
            System.out.println("You entered wrong number, try again!");
            putChar();
        }
        else {
            if (board[place / 3 * 2][place % 3 * 2] == ' ') {
                board[place / 3 * 2][place % 3 * 2] = symb.get(turn);
                turn++;
                turn %= 2;
            } else {
                System.out.println("This is place is already in use, try again!");
                putChar();
            }
        }
    }

    public static char checkForWin(){
        for (int i=0;i<checkWinPositions.size();i++){
            boolean check = true;
            for (int j = 0;j<2;j++){
                if (board[(checkWinPositions.get(i).get(j) - 1) / 3 * 2][(checkWinPositions.get(i).get(j) - 1) % 3 * 2]
                        != board[(checkWinPositions.get(i).get(j+1) - 1) / 3 * 2][(checkWinPositions.get(i).get(j+1) - 1) % 3 * 2]){
                    check = false;
                    break;
                }
            }
            if (check){
                return board[(checkWinPositions.get(i).get(0) - 1) / 3 * 2][(checkWinPositions.get(i).get(0) - 1) % 3 * 2];
            }
        }
        return ' ';
    }

    public static void runGame(){
        changeOrder();
        System.out.println("Starts " + symb.get(turn));
        printBoard();
        char res = ' ';
        int counter = 0;
        while (res == ' ' && counter <= 8){
            putChar();
            printBoard();
            res = checkForWin();
            counter++;
        }
        if (res != ' ')
            System.out.println("Congrats " + res);
        else
            System.out.println("Ohh! It's draw! Congrats");
    }
    public static void changeOrder(){
        boolean flag = true;
        while (flag) {
            System.out.println("To start the game enter who will start first (enter X or 0):");
            char start = scanner.next().charAt(0);
            switch (start) {
                case 'X':
                    turn++;
                    flag = false;
                    break;
                case '0':
                    flag = false;
                    break;
                default:
                    System.out.println("Ypu entered wrong char, try again!");
            }
        }
    }

}
