import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

public class Prime {
    private int primeTail;
    private int queueCount;
    private int primeCount;
    private Integer queue[];
    private Integer prime[];

    public static void main(String[] args) {
        Prime prime = new Prime();
        prime.Init();
    }
    /*
    load queue to from 2 to n. Move head to prime and array.
    Remove multiples of head from queue.
    */
    public void Init(){
        System.out.println("please input number: ");
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            System.out.println("input was of bad format");
            sc.close();
            return;
        }
        int size = sc.nextInt();
        sc.close();
        size = size - 1;
        queue = new Integer[size];
        prime = new Integer[10];
        primeTail = queueCount = primeCount = 0;
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

        while (queue[0] != null) {
            add();
            remove(queue[0]);
        }
        for (int i = 0; i < prime.length; i++) {
            if (prime[i] == null){
                break;
            }
            if (i > 0 && i % 10 == 0) {
                System.out.println();
            }
            System.out.print(prime[i]+", ");
        }
    }

    // remove all multiples for the head of the queue
    public void removeMultiples() {
        int base = queue[0];
        for (int i = 0; i < queueCount; i++) {
            if (queue[i] != null && queue[i] % base == 0) {
                remove(queue[i]);
            }
        }
    }

    // remove single record. Need temp queue because not
    // removing from tail, but from middle of queue
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
        Integer[] tempArr = new Integer[prime.length+10];
        for (int i = 0; i < prime.length; i++) {
            tempArr[i] = prime[i];
        }
        prime = tempArr;
    }

    public boolean isFull() {
        return(primeCount == prime.length);
    }
}