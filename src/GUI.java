import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI
{
    public JFrame window;
    public JTable table, stable;
    int[] nums_ = new int[0];
    int[][] sub_arra = {
            {1,1,1},
            {2,2,2}
    };
    public GUI(int x, int y, int width, int height, String title)
    {
        window = CreateUI(x,y,width,height,title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
    }
    public static JFrame CreateUI(int x, int y, int width, int height, String title)
    {
        JFrame window = new JFrame(title);
        window.setBounds(x,y,width,height);
        window.setMinimumSize(new Dimension(width,height));
        window.setLayout(null);
        window.setVisible(true);

        return window;
    }
    public void DrawTable(int x, int y, int width,int height, int rows, int columns)
    {
        table = new JTable(rows,columns);
        table.setBounds(x,y,width,height);

        window.add(table);
    }
    public void InputIntArrayWindow()
    {
        final JFileChooser fc = new JFileChooser();
        JButton[] buttons = new JButton[0];
        //Таблица
        table = new JTable(10,10);
        table.setBounds(10,10,window.getWidth()-window.getX()*3-150,window.getHeight() - 140);

        stable = new JTable(2,3);
        stable.setBounds(table.getX() + table.getWidth() + 10,table.getY(),window.getWidth()-(table.getX() + table.getWidth() + 35),table.getHeight());
        WriteIntTable(sub_arra,stable);
        //Открыть
        JButton open_button = new JButton("Открыть");
        open_button.setBounds(10,table.getHeight()+table.getX() + 10,100,30);
        ActionListener openAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    path = file.getPath();
                }
                try {
                    WriteIntTable(FileManager.ReadIntArray(path),table);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Данные из файла считаны!");
            }
        };
        open_button.addActionListener(openAct);
        //Сохранить в файл
        JButton save_button = new JButton("Сохранить");
        save_button.setBounds(open_button.getX(), open_button.getY()+ open_button.getHeight() + 5, open_button.getWidth(), open_button.getHeight());
        ActionListener saveAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] rd = ReadIntTable(table);
                for (int i = 0; i < rd.length;i++)
                {
                    for (int j = 0; j < rd[i].length;j++)
                    {
                        System.out.print(rd[i][j] + " ");
                    }
                    System.out.print('\n');
                }
                int returnVal = fc.showSaveDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path = file.getPath();
                }
                try {
                    FileManager.WriteIntArray(path,rd);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        save_button.addActionListener(saveAct);

        //Задание 3
        JButton task8b = new JButton("Задание 8");
        task8b.setBounds(open_button.getX()+open_button.getWidth()+20,open_button.getY(),120,30);
        ActionListener actionListener8 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteIntTable(Main.Task3(ReadIntTable(table)),table);
            }
        };
        task8b.addActionListener(actionListener8);

        //Задание 4
        JButton task24b = new JButton("Задание 24");
        task24b.setBounds(save_button.getX()+save_button.getWidth()+20,save_button.getY(),120,30);

        ActionListener actionListener24b = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinates crd = Main.Task4(ReadIntTable(table), ReadIntTable(stable));
                if(crd.y != -1)
                JOptionPane.showMessageDialog(null,"Координаты " + crd.y + ", " + crd.x);
                else JOptionPane.showMessageDialog(null,"Не найдено подходящей позиции!");
            }
        };
        task24b.addActionListener(actionListener24b);


        window.add(table);
        window.add(stable);
        window.add(open_button);
        window.add(save_button);
        window.add(task8b);
        window.add(task24b);

        window.hide();
        window.show();
    }
    public static int[][] ReadIntTable(JTable tablet)
    {
        int[][] nums = new int[0][0];
        for (int i = 0; i < tablet.getRowCount(); i++)
        {
            if(tablet.getValueAt(i,0)==null) continue;
            int[] t_arr = new int[0];
            for (int j = 0; j < tablet.getColumnCount(); j++) {
                int tmp = -18203;
                if (tablet.getValueAt(i, j) != null) {
                    try {
                        tmp = (int)Integer.parseInt(tablet.getValueAt(i, j).toString());
                    } catch (Exception ex) {
                    }
                    if (tmp != -18203) {
                        t_arr = Main.Add(t_arr, tmp);
                    }
                } else continue;
            }

            nums = Main.Add(nums,t_arr);
        }
        return nums;
    }
    public static DefaultTableModel WriteIntTable(int[][] array,JTable table)
    {
        DefaultTableModel model = new DefaultTableModel(array.length,array[0].length);
        table.setModel(model);
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++) {
                table.setValueAt(array[i][j],i,j);
            }
        }
        return model;
    }

}
