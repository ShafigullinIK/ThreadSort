import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static int N = 3;
    static int M = 70_003;

    public static void main(String[] args) throws InterruptedException {
        int[] arr = genArray(M);
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
//        sort(arr1);
        long finish = System.currentTimeMillis();
        System.out.println("время = " + (finish - start));

        start = System.currentTimeMillis();
        int[][] mass = new int[N][];
        for (int i = 0; i < mass.length - 1; i++) {
            mass[i] = new int[M / N];
            for (int j = 0; j < mass[i].length; j++) {
                mass[i][j] = arr2[i * M / N + j];
            }
        }
        int last = mass.length - 1;
        mass[last] =
                new int[arr.length - (last) * M / N];
        for (int j = 0; j < mass[last].length; j++) {
            mass[last][j] = arr2[last * M / N + j];
        }
        Sorter[] sorters = new Sorter[N];
        for (int i = 0; i < sorters.length; i++) {
            sorters[i] = new Sorter(mass[i]);
        }
        for (Sorter s : sorters) {
            s.start();
        }
        for (Sorter s : sorters) {
            s.join();
        }
        int[] result = mass[0];
        for (int i = 1; i < N; i++) {
            result = merge(result, mass[i]);
        }
        finish = System.currentTimeMillis();
        System.out.println("время = " + (finish - start));
        System.out.println(Arrays.toString(result));


    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int ind1 = 0;
        int ind2 = 0;
        while (ind1 < arr1.length && ind2 < arr2.length) {
            if (arr1[ind1] < arr2[ind2]) {
                result[ind1 + ind2] = arr1[ind1];
                ind1++;
            } else {
                result[ind1 + ind2] = arr2[ind2];
                ind2++;
            }
        }
        if (ind1 == arr1.length) {
            for (int i = ind2; i < arr2.length; i++) {
                result[ind1 + i] = arr2[i];
            }
        } else {
            for (int i = ind1; i < arr1.length; i++) {
                result[i + ind2] = arr1[i];
            }
        }
        return result;
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    private static int[] genArray(int i) {
        Random r = new Random();
        int[] array = new int[i];
        for (int j = 0; j < array.length; j++) {
            array[j] = r.nextInt(1_000_000);
        }
        return array;
    }
}
