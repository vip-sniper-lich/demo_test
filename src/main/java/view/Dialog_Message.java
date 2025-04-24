package view;

import controler.New_Exception;
import javax.swing.*;
import java.awt.*;

//типа диалоги вызывать пока лень делать
public class Dialog_Message
{
    private JDialog message;

    public Dialog_Message ()
    {
        message = new JDialog();
    }

    public void choice (Point poin, New_Exception e)
    {
        message.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Отступы
        JLabel label = new JLabel("<html><center>" + e.getMessage()+ "</html></center>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        message.add(label, gbc);
        JButton button1 = new JButton("ОК");
        button1.setSize(149,14);
        gbc.gridy = 1;
        button1.addActionListener(e1 ->
        {
            message.dispose();
        });
        message.add(button1, gbc);
        message.setVisible(true);
        message.setSize(300, 200);
        message.setLocation(poin);
    }

}
