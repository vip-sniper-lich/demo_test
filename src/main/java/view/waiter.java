package view;

import javax.swing.*;
import java.awt.*;

//просто интерфейс и просто кнопка без функционала
public class waiter extends JFrame
{
    private JPanel waiter_panel;
    private JLabel name;
    private JPanel avatar;
    private JButton создатьНовыйЗаказаButton;
    private JTable table1;


    public waiter ()
    {
        setName("Форма официанта");
        setBounds(400,100, 800, 600);

        name.setText("Иванованов И.И.");
        //status.setText("онлайн");
        avatar.getLocation();
        создатьНовыйЗаказаButton.addActionListener(e -> создатьНовыйЗаказаButtonActionPerformed());

        setContentPane(waiter_panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void создатьНовыйЗаказаButtonActionPerformed()
    {
        JFrame frame = new JFrame("Создание заказа");
        frame.setBounds(400,100, 800, 600);
        frame.setLayout(new GridLayout(10, 2));
        frame.setVisible(true);


        JLabel table = new JLabel("Номер столика:");
        JComboBox<Integer>  table_number = new JComboBox();
        for(int i = 1; i <= 20; i++)
        {
            table_number.addItem(i);
        }
        JLabel number_people = new JLabel("Количество людей:");
        JTextField people = new JTextField();
        people.setText("0");
        JLabel food = new JLabel("Заказаная еда:");
        JLabel water = new JLabel("Заказанные напитки:");
        JTextField number_food = new JTextField();
        JTextField number_water = new JTextField();
        number_food.setText("");
        number_water.setText("");
        JButton add_new = new JButton("Добавить заказ");
        add_new.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(table);
        frame.add(table_number);
        frame.add(number_people);
        frame.add(people);
        frame.add(food);
        frame.add(number_food);
        frame.add(water);
        frame.add(number_water);
        frame.add(add_new);
    }
}
