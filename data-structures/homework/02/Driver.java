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
            if (tries > 2000) {
                System.out.println("aborting");
                stack.print();
                success = true;
            }
            stack.incrementMove();
            Boolean conflict = stack.isConflict();
            if (conflict) {
                System.out.println("isconflict for "+stack.potentialMove.rc.row+","+stack.potentialMove.rc.column);
                if (stack.potentialMove.rc.column == 8) {
                    System.out.println("end of column, resetting top from "+stack.top.rc.row+","+stack.top.rc.column);
                    while (stack.potentialMove.rc.column == 8) {
                        // no viable move in next row
                        // set potential next move to head
                        stack.potentialMove = stack.top;
                        // stack.potentialMove.rc.column++;
                        // reset head to previous good move
                        // stack.top = stack.top.next;
                        stack.pop();
                    }
                    // stack.potentialMove.rc.column++;
                    // if (stack.potentialMove.rc.column == 8) {
                    //     stack.potentialMove.rc.column = 1;
                    // }
                    
                    System.out.println("new top: "+stack.top.rc.row+","+stack.top.rc.column);
                    System.out.println("potential new move "+stack.potentialMove.rc.row+","+stack.potentialMove.rc.column);
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