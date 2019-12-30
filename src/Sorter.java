public class Sorter extends Thread {

    private int[] arr;

    public Sorter(int[] arr){
        this.arr = arr;
    }

    public int[] getArr(){
        return arr;
    }

    @Override
    public void run() {
        Main.sort(arr);
    }
}
