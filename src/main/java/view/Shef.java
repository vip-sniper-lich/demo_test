package view;

import javax.swing.*;

//недоделано
public class Shef extends JFrame
{
    private JPanel shef_panel;
    private JPanel avatar;
    private JPanel food;
    private JTable table1;
    private JLabel name;

    public Shef ()
    {
        setContentPane(shef_panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
