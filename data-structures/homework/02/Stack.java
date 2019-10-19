public class Stack {
    public Move top;
    public Move potentialMove;
    public int count;

    public void push(RowColumn rc) {
        Move m = new Move();
        m.Set(rc);
        m.SetNext(top);
        top = m;
        count++;
    }

    public void incrementMove(){
        Move move = new Move();
        RowColumn rc = new RowColumn();
        move.rc = rc;
        if (potentialMove == null) {
            potentialMove = top;
        }
        if (potentialMove.rc.column == 8) {
            move.rc.column = 1;
            move.rc.row = potentialMove.rc.row + 1;
        } else {
            move.rc.column = potentialMove.rc.column + 1;
            move.rc.row = potentialMove.rc.row;
        }
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

    public boolean isConflict(){
        Boolean isSame = false;
        Move tmp = top;
        while(tmp != null) {
            if (tmp.rc.column == potentialMove.rc.column || tmp.rc.row == potentialMove.rc.row) {
                return true;
            }
            if (isDiagnalConflict(tmp, potentialMove)) {
                return true;
            }
            // test if (+/- 7) || (+/- 9)
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
        for (int i = 0; i < count; i++) {
            if (isEmpty()) {
                System.out.println("empty");
                return;
            }
            System.out.println("==================================");
            System.out.println("r: "+ move.rc.row);
            System.out.println("c: "+ move.rc.column);
            move = move.GetNext();
        }
    }
}