import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MSMenu extends JPanel
{
   private JButton easy;
   private JButton medium;
   private JButton hard;
   
   public MSMenu()
   {
      
   }
   
   private class action implements ActionListener
   {
      private int num;
      public action(int n)
      {
         num=n;
      }
      public void actionPerformed(ActionEvent action)
      {
         if(num==0)
         {
            
            gBoard.reset();
         }
         else if(num==1)
         {
            gBoard.shift();
            if(flags)
            {
               flags=!flags;
               flagOn.setText("Flags OFF");
            }
            else if(!flags)
            {
               flags=!flags;
               flagOn.setText("Flags ON");
            }
            else if(num==2)
            {
               JOptionPane.showMessageDialog( new JOptionPane(),
                  "On my honor as a Woodson High School Student,\n"
                  + "I, [your name] certify that I have neither given \n"
                  + "nor received unauthorized aid on this assignment, \n"
                  + "that I have cited my sources for authorized aid, and \n"
                  + "that this project was created on or after May 10, 2017.", "Honor Code",
                  JOptionPane.INFORMATION_MESSAGE);
               System.exit(0);
            }
         }
      }
   }
   
}