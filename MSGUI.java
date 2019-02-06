import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MSGUI extends JPanel
{
   private MSScoreboard board;
   private MSGameBoard gBoard;
   private JButton easy;
   private JButton medium;
   private JButton hard;
   private JButton back;
   private JPanel bottom;
   private JPanel middle;
   private JButton resetButton;
   private JButton flagButton;
   private JButton exit;
   private boolean flags;
   private JLabel flagOn;
   
   
   public MSGUI() throws IOException
   {
      flags = false;
      flagOn= new JLabel("Flags OFF");
      setLayout(new BorderLayout());
      board =new MSScoreboard();
      add(board,BorderLayout.NORTH);
      gBoard= new MSGameBoard();
      middle = new JPanel();
      middle.setLayout(new GridLayout(12,3));
      easy=new JButton("Easy");
      easy.addActionListener(new action(3));
      middle.add(easy);
      medium=new JButton("Medium");
      medium.addActionListener(new action(4));
      
      middle.add(medium);
      hard=new JButton("Hard");
      hard.addActionListener(new action(5));
      middle.add(hard);
      
      middle.add(new JLabel("Easy Scores:"));
      middle.add(new JLabel("Medium Scores:"));
      middle.add(new JLabel("Hard Scores:"));
      int a = 0;
      int b = 0;
      int c = 0;
      for(int i = 0; i< 30;i++){
         if(i%3==0)
         {
            if(board.getScoresList().size()>a)
               middle.add(new JLabel(board.getScoresList().get(a).toString()));
            else 
               middle.add(new JLabel("-"));
            a++;
         }
         else if(i%3==1)
         {
            if(board.getScoresList2().size()>b)
               middle.add(new JLabel(board.getScoresList2().get(b).toString()));
            else 
               middle.add(new JLabel("-"));
            b++;
         }
         else if(i%3==2)
         {
            if(board.getScoresList3().size()>c)
               middle.add(new JLabel(board.getScoresList3().get(c).toString()));
            else 
               middle.add(new JLabel("-"));
            c++;
         }
      }
      add(middle,BorderLayout.CENTER);
      //gBoard.setVisible(false);
      //add(gBoard,BorderLayout.CENTER);
      bottom = new JPanel();
      bottom.setLayout( new FlowLayout());
      exit= new JButton("Exit");
      exit.addActionListener(new action(2));
      bottom.add(exit);
      resetButton = new JButton("Reset");
      resetButton.addActionListener(new action(0));
      //bottom.add(resetButton);
      flagButton = new JButton("Toggle Flags");
      flagButton.addActionListener(new action(1));
      //bottom.add(flagButton);
      //bottom.add(flagOn);
      add(bottom, BorderLayout.SOUTH);
      back = new JButton("Back");
      back.addActionListener(new action(6));
   }
   
   public void showMenu()
   {
      gBoard.setVisible(false);
      middle = new JPanel();
      middle.setLayout(new GridLayout(12,3));
      easy=new JButton("Easy");
      easy.addActionListener(new action(3));
      middle.add(easy);
      medium=new JButton("Medium");
      medium.addActionListener(new action(4));
      
      middle.add(medium);
      hard=new JButton("Hard");
      hard.addActionListener(new action(5));
      middle.add(hard);
      
      middle.add(new JLabel("Easy Scores:"));
      middle.add(new JLabel("Medium Scores:"));
      middle.add(new JLabel("Hard Scores:"));
      int a = 0;
      int b = 0;
      int c = 0;
      for(int i = 0; i< 30;i++){
         if(i%3==0)
         {
            if(board.getScoresList().size()>a)
               middle.add(new JLabel(board.getScoresList().get(a).toString()));
            else 
               middle.add(new JLabel("-"));
            a++;
         }
         else if(i%3==1)
         {
            if(board.getScoresList2().size()>b)
               middle.add(new JLabel(board.getScoresList2().get(b).toString()));
            else 
               middle.add(new JLabel("-"));
            b++;
         }
         else if(i%3==2)
         {
            if(board.getScoresList3().size()>c)
               middle.add(new JLabel(board.getScoresList3().get(c).toString()));
            else 
               middle.add(new JLabel("-"));
            c++;
         }
      }
      add(middle,BorderLayout.CENTER);
      middle.setVisible(true);
      add(middle,BorderLayout.CENTER);
      resetButton.setVisible(false);
      flagButton.setVisible(false);
      flagOn.setVisible(false);
      back.setVisible(false);
   }
   
   public void hideMenu(int n)
   {
      /*easy.setVisible(false);
      medium.setVisible(false);
      hard.setVisible(false);*/
      middle.setVisible(false);
      if(n==3)
         gBoard.setNumBombs(50);
      else if(n==4)
         gBoard.setNumBombs(63);
      else //if(n==5)
         gBoard.setNumBombs(83);
      gBoard.reset();
      gBoard.setVisible(true);
      add(gBoard,BorderLayout.CENTER);
      resetButton.setVisible(true);
      flagButton.setVisible(true);
      flagOn.setVisible(true);
      back.setVisible(true);
      bottom.add(resetButton);
      bottom.add(back);
      bottom.add(flagButton);
      bottom.add(flagOn);
      
   }
   
   public void pressShift(boolean b)
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
         }
         else if(num==2)
         {
            System.exit(0);
         }
         else if(num==6)
         {
            showMenu();
         }
         else
         {
            hideMenu(num);
         }
         
      }
   }
   
}