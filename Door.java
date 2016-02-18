import becker.robots.*;

/**
 * A class extending Wall, creating a special type of wall that can
 * grant the adventure passage after they have met a specific condition.
 * 
 * @author Nathaniel Pankhurst
 * @version 18/03/2015
 */

public class Door extends Wall
{
  public Door(City sCity, int str, int ave, Direction dir)
  {
    super(sCity, str, ave, dir); 
  }
  
  /**
   * This method sets the status of a door in the game to closed
   */
  public void closeDoor()
  {
    this.setBlocksEntry(Direction.NORTH, true);
    this.setBlocksEntry(Direction.EAST, true);
    this.setBlocksEntry(Direction.SOUTH, true);
    this.setBlocksEntry(Direction.WEST, true);
    this.setBlocksExit(Direction.NORTH, true);
    this.setBlocksExit(Direction.EAST, true);
    this.setBlocksExit(Direction.SOUTH, true);
    this.setBlocksExit(Direction.WEST, true);    
  }
  
  /**
   * This method sets the status of a door in the game to open
   */
  public void openDoor()
  {
    this.setBlocksEntry(Direction.NORTH, false);
    this.setBlocksEntry(Direction.EAST, false);
    this.setBlocksEntry(Direction.SOUTH, false);
    this.setBlocksEntry(Direction.WEST, false);
    this.setBlocksExit(Direction.NORTH, false);
    this.setBlocksExit(Direction.EAST, false);
    this.setBlocksExit(Direction.SOUTH, false);
    this.setBlocksExit(Direction.WEST, false); 
  }
  
  
}