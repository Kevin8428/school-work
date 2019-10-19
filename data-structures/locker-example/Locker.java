public class Locker {
    private static int LockersAvailable;
    // before declared, is zero because it's an int. if was object == null
    private static boolean isSetup = false;

    private String combination;
    private boolean isAllocated;
    private boolean isLocked;

    // how keep ppl from callingn constructor - make it private
    private Locker() {
        combination = null;
        // noone will call this
    }

     public static void setupSystem(int lockerCount) {
         LockersAvailable = lockerCount;
         isSetup = true;
     }


    public Locker getInstance() {
        if (!isSetup) {
            // some message to make change
            return null;
        }
        if (LockersAvailable > 0 ) {
            LockersAvailable--;
            Locker tmp = newLocker();
            tmp.isAllocated = true;
            return tmp;
        }
        return null;
    }
    public boolean setLockerCombination(String combination) {
        if (combination != null) {
            return false;
        } else {
            combination = this.combination;
            return true;
        }
    }
    public boolean lockLocker(String combination) {
        if (combination == null) {
            return false;
        }
        isLocked = true;       
        return true;
    }

    public void printLockersAvailable() {
        System.out.print(LockersAvailable + " lockers available");
    }
}