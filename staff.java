import becker.robots.*;
import java.util.Random;

/**
 * This class is used to define the tasks of the npcs in the game,
 * it shall use threads and implement runnable so that the npcs are automated 
 * and can act without user input.
 * 
 * @author Nathaniel Pankhurst
 * @author 18/03/2015
 */
public class staff extends RobotSE implements Runnable
{
  private Random randGen = new Random();
  public staff(City sCity, int str, int ave, Direction dir)
  {
    super(sCity, str, ave, dir);
  }
  
  public void run(){
    while(true)
    {
      this.moveCheck();
    }
  
  }
  
  private void moveCheck()
  {
    int iSwitch = randGen.nextInt(3) + 1;
    if(this.frontIsClear())
    {
      this.move();
    }
    else
    {
      switch(iSwitch)
      {
        case 1:
          this.turnLeft();
          break;
          
        case 2:
          this.turnRight();
          break;
          
        case 3:
          this.turnAround();
          break;
      }
    }
  }
}