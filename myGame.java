import becker.robots.*;
import java.util.Scanner;
import java.awt.*;

/**
 * A class listing all the main functions of all characters and objects in the game.
 * This class is the supporting structure of the game's functionality and the game would not
 * work without it.
 * 
 * @author Nathaniel Pankhurst
 * @author 18/03/2015
 */
public class myGame
  
{
 private City Library = new City("Library.txt"); 
 private myPlayer Student = new myPlayer(Library, 2, 2, Direction.SOUTH, 0);
 private staff Janitor1 = new staff(Library, 18, 1, Direction.EAST);
 private staff Janitor2 = new staff(Library, 10, 5, Direction.EAST);
 private staff Janitor3 = new staff(Library, 5, 13, Direction.SOUTH);
 private Thread JanitorThread1 = new Thread(Janitor1);
 private Thread JanitorThread2 = new Thread(Janitor2);
 private Thread JanitorThread3 = new Thread(Janitor3);
 
 // Creation of endGame doors
 private Door northExit = new Door(Library, 0, 10, Direction.SOUTH);
 private Door eastExit = new Door(Library, 6, 14, Direction.WEST);
 private Door southExit = new Door(Library, 19, 8, Direction.NORTH);
 private Door westExit = new Door(Library, 5, 0, Direction.EAST);
 // Creation of Possessions
 private Possession myBook = new Possession(Library, 5, 8);
 private Possession myLaptop = new Possession(Library, 15, 5);
 private Possession myUsb = new Possession(Library, 13, 1);
 private Possession myWallet = new Possession(Library, 10, 4);
 private Possession myKeys = new Possession(Library, 2, 13);
 private Possession myPod = new Possession(Library, 11, 13);
 private Possession myText = new Possession(Library, 18, 2);
 private Possession myBag = new Possession(Library, 6, 5);
 
 
  /**
   * This method forms the main structure of the class, allowing the game to execute
   * each command in a set order.
   */
  public void play()
  {
    janitorThreads();
    setStaff();
    setPossessions();
    colorDoors();
    int iRepeat = 0; 
    boolean gameOver = false;
    while (gameOver == false)
    {
      gameOver = checkLoss();
      if (gameOver == false)
      {
        assessInput();
        if (Student.countThingsInBackpack() >= 2)
        {
          if (iRepeat == 0)
          {
            openExits();
            System.out.println("That should be enough of my things, I could collect more but the longer I spend in the library the more I am at risk of getting caught. I should probably leave whilst I'm ahead.");
            iRepeat ++;
          }
        }
        gameOver = checkWin();
      }
    else
    {
      System.out.println("GAME OVER, You have been caught");
    }
   }
    if(checkWin())
    {
      System.out.println("Congratulations, you have won the game");
      int completion = Student.countThingsInBackpack();
      printEnd(completion);
    }    
    try {
       wait(10000);  
    }
    catch(Exception e){}
    //System.exit(0);
  }
  
  /**
   * This method is used to generate a response based on the input retrieved
   * from the user in the getInput() method.
   */
  private void assessInput()
  {
  char myChar = getInput();
  switch(myChar)
      {
        case 'w':
          Student.faceNorth();
          Student.nonMoveErrorCheck();
          break;
          
        case 'a':
          Student.faceWest();
          Student.nonMoveErrorCheck();
          break;
          
        case 's':
          Student.faceSouth();
          Student.nonMoveErrorCheck();
          break;
          
        case 'd':
          Student.faceEast();
          Student.nonMoveErrorCheck();
          break;
          
        case 'e':
          pickItem();
          break;
          
        case 'c':
          Student.nonPutErrorCheck();
          break;
          
        case 'h':
          displayHelp();
          break; 
          
        case 'i':
          listInv();
          break;
          
        case 'o':
          openExits();
          break;
          
     
      }
  }
  
  /**
   * This method retrieves the input from the user with the use of a Scanner
   */
  private char getInput()
  {
    Scanner keyboard = new Scanner(System.in);
    char myChar = keyboard.findInLine(".").charAt(0);
    return myChar;
  }
  
  /**
   * This method changes the colour of the doors so that they can be told apart
   * from the standard walls that are used to form the structure of the library,
   * this allows the user to know the exits as soon as the game launches.
   */
  private void colorDoors()
  {
    northExit.getIcon().setColor(Color.cyan);
    eastExit.getIcon().setColor(Color.cyan);
    southExit.getIcon().setColor(Color.cyan);
    westExit.getIcon().setColor(Color.cyan);
  }
  
  /**
   * This method is used to check whether the user has met the win requirements
   * of the game so that the game instance can be terminated.
   */
  private boolean checkWin()
  {
    int x = Student.getAvenue();
    int y = Student.getStreet();
    if ((y < 1) || (y > 18) || (x < 1) || (x > 13))
    {
      return true;
    }
    else 
    {
      return false;
    }
    
  }
  
