public class OpenAddressHash {
    private Node []table;
    private static final int TABLE_SIZE = 31;
    int count;

    // build new table
    public OpenAddressHash(){
        table = new Node[TABLE_SIZE];
        count = 0;
    }

    // wrapper for get to print true/false
    public boolean contains(int k){
        return get(k) != -1;
    }

    // get next location so we can see if open
    private int probe(int pos) {
        return(pos + 1) % TABLE_SIZE;
    }

    private boolean isFull() {
        return count == table.length;
    }

    // get location within table
    private int hash(int k) {
        return k % TABLE_SIZE;
    }

    // set at position k
    // return false at end if never found open position
    public boolean put(int k, String v) {
        // if already set, just return and say we set it
        if (get(k) != -1) {
            return true;
        }
        if (isFull()) {
            return false;
        }
        int pos = hash(k);
        boolean done = false;
        while(!done) {
            if ((table[pos] == null) || (table[pos].isPrevUsed())) {
                Node node = new Node();
                node.setData(k);
                table[pos] = node;
                count++;
                done = true;
            }
            pos = probe(pos);
        }
        return false;
    }
    // just set previously used flag
    public void delete(int k) {
        int pos = get(k);
        if (pos != -1) {
            table[pos].setPrevUsed();
            count--;
        }
    }

    public void printHash() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].isPrevUsed()){
                    System.out.println("table position: "+ i + " is previously used");
                } else {
                    System.out.println("table position: "+ i + " has value " + table[i].getData());
                }
            } else {
                System.out.println("table position: "+ i + " has value null");
            }
        }
    }

    public int get(int k) {
        int pos = position(k);
        int count = 0;

        while ((table[pos] != null) && (count < TABLE_SIZE)) {
            count++;
            if (table[pos].isPrevUsed()) {
                pos = probe(pos);
                continue;
            }
            if (table[pos].getData() == k) {
                return pos;
            }
            pos = probe(pos);
        }
        return -1;
    }

    private int position(int k) {
        return k % TABLE_SIZE;
    }
}