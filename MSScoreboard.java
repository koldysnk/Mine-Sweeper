import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MSScoreboard extends JPanel
{
   private static JLabel score; 
   private static JLabel hScore;
   private static int s, hS;
   private static int flagCounter;
   private static Timer timer;
   private static ArrayList<PlayerScore> scores;
   private static ArrayList<PlayerScore> scores2;
   private static ArrayList<PlayerScore> scores3;
   
   public MSScoreboard() throws IOException
   {
      setLayout(new FlowLayout());
      s=hS=0;
      hScore = new JLabel("Bombs Left: 0");
      add(hScore);
      add(new JLabel("              "));
      score = new JLabel("Score: 0");
      add(score);
      flagCounter=40;
      scores= getScores();
      scores2= getScores2();
      scores3= getScores3();
   }
   
   public static void updateSaves()
   {
   try
   {
      File myFoo = new File("easyScores.txt");
      FileWriter fooWriter = new FileWriter(myFoo, false); // true to append false to overwrite.
      String st="";
      for(int i = 0; i<MSScoreboard.getScoresList().size()-1;i++)
      {
         st+=MSScoreboard.getScoresList().get(i)+"\n";
      }
      st+=MSScoreboard.getScoresList().get(MSScoreboard.getScoresList().size()-1);
      fooWriter.write(st);
      fooWriter.close();
      
      File myFoo2 = new File("mediumScores.txt");
      FileWriter fooWriter2 = new FileWriter(myFoo2, false); // true to append false to overwrite.
      st="";
      for(int i = 0; i<MSScoreboard.getScoresList2().size()-1;i++)
      {
         st+=MSScoreboard.getScoresList2().get(i)+"\n";
      }
      st+=MSScoreboard.getScoresList2().get(MSScoreboard.getScoresList2().size()-1);
      fooWriter2.write(st);
      fooWriter2.close();
      
      File myFoo3 = new File("hardScores.txt");
      FileWriter fooWriter3 = new FileWriter(myFoo3, false); // true to append false to overwrite.
      st="";
      for(int i = 0; i<MSScoreboard.getScoresList3().size()-1;i++)
      {
         st+=MSScoreboard.getScoresList3().get(i)+"\n";
      }
      st+=MSScoreboard.getScoresList3().get(MSScoreboard.getScoresList3().size()-1);
      fooWriter3.write(st);
      fooWriter3.close();
      }
      catch(Exception ex)
      {
      System.out.println("ERROR");
      }
   }
   
   private static ArrayList<PlayerScore> getScores()throws IOException
   {
      Scanner in = new Scanner(new File("easyScores.txt"));
      ArrayList<PlayerScore> s = new ArrayList<PlayerScore>();
      while(in.hasNextLine())
      {
         s.add(new PlayerScore(in.nextLine()));
      }
      in.close();
      return s;
   }
   
   private static ArrayList<PlayerScore> getScores2()throws IOException
   {
      Scanner in = new Scanner(new File("mediumScores.txt"));
      ArrayList<PlayerScore> s = new ArrayList<PlayerScore>();
      while(in.hasNextLine())
      {
         s.add(new PlayerScore(in.nextLine()));
      }
      in.close();
      return s;
   }
   
   private static ArrayList<PlayerScore> getScores3()throws IOException
   {
      Scanner in = new Scanner(new File("hardScores.txt"));
      ArrayList<PlayerScore> s = new ArrayList<PlayerScore>();
      while(in.hasNextLine())
      {
         s.add(new PlayerScore(in.nextLine()));
      }
      in.close();
      return s;
   }
   
   public static void changeScores(String s,int num)
   {
      PlayerScore newS = new PlayerScore(s,num);
      for(int i = scores.size()-1;i>=0;i--)
      {
         if(newS.compareTo(scores.get(i))==1)
         {
            scores.add(i+1,newS);
            
            return;
         }
      }
      scores.add(0,newS);
      
   }
   
   public static void changeScores2(String s,int num)
   {
      PlayerScore newS = new PlayerScore(s,num);
      for(int i = scores2.size()-1;i>=0;i--)
      {
         if(newS.compareTo(scores2.get(i))==1)
         {
            scores2.add(i+1,newS);
            
            return;
         }
      }
      scores2.add(0,newS);
      
   }
   
   public static void changeScores3(String s,int num)
   {
      PlayerScore newS = new PlayerScore(s,num);
      for(int i = scores3.size()-1;i>=0;i--)
      {
         if(newS.compareTo(scores3.get(i))==1)
         {
            scores3.add(i+1,newS);
            
            return;
         }
      }
      scores3.add(0,newS);
      
   }
   
   public static ArrayList<PlayerScore> getScoresList()
   {
      return scores;
   }
   
   public static ArrayList<PlayerScore> getScoresList2()
   {
      return scores2;
   }
   
   public static ArrayList<PlayerScore> getScoresList3()
   {
      return scores3;
   }
   
   public static int getFlags()
   {
      return flagCounter;
   }
   public static void setFlags(int n)
   {
      flagCounter=n;
   }
   
   public static void setScore(int n)
   {
      s=n;
      score.setText("Score: "+s);
   }
   
   public static int getScore()
   {
      return s;
   }
   
   public static void setBombs(int n)
   {
      hS=n;
      hScore.setText("Bombs Left: "+hS);
   }
   
   public static int getBombs()
   {
      return hS;
   }
   
   /*public void update(int b)
   {
      if(b==-1)
      {
         s=0;
         score.setText("Score: "+s);
      }
      else
      {
         s++;
         score.setText("Score: "+s);
         if(s>hS)
         {
            hS=s;
            hScore.setText("Bombs Left: "+hS);
         }
      }
   }*/
   
}