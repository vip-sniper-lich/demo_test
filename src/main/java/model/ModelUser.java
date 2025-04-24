package model;

import javax.swing.JOptionPane;

//а в лабе это объясняли!
public class ModelUser
{
    //
    private String idUser;
    private String nameUser;
    private String firstnameUser;
    private String patronymic;
    private String loginUser;
    private String passwordUser;
    private String roleUser;
    private String status;
    //

    //конструкторы
    public ModelUser (String id)
    {
        idUser = id;
        nameUser = "";
        firstnameUser = "";
        patronymic = "";
        loginUser = "";
        passwordUser = "";
        roleUser = "";
        status = "";
    }
    public ModelUser (String loginUser, String passwordUser, String roleUser)
    {
        idUser = "";
        nameUser = "";
        firstnameUser = "";
        patronymic = "";
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
        status = "";
    }
    public ModelUser (String nameUser, String firstnameUser, String patronymic, String loginUser, String passwordUser, String roleUser)
    {
        idUser = "";
        this.nameUser = nameUser;
        this.firstnameUser = firstnameUser;
        this.patronymic = patronymic;
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
        status = "";
    }
    public ModelUser (String idUser, String nameUser, String firstnameUser, String patronymic,
                      String loginUser, String passwordUser, String roleUser, String status)
    {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.firstnameUser = firstnameUser;
        this.patronymic = patronymic;
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
        this.status = status;
    }
    //конструкторы

    //set
    public void setIdUser( String idUser)
    {
        this.idUser = idUser;
    }
    public void setNameUser (String nameUser)
    {
        this.nameUser = nameUser;
    }
    public void setFirstnameUser(String firstnameUser)
    {
        this.firstnameUser = firstnameUser;
    }
    public void setPatronymic(String patronymic)
    {
        this.patronymic = patronymic;
    }
    public void setLoginUser (String loginUser)
    {
        this.loginUser = loginUser;
    }
    public void setRoleUser (String roleUser)
    {
        if("admin".equals(roleUser) || "waiter".equals(roleUser) || "povar".equals(roleUser))
        {
            this.roleUser = roleUser;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Этой должности нет!!!",
                    "Вы ввели неправильную должность!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void setPasswordUser (String passwordUser)
    {
        this.passwordUser = passwordUser;
    }
    //set

    //get
    public String getIdUser ()
    {
        return idUser;
    }
    public String getNameUser()
    {
        return nameUser;
    }
    public String getFirstnameUser()
    {
        return firstnameUser;
    }
    public String getLoginUser()
    {
        return loginUser;
    }
    public String getPasswordUser()
    {
        return passwordUser;
    }
    public String getRoleUser()
    {
        return roleUser;
    }
    public String getPatronymic ()
    {
        return patronymic;
    }
    public char getN()
    {
        return nameUser.charAt(0);
    }
    public char getP()
    {
        return patronymic.charAt(0);
    }
    public void getData ()
    {
        System.out.println("Id: " + getIdUser() + "\nname: " + getNameUser() + "\nlast name: " + getFirstnameUser() + "\n" +
                "Login: " + getLoginUser() + "\nPassword: " + getPasswordUser() + "\nRole: " + getRoleUser());
    }
    //get
}
