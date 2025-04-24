package controler;

import model.ModelUser;

import java.sql.ResultSet;
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
            if(result.next())
            {
                if(!result.getString(1).equals("bloced"))
                {
                    String query1 = "SELECT true FROM staff WHERE id = '" + user.getIdUser() + "' AND password = '1111';" ;
                    String query2 = "SELECT true FROM staff WHERE id = '" + user.getIdUser() + "' AND password = '111';" ;
                    T<ResultSet> t1 = query("lox", "1111", query1);
                    if (t1.getItem() != null)
                    {
                        if (t1.getItem().next()) {
                            if (t1.getItem().getBoolean(1))
                            {
                                System.out.println("Правильный вход: true");
                            }
                            else
                            {
                                System.out.println("Ошибка: false");
                            }
                            t1 = query("lox", "1111", query2);
                            if (t1.getItem() != null)
                            {
                                if (t1.getItem().next()) {
                                    System.out.println("\n\n\n true???? \n\n\n");
                                    System.out.println(t1.getItem().getString(1));
                                    if (t1.getItem().getBoolean(1)) {
                                        System.out.println("Ошибка: true");
                                    }
                                }
                                else
                                {
                                    System.out.println("Правильно: false");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Не правильно: false");
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}