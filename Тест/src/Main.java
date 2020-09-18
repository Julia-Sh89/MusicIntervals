
import java.util.Scanner;

public class Main {

    public static char[][] matrix = new char[3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        matrix = createMatrix("         ");
        printMatrix(matrix);
        int countMoves = 1;
        int[] checkCombo;
        boolean s;
        while (true) {
            System.out.println("Enter the coordinates:");
            String b = scanner.next();
            String c = scanner.next();
            s = checkCoordinates(countMoves, b, c);

            checkCombo = checkCombo(matrix);

            if (checkCombo[0] == 1 && checkCombo[1] == 0) {
                System.out.println("X wins");
                break;
            } else if (checkCombo[0] == 0 && checkCombo[1] == 1) {
                System.out.println("O wins");
                break;
            } else if (checkCombo[0] > 0 && checkCombo[1] > 0) {
                System.out.println("Impossible");
                break;
            } else if (checkCombo[0] == 0 && checkCombo[1] == 0 && countMoves == 9 && s) {
                System.out.println("Draw");
                break;
            }

            countMoves = s?countMoves+1:countMoves;

        }


       /*



        //проверка на количество х и о
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 9; i++) {
            switch (str1.charAt(i)) {
                case 'X': countX++;
                    break;
                case 'O': countO++;
                    break;
                default:
                    break;
            }
        }

        if (Math.abs(countX - countO) > 1) {
            System.out.println("Impossible");
            return;
        }

        int countField = countX + countO;


*/

    }

    public static char[][] createMatrix(String str) {
        int x = 0;
        char arr[][] = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = str.charAt(x);
                x++;
            }
        }
        return arr;
    }

    public static void printMatrix(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " |");
        }
        System.out.println("---------");
    }

    public static boolean checkCoordinates(int moves, String b, String c) {
        try {
            int x = Integer.parseInt(b);
            int y = Integer.parseInt(c);

            if (x >= 1 && x <= 3 && y  >= 1 && y <= 3 ) {
                if (matrix[3-y][x-1] == ' ' ||  matrix[3-y][x-1] == '_') {
                    if (moves % 2 == 0) {
                        matrix[3 - y][x - 1] = 'O';
                    } else {
                        matrix[3 - y][x - 1] = 'X';
                    }
                    printMatrix(matrix);
                    return true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    return false;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    public static int checkLine(char a, char b, char c) {
        int result = 0;
        if (a == b && a == c ) {
            if (a == 'X') {
                result = 1;
            } else if (a == 'O') {
                result = 2;
            }
        }
        return result;
    }

    public static int[] checkCombo(char[][] arr) {
        int[] state = new int[8];
        int index = 0;
        int[] result = {0, 0};

        //проверить столбцы и строки на комбо
        for (int i = 0; i < 3; i++) {
            state[index] = checkLine(arr[i][0], arr[i][1], arr[i][2]);
            index++;
            state[index] = checkLine(arr[0][i], arr[1][i], arr[2][i]);
            index++;
        }


        //проверить диагонали на комбо
        state[6] = checkLine(arr[0][0], arr[1][1], arr[2][2]);
        state[7] = checkLine(arr[0][2], arr[1][1], arr[2][0]);

        //подсчитать количество комбо X и O

        for (int i:state) {
            switch (i) {
                case 1:
                    result[0]++;
                    break;
                case 2:
                    result[1]++;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

}
