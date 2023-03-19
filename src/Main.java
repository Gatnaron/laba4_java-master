import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 4;
        int m = 4;
        int[][] arr = new int[n][m];


        Random random = new Random();
        for (int j = 0; j < m; j++)
        {
            for (int i = 0; i < n; i++)
            {
                arr[i][j] = random.nextInt(9) + 1;
            }
        }

        GUI ui = new GUI(5,5,500,300,"ЛР4");
        ui.InputIntArrayWindow();
    }

    static int[] Add(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    static int[][] Add(int[][] arr, int[] element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public static int[] SUM(int[][] arr)
    {
        int k = 0;
        int[] sum = new int[arr.length];
        for (int i = 0; i < arr[0].length; i++)
        {
            sum[i] = 0;
        }

        for (int j = 0; j < arr.length; j++)
        {
            for (int i = 0; i < arr[j].length; i++)
            {
                sum[k] += arr[i][j];
            }
            k++;
        }
        return sum;

    }
    private static int MAX_3(int[]sum)
    {
        int max = sum[0];
        int indexMAX = 0;
        for (int j = 0; j < sum.length; j++)
        {
            if(max <= sum[j])
            {
                max = sum[j];
                indexMAX = j;
            }
        }
        return indexMAX;
    }
    private static int MIN_3(int[]sum)
    {
        int min = sum[0];
        int indexMIN = 0;
        for (int j = 0; j < sum.length; j++)
        {
            if(min > sum[j])
            {
                min = sum[j];
                indexMIN = j;
            }
        }
        return indexMIN;
    }
    private static int MAX_4(int[]sum)
    {
        int max = sum[0];
        int indexMAX = 0;
        for (int j = 0; j < sum.length; j++)
        {
            if(max < sum[j])
            {
                max = sum[j];
                indexMAX = j;
            }
        }
        return indexMAX;
    }
    private static int MIN_4(int[]sum)
    {
        int min = sum[0];
        int indexMIN = 0;
        for (int j = 0; j < sum.length; j++)
        {
            if(min >= sum[j])
            {
                min = sum[j];
                indexMIN = j;
            }
        }
        return indexMIN;
    }

    private static void swap(int[][] arr, int indexMIN, int indexMAX) {
        int tmp = 0;
        for (int j = 0; j < arr[0].length; j++)
        {
            tmp = arr[indexMAX] [j];
            arr[indexMAX] [j] = arr[indexMIN] [j];
            arr[indexMIN] [j] = tmp;
        }
    }

    public static int[][] Task3(int[][] arr)
    {
        int[] arr_ = SUM(arr);
        int max_ = MAX_3(arr_);
        int min_ = MIN_3(arr_);
        swap(arr, min_, max_);
        return arr;
    }
    public static int[][] Task4(int[][] arr)
    {
        int[] arr_ = SUM(arr);
        int max_ = MAX_4(arr_);
        int min_ = MIN_4(arr_);
        swap(arr, min_, max_);
        return arr;
    }

}