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
        System.out.println("Setup...");
        System.out.println("Enter numbers in correct locations");
        System.out.println("Enter s to skip locations");
        System.out.println("Enter done when all data is entered");

        tiles = setupGame(tiles);
        printGame(tiles);
        tiles = runPoss(tiles);
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (tiles[i][k].poss == null) {
                    // do nothing
                } else {
                    for (int h = 0; h < tiles[i][k].poss.size(); h++) {
                        System.out.print(tiles[i][k].poss.get(h) + "|");
                    }
                    System.out.println();
                    System.out.println(tiles[i][k].finalNum + " <- final");
                }

            }
        }

        printGame(tiles);
    }

    public static location[][] finishBoard(location[][] temp) {

        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (temp[i][k] == null) {
                    temp[i][k] = new location();
                }

            }
        }
        return temp;
    }

    public static location[][] runPoss(location[][] temp) {

        for (int row = 0; row < temp.length; row++) {
            for (int col = 0; col < temp[0].length; col++) {
                for (int i = 1; i <= 9; i++) {
                    if (temp[row][col].finalNum != 0) {
                        System.out.println("Already has final number" + temp[row][col].finalNum);
                    } else if (temp[row][col].finalNum == 0) {
                        if (!searchUpDown(col, i, temp)) {
                            // System.out.println("Searched up down");
                            if (!searchLeftRight(row, i, temp)) {
                                // System.out.println("Searched left right");

                                if (!searchBox(row, col, i, temp)) {
                                    // System.out.println("Searched box - value added to poss");

                                    temp[row][col].poss.add(i);
                                } else {
                                    System.out.println(i + "value not used in row:" + row + " col:" + col);
                                }
                            } else {
                                System.out.println(i + "value not used in row:" + row + " col:" + col);
                            }
                        } else {
                            System.out.println(i + "value not used in row:" + row + " col:" + col);
                        }
                    } else {
                        System.out.println("UHHHHHHH");
                    }

                }
            }
        }
        return temp;
    }

    public static boolean searchUpDown(int col, int value, location[][] temp) {
        // search up to row 1 and down to 9 from whatever row col is given
        // return true if number shows up as final
        // return false if number does not show up as final in the column
        for (int currRow = 0; currRow > 9; currRow++) {
            if (temp[currRow][col].finalNum == value) {
                System.out.println(" .finalNum == value ");
                return true;
            }
        }
        return false;
    }

    public static boolean searchLeftRight(int row, int value, location[][] temp) {
        for (int currCol = 0; currCol > 9; currCol++) {
            if (temp[row][currCol].finalNum == value) {
                System.out.println(" .finalNum == value ");
                return true;
            }
        }
        return false;
    }

    public static boolean searchBox(int row, int col, int value, location[][] temp) {
        for (int i = 1; i <= 9; i++) {

            i++;
        }
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

    public static location[][] setupGame(location[][] temp) {
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int h = 0; h < 9; h++) {
                System.out.println("Loc: " + i + "-" + h + "=");
                String tmp = console.next();
                if (tmp.equalsIgnoreCase("s")) {
                    System.out.println("Skipped this one");
                    temp[i][h] = new location();
                } else if (tmp.equalsIgnoreCase("done")) {

                    System.out.println("Done with input");
                    console.close();
                    return finishBoard(temp);
                } else if (isNumeric(tmp) && (Integer.parseInt(tmp) < 10) && (Integer.parseInt(tmp) > 0)) {
                    temp[i][h] = new location(Integer.parseInt(tmp));
                } else {
                    System.out.println("UNKNOWN INPUT -> Skipped this one");
                }
            }
            System.out.println();
        }

        console.close();
        return temp;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

class location {

    ArrayList<Integer> poss;
    int finalNum;
    char area;

    public location() {
        poss = new ArrayList<Integer>();
        finalNum = 0;
    }

    public location(int x) {
        finalNum = x;
    }

}
