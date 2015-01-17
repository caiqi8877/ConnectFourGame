
/**
 * 
 * @author Tony . This game's listener interface, it declare some 
 * methods that need to be implemented in the C4View Class;
 *
 */

public interface C4Listener {
	
	/**
	 * specifies actions needed to be taken by view when game restart
	 */
  public void restart();
	
  /**
   * specifies actions needed to be taken by view when game start
   */
  public void gameStart();
	
  /**
   * specifies actions needed to be taken by view when turn changed
   */
  public void changeTurn();
  
  /**
   * specifies actions needed to be taken by view and model when 
   * some slot is filled
   * @param num: the place where the slot is filled
   */
  public void fillSlot(int num);
	
  /**
   * specifies actions needed to be taken by view when game finished
   */
  public void gameFinished();
}
