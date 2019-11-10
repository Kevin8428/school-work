public class Driver {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run(1234, 0);
    }
    // print every time stepping into recursion.
    public void run(int n, int place) {
        String s = String.valueOf(n);
        if (place + 1 == s.length()) {
            System.out.println(s.charAt(place));
            return;
        } else {
            System.out.print(s.charAt(place));
        }
        run(n, place + 1);
    }
}