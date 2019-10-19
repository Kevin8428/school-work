public class Stack {
    public Move top;

    public void push(RowColumn rc) {
        Move m = new Move();
        m.Set(rc);
        m.SetNext(top);
        top = m;
    }

    public void pop(){
        top = top.GetNext();
    }

    public void clear(){
        top = null;
    }

    public boolean isEmpty(){
        return top == null;
    }
}