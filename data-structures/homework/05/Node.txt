public class Node {
    public int data;
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