/**
 * 
 * @author Tony. This is our C4 app, which create a new C4Model object and
 * a frame which is the object of our C4View.
 *
 */
public class C4App {
	public void start(){
	  C4Model model = new C4Model();
	  // Construtor with param 
	  C4View frame = new C4View(model);
	  C4View frame1 = new C4View(model);
	}
}
