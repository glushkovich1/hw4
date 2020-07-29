package lesson4;

import java.util.Random;
import java.util.Scanner;


public class HomeWork4 {
    static final int SIZE = 5;
    //static final int SIZE_WIN = 4;

    static final char PLAYER = 'X';
    static final char CPU = 'O';
    static final char DOT_EMPTY = '.';

    static final char[][] map = new char[SIZE][SIZE];;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        HomeWork4 g = new HomeWork4();
        g.newMap();
        g.printMap();

        /*while (true) {
            HomeWork4.humanTurn();
            HomeWork4.printMap();
            if(HomeWork4.checkWin(HomeWork4.DOT_X)){
                System.out.println("Ты победил! ");
                break;
            }
            if (HomeWork4.isFull()) {
                System.out.println("Ничья!");
                break;
            }

            HomeWork4.aiTurn(HomeWork4.DOT_O);
            HomeWork4.printMap();
            if(HomeWork4.chekWin(HomeWork4.DOT_O)){
                System.out.println("Компьютер победил! ");
                break;
            }
            if (HomeWork4.isFull()) {
                System.out.println("Ничья!");
                break;
            }*/
        while (true) {
            //player turn
            g.playerTurn();
            g.printMap();
            if (g.checkWin(g.PLAYER)) { System.out.println("Поздраляем! Вы победили"); break; }
            if (g.isMapFull()) { System.out.println("Конец игры. Никто не выйграл"); break; }

            //AI-1 turn
            g.aiTurn(g.CPU);
            g.printMap();
            if (g.checkWin(g.CPU)) { System.out.println("Конец игры. Победил компьютер"); break; }
            if (g.isMapFull()) { System.out.println("Конец игры. Никто не выйграл"); break; }
        }
    }


    public static void newMap() {
        //map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        /*for (int i = 0; i < SIZE + 1; i++) {
            System.out.print(" " + i + " ");*/
        }
        //System.out.print(">X");
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" " + (i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(i + 1);
        }
        /*System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");*/
        /*System.out.print(" vY ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
            System.out.println("0\n");
        }*/
    }

    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты X, Y ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = PLAYER;
    }

    static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    /*public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }*/

    boolean checkWin(char c) {
        int countV;
        int countH;
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i <= SIZE - 1; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j <= SIZE - 1; j++) {
                if (map[i][j] == c) {
                    countH++;
                    if (countH == SIZE) return true;
                }
                if (map[i][j] == c) {
                    countV++;
                    if (countV == SIZE) return true;
                }
            }
            if (map[i][i] == c) {
                countDiagonalA++;
                if (countDiagonalA == SIZE) return true;
            } else countDiagonalA = 0;

            if (map[i][SIZE - 1 - i] == c) {
                countDiagonalB++;
                if (countDiagonalB == SIZE) return true;
            } else countDiagonalB = 0;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public static boolean checkWin(char c) {
        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {return true; }
        if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {return true; }
        if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {return true; }

        if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {return true; }
        if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {return true; }
        if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {return true; }

        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {return true; }
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {return true; }

        return false;
    }*/

    public static void aiTurn(char c) {
        int x = 0, y = 0, countH = 0, countHNull = 0, countV = 0, countVNull = 0, countDiagonalA = 0, countDiagonalB = 0, countDANull = 0, countDBNull = 0;
        System.out.println();

        for (int i = 0; i < SIZE; i++) {//Игрок
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == c) countV++;
                else if(map[j][i] == DOT_EMPTY) countVNull++;
                if((countV == SIZE -1) && (countVNull == 1)){
                    for (int k = 0; k < SIZE; k++) {
                        if (map[k][i] == DOT_EMPTY) {
                            map[k][i] = c;
                            return;
                        }
                    }
                }
                if (map[i][j] == c) countH++;
                else if (map[i][j] == DOT_EMPTY) countHNull++;
                if ((countH == SIZE - 1) && (countHNull == 1)) {
                    for (int k = 0; k < SIZE; k++) {
                        if (map[i][k] == DOT_EMPTY) {
                            map[i][k] = c;
                            return;
                        }
                    }
                }
            }
            if (map[i][i] == c)countDiagonalA++;
            else if (map[i][i] == DOT_EMPTY) countDANull++;
            if ((countDiagonalA == SIZE - 1) && (countDANull == 1)) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[j][j] == DOT_EMPTY) {
                        map[j][j] = c;
                        return;
                    }
                }
            }

            if (map[i][SIZE - 1 - i] == c) countDiagonalB++;
            else if (map[i][SIZE - 1 - i] == DOT_EMPTY)  countDBNull++;
            if ((countDiagonalB == SIZE - 1) && (countDBNull == 1)) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[j][SIZE - 1 - j] == DOT_EMPTY) {
                        map[j][SIZE - 1 - j] = c;
                        return;
                    }
                }
            }
        }

        countH = 0;
        countHNull = 0;
        countV = 0;
        countVNull = 0;
        countDiagonalA = 0;
        countDiagonalB = 0;
        countDANull = 0;
        countDBNull = 0;

        for (int i = 0; i < SIZE; i++) {
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < SIZE; j++) {

                if (map[j][i] == PLAYER) countV++;
                else if (map[j][i] == DOT_EMPTY) countVNull++;
                if ((countV == SIZE - 1) && (countVNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (map[k][i] == DOT_EMPTY) {
                            map[k][i] = c;
                            return;
                        }
                    }
                }

                if (map[i][j] == PLAYER) countH++;
                else if (map[i][j] == DOT_EMPTY) countHNull++;
                if ((countH == SIZE - 1) && (countHNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (map[i][k] == DOT_EMPTY) {
                            map[i][k] = c;
                            return;
                        }
                    }
                }

            }


            if (map[i][i] == PLAYER) countDiagonalA++;
            else if (map[i][i] == DOT_EMPTY) countDANull++;
            if ((countDiagonalA == SIZE - 1) && (countDANull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (map[j][j] == DOT_EMPTY) {
                        map[j][j] = c;
                        return;
                    }
                }
            }


            if (map[i][SIZE - 1 - i] == PLAYER) countDiagonalB++;
            else if (map[i][SIZE - 1 - i] == DOT_EMPTY)  countDBNull++;
            if ((countDiagonalB == SIZE - 1) && (countDBNull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (map[j][SIZE - 1 - j] == DOT_EMPTY) {
                        map[j][SIZE - 1 - j] = c;
                        return;
                    }
                }
            }
        }


        if (!(SIZE % 2 == 0)) {
            int center = (((SIZE + 1) / 2) - 1);
            if (map[center][center] == DOT_EMPTY) {
                map[center][center] = c;
                return;
            }
        }


        if (map[0][0] == DOT_EMPTY) { map[0][0] = c; return; }
        if (map[0][map.length - 1] == DOT_EMPTY) { map[0][map.length - 1] = c; return; }
        if (map[map.length - 1][0] == DOT_EMPTY) { map[map.length - 1][0] = c; return; }
        if (map[map.length - 1][map.length - 1] == DOT_EMPTY) { map[map.length - 1][map.length - 1] = c; return; }


        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (isCellValid(x, y));
        map[y][x] = c;
        System.out.println("AI X: " + (x + 1) + " Y: " + (y + 1));
    }
}