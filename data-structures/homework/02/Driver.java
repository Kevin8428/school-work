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
        run(stack);
    }
    public void run(Stack stack){
        while (!success && !stack.isEmpty()) {
            Boolean isConflict = stack.isSameRow();
            if (isConflict) {
                stack.pop();
            } else {
                stack.push();
            }
        }
    }
}