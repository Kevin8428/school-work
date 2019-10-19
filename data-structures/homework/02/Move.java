public class Move {
    public Move next;
    public RowColumn rc;

    public void Set(RowColumn data){
        RowColumn rc1 = new RowColumn();
        rc1.column = data.column;
        rc1.row = data.row;
        this.rc = rc1;
    }

    public RowColumn Get(){
        return rc;
    }

    public void SetNext(Move next) {
        this.next = next;
    }

    public Move GetNext(){
        return next;
    }
}