package controler;

import model.ModelUser;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static controler.Controler.get_accept_in_bd;
import static controler.Controler.query;

//класс для тестов, вопросы?
public class Controler_test
{

    public static void main(String[] args) {
        try {
            ModelUser user = get_accept_in_bd("alex");
            String query = "SELECT status, date_open FROM staff WHERE id = '" + user.getIdUser() + "';";
            System.out.println("1");
            T<ResultSet> res = query("lox", "1111", query);
            System.out.println("2");
            ResultSet result = res.getItem();
            System.out.println("3");
            LocalDate currentTime = LocalDate.now();
            System.out.println("4");
            //создание объекта для открытие интерфейса авторизации
            System.out.println("Член группы " + currentTime);
            if (result.next())
            {
                System.out.println(result.getString(2));
                System.out.println(currentTime);
                System.out.println(LocalDate.parse(result.getString(2)));
                System.out.println("Член группы " + ChronoUnit.DAYS.between(currentTime, LocalDate.parse(result.getString(2))));
            }
        }catch (Exception e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}