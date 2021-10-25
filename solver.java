import java.util.ArrayList;
import java.util.Scanner;

public class solver {
    public static ArrayList<Integer> possibles = new ArrayList<Integer>();
    public static location[][] tiles = new location[9][9];
    public static int finalNum = 0;

    /*
     * Visual of locations each value represents a location() [ 0, 0, 0, 0, 0, 0, 0,
     * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
     * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
     * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, ]
     * 
     */

    public static void main(String[] args) {
        // Board is store as arraylist of objects
        // Object holds possibles and the final number
        System.out.println("Creating Board...");
        tiles = createBoard();
        System.out.println("Setup...");
        System.out.println("Enter numbers in correct locations");
        System.out.println("Enter s to skip locations");
        System.out.println("Enter done when all data is entered");

        setupGame(tiles);
        tiles = runPoss(tiles);
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                for (int h = 0; h < tiles[i][k].poss.size(); h++) {
                    System.out.print(tiles[i][k].poss.get(h) + "|");
                }
                System.out.println();
                System.out.println(tiles[i][k].finalNum + " <- final");
            }
        }

        printGame(tiles);
    }

    public static location[][] createBoard() {
        location[][] temp = new location[9][9];
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                temp[i][k] = new location();
            }
        }
        return temp;
    }

    public static location[][] runPoss(location[][] temp) {

        for (int row = 0; row < temp.length; row++) {
            for (int col = 0; col < temp[0].length; col++) {
                for (int i = 0; i < 9; i++) {
                    if (!searchUpDown(row, col, i, temp)) {
                        System.out.println("Searched up down");
                        if (!searchLeftRight(row, col, i, temp)) {
                            System.out.println("Searched left right");

                            if (!searchBox(row, col, i, temp)) {
                                System.out.println("Searched box - value added to poss");

                                temp[row][col].poss.add(i + 1);
                            } else {
                                System.out.println(i + "value not used in row:" + row + " col:" + col);
                            }
                        } else {
                            System.out.println(i + "value not used in row:" + row + " col:" + col);
                        }
                    } else {
                        System.out.println(i + "value not used in row:" + row + " col:" + col);
                    }
                }
            }
        }
        return temp;
    }

    public static boolean searchUpDown(int row, int col, int value, location[][] temp) {
        // search up to row 1 and down to 9 from whatever row col is given
        // return true if number shows up as final
        // return false if number does not show up as final in the column
        for (int currRow = 1; currRow >= 9; currRow++) {
            if (temp[currRow][col].finalNum == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchLeftRight(int row, int col, int value, location[][] temp) {
        int currCol = 0;
        while (currCol >= 9) {

            if (temp[row][currCol].finalNum == value) {
                return true;
            } else {
                if (searchBox(row, col, value, temp)) {
                    return true;
                }
                currCol++;
            }
        }
        return false;
    }

    public static boolean searchBox(int row, int col, int value, location[][] temp) {
        return false;
    }

    public static void printGame(location[][] temp) {
        for (int i = 0; i < 9; i++) {
            for (int h = 0; h < 9; h++) {
                System.out.print("|" + temp[i][h].finalNum + "|");
            }
            System.out.println();
        }
    }

    public static void setupGame(location[][] temp) {
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int h = 0; h < 9; h++) {

                if (!console.hasNextInt()) {
                    System.out.println("Loc: " + i + "-" + h + "= unset");

                } else if (console.next().equalsIgnoreCase("done")) {
                    console.close();
                    return;
                } else {
                    System.out.println("Loc: " + i + "-" + h + "=");
                    temp[i][h].finalNum = console.nextInt();
                }
                console.next(); // this is important!
            }
            System.out.println();
        }

        console.close();
    }

}

class location {

    ArrayList<Integer> poss;
    int finalNum;

    public location() {
        poss = new ArrayList<Integer>();
        finalNum = 0;
    }

    public location(int x) {
        finalNum = x;
        poss = null;
    }

}
