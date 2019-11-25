public class Node {
    private int data;
    private String str;
    private boolean isPrevUsed;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isPrevUsed() {
        return isPrevUsed;
    }

    public void setPrevUsed() {
        this.isPrevUsed = true;
    }
}