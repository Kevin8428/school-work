public class OpenAddressHash {
    private Node []table;
    private static final int TABLE_SIZE = 31;
    int count;

    public OpenAddressHash(){
        table = new Node[TABLE_SIZE];
        count = 0;
    }

    private int probe(int pos) {
        return(pos + 1) % TABLE_SIZE;
    }

    private boolean isFull() {
        return count == table.length;
    }

    private int hash(int k) {
        return k % TABLE_SIZE;
    }

    public boolean put(int k, String v) {
        // if already set, just return and say we set it
        if (find(k) != -1) {
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
        return true;
    }

    private int find(int k) {
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