/**
 * This class is the glorified main method for the game, it is used to inititate
 * a new instance of the game, and provide an introduction to the game for the 
 * player
 * 
 * @author Nathaniel Pankhurst
 * @version 18/03/2015
 */
public class playMyGame
{
     /**
      * This is the main method of the class
      */
     public static void main(String[] args)
     {
          System.out.println(".................. Welcome to ..................");
          System.out.println("................  The Library   ................");
          System.out.println("................................................");
          System.out.println("................................................");
          System.out.println("You are a student at the university of beckersville, and you have fallen asleep during");
          System.out.println("an intense study session at the university library.");
          System.out.println("You realise that you should probably leave because it is past the library's closing time,");
          System.out.println("and if any of the library staff were to find you it would cause big trouble.");
          System.out.println("It is after this realisation that you decide to collect your things and leave before you get caught.");
          myGame g = new myGame();
          g.play();
     }
}