  private boolean checkLoss()
  {
    int x = Student.getStreet();
    int y = Student.getAvenue();
    if (((x >= Janitor1.getStreet()) && (y == Janitor1.getAvenue()) && (Janitor1.getDirection() == Direction.EAST) && ((x - Janitor1.getStreet()) < 4)))
    {
      return true;
    }
    else if ((x <= Janitor1.getStreet()) && (y == Janitor1.getAvenue()) && (Janitor1.getDirection() == Direction.WEST) && ((Janitor1.getStreet() - x) < 4))
    {
      return true;
    }
    else if ((x == Janitor1.getStreet()) && (y >= Janitor1.getAvenue()) && (Janitor1.getDirection() == Direction.SOUTH) && ((y - Janitor1.getAvenue()) < 4))
    {
      return true;
    }
    else if ((x == Janitor1.getStreet()) && (y <= Janitor1.getAvenue()) && (Janitor1.getDirection() == Direction.NORTH) && ((Janitor1.getAvenue() - y) < 4))
    {
      return true;
    }
    else if (((x >= Janitor2.getStreet()) && (y == Janitor2.getAvenue()) && (Janitor2.getDirection() == Direction.EAST)) && ((x - Janitor2.getStreet()) < 4))
    {
      return true;
    }
    else if ((x <= Janitor2.getStreet()) && (y == Janitor2.getAvenue()) && (Janitor2.getDirection() == Direction.WEST) && ((Janitor2.getStreet() - x) < 4))
    {
      return true;
    }
    else if ((x == Janitor2.getStreet()) && (y >= Janitor2.getAvenue()) && (Janitor2.getDirection() == Direction.SOUTH) && ((y - Janitor2.getAvenue()) < 4))
    {
      return true;
    }
    else if ((x == Janitor2.getStreet()) && (y <= Janitor2.getAvenue()) && (Janitor2.getDirection() == Direction.NORTH) && ((Janitor2.getAvenue() - y) < 4))
    {
      return true;
    }
    else if (((x >= Janitor3.getStreet()) && (y == Janitor3.getAvenue()) && (Janitor3.getDirection() == Direction.EAST) && ((x - Janitor3.getStreet()) < 4)))
    {
      return true;
    }
    else if ((x <= Janitor3.getStreet()) && (y == Janitor3.getAvenue()) && (Janitor3.getDirection() == Direction.WEST) && ((Janitor3.getStreet() - x) < 4))
    {
      return true;
    }
    else if ((x == Janitor3.getStreet()) && (y >= Janitor3.getAvenue()) && (Janitor3.getDirection() == Direction.SOUTH) && ((y - Janitor3.getAvenue()) < 4))
    {
      return true;
    }
    else if ((x == Janitor3.getStreet()) && (y <= Janitor3.getAvenue()) && (Janitor3.getDirection() == Direction.NORTH) && ((Janitor3.getAvenue()) < 4))
    {
      return true;
    }
    
    else
    {
      return false;
    }
  }
  
  /**
   * This method is used to determine to what extent the user has completed the
   * game upon completion, and output the relevant dialogue.
   */
  private void printEnd(int completion)
  {
        
    if (completion < 3)
    {
      System.out.println(".....Grade C.....");
      System.out.println("You have escaped the library, but you can't help but feel that you probably should have collected more of your possessions.");
    }
    
    else if ((completion >= 3) && (completion < 5))
    {
      System.out.println(".....Grade B.....");
      System.out.println("You have escaped the library, and you have collected a decent number of your possessions, you should be proud");
    }
    
    else if ((completion >= 5) && (completion < 8))
    {
      System.out.println(".....Grade A.....");
      System.out.println("You have escaped the library, and you have collected your possessions, but you can't help but feel that you forgot something");
    }
    
    else if (completion == 8)
    {
      System.out.println(".....Grade S.....");
      System.out.println("You have escaped the library with all of your possessions, that was a close call, maybe you shouldn't spend so much time in the library");
    }
  }
  
  /**
   * This method is used to check whether there is an item present for the player
   * to pick up, if an item is present, the robot will pick the item up and it
   * will be stored in the player's inventory. Otherwise the game will output an error
   * message informing the user that there is no item present for the robot to retrieve
   */
  private void pickItem()
  {
     if (Student.canPickThing())
     {
       if(Student.getWeight() <= 7)
       {
         Student.pickThing();
         heldItem(Student.getStreet(), Student.getAvenue());
       }
       else
       {
         System.out.println("You don't have enough room to carry this.");
       }
     }
     else
     {
       System.out.println("Error: There isn't anything to pick up.");
     } 
  }
  
  /**
   * This method is used to print the contents of the player's inventory on the
   * screen.
   */
  private void listInv()
  {
    System.out.println("Item checklist:");
    System.out.println("");
    System.out.println("Item name, do I have it?");
    System.out.println("Notebook, " + myBook.getHeld());
    System.out.println("Laptop, " + myLaptop.getHeld());
    System.out.println("USB Stick, " + myUsb.getHeld());
    System.out.println("Wallet, " + myWallet.getHeld());
    System.out.println("Keys, " + myKeys.getHeld());
    System.out.println("piPod, " +  myPod.getHeld());
    System.out.println("TextBook, " + myText.getHeld());
    System.out.println("Backpack, " + myBag.getHeld());
  }
  
  /**
   * This method displays the robots controls on the screen.
   */
  private void displayHelp()
  {
    System.out.println("The controls are:");
    System.out.println("W: move up");
    System.out.println("A: move left");
    System.out.println("S: move down");
    System.out.println("D: move right");
    System.out.println("E: pick up item");
    System.out.println("C: put down item");
    System.out.println("I: list inventory");
    System.out.println("h: display help");
  }
  
  /**
   * This method sets the values for the item descriptions, and colours upon the booting
   * of the game.
   */
  private void setPossessions()
  {
    myBook.setDesc("This is you're notebook, you're glad that you found this.");
    myLaptop.setDesc("This is you're laptop, your parents would have killed you if you'd left this.");
    myUsb.setDesc("This is your USB stick, it contains most of your coursework, you'd have been in real trouble if you'd lost this.");
    myWallet.setDesc("This is your wallet, there isn't any money in it, but at least you don't have to cancel your cards.");
    myKeys.setDesc("These are your housekeys, now you don't have to get a flat-mate to let you in.");
    myPod.setDesc("This is your piPod, you probably shouldn't have  brought this to the library in the first place.");
    myText.setDesc("This is your course Textbook, this was expensive and you're glad you don't have to buy a replacement.");
    myBag.setDesc("This is your backpack, you should be able to carry more items now.");
    myBook.getIcon().setColor(Color.gray);
    myLaptop.getIcon().setColor(Color.magenta);
    myUsb.getIcon().setColor(Color.green);
    myWallet.getIcon().setColor(Color.yellow);
    myKeys.getIcon().setColor(Color.orange);
    myPod.getIcon().setColor(Color.pink);
    myText.getIcon().setColor(Color.darkGray);
    myBag.getIcon().setColor(Color.cyan);
  }
  
  /**
   * This method sets the status of all exit doors in the game to open
   */
  private void openExits()
  {
    northExit.openDoor();
    northExit.getIcon().setColor(Color.white);
    eastExit.openDoor();
    eastExit.getIcon().setColor(Color.white);
    southExit.openDoor();
    southExit.getIcon().setColor(Color.white);
    westExit.openDoor();
    westExit.getIcon().setColor(Color.white);
  }
  
  /**
   * This method defines the colour of the staff members
   */
  private void setStaff()
  {
    Janitor1.getIcon().setColor(Color.blue);
    Janitor2.getIcon().setColor(Color.blue);
    Janitor3.getIcon().setColor(Color.blue);
  }
  
  /**
   * This method sets the value of a collected item's slot in the inventory to 
   * true.
   * 
   */
  private void heldItem(int studentY, int studentX)
  {
    if ((studentX == 8) && (studentY == 5))
    {
      myBook.setHeld(true);
      Student.changeWeight("add", 2);
      System.out.println(myBook.getDesc());
    }
    else if ((studentX == 5) && (studentY == 15))
    {
      myLaptop.setHeld(true);
      Student.changeWeight("add", 4);
      System.out.println(myLaptop.getDesc());
    }
    else if ((studentX == 1) && (studentY == 13))
    {
      myUsb.setHeld(true);
      Student.changeWeight("add", 1);
      System.out.println(myUsb.getDesc());
    }
    else if ((studentX == 4) && (studentY == 10))
    {
      myWallet.setHeld(true);
      Student.changeWeight("add", 1);
      System.out.println(myWallet.getDesc());
    }
    else if ((studentX == 13) && (studentY == 2))
    {
      myKeys.setHeld(true);
      Student.changeWeight("add", 1);
      System.out.println(myKeys.getDesc());
    }
    else if ((studentX == 13) && (studentY == 11))
    {
      myPod.setHeld(true);
      Student.changeWeight("add", 1);
      System.out.println(myPod.getDesc());
    }
    else if ((studentX == 2) && (studentY == 18))
    {
      myText.setHeld(true);
      Student.changeWeight("add", 2);
      System.out.println(myText.getDesc());
    }
    else if ((studentX == 5) && (studentY == 6))
    {
      Student.changeWeight("subtract", 5);
      myBag.setHeld(true);
      System.out.println(myBag.getDesc());
    }
  }
  
  /**
   * this method calls all the Janitor threads
   */
  private void janitorThreads()
  {
    JanitorThread1.start();
    JanitorThread2.start();
    JanitorThread3.start();
  }

}