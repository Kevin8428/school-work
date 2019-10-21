public class Driver {
    private Boolean success;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.init();
    }
    public void init(){
        Stack stack = new Stack();
        RowColumn rc = new RowColumn();
        rc.set(1, 1);
        Move move = new Move();
        move.rc = rc;
        stack.start = move;
        stack.newRow = false;
        // stack.push(rc);
        success = false;
        run(stack);
    }
    public void run(Stack stack){
        Integer tries = 0;
        while (!success) {
            tries++;
            if (tries > 80) {
                System.out.println("aborting");
                stack.print();
                success = true;
            }
            stack.incrementMove();
            Boolean conflict = stack.isConflict();
            if (conflict) {
                System.out.println("isconflict for "+stack.potentialMove.rc.row+","+stack.potentialMove.rc.column);
                if (stack.potentialMove.rc.column == 8) {
                    stack.top.rc.column++;
                    stack.potentialMove = top;
                    top = top.next;
                    System.out.println("end of column, resetting top");
                    // while (stack.top.rc.column == 8) {
                    //     stack.pop();
                    // }
                    System.out.println("top reset "+stack.top.rc.row+","+stack.top.rc.column);
                    // if top is not at end of column, move over one
                    // if top is end of column, pop and move new top over one
                    // end of column, move top over one and try again
                }
            } else {
                System.out.println("no conflict - adding "+stack.potentialMove.rc.row+","+stack.potentialMove.rc.column);
                stack.push(stack.potentialMove.rc);
                stack.print();

                if (stack.count == 8){
                    success = true;
                    System.out.println("done!");
                    stack.print();
                }
                stack.incrementMoveNextRow();
                // stack.potentialMove = null;
            }
        }
    }
}