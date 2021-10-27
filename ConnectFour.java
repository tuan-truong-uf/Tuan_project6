import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array) {                          // print te boardf
        for(int i=array.length-1; i>=0; i--) {
            for(int j=0; j<array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array) {                       // method to initialize the board.
        for (int i=0; i<array.length; i++) {
            for(int j=0; j<array[0].length; j++) {
                array[i][j] = '-';
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        for (int i=0; i<array.length; i++) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                return i;
            }
        }
        return -1;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {          // check if you win/
        int count=0;
        for(int i=0; i<array.length; i++) {
            if(array[i][col] == chipType) {
                count++;
                if(count == 4) {
                    return true;
                }
            }
            else {
                count = 0;
            }
        }
        count = 0;
        for(int i=0; i<array[0].length; i++) {
            if(array[row][i] == chipType) {
                count++;
                if(count == 4) {
                    return true;
                }
            }
            else {
                count = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row, col;
        while(true) {                                                                  // choose the height for the board.
            System.out.print("What would you like the height of the board to be ? ");
            row = sc.nextInt();
            if(row >= 4) {
                break;
            }
            else {                                                                    // height must be at least 4.
                System.out.println("Height should be at least 4. Please try again!");
            }
        }
        while(true) {                                                                  // choose the length of the board.
            System.out.print("What would you like the length of the board to be ? ");
            col = sc.nextInt();
            if(col >= 4) {
                break;
            }
            else {                                                                    // length should be >= 4.
                System.out.println("Length should be at least 4. Please try again!");
            }
        }
        char board[][] = new char[row][col];
        initializeBoard(board);
        printBoard(board);

        System.out.println("Player 1 : x");
        System.out.println("Player 2 : o");

        boolean player1 = true;
        char player;
        int choiceCol = 0;
        int result = 0;
        boolean isGameDone = false;
        int totalPlay = 0;
        while(true) {

            if(player1) {
                System.out.print("Player 1:");
                player = 'x';
            }
            else {
                System.out.print("Player 2:");
                player = 'o';
            }
            System.out.print("Which column would you like to choose ? ");
            choiceCol = sc.nextInt();
            if(choiceCol<0 || choiceCol >= col) {
                System.out.println("Please enter choice between 0 and " + (col - 1));
            }
            else {
                result=insertChip(board,choiceCol,player);
                if(result == -1) {
                    System.out.println("There is no room to insert.Please try again!!!");
                }
                else {
                    printBoard(board);
                    isGameDone = checkIfWinner(board, choiceCol, result, player);

                    if(isGameDone) {
                        if(player1) {
                            System.out.print("Player 1 won the game! \n");            // print if player 1 won.
                        }
                        else {
                            System.out.print("Player 2 won the game! \n");            // print if player 2 won.
                        }
                        break;
                    }
                    player1 = !player1;
                    totalPlay++;
                }
            }
            if(totalPlay==row*col){
                System.out.println("Draw. Nobody wins.");                 // print if it is a draw.
                break;
            }
        }
        sc.close();
    }
}