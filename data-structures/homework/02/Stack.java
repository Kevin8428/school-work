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
            tmp = tmp.GetNext();
        }
        return isSame;
    }

    public boolean isSameColumn(){
        return top == null;
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