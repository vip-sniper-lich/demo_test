package model;

//вроде надо, а вроде и не очень
public class LocalDataBase
{
    private String column [];
    private String line [] [];

    public LocalDataBase (String [] column, String [] [] line)
    {
        System.out.println("Копирование данных бд");
        this.column = column;
        this.line = line;
    }

    //гетеры
    public String[] getColumn()
    {
        return column;
    }
    public String[][] getLine()
    {
        return line;
    }
    public String getColumn(int i)
    {
        return column [i];
    }
    public String[] getLine(int i)
    {
        return line [i];
    }
    public String getLine(int i, int j)
    {
        return line [i][j];
    }
    //гетеры
}
