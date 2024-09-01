package tictactoe;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int x = 0;
    static int o = 0;
    static boolean xWin = false;
    static boolean oWin = false;
    static int dash = 0;
    public static void main(String[] args) {
        int[][] winState = { {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6} };
        String xo = "         ";
        printField(xo);
        boolean toContinue = true;
        boolean writeX = true;
        do {
            xo = getUserInput(xo, writeX);
            printField(xo);
            countOccurence(xo);
            checkWinner(xo, winState);
            if( Math.abs(x - o) > 2 || (xWin && oWin)) {
                System.out.println("Impossible");
                toContinue = false;
            } else {
                if(xWin){
                    System.out.println("X wins");
                    toContinue = false;
                } else if(oWin){
                    System.out.println("O wins");
                    toContinue = false;
                } else if(dash == 0) {
                    System.out.println("Draw");
                    toContinue = false;
                }
            }
            writeX = !writeX;
        } while(toContinue);
    }

    public static String getUserInput(String xo, boolean writeX){
        boolean ToContinue = true;
        do {
            String input = sc.nextLine();
            int row = input.charAt(0);
            int col = input.charAt(2);

            if(row < 49 || row >57 ||col < 49 || col >57 ) {
                System.out.println("You should enter numbers!");
                continue;
            }
            row = Integer.parseInt(String.valueOf(input.charAt(0)));
            col = Integer.parseInt(String.valueOf(input.charAt(2)));
            if (row > 3 || col > 3 || row < 1 || col < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int index = (row - 1) * 3 + (col - 1);
            if (xo.charAt(index) == 'X' || xo.charAt(index) == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if(writeX) {
            return xo.substring(0, index) + 'X' + xo.substring(index + 1);
            } else {
                return xo.substring(0, index) + 'O' + xo.substring(index + 1);
            }
        }
        while(ToContinue);

    return "";
    }
    private static void countOccurence(String xo) {
        x = 0;
        o = 0;
        dash = 0;
        for(int i = 0; i < xo.length(); i++){
            if(xo.charAt(i) == 'X') {
                x++;
            } else if(xo.charAt(i) == 'O') {
                o++;
            } else {
                dash++;
            }

        }
    }
    private static void checkWinner(String xo, int[][] winState) {
        for(int i = 0; i < winState.length; i++){
            int[] j = winState[i];
                if(xo.charAt(j[0]) == xo.charAt(j[1]) &&
                        xo.charAt(j[1]) == xo.charAt(j[2])) {
                    if(xo.charAt(j[0]) == 'X'){
                        xWin = true;
                    } else if(xo.charAt(j[0]) == 'O'){
                        oWin = true;
                    }
                }
        }
    }
    private static void printField(String xo) {
        System.out.println("---------");
        for(int i = 0; i < xo.length(); i= i+3) {
            System.out.print("| ");
            System.out.printf("%c %c %c", xo.charAt(i), xo.charAt(i + 1), xo.charAt(i + 2));
            System.out.print(" | \n");
        }
        System.out.println("---------");
    }
}
