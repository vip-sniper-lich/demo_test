package controler;  //принадлежность к пакету controler

import model.ModelUser;
import view.*; //импорт графического интерфейса

public class Controler_view //название класса
{
    //метод определения роли
    public static boolean role (String role, ModelUser user)
    {
        Admin admin;
        waiter waiter;
        Shef powar;
        //проверка на роль админа
        if ("admin".equals(role))
        {
            //вывод в консоль сообщения
            System.out.println("автаризация пройдена");
            //создание объекта интерфейса админа
            admin = get_Admin(user);
            return true;
        }
        //проверка на роль официанта
        else if ("waiter".equals(role))
        {
            //вывод в консоль сообщения
            System.out.println("автаризация пройдена");
            //создание объекта интерфейса официанта
            waiter = get_Waiter();
            return true;
        }
        //проверка на роль повара
        else if("povar".equals(role))
        {
            //вывод в консоль сообщения
            System.out.println("автаризация пройдена");
            //создание объекта интерфейса официанта
            powar = get_Shef();
            return true;
        }
        return false;
    }

    //вывод формы админа
    private static Admin get_Admin(ModelUser user)
    {
        //создание объекта формы админа
        Admin admin = new Admin(user);
        //вывод админа
        return admin;
    }
    //вывод формы официанта
    private static waiter get_Waiter()
    {
        waiter waiter = new waiter();
        return waiter;
    }
    //вывод формы повара
    private static Shef get_Shef()
    {
        Shef shef = new Shef();
        return shef;
    }
    public static Authorization start ()
    {
        Authorization authorization = new Authorization();
        return authorization;
    }
}
