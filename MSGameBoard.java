import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MSGameBoard extends JPanel
{
   
   private static JButton[][] squares;
   private static int[][] values;
   private static ImageIcon bomb,flag, one,two,three,four,five,six,seven,eight,blank,noFlag;
   private static boolean shift=false;
   private static boolean firstClick;
   private static int numBombs;
   private static int numFlags;
   private static int numberOfRows;
   private static int numberOfColumns; 
   private static Timer timer;
   
   public MSGameBoard(/*MSScoreboard*/)
   {
      //sBoard=b;
      firstClick = true;
      timer = new Timer(1000, new AddSecond());
      numBombs=45;
      numFlags=0;
      setLayout(new GridLayout(20,20));
      bomb = new ImageIcon("bomb.GIF");
      flag = new ImageIcon("images/flag.GIF");
      one = new ImageIcon("images/one.GIF");
      two = new ImageIcon("images/two.GIF");
      three = new ImageIcon("images/three.GIF");
      four = new ImageIcon("images/four.GIF");
      five = new ImageIcon("images/five.GIF");
      six = new ImageIcon("images/six.GIF");
      seven = new ImageIcon("images/seven.GIF");
      eight = new ImageIcon("images/eight.GIF");
      blank = new ImageIcon("images/eight(1).GIF");
      noFlag = new ImageIcon("images/noflag.GIF");
      
      squares = new JButton[20][20];
      values = new int[20][20];
      for(int r=0; r<values.length;r++)
      {
         for(int c=0; c<values[0].length;c++)
         {
            squares[r][c]= new JButton();
            squares[r][c].addActionListener(new action(r,c));
            add(squares[r][c]);
            values[r][c]=0;
         }
      }
      
   }
   
   public static void setNumBombs(int n)
   {
      numBombs=n;
      numFlags=n;
      MSScoreboard.setBombs(n);
   }
   
   public static void checkWin()
   {
      for(int a = 0; a<values.length; a++)
      {
         for(int b = 0; b<values[a].length; b++)
         {
            if(squares[a][b].getIcon()!=null && squares[a][b].getIcon().equals(flag))
            {
               if(values[a][b]!=-9)
                  return;
            }
            else if(values[a][b]==-9)
            {
               return;
            }
         }
      }
      timer.stop();   
      String inputValue = JOptionPane.showInputDialog("YOU WON! Score: "+MSScoreboard.getScore()+"\nEnter your name.");
      if(numBombs==50)
         MSScoreboard.changeScores(inputValue,MSScoreboard.getScore());
      else if(numBombs==63)
         MSScoreboard.changeScores2(inputValue,MSScoreboard.getScore());
      else if(numBombs==83)
         MSScoreboard.changeScores3(inputValue,MSScoreboard.getScore());
      reset();
   }
   
   public static void reset()
   {
      timer.stop();
      MSScoreboard.setBombs(numBombs);
      for(int r=0; r<values.length;r++)
      {
         for(int c=0; c<values[0].length;c++)
         {
            values[r][c]=0;
            squares[r][c].setIcon(null);
            if(!squares[r][c].isEnabled())
            {
               squares[r][c].setEnabled(true);
            }
         }
      }
      MSScoreboard.setScore(0);
      firstClick=true;
   }
   public static void fillBoard(int x,int y)
   {
      
      for(int i=0;i<numBombs;i++)
      {
         int r=(int)(Math.random()*20);
         int c=(int)(Math.random()*20);
         if(r==x &&c==y)
            i--;
         else if(r==x &&c==y+1)
            i--;
         else if(r==x &&c==y-1)
            i--;
         else if(r==x-1 &&c==y)
            i--;
         else if(r==x-1 &&c==y-1)
            i--;
         else if(r==x-1 &&c==y+1)
            i--;
         else if(r==x+1 &&c==y+1)
            i--;
         else if(r==x+1 &&c==y)
            i--;
         else if(r==x+1 &&c==y-1)
            i--;
         else if(values[r][c]==-9)
            i--;
         else
         {
            values[r][c]=-9;
            suround(r,c);
            
         }
      }
   }
   public static void suround(int r, int c)
   {
      
      if(r-1>=0)
      {
         if(c-1>=0)
         {
            if(values[r-1][c-1]!=-9)
               values[r-1][c-1]-=1;
         }
         if(values[r-1][c]!=-9)
            values[r-1][c]-=1;
         if(c+1<20)
         {
            if(values[r-1][c+1]!=-9)
               values[r-1][c+1]-=1;
         }
      }
      if(c-1>=0)
      {
         if(values[r][c-1]!=-9)
            values[r][c-1]-=1;
      }
      if(c+1<20)
      {
         if(values[r][c+1]!=-9)
            values[r][c+1]-=1;
      }
      if(r+1<20)
      {
         if(c-1>=0)
         {
            if(values[r+1][c-1]!=-9)
               values[r+1][c-1]-=1;
         }
         if(values[r+1][c]!=-9)
            values[r+1][c]-=1;
         if(c+1<20)
         {
            if(values[r+1][c+1]!=-9)
               values[r+1][c+1]-=1;
         }
      }
   }
   public static void show(int r, int c)
   {
      
      values[r][c]*=-1;
      if(values[r][c]==0)
         squares[r][c].setEnabled(false);
      
      if(values[r][c]==0)
      {
         if(r-1>=0)
         {
            if(c-1>=0)
            {
               if(squares[r-1][c-1].isEnabled())
                  show(r-1,c-1);
            }
            if(squares[r-1][c].isEnabled())
               show(r-1,c);
            if(c+1<20)
            {
               if(squares[r-1][c+1].isEnabled())
                  show(r-1,c+1);
            }
         }
         if(c-1>=0)
         {
            if(squares[r][c-1].isEnabled())
               show(r,c-1);
         }
         if(c+1<20)
         {
            if(squares[r][c+1].isEnabled())
               show(r,c+1);
         }
         if(r+1<20)
         {
            if(c-1>=0)
            {
               if(squares[r+1][c-1].isEnabled())
                  show(r+1,c-1);
            }
            if(squares[r+1][c].isEnabled())
               show(r+1,c);
            if(c+1<20)
            {
               if(squares[r+1][c+1].isEnabled())
                  show(r+1,c+1);
            }
         }
      }
      else
      {
         if(values[r][c]!=9)
         {
            if(values[r][c]==8)
               squares[r][c].setIcon(eight);
            if(values[r][c]==7)
               squares[r][c].setIcon(seven);
            if(values[r][c]==6)
               squares[r][c].setIcon(six);
            if(values[r][c]==5)
               squares[r][c].setIcon(five);
            if(values[r][c]==4)
               squares[r][c].setIcon(four);
            if(values[r][c]==3)
               squares[r][c].setIcon(three);
            if(values[r][c]==2)
               squares[r][c].setIcon(two);
            if(values[r][c]==1)
               squares[r][c].setIcon(one);
         }
         else
         {
            squares[r][c].setIcon(bomb);
            youLost(); 
         }
      }      
   }
   
   public static void youLost()
   {
      //squares[r][c].setIcon(bomb);
      for(int a = 0; a<values.length; a++)
      {
         for(int b = 0; b<values[a].length; b++)
         {
            if(squares[a][b].getIcon()!=null && squares[a][b].getIcon().equals(flag))
            {
               if(values[a][b]!=-9)
                  squares[a][b].setIcon(noFlag);
            }
            else if(values[a][b]==-9)
            {
               squares[a][b].setIcon(bomb);
            }
         }
      }
      JOptionPane.showMessageDialog( null,
                  "You Lost", "You Lost",
                  JOptionPane.ERROR_MESSAGE);
            
      reset();
   }
   
   public static void shift()
   {
      shift=!shift;
   }
   
   private class AddSecond implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         MSScoreboard.setScore(MSScoreboard.getScore()+1);
      }
   }
   
   private class action implements ActionListener
   {
      private int row,col;
      
      
      public action(int r, int c)
      {
         row=r;
         col=c;
      }
      public void actionPerformed(ActionEvent action)
      {
         if(firstClick)
         {
            firstClick=false;
            fillBoard(row,col);
            if(shift)
            {
               squares[row][col].setIcon(flag);
            }
            else
               show(row,col);
            timer.start();
         }
         else
            if(shift)
            {
               if(squares[row][col].getIcon()==null)
               {
                  squares[row][col].setIcon(flag);
                  MSScoreboard.setBombs(MSScoreboard.getBombs()-1);
                  if(MSScoreboard.getBombs()==0)
                  {
                     checkWin();
                  }
               }
               else if(squares[row][col].getIcon().equals(flag))
               {
                  squares[row][col].setIcon(null);
                  MSScoreboard.setBombs(MSScoreboard.getBombs()+1);
               }
            }
            else
            {
               if(squares[row][col].getIcon()==null)
                  show(row,col);
            }
      }
      
   }
   
}