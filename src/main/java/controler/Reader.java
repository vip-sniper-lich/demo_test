package controler;

import java.io.*;

//ненужная вещь
public class Reader
{
    private final String path = "C:\\test xml\\paths.xml";

    public Reader ()
    {
        System.out.println("Объект создан");
    }

    public String getUser () throws IOException
    {
        String text = "";
        BufferedReader read = new BufferedReader(new FileReader("C:\\test xml\\users\\users.txt"));

        while(read.ready())
        {
            text = read.readLine();

            if(text == "")
            {
                System.out.print("Походу зарегестрированных пользователей нет");
            }
        }


        return text;
    }
}
