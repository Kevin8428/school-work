public class Driver {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.Init();
    }
    public void Init(){
        Stack stack = new Stack();
        RowColumn rc = new RowColumn();
        rc.row = 1;
        rc.column = 4;
        stack.push(rc);
        System.out.println(stack);
    }
}