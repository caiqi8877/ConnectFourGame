/**
 * Using Factory design pattern
 * @author Tony. Using this pattern so that we don't need to know 
 * the exact class of the object. It could be C4PlayerComputer or 
 * C4HumanPlaers.
 *
 */

public class C4PlayerFactory {
	/**
	 * The main factory method which gives us different 
	 * type of Player objects.
	 * 
	 * @param type : specifies the type, could be human or computer
	 * 	
	 * @param name : specifies the name of the player
	 * 
	 * @return a C4player object based on the input to determine its class
	 */
  public static C4Player createPlayer(String type,String name){
  	if(type.equalsIgnoreCase("human")){
  		
  	  return new C4HumanPlayers.Builder(name).build();
  	  
  	}else if(type.equalsIgnoreCase("computer")){
  		
  	  return new C4PlayerComputer.Builder(name).build();
  	  
  	}throw new IllegalArgumentException("No such player type.");
  }
}
