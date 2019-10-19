public class Stack {
    public Move top;
    public int count;

    public void push(RowColumn rc) {
        Move m = new Move();
        m.Set(rc);
        m.SetNext(top);
        top = m;
        count++;
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

    public boolean isSameRow(){
        Boolean isSame = false;
        Move tmp = top;
        while(tmp != null) {
            Move next = tmp.GetNext();
            if (next == null) {
                return isSame;
            }
            if (top.rc.column == next.rc.column || top.rc.row == next.rc.row) {
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
            System.out.println("move row: "+ move.rc.row);
            System.out.println("move column: "+ move.rc.column);
            move = move.GetNext();
        }
    }
}