import becker.robots.*;

/** 
 * A class extending Thing that represents the Students possessions
 * 
 * @author Nathaniel Pankhurst
 * @version 18/03/2015
 */

public class Possession extends Thing
{
  boolean held = false;
  String desc;
  public Possession(City sCity, int str, int ave)
  {
    super(sCity, str, ave);
  }
  
 /**
  * This method is used to declare the description of an item
  */
 public void setDesc(String myDesc)
 {
   desc = myDesc;
 }
 
 /**
  * This method is used to declare whether or not an item is 
  * currently held in the inventory or not
  * 
  * @param myHeld: this parameter is used to take the value for whether or not a player is 
  * in possession of the item in question, so that the state can be changed within the instance
  * of the class.
  */
 public void setHeld(boolean myHeld)
 {
   held = myHeld;
 }
 
 /**
  * This method is used to retrieve the description of an item
  * for the purpose of displaying on the screen.
  */
 public String getDesc()
 {
   return desc;
 }
 
 /** 
  * This method is used to display whether or not an item is currently held in
  * the inventory.
  */
 public boolean getHeld()
 {
   return held;
 }
}