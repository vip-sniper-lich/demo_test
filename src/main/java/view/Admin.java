package view;

import controler.T;
import model.ModelUser;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static controler.Controler.query;

public class Admin extends JFrame
{
    ModelUser user;
    //лол тут ни чего нового
    private JPanel admin_panel;
    private JButton список_user;
    private JButton new_user;
    private JButton список_заказов;
    private JButton расписание;
    private JLabel admin_name;
    private JLabel role;
    private JLabel avatar;

    public Admin (ModelUser user)
    {
        this.user = user;
        avatar.setText("<html><><img src=https://kamtk.ru:9096/el-zurnal/el-dnevnik/Css/image1/profile.png alt=\"Изображение\" width=\"50\" height=\"50\" /> </html>");
        // задание размера и положения на экране фрейма
        setBounds(400,100, 800, 600);
        //заполнение интерфейса текстом
        список_user.setText("<html><center>Список<br>пользователей</center></html>");
        new_user.setText("<html><center>Регистрация<br>новых<br>пользователей</center></html>");
        список_заказов.setText("<html><center>Список<br>заказов</center></html>");
        расписание.setText("<html><center>Расписание</center></html>");
        //заполнение интерфейса текстом

        //Удаление границ
        список_user.setBorder(null);
        new_user.setBorder(null);
        список_заказов.setBorder(null);
        расписание.setBorder(null);
        //Удаление границ
        //заполнение интерфейса текстом
        admin_name.setText(user.getFirstnameUser() + " " + user.getN() + "." + user.getP() + ".");
        admin_name.setHorizontalAlignment(SwingConstants.RIGHT);
        //заполнение интерфейса текстом
        role.setText(user.getRoleUser());
        role.setHorizontalAlignment(SwingConstants.RIGHT);

        //добавление функции кнопки
        список_user.addActionListener(e -> список_userButtonActionPerformed());
        new_user.addActionListener(e -> new_userButtonActionPerformed());
        список_заказов.addActionListener(e -> список_заказовButtonActionPerformed());
        расписание.addActionListener(e -> расписаниеButtonActionPerformed());
        //добавление функции кнопки

        //Добавление панели на фрейм
        setContentPane(admin_panel);
        //чтоб видно было
        setVisible(true);
        //чтоб процесс работы приложения заканчивался
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //функционал кнопки для отображения списка пользователей
    private void список_userButtonActionPerformed()
    {
        //ручное создание интерфейса
        //создание фрейма
        JFrame frame = new JFrame("Создание нового пользователя");
        //добавление размера и положения на экране для фрейма
        frame.setBounds(400,100, 800, 600);
        //Добавление фрейму типа расположения элементов интерфейса
        frame.setLayout(new GridLayout(10, 3));
        //чтоб видно было
        frame.setVisible(true);

        //создание объекта модели
        DefaultTableModel model = new DefaultTableModel();
        //создание таблицы
        JTable table = new JTable(model);

        //ссылка на базу данных
        String url = "jdbc:sqlite:database.db";
        //подключение драйвера
        try (Connection conn = DriverManager.getConnection(url))
        {
            //есть ли подлючение
            if (conn != null)
            {
                // Выполняем запрос к базе данных
                String sql = "SELECT * FROM test_users";
                //це надо поверь
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                // Получаем метаданные для определения колонок
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Добавляем названия колонок в модель
                for (int i = 1; i <= columnCount; i++)
                {
                    model.addColumn(metaData.getColumnName(i));
                }

                // Добавляем данные в модель
                while (rs.next())
                {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++)
                    {
                        System.out.println(rs.getObject(i));
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
                //добавление таблицы на фрейм
                frame.add(new JScrollPane(table));
                //чтоб видно было
                frame.setVisible(true);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //функционал кнопки для добавления нового пользователя в бд
    private void new_userButtonActionPerformed()
    {
        //ручное создание интерфейса
        //создание фрейма и т.д.
        JFrame frame = new JFrame("Создание нового пользователя");
        frame.setBounds(400,100, 800, 600);
        frame.setLayout(new GridLayout(10, 3));
        frame.setVisible(true);

        JLabel last_name = new JLabel("Имя:");
        JLabel first_name = new JLabel("Фамилия:");
        JLabel patronymic = new JLabel("Отчество:");
        JLabel login = new JLabel("Логин:");
        JLabel password = new JLabel("Пароль:");
        JLabel role = new JLabel("Должность:");
        JLabel phone = new JLabel("Телефон:");

        JButton add_new = new JButton("Добавить пользователя");

        JTextField last_name_inp = new JTextField();
        JTextField first_name_inp = new JTextField();
        JTextField patronymic_inp = new JTextField();
        JTextField phone_inp = new JTextField();
        JTextField login_inp = new JTextField();
        //добавляю ограничение на количество символов
        JTextField password_inp = new JTextField(20);
        JComboBox<String> role_inp = new JComboBox();

        role_inp.addItem("Повар");
        role_inp.addItem("Официант");
        role_inp.addItem("Администратор");
        phone_inp.setText("+7");

        //добавление функции которая вносит изменение в бд
        add_new.addActionListener(e ->
        {
            String query1, query2, query3, query4, query5;
            ResultSet id;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime currentTime = LocalDateTime.now();
            String role_user = " ";
            if(role_inp.getItemAt(role_inp.getSelectedIndex()).equals("Администратор"))
            {
                role_user = "admin";
            }
            else if (role_inp.getItemAt(role_inp.getSelectedIndex()).equals("Официант"))
            {
                role_user = "povar";
            }
            else if (role_inp.getItemAt(role_inp.getSelectedIndex()).equals("Повар"))
            {
                role_user = "waiter";
            }
            /*
            query1 = "SELECT id_role FROM rols WHERE role = '" + role_user + "';";
            query2 = "SELECT id FROM name WHERE last_name = '" + last_name_inp.getText() + "', first_name = " +
                    "'" + first_name_inp.getText() + "', patronymic = '" + patronymic_inp.getText() + "';";
            try
            {
                T<ResultSet> res = query("lox", "1111", query2);
                id = res.getItem();
                res = query("lox", "1111", query1);
                ResultSet role = res.getItem();
                query4 = "SELECT MAX(id) FROM name;";
                res = query("lox", "1111", query4);


                ResultSet name = res.getItem();

                if (id != null)
                {
                    query3 = "INSERT INTO name (last_name, first_name, patronymic) VALUES ('" + last_name_inp.getText() + "', " +
                            "'" + first_name_inp.getText() + "', '" + patronymic_inp.getText() + "');";
                    query("lox", "1111", query3);
                    query5 = "INSERT INTO staff (id_role, id_name, login, password, date_open) \n" +
                            "VALUES ('" + role.getString(1) + "'" +
                            ",'" + name.getString(1) + "'," +
                            "'" + login_inp.getText() + "','" + password_inp.getText() + "','1'";
                    System.out.println("Запрос на создания пользователя: " + query5);
                    query("lox", "1111", query5);
                }
                else
                {
                    query5 = "INSERT INTO staff (id_role, id_name, login, password, date_open) \n" +
                            "VALUES ('" + role.getString(1) + "'" +
                            ",'" + id.getString(1) + "', '" + login_inp.getText() + "','" + password_inp.getText() + "'" +
                            ",'" + currentTime.format(formatter) + "';";
                    System.out.println("Запрос на создания пользователя: " + query5);
                    query("lox", "1111", query5);

                }
            }
            catch (SQLException e1)
            {
                System.out.println(e1.getMessage());
            }*/
            //закрытие фрейма
            frame.dispose();
        });

        //добавление элементов на фрейм
        frame.add(last_name);
        frame.add(last_name_inp);
        frame.add(first_name);
        frame.add(first_name_inp);
        frame.add(patronymic);
        frame.add(patronymic_inp);
        frame.add(phone);
        frame.add(phone_inp);
        frame.add(login);
        frame.add(login_inp);
        frame.add(password);
        frame.add(password_inp);
        frame.add(role);
        frame.add(role_inp);
        frame.add(add_new);
    }

    //функционал кнопки
    private void список_заказовButtonActionPerformed()
    {
        //угадай почему тут пусто
    }

    //функционал кнопки
    private void расписаниеButtonActionPerformed()
    {
        //угадай почему тут пусто
    }
}