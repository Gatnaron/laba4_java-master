import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static int[][] ReadIntArray(String path)
            throws IOException {
        int[][] array = new int[0][0];
        Scanner scanner = new Scanner(new File(path));


        while (scanner.hasNextLine())
        {
            Scanner colReader = new Scanner(scanner.nextLine());
            int[] tempo = new int[0];
            while (colReader.hasNextInt())
            {
                tempo = Main.Add(tempo,colReader.nextInt());
            }
            array = Main.Add(array,tempo);
            colReader.close();
        }
        scanner.close();
        return array;
    }
    public static int WriteIntArray(String path, int[][] arr) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                outputWriter.write(Integer.toString(arr[i][j]));
                if (j != arr[i].length-1) outputWriter.write(" ");
            }
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();

        return 1;
    }
}
