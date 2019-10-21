public class Stack {
    public Move top;
    public Move start;
    public Boolean newRow;
    public Move potentialMove;
    public int count;

    public void push(RowColumn rc) {
        Move m = new Move();
        m.Set(rc);
        m.SetNext(top);
        top = m;
        count++;
    }
    // set r,c => top.row+1,1
    public void incrementMoveNextRow(){
            potentialMove.rc.column = 1;
            potentialMove.rc.row = top.rc.row+1;
            newRow = true;
    }
    // look for next valid move on the row. If on newRow, return so first item 
    // on new row is analyzed
    public void incrementMove(){
        Move move = new Move();
        RowColumn rc = new RowColumn();
        if (newRow) {
            newRow = false;
            return;
        }
        move.rc = rc;
        if (potentialMove == null && top == null) {
            potentialMove = start;
            return;
        }
        if (potentialMove == null) {
            potentialMove = top;
        }
        move.rc.column = potentialMove.rc.column + 1;
        move.rc.row = potentialMove.rc.row;
        potentialMove = move;
    }

    public void pop(){
        top = top.GetNext();
        count--;
    }

    public void clear(){
        top = null;
    }

    public boolean isEmpty(){
        return top == null;
    }

    // walk stack to see if potential move has same row/column/diagnal
    public boolean isConflict(){
        Boolean isSame = false;
        Move tmp = top;
        while(tmp != null) {
            if (tmp.rc.column == potentialMove.rc.column || tmp.rc.row == potentialMove.rc.row) {
                return true;
            }
            Boolean isDiagnal = isDiagnalConflict(tmp, potentialMove);
            if (isDiagnal) {
                return true;
            }
            tmp = tmp.GetNext();
        }

        return isSame;
    }
    // only need to test less than values, which works well for walking down the stack
    // for every n row you decrement, perform two checks
    // check 1: row, col == nextMove.rc.row - n, nextMove.rc.column - n
    // check 2: row, col == nextMove.rc.row - n, nextMove.rc.column + n)
    // if col value < 1 || col value > 8, ignore the check
    public Boolean isDiagnalConflict(Move tmp, Move nextMove) {

        Integer rowsToCheck = nextMove.rc.row -1;
        if (rowsToCheck < 1) {
            return false;
        }
        for (int i = rowsToCheck; i > 0; i--) {
            Integer row = nextMove.rc.row - i;
            Integer lowerColumn = nextMove.rc.column - i;
            Integer upperColumn = nextMove.rc.column + i;
            if (lowerColumn > 0) {
                if (tmp.rc.row == row && tmp.rc.column == lowerColumn) {
                    return true;
                }
            }
            if (upperColumn < 9) {
                if (tmp.rc.row == row && tmp.rc.column == upperColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    public void print(){
		Move move = top;
        String[][] board = new String[8][8];
        for (int i = 0; i < count; i++) {
            if (isEmpty()) {
                System.out.println("empty");
                return;
            }
            if (move == null) {
                return;
            }
            board[move.rc.row-1][move.rc.column-1] = "x";
            move = move.GetNext();
        }
        for (int row = 0; row < 8; row++) {
            System.out.println("----------------------------------------");
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == "x") {
                    System.out.print("| x |");
                } else {
                    System.out.print("|   |");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
}