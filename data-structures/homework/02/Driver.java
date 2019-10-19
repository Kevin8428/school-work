public class Driver {
    private Boolean success;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.init();
    }
    public void init(){
        Stack stack = new Stack();
        RowColumn rc = new RowColumn();
        rc.Set(1, 1);
        stack.push(rc);
        success = false;
        run(stack);
    }
    public void run(Stack stack){
        Integer tries = 0;
        while (!success && !stack.isEmpty()) {
            tries++;
            if (tries > 80) {
                System.out.println("aborting");
                stack.print();
                success = true;
            }
            stack.incrementMove();
            Boolean conflict = stack.isConflict();
            if (conflict) {
                System.out.println("isconflict");
            } else {
                // System.out.println("no conflict - adding");
                // System.out.println("potential row: " + stack.potentialMove.rc.row);
                // System.out.println("potential col: " + stack.potentialMove.rc.column);
                // System.out.println("row: " + stack.top.rc.row);
                // System.out.println("col: " + stack.top.rc.column);
                stack.push(stack.potentialMove.rc);
            }
        }
    }
}