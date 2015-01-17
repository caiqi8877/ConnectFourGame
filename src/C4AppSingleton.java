/**
 * 
 * @author Tony. This is a singleton factory design pattern implemetation. 
 * It allows one object of C4App and one only.
 *
 */
public class C4AppSingleton implements C4Factory{
	
  private C4App c4app;
  /**
   * (non-Javadoc)
   */
  public synchronized C4App create() {
  	
  	if(this.c4app == null){
  	  this.c4app = new C4App();
  	}
  	
  	return c4app;
  }
}
