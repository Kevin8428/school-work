public class LockerManager {
    private int maxLockerCount;
    private Locker []lockers;

    public LockerManager(int count) {
        maxLockerCount = count;
        Locker.setupSystem(count);
        // when access static, access by class name, not instantiation
        lockers = new Locker[count];
    }

    public Locker getLocker() {
        if (Locker.)
    }
}