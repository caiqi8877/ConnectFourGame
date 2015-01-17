
/**
 * @author Tony
 * 
 *         Uses Builder pattern to generate a C4PlayerComputer. 
 *         It implements the C4Player interface.
 *     
 * 
 */
public class C4PlayerComputer implements C4Player{
	
  private String playerName;
  
  /**
   * 
   * @author Tony
   *
   *				 Builder for the C4PlayerComputer class
   */
  public static class Builder{
  	//Required param
    private String playerName = "";
  	
    /**
     * Constructor for Builder class
     * 
     * @param playerName : play's name
     */
    public Builder(String playerName){
      this.playerName = playerName;
    }
    
    /**
     * Does the job of building
     * 
     * @return a C4PlayerComputer object
     */
    public C4PlayerComputer build(){
      return new C4PlayerComputer(this);
    }
  }
  
  /**
   * Private construcotr of C4PlayerComputer class, use the builder to 
   * set the C4PlayerComputer's param
   * @param builder
   */
  private C4PlayerComputer(Builder builder){
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
