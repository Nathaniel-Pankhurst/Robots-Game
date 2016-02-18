import becker.robots.*;

/** a class extending RobotSE which represents an adventure playing the game of 
  * "Escape the Library".
  * A player has a location based on x coordinate and y coordinate that correlate
  * to a location within a room, the robot faces a direction.
  * 
  * @author Nathaniel Pankhurst
  * @version 18/03/2015
  */

public class myPlayer extends RobotSE
{
  private int weight = 0;
  
  public myPlayer(City sCity, int str, int ave, becker.robots.Direction dir, int inv)
  {
    super(sCity, str, ave, dir, inv); 
  }

 /**
  * This method is used to change the player's direction to north.
  */
  public void faceNorth()
  {
    if(this.getDirection() == Direction.WEST)
    {this.turnRight();}
    else
    {
     while(this.getDirection() != Direction.NORTH)
     {
        this.turnLeft();
     }
    }
  }
    
  
 /**
  * This method is used to change the player's direction to east.
  */
  public void faceEast()
  {
    if(this.getDirection() == Direction.NORTH)
    {
      this.turnRight();
    }
    else
    {
      while(this.getDirection() != Direction.EAST)
      {
        this.turnLeft();
      }
    }
    }
      
  
 /**
  * This method is used to change the player's direction to south.
  */
  public void faceSouth()
  {
    if(this.getDirection() == Direction.EAST)
    {
      this.turnRight();
    }
    else
    {
      while(this.getDirection() != Direction.SOUTH)
      {
        this.turnLeft();
      }
    }
  }
          

  /**
   * This method is used to change the player's direction to west.
   */
  public void faceWest()
  {
    if(this.getDirection() == Direction.SOUTH)
    {
      this.turnRight();
    }
    else
    {
      while(this.getDirection() != Direction.WEST)
      {
        this.turnLeft();
      }
    }
      
  }
  
  /**
   * this method is used to check that a players path is clear before moving
   * to ensure that the game doesn't crash from the player trying to move through
   * a wall.
   */
  public void nonMoveErrorCheck()
  {
    if(!this.frontIsClear())
    {
      int x = this.getAvenue();
      int y = this.getStreet();
      if ((y == 5) && (x == 1))
      {
        System.out.println("I shouldn't leave till I have collected more of my things.");  
      }
      else if ((y == 6) && (x == 13))
      {
        System.out.println("I shouldn't leave till I have collected more of my things.");  
      }
      else if ((y == 1) && (x == 10))
      {
        System.out.println("I shouldn't leave till I have collected more of my things.");  
      }
      else if ((y == 18) && (x == 8))
      {     
        System.out.println("I shouldn't leave till I have collected more of my things.");  
      }
      else
      {
        System.out.println("Error: This way is blocked.");
      }
    }
    else
    {
      this.move();
    }    
  }
  
  /**
   * This method is used to ensure that there is something in the players
   * backpack before they attempt to place an item, this is due to the fact that
   * in the case of the player attempting to place an item when the bacjpacj is empty
   * the game would crash.
   */
  public void nonPutErrorCheck()
  {
    if(this.countThingsInBackpack() < 1)
    {
      System.out.println("Error: I don't have anything to put down");
    }
    else
    {
      this.putThing();
    }
  }
  
  /**
   * This method is used to change the current stored value for the weight of items being 
   * carried by the player.
   * 
   * @param operator: this parameter is used to determine whether addition or subtraction
   * will be taking place.
   * 
   * @param newWeight: this parameter is used to provide the value that should be added to/
   * subtracted from the original weight value to provide the new value. 
   * 
   */
  public void changeWeight(String operator, int newWeight)
  {
    switch(operator)
    {
      case"add":
        weight = weight + newWeight;
        break;
      
      case "subtract":
        weight = weight - newWeight;
        break;    
    }
  }
  
  /**
   * This method is used to make the weight value available for use in other classes
   * so that validation can occur.
   * @return weight is returned so that it can be used in other classes for validation purposes.
   */
  public int getWeight()
  {
    return weight;
  }
  
}