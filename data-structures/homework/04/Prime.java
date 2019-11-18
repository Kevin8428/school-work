import java.lang.Math;
import java.util.Arrays;

public class Prime {
    private int head;
    private int primeTail;
    private int queueCount;
    private int primeCount;
    private Integer queue[];
    private Integer prime[];

    public static void main(String[] args) {
        Prime prime = new Prime();
        prime.Init();
    }
    public void Init(){
        int size = 10;
        size = size - 1;
        queue = new Integer[size];
        prime = new Integer[size];
        head = primeTail = queueCount = primeCount = 0;
        // build queue
        for (int i = 2, j = 0; i < size + 2; i++, j++) {
            queue[j] = i;
            queueCount++;
        }
        int p = 0;
        do {
            p = queue[0];
            add();
            removeMultiples();
        }while( p < Math.sqrt(size) );
        System.out.println("done: "+Arrays.toString(prime));
        System.out.println("queue: "+Arrays.toString(queue));
    }

    public void removeMultiples() {
        System.out.println("removing multiples of "+queue[0]);
        int base = queue[0];
        for (int i = 0; i < queueCount; i++) {
            if (queue[i] != null && queue[i] % base == 0) {
                remove(queue[i]);
            }
        }
    }

    public boolean add() {
        if (isFull()) {
            resize();
        }
        prime[primeTail] = queue[0];
        primeTail++;
        primeCount++;
        return true;
    }

    public void resize() {
        Integer[] tempArr = new Integer[queueCount+10];
        for (int i = 0; i < queue.length; i++) {
            tempArr[i] = queue[i];
        }
        queue = tempArr;
    }

    public void remove(int nRemove) {
        Integer[] tempArr = new Integer[queueCount];
        for (int i = 0, j = 0; i < queue.length; i++) {
            if (queue[i] == null || nRemove == queue[i]) {
                continue;
            }
            tempArr[j] = queue[i];
            j++;
        }
        queue = tempArr;
    }

    public boolean isFull() {
        return(primeCount == queue.length);
    }

    public boolean isEmpty() {
        return(queueCount == 0);
    }
}