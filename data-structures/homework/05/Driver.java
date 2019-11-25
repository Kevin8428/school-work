import java.util.Scanner;
public class Driver {
    

    public static void main(String[] args) {
        Driver driver = new Driver();
        System.out.println("preloading data into table for buckets 3,4,5,6 and two collisions");
        System.out.println("please input action(get, put, contains, delete or print): ");
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();
        if ("print".equals(action)) {
            driver.init(action, 0);
        } else {
            System.out.println("please input a number: ");
            int n = sc.nextInt();
            driver.init(action, n);
        }
    }

    public void init(String action, int n) {
        OpenAddressHash table = new OpenAddressHash();
        table.put(3, "");
        table.put(4, "");
        table.put(5, "");
        table.put(6, "");
        table.put(5 + 31, "");
        table.put(5 + 31 + 31, "");
        table.delete(4);
        switch (action) {
            case "get":
                System.out.println(table.get(n));
                break;
            case "put":
                table.put(n, "");
                break;
            case "contains":
                table.contains(n);
                break;
            case "delete":
                table.delete(n);
                break;
            case "print":
                table.printHash();
                break;
            default:
                break;
        }
    }

}