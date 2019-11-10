public class Driver {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run(1,4);
    }

    // recursive method
    public void run(int cur, int end) {
        if (cur == end) {
            print(cur - 1);
            return;
        }
        print(cur);
        run(cur + 1, end);
        print(cur);
    }

    // print result, if statement is for line break
    public void print(int n) {
        for (int i = 0; i < n; i++) {
            if (i == n-1){
                System.out.println("*");
            } else {
                System.out.print("*");
            }
        }
    }
}