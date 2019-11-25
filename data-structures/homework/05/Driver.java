public class Driver {
    

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.init();
    }

    public void init() {
        OpenAddressHash table = new OpenAddressHash();
        table.put(3, "");
        table.put(4, "");
        table.put(5, "");
        table.put(6, "");
        table.put(5 + 31, "");
        table.put(5 + 31 + 31, "");
    }

}