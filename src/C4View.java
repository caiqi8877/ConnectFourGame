
//Using observer pattern
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Tony. This class is the view or our C4model and 
 * it implements C4Listener
 *
 */
public class C4View implements C4Listener{
  //required field                                                                                                                            
	private JFrame StartFrame;
	private JFrame frame;
	private JCheckBox twoPlayers;
	private JCheckBox PlayerVsComputer;
	private JButton btn;
	private JButton[] buttons = new JButton[42];
	private JPanel uppanel;
	private JTextField text;
  private JPanel downpanel;
  private JButton restart;
  private int gameMode = 0;
  private final C4Model model;
	
  /**
   * Constructor for our C4View class, which taks in an C4Model object that 
   * is observing
   * @param newmodel : a C4Model object
   */
  public C4View(final C4Model newmodel) {
  	this.model = newmodel;
  	//below is the first screen of the game. Used to choose mode
    StartFrame = new JFrame("Connect Four");
  	StartFrame.setLayout(new FlowLayout());
  	//check box for choosing which mode
  	twoPlayers = new JCheckBox("human vs human");
  	twoPlayers.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent event) {
		    gameMode = 0;
		  }
		});
  	PlayerVsComputer = new JCheckBox("human vs computer");
  	PlayerVsComputer.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent event) {
		    gameMode = 1;
		  }
		});
  	btn = new JButton("start"); 
  	btn.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent event) {
			  gameStart();
			}
		});
  	StartFrame.add(twoPlayers);
  	StartFrame.add(PlayerVsComputer);
  	StartFrame.add(btn);
  	StartFrame.setSize(350,100); 
  	StartFrame.setLocation(500,300); 
  	StartFrame.setVisible(true);
  	
	}
  /**
   * (non-Javadoc)
   */
  @Override
  public void gameStart(){
  	//delete the first frame and enter the game frame
  	StartFrame.removeAll();
  	frame = new JFrame("connect four game");
  	String mode = ( gameMode == 0 ? "human vs human" : "human vs computer");
  	if(gameMode == 0){
  	  model.startGame("player1","human","player2","human");
  	}else{
  	  model.startGame("player1","human","Computer","computer");
  	}
	  text = new JTextField(" " + mode + "      Now the game begins ");
	  text.setPreferredSize(new Dimension(700,50));
	  uppanel = new JPanel(new GridLayout(6,7));
    restart = new JButton("restart");
    
    restart.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent event) {
			  restart();
			}
		});
    // there are 42 buttons and they all add eventListeners
    
		for(int i = 0 ; i < 42 ; i++){
		  buttons[i] = new JButton();
		  buttons[i].setOpaque(true);
			// gameMode 0 means human vs human
			if(gameMode == 0){
			  buttons[i].addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent event) {
				  JButton btn = (JButton)event.getSource();
				  int j = 0;
				  boolean validFlag = true;
				  boolean reEnterFlag = true;
				  for(; j < 42 ; j++){
					  if(btn == buttons[j]) break;
					}
					//check if it is a valid move
					validFlag = model.checkValidButton(j);
				  if(!validFlag){
				    text.setText("Invalid slot ! Enter the slot from bottom to the "
				    		+ "top");
				  }
				//check if it is a duplicate move
				  reEnterFlag = model.checkReenterButton(j);
				  if(!reEnterFlag){
				    text.setText("This slot is already filled");
				  }
				  
				  //if it is the right move, then do a list of things like fill the 
				  //slot and change turn
					if(validFlag && reEnterFlag){
					  if( model.isP1Turn() ){
					    btn.setBackground(Color.RED);
					    btn.setBorderPainted(false);
					  }else{
					    btn.setBackground(Color.YELLOW);
					    btn.setBorderPainted(false);
					  }
					  
					  text.setText("then it is " + model.getName() + "'s turn");
					  fillSlot(j);
					  //if game finished, then disable all buttons
					  if(!model.gameFinished()){
					    model.changeTurn();
					  }else{
				      text.setText("game won by " + model.getName());
				      for(int i = 0; i < 42 ; i++){
				    	  buttons[i].setEnabled( false );
				      }
					  }
					}
				}
			  
			});
			  //this gameMode means human vs computer
		}else if(gameMode == 1){
		  buttons[i].addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent event) {
				  JButton btn = (JButton)event.getSource();
				  int j = 0;
				  boolean validFlag = true;
				  boolean reEnterFlag = true;
				  for(; j < 42 ; j++){
					  if(btn == buttons[j]) break;
					}
					//check if it is a valid move
					validFlag = model.checkValidButton(j);
				  if(!validFlag){
				    text.setText("Invalid slot ! Enter the slot from bottom to the "
				    		+ "top");
				  }
				//check if it is a duplicate move
				  reEnterFlag = model.checkReenterButton(j);
				  if(!reEnterFlag){
				    text.setText("This slot is already filled");
				  }
				  //if it is a right move
					if(validFlag && reEnterFlag){
					  btn.setBackground(Color.RED);
					  btn.setBorderPainted(false);
					  text.setText("then it is the " + model.getName() + " turn");
					  fillSlot(j);
					  
					  if(model.gameFinished()){
					    text.setText("game won by " + model.getName());
				      
					  }
					  model.changeTurn();
					}
					
					//after the human's move, the computer moves immediately
					if(validFlag && reEnterFlag & !model.gameFinished()){
						
					  for(int n = 40 ; n >= 0 ; n--){
						  if(model.checkReenterButton(n)){
						    buttons[n].setBackground(Color.YELLOW);
							  buttons[n].setBorderPainted(false);
							  fillSlot(n);
							  // != -1 means the computer will win
							  if(model.LookAheadFinished() != -1){
							    int place = model.LookAheadFinished();
							    try {
							    	//wait for 0.2 sec
			              Thread.sleep(200);
			            } catch (InterruptedException e) {
			              e.printStackTrace();
			            }
							    // if look ahead and wins then do the folling things
							  	buttons[place].setBackground(Color.YELLOW);
								  buttons[place].setBorderPainted(false);
								  text.setText("game won by " + model.getName());
							  	for(int i = 0; i < 42 ; i++){
						    	  buttons[i].setEnabled( false );
						      }
							  	break;
							  }
							  //sometimes the computer still wins after looing ahead
							  if(model.gameFinished()){
							    text.setText("game won by " + model.getName());
							  	for(int i = 0; i < 42 ; i++){
						    	  buttons[i].setEnabled( false );
						      }
							  }
							  model.changeTurn();
							  break;
							}
						}
				  }
			  }
			});
		}
		  uppanel.add(buttons[i]);
		}
		
		// set the layout of the Jpanel
		downpanel = new JPanel();
		downpanel.add(restart);
		frame.add(text,BorderLayout.NORTH);
		frame.add(uppanel);
		frame.add(downpanel,BorderLayout.SOUTH);
		frame.setTitle("Connect four game");
		frame.setSize(700,500); 
		frame.setLocation(400,200); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
  * (non-Javadoc)
  */
  
  @Override
  public void restart(){
    model.reset();
    frame.removeAll();
    this.gameStart();
	}

  
  /**
   * (non-Javadoc)
   */
  @Override
  public void changeTurn(){
    model.changeTurn();
    for(int i = 0 ; i < 42 ; i++){
		  buttons[i] = new JButton();
    }
  }
  
 /**
 * (non-Javadoc)
 */
  @Override
  public void fillSlot(int num){
    model.fillSlot(num);
  }
  
/**
 * (non-Javadoc)
 */
  @Override
  public void gameFinished(){
    for(int i = 0; i < 42 ; i++){
  	  buttons[i].setEnabled( false );
    }
  }
  
}
