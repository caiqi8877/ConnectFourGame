

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Using Oberser pattern
 * @author Tony
 *
 */
public class C4Model {
	//required filed
  private int[][] board = new int[6][7];
  private C4Player P1;
  private C4Player P2;
  private C4Player cur;
  private boolean P1Turn;
  private List<C4Listener> listeners = 
    new ArrayList<C4Listener>();
  
  /**
   * Construtor with no param
   */
  public C4Model(){
    P1Turn = true;
  }
  
  /**
   * set P1 player
   * @param player1Type : specifies the type of player
   * @param player1Name :specifies the name of player
   */
  private void setP1(String player1Type, String player1Name){
    P1 = C4PlayerFactory.createPlayer(player1Type, player1Name);
  }
  
  /**
   * set P2 player
   * @param player2Type : specifies the type of player
   * @param player2Name :specifies the name of player
   */
  private void setP2(String player2Type, String player2Name){
    P2 = C4PlayerFactory.createPlayer(player2Type, player2Name);
  }
  /**
   * Used to initialize the P1, P2 names and types and the turn.
   * @param nameP1 : name of P1
   * @param player1Type : type of P1
   * @param nameP2 : name of P2
   * @param player2Type : type of P2
   */
  public void startGame(String nameP1, String player1Type, String nameP2,
    String player2Type) {
  	
    setP1(player1Type,nameP1);
    setP2(player2Type,nameP2);
    P1Turn = true;
    cur = P1;
    fireGameStarted();
  }
  /**
   * This method is used to register a listener with this current model object.
   * @param c4Listener : an observer
   */
  public void addListener(C4Listener c4Listener) {
    listeners.add(c4Listener);
  }
  
  /**
   * This method is used to unregister a listener with this current model object.
   * @param c4Listener : an observer
   */
  public void removeListener(C4Listener c4Listener) {
    listeners.remove(c4Listener);
  }
  /**
   * 
   * @return : a defensive copy of List<C4Listener> listeners
   */
  public List<C4Listener> getList(){
  	List<C4Listener> listenersCopy = 
  	    new ArrayList<C4Listener>();
  	listenersCopy.addAll(listeners);
  	return listenersCopy;
  }
  /**
   * 
   * @return : the current player's name
   */
  public String getName(){
    return cur.getPlayerName();
  }
  
  /**
   * change the turn of the current player
   */
  public void changeTurn(){
    if(P1Turn){
      cur = P2;
      P1Turn = false;
    }else{
      cur = P1;
      P1Turn = true;
    }
  }
  
  /**
   * 
   * @param num : represents the place to be filled, it varies from 0 to 41.
   * For example, fillSlot(0) fills board[0][0], fillSlot(41) fills board[5][6],
   */
  public void fillSlot(int num){
    int row = num / 7;
    int col = num % 7;
    // if it is P1 , fill it with value 1
    // if it is P2 , fill it with value 2
    board[row][col] = (P1Turn ? 1 : 2);
    
  }
  
  /**
   * 
   * @return whether it is P1's turn
   */
  public boolean isP1Turn(){
    return P1Turn;
  }
  
