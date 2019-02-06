import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class MineSweeperDriver
{
   public static boolean shiftIsPressed;
   public static MSGUI screen;
   public static boolean b=false;
   public static void main(String[] args) throws IOException
   {
   //I learned how to do this in Comp sci last year
   
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run()
            {
               MSScoreboard.updateSaves();
            }
            
         }, "Shutdown-thread"));
      shiftIsPressed=false;
      JFrame jamie = new JFrame("Mine Sweeper");
      screen = new MSGUI();
      jamie.setContentPane(screen);
      jamie.setSize(700,500);
      jamie.setLocation(100, 100);
      jamie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jamie.setResizable(false);
      jamie.setVisible(true);
      jamie.addKeyListener(new listen());
      
   }
   public static class listen implements KeyListener
   {
      public void keyTyped(KeyEvent e)
      { 
      
      }
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_SHIFT && !shiftIsPressed)
         {
            shiftIsPressed=true;
            screen.pressShift(shiftIsPressed);
         }
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_SHIFT && shiftIsPressed)
         {
            shiftIsPressed=false;
            screen.pressShift(shiftIsPressed);
         }
      }
   }

}