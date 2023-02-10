/* Program for placement of N queens on NxN chessboard
 * in a way in which the queens cannot attack each one another.
 */
public class NQueens {

    // Method to place the "currentQueen" from "size" queens on the board
    // After placement, the column on the board at which the currentQueen is placed will be updated 
    // to corresponding number (0 to 7)  
    public static Boolean recursiveQueens(int[] board, int currentQueen, int size) {
        // base case: the "size" queen (or the last queen) is placed
        Boolean result = false;
        if (currentQueen == size)
            result = true;
        else { // Inductive case
            for(int i = 0; i < size && result == false; i++) {
                // place the current queen at the -ith column
                board[currentQueen] = i;
                // check whether there is a queen conflict after the placement
                if (!conflicts(board, currentQueen)) { // current placement is good to go
                    result = recursiveQueens(board, currentQueen + 1, size); // place the next queen
                } 
            }
        }
        return result;
    }
    // Place currentQueen at currentColumn
    public static Boolean recursiveQueens(int[] board, int currentQueen, int currentColumn, int size) {
        // base case: the "size" queen (or the last queen) is placed
        Boolean result = false;
        int colCount = 1;   // number of columns having been tried to place a queen
        if (currentQueen == size)
            result = true;
        else { // Inductive case
            for(int i = currentColumn; i < size && colCount <= size && result == false; i++, colCount++) {
                // place the current queen at the -ith column
                board[currentQueen] = i % size;
                // check whether there is a queen conflict after the placement
                if (!conflicts(board, currentQueen)) { // current placement is good to go
                    result = recursiveQueens(board, currentQueen + 1, size); // place the next queen
                } 
            }
        }
        return result;
    }

    // Check whether currentQueen causes conflicts with previous ones
    public static Boolean conflicts(int[] board, int currentQueen) {
        boolean hasConflict = false;
        for(int i = 0; i < currentQueen && hasConflict == false; i++) {
            if(board[i] == board[currentQueen]) {   // if there are 2 queens on the same column
                hasConflict = true;
            }
            if(currentQueen - i == abs(board[currentQueen] - board[i])) { // if there are 2 queens on the same diagonal
                hasConflict = true;    
            }  
        }
        return hasConflict;
    }

    // Return the absolute value of number n
    private static int abs(int n) {
        return (n < 0) ? (-1 * n) : n;
    }

    public static void printBoard(int[] board) {
        for(int i = 0; i < board.length; i++) { 
            System.out.print(board[i]);
            System.out.printf(", ");
        }
        System.out.println();
    }

    public static void resetBoard(int[] board, int size) {
        for (int i = 0; i < size; i++) {
            board[i] = -1;
        }
    }

    public static void main(String[] args) {
        int[] board = {-1, -1, -1, -1, -1, -1, -1, -1};    // data structure to store the column 
                                                                // where queen -ith is placed with -ith the indices                                          
        int size = 8;                                       // the number of queens to be placed on the board 
        for(int i = 0; i < size; i++) {
            resetBoard(board, size);
            recursiveQueens(board, 0, i, size);
            printBoard(board);
        }
    }


}