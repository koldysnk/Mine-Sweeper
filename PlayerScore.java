public class PlayerScore implements Comparable
{
   private int score;
   private String name;
   public PlayerScore(String s)
   {
      name = s.substring(0,s.indexOf(" "));
      score= Integer.parseInt(s.substring(s.indexOf(" ")+1));
   }
   
   public PlayerScore(String s, int i)
   {
      name = s;
      score=i;
   }
   
   public int getScore()
   {
      return score;
   }
   
   public String toString()
   {
      return name+" "+score;
   }
   
   public int compareTo(Object o)
   {
      PlayerScore p= (PlayerScore) o;
      if(p.getScore()>this.getScore())
         return -1;
      if(p.getScore()<this.getScore())
         return 1;
         return 0;
   }
   
}