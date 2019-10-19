public class Move {
    private Move next;
    private RowColumn rc;

    public void Set(RowColumn data){
        System.out.println("row: "+ data.row);
        System.out.println("col: "+ data.column);
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