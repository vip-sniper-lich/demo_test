package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static controler.Controler.*;

public class Authorization extends JFrame
{
    //данная часть интерфейса сделан на интеладжи с помощью конструктора, так что удачи лол
    private JLabel Авторизация;
    private JTextField login;
    private JButton авторизацияButton;
    private JPasswordField password;
    private JPanel authorization;
    private JComboBox comboBox1;

    public Authorization ()
    {
        System.out.println("Запуск интерфейса авторизации");
        //удаление границ
        login.setBorder(null);
        password.setBorder(null);
        авторизацияButton.setBorder(null);
        //удаление границ

        //добавление панели на фрейм
        setContentPane(authorization);
        //добавление панели на фрейм

        //костыль для ленивых (не работает)
        comboBox1.addItem("not");
        comboBox1.addItem("admin");
        comboBox1.addItem("povar");
        comboBox1.addItem("waiter");
        comboBox1.addActionListener(e ->
        {
            if(comboBox1.getSelectedIndex() == 0)
            {
                login.setText("");
                password.setText("");
            }
            else if (comboBox1.getSelectedIndex() == 1)
            {
                login.setText("alex");
                password.setText("1111");
            }
            else if (comboBox1.getSelectedIndex() == 2)
            {
                login.setText("povar");
                password.setText("povar");
            }
            else if (comboBox1.getSelectedIndex() == 3)
            {
                login.setText("waiter");
                password.setText("waiter");
            }
        });
        /*костыль для ленивых(не работает)
            его суть в том чтобы не вводить каждый раз логин и пароль в поля авторизации.
        */

        //Добавление размера и место появления на экране для фрейма
        setBounds(400,100, 640,480);
        //типа чтоб видно было
        setVisible(true);
        //добавление функции кнопки
        авторизацияButton.addActionListener(e -> авторизацияButtonActionPerformed());
        //чтоб процесс работы приложения заканчивался
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //функционал кнопки
    private void авторизацияButtonActionPerformed()
    {
        //сохранение данных с полей интерфейса
        String login = this.login.getText();
        String password = this.password.getText();

        //проверка еа пустоту в полях
        if(cheak_accept(login, password))
        {
            System.out.println("Поле заполнено");
            //проверка роли пользователя
            if (get_accept(login, password, getLocation_dialog()))
            {
                LocalDate currentTime = LocalDate.now();
                query("lox", "1111", "UPDATE staff SET date_open = '" + currentTime + "' WHERE login =" +
                        "'" + login + "' AND password = '" + password + "';");
                dispose();
            }
        }
    }

    //get
    public Point getLocation_dialog ()
    {
        System.out.println(авторизацияButton.getSize().toString());
        return авторизацияButton.getLocationOnScreen();
    }
    //get
}