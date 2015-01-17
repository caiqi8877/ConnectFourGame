public class C4Main {
	/**
	 * In the main application, an instance of C4app is created.
	 * @param arg
	 */
  public static void main(String arg[]){
  	
	  C4Factory c4factory = new C4AppSingleton();
	  C4App main = c4factory.create();
	  main.start();
	}
}