  /**
   * 
   * @return: true if the game is finished, and false otherwise
   */
  public boolean gameFinished(){
  	//check the row
    for(int i = 0; i <= 5 ; i++){
      for(int j = 0; j <= 3 ; j++){
  		  if(board[i][j] != 0 && board[i][j] == board[i][j + 1] 
  		                      && board[i][j] == board[i][j + 2] 
  		                      && board[i][j] == board[i][j + 3]){
  		    return true;
  		  }
  		}
  	}
    //check the col
    for(int j = 0; j <= 6 ; j++){
      for(int i = 0; i <= 2 ; i++){
  		  if(board[i][j] != 0 && board[i][j] == board[i + 1][j] 
  		  		                && board[i][j] == board[i + 2][j]
  		  		                && board[i][j] == board[i + 3][j]){
  		    return true;
  		  }
  		}
  	}
    // check the Diagonal
  	for(int i = 0; i <= 2 ; i++){
      for(int j = 0; j <= 3 ; j++){
  		  if(board[i][j] != 0 && board[i][j] == board[i + 1][j + 1] 
  		  		                && board[i][j] == board[i + 2][j + 2]
  		  		                && board[i][j] == board[i + 3][j + 3]){
  		    return true;
  		  }
  		}
  	}
 // check the Diagonal
  	for(int i = 0; i <= 2 ; i++){
      for(int j = 3 ; j <= 6 ; j++){
  		  if(board[i][j] != 0 && board[i][j] == board[i + 1][j - 1] 
                            && board[i][j] == board[i + 2][j - 2]
                            && board[i][j] == board[i + 3][j - 3]){
  		    return true;
        }	
  		}
  	}
  	return false;
  }
  /**
   * look ahead a step 
   * @return -1 if look ahead a step and computer does not win
   * @return other above 0 number if look ahead a step and computer wins,
   * the number is the place that where the computer can win
   */
  public int LookAheadFinished(){
  	// check the row
    for(int i = 0; i <= 5 ; i++){
      for(int j = 1; j <= 3 ; j++){
  		  if(board[i][j] == 2 && board[i][j] == board[i][j + 1] 
  		                      && board[i][j] == board[i][j + 2]
  		                      && board[i][j - 1] == 0
  		                      && board[i][j + 3] == 0){
  		    return 7 * i + j - 1;
  		  }
  		}
  	}
    //check the column
  	for(int j = 0; j <= 6 ; j++){
      for(int i = 1; i <= 2 ; i++){
        if(board[i][j] == 2 && board[i][j] == board[i + 1][j] 
  		  		                && board[i][j] == board[i + 2][j]
  		  		                && board[i - 1][j] == 0
  		  		                && board[i + 3][j] == 0){
  		    return i * 7 + j - 7;
  		  }
  		}
  	}
  	//check the diagonal
  	for(int i = 1; i <= 2 ; i++){
      for(int j = 1; j <= 3 ; j++){
  		  if(board[i][j] == 2 && board[i][j] == board[i + 1][j + 1] 
  		  		                && board[i][j] == board[i + 2][j + 2]
  		  		                && board[i - 1][j - 1] == 0
  		  		                && board[i + 3][j + 3] == 0){
  		    return i *7 + j - 8;
  		  }
  		}
  	}
  //check the diagonal
  	for(int i = 0; i <= 2 ; i++){
  	  for(int j = 3 ; j <= 6 ; j++){
  		  if(board[i][j] == 2 && board[i][j] == board[i + 1][j - 1] 
                            && board[i][j] == board[i + 2][j - 2]
                            && board[i - 1][j + 1] == 0
                            && board[i + 1][j - 1] == 0){
  	    return 7 * i + j - 6;
        }	
  		}
  	}
    return -1;
  }
  /**
   * reset the model, set all field to its' initialized value
   */
  public void reset(){
    board = new int[6][7];
    P1Turn = true;
    cur = P1;
    fireGameResetEvent();
  }
  
  /**
   * 
   * @param num : the place where needed to be check
   * @return true if the place is 0. means this slot has not
   * be filled yet
   */
  public boolean checkReenterButton(int num){
    return ( board[num / 7][num % 7] == 0 );
  }
  
  /**
   * 
   * @param num :: the place where needed to be check
   * @return true if the player follows the from botton to up
   * rule
   */
  public boolean checkValidButton(int num){
    int i = num;
    while(i < 35){
  	  i += 7;
  	  if( board[i / 7][i % 7] == 0){
  	    return false;
  		}
  	}
    return true;
  }
  /**
   * Event firing method Tells all listeners that the game has started.
   */
  private void fireGameStarted() {
    for (C4Listener listener : listeners) {
      listener.gameStart();
    }
  }
  
  /**
   * Event firing method Tells all listeners that the game has reset.
   */
  private void fireGameResetEvent() {
    for (C4Listener listener : listeners) {
      listener.restart();
    }
  }
  /**
   * Event firing method Tells all listeners that the game has finished.
   */
  private void fireGameFinished() {
    for (C4Listener listener : listeners) {
      listener.gameFinished();
    }
  }
  
  @Override
  public int hashCode(){
	  final int prime = 29;
	  int result = 1;
	  result = prime * result + ((P1 == null) ? 0 : P1.hashCode());
	  result = prime * result + ((P2 == null) ? 0 : P2.hashCode());
	  result = prime * result + Arrays.hashCode(board);
	  result = prime * result + (P1Turn ? 3 : 5 );
	  result = prime * result + ((listeners == null) ? 0 : listeners.hashCode());
	  return result;
  }
  
  @Override
  public boolean equals(Object obj){
  	if(this == obj) return true;
  	if(obj == null) return false;
  	if(this.getClass() != obj.getClass()) return false;
  	C4Model other = (C4Model) obj;
  	if(P1 == null && other.P1 != null) return false;
  	if(other.P1 == null && P1 != null) return false;
  	if(!P1.equals(other.P1)) return false;
  	if(P2 == null && other.P2 != null) return false;
  	if(other.P2 == null && P2 != null) return false;
  	if(!P2.equals(other.P2)) return false;
  	if(cur == null && other.cur != null) return false;
  	if(other.cur == null && cur!= null) return false;
  	if(!cur.equals(other.cur)) return false;
  	if(P1Turn != other.P1Turn) return false;
  	if(listeners == null && other.listeners != null) return false;
  	if(other.listeners == null && listeners != null) return false;
  	if(!listeners.equals(other.listeners)) return false;
  	return true;
  }
  
  @Override
  public String toString(){
  	String boardstring = "";
  	for(int i = 0 ; i < 6 ; i++){
  	  for(int j = 0 ; j < 7 ; j++){
  		  boardstring += board[i][j] + " ";
  		}
  	  boardstring += "\n";
  	}
  	return "Now it is " + cur.getPlayerName() + "'s turn and the "
  	     + "board is filled like" + "\n" + boardstring; 
  }
}
