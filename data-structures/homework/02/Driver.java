public class Driver {
    private Boolean success;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.init();
    }
    public void init(){
        Stack stack = new Stack();
        RowColumn rc = new RowColumn();
        // create initial move of 1,1
        // stack.start is the first point being tested
        // if no solutions work for 1,1 this will increment to 1,2; 1,3 etc.
        Move move = new Move();
        rc.set(1, 1);
        move.rc = rc;
        stack.start = move;
        stack.newRow = false;
        success = false;
        run(stack);
    }
    // increment "potentialMove", which is a Move that isn't yet placed on the stack
    // if a potentialMove does not conflict with all moves on the stack, then it's added
    // to the stack.
    // Every row and column must only have 1 move. If a row reaches column 8 without a
    // valid potentialMove, pop the latest move off the stack and set potentialMove to that
    // latest move, plus one column. 
    public void run(Stack stack){
        while (!success) {
            stack.incrementMove();
            Boolean conflict = stack.isConflict();
            if (conflict) {
                if (stack.potentialMove.rc.column == 8) {
                    while (stack.potentialMove.rc.column == 8) {
                        stack.potentialMove = stack.top;
                        stack.pop();
                    }
                }
            } else {
                stack.push(stack.potentialMove.rc);
                if (stack.count == 8){
                    success = true;
                    System.out.println("done!");
                    stack.print();
                }
                stack.incrementMoveNextRow();
            }
        }
    }
}