/**
 * 
 * @author Tony
 *
 *         Using Builder pattern
 */
public class C4HumanPlayers implements C4Player{
	
	private String playerName;
  
	/**
	 * 
	 * @author Tony
	 *
	 *	       Builder for the C4HumanPlayers class
	 */
  public static class Builder{
    private String playerName = "";
  	
    /**
     * Constructor for the Builder class
     * @param playerName: player's name
     */
    public Builder(String playerName){
      this.playerName = playerName;
    }
    
    /**
     * Does the autual job of building
     * @return
     */
    public C4HumanPlayers build(){
      return new C4HumanPlayers(this);
    }
  }
  
  /**
   * Private constructor of C4HumanPlayers class, used to set param
   * @param builder
   */
  private C4HumanPlayers(Builder builder){
    this.playerName = builder.playerName;
  }
  
  /**
   * (non-Javadoc)
   */
  @Override
  public String getPlayerName(){
    return playerName;
  }
}