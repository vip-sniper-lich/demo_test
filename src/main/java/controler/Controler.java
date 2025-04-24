package controler;

import model.*;     //ну импорт моделей
import view.Dialog_Message;
import java.awt.*;
import java.sql.*;  //мне кажется это очевидно что
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static controler.Controler_view.role;
import static controler.Controler_view.start;

//класс контролера
public class Controler
{

    public Controler ()
    {
        start();
    }
    //Проверка существования пользователя
    public static ModelUser get_accept_in_bd (String login)
    {
        ModelUser model = null;     //создаётся модель пользователя
        try
        {
            //запрос
            String query1 = "SELECT id FROM staff WHERE staff.login = '" + login + "';";
            String query2 = "SELECT \n" +
                    "    name.last_name, \n" +
                    "    name.first_name, \n" +
                    "    name.patronymic,\n" +
                    "    rols.role\n" +
                    "FROM \n" +
                    "    staff \n" +
                    "JOIN \n" +
                    "    name ON staff.id_name = name.id\n" +
                    "JOIN\n" +
                    "    rols ON rols.id_role = staff.id_role\n" +
                    "WHERE \n" +
                    "    staff.login = '" + login + "';";
            //метод, который выполняет запрос
            T<ResultSet> resultSetT = query("postgres", "1111", query1);
            ResultSet resultSet = resultSetT.getItem();

            System.out.println("Вывод сохранён 1 запрос");
            //сохраняет вывод запроса
            //если в запросе есть данные (вроде как)
            if (resultSet != null)
            {
                if (resultSet.next())
                {
                    model = new ModelUser(resultSet.getString(1));
                }
            }
            else
            {
                return null;
            }

            //вывод в консоль для проверки
            if (model != null)
            {
                model.getData();
            }


            //вывод модели из метода
            return model;
        }
        catch (Exception e)
        {
            System.out.println("Ошибка подключения: " + e.getMessage());
            return null;    //чтоб java не ругалась
        }
    }
    //Проверка существования пользователя
    //Проверка на пустые поля
    //я это даже разбирать не буду
    public static boolean cheak_accept(String login, String password)
    {
        boolean cheak = false;

        if (!"".equals(login))
        {
            if (!"".equals(password))
            {
                cheak = true;
            }
        }
        return cheak;
    }
    //Проверка на пустые поля
    //проверка роли
    public static boolean get_accept (String login, String password, Point point)
    {
        String user_role;    //id роли в бд
        //запрос
        try
        {
            ModelUser user = get_accept_in_bd(login);

            if (user == null)
            {
                throw new New_Exception("Пароль или логин введены не правильно");
            }
            else
            {
                String query = "SELECT status, date_open FROM staff WHERE id = '" + user.getIdUser() + "';" ;
                //String query = "SELECT true FROM staff WHERE id = '" + user.getIdUser() + "' AND + password = '" + password + "';" ;
                /*String query2 = "SELECT name.last_name, name.first_name, name.patronymic, rols.role FROM staff \n" +
                        "JOIN name ON staff.id_name = name.id\n" +
                        "JOIN rols ON rols.id_role = staff.id_role\n" +
                        "WHERE staff.id = '" + user.getIdUser() + "';";*/
                T<ResultSet> res = query("lox", "1111", query);
                if(res.getItem() != null)
                {
                    ResultSet result = res.getItem();
                    if(!result.getString(1).equals("bloced"))
                    {
                        LocalDate currentTime = LocalDate.now();
                        if (ChronoUnit.DAYS.between(currentTime, LocalDate.parse(result.getString(2))) > 31)
                        {
                            if (result.getString(1).equals("active"))
                            {
                                query("lox", "1111", "UPDATE staff SET status = " + "bloced" + " WHERE id ='" + user.getIdUser() + "';");
                            }
                            else if (result.getString(1).equals("new"))
                            {

                            }
                        }
                    }
                }
            }

            user_role = user.getRoleUser();
            //пока есть что-то в запросе (вроде как)
            System.out.println("role user: " + user_role);

            //определение что за роль
            if (user_role.equals("admin"))
            {
                return role("admin", user);
            }
            else if (user_role.equals("waiter"))
            {
                return role("waiter", user);
            }
            else if (user_role.equals("povar"))
            {
                 return role("povar", user);
            }
            else
            {
                throw new IllegalArgumentException("Crash system");     //чтоб жизнь мёдом не казалась
            }

        }
        catch (New_Exception e)
        {
            Dialog_Message message = new Dialog_Message();
            message.choice(new Point(point.x-60,point.y-125),e);
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Ошибка подключения: " + e.getMessage());
            return false;
        }
    }
    //проверка роли
    //запросы к бд
    //самое интересное
    private static int type_query (String query)
    {
        //сохранения запроса по словесно?
        String[] words = query.trim().split("\\s+");
        //сохранение первого слова
        String query_type = words.length > 0 ? words[0] : "";

        //вывод запроса для проверки
        System.out.println("Запрос:" + query);
        //вывод 1 слова для проверки
        System.out.println("Первое слова запроса:" + query_type);
        //тип запроса
        int type = 0;

        try
        {
            //распределение
            switch (query_type) {
                case "INSERT" -> type = 1;
                case "SELECT" -> type = 2;
                case "CREATE" -> type = 3;
                case "UPDATE" -> type = 4;
                case null, default -> throw new New_Exception("Пароль или логин введены не правильно");
            }
        }
        catch (New_Exception e)
        {
            Dialog_Message message = new Dialog_Message();
            PointerInfo a = MouseInfo.getPointerInfo();
            message.choice(a.getLocation(), e);
        }

        //тип запроса для проверки
        System.out.println("Тип запроса определён:" + type);
        return type;
    }
    public static T query(String login, String password, String query)
    {
        //ссылка на бд

        String url = "jdbc:postgresql://localhost:5433/test";
        try
        {
            switch (type_query(query))
            {
                //insert
                case (1) ->
                {
                    Class.forName("org.postgresql.Driver");
                    Connection connection = DriverManager.getConnection(url, login, password);
                    System.out.println("Запрос: " + query);


                    T<Integer> item = new T();
                    item.setItem(Insert(connection, query));
                    connection.close();
                    return item;
                }
                //SELECT
                case (2) ->
                {
                    Class.forName("org.postgresql.Driver");
                    //определения какая именно бд

                    // Устанавливаем соединение
                    Connection connection = DriverManager.getConnection(url, login, password);

                    T<ResultSet> item = new T();
                    item.setItem(Select(connection, query));

                    // Закрываем соединение
                    connection.close();
                    return item;
                }
                //CREATE
                case (3) ->
                {
                    Class.forName("org.postgresql.Driver");
                    Connection connection = DriverManager.getConnection(url, login, password);

                    T<Boolean> item = new T();
                    item.setItem(Create(connection, query));

                    connection.close();
                    return item;
                }
                case (4) ->
                {
                    Class.forName("org.postgresql.Driver");
                    Connection connection = DriverManager.getConnection(url, login, password);

                    T<Integer> t = new T();
                    t.setItem(Update(connection, query));

                    connection.close();
                    return t;

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    private static ResultSet Select(Connection conn, String query)
    {
        ResultSet result;
        try
        {
            //для проверки
            System.out.println("Подключение к базе данных успешно! 2P");
            //чтобы можно было туда-сюда вертеть ячейки
            Statement resultUsers = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ты до сих пор не запомнил что делает этот кусок кода?
            result = resultUsers.executeQuery(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
    private static int Insert(Connection conn, String query)
    {
        try
        {
            Statement resultUsers = conn.createStatement();
            return resultUsers.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static boolean Create(Connection conn, String query)
    {
        try
        {
            Statement resultUsers = conn.createStatement();
            return resultUsers.execute(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static int Update (Connection conn, String query)
    {
        try
        {
            Statement resultUsers = conn.createStatement();
            return resultUsers.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    //запросы к бд
}