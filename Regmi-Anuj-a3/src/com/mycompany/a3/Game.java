package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	public final float GAMETIME = 10;
	
	//create a command variable for all the commands
	private CheckBox checkbox;
	private AccelerateCommand ac;
	private AboutCommand abc;
	private HelpCommand hc;
	private ExitCommand ec;
	private LeftCommand lc;
	private BrakeCommand bc;
	private RightCommand rc;
	private PositionCommand poc;
	private PauseCommand pc;
	private SoundCommand sc;
	
	//create all the buttons here
	private Button accelerateBtn;
	private Button leftBtn;
	private Button brakeBtn;
	private Button rightBtn;
	private Button pauseBtn;
	private Button positionBtn;
	   
	//instantiate the GameWorld, call init() and play 
	public Game() {
		//create a BoarderLayout
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		//add the observers
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//add the mp and sv to the Layout
		this.add(BorderLayout.NORTH,sv);
		this.add(BorderLayout.CENTER,mv);
		
		
		
		//functions
		toolbarThings();
		leftSideCommand();
		rightSideCommand();
		bottomSideCommand();
		
		
		this.show();
		
		//get the width and height of the gameWorld
		GameWorld.setGameHeight(mv.getHeight());
		GameWorld.setGameWidth(mv.getWidth());
				
		mv.setMapOrigin(new Point(mv.getX(), mv.getY()));
		MapView.setMapWidth(mv.getWidth());
		MapView.setMapHeight(mv.getHeight());
		
		gw.init();
		
		//create timer
		timer = new UITimer(this);
		timer.schedule((int)GAMETIME, true, this);
		
		//create sound
		gw.createSound();
		revalidate();
	}
	
	
	//function for the toolbar stuff
	private void toolbarThings() {
		//toolbar
		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);
		toolbar.setTitle("ThePath Game");  //toolbar title
		
		//sound checkbox in the side menu
		checkbox = new CheckBox();
		checkbox.getAllStyles().setBgTransparency(255);
		checkbox.getAllStyles().setBgColor(ColorUtil.GRAY);    /////////try focusable 
		sc = new SoundCommand(gw);
		checkbox.setCommand(sc);
		toolbar.addComponentToSideMenu(checkbox);
		
		//accelerate in the side menu
		ac = new AccelerateCommand(gw);
		toolbar.addCommandToSideMenu(ac);
		
		//about in side menu
		abc = new AboutCommand();
		toolbar.addCommandToSideMenu(abc);
		
		//help in side menu
		hc = new HelpCommand();
		toolbar.addCommandToRightBar(hc);
		
		//exit in side menu
		ec = new ExitCommand();
		toolbar.addCommandToSideMenu(ec);
		
	}
	
	//function for the leftSide
	private void leftSideCommand() {
		Container leftContainer = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		//accelerate button
		ac = new AccelerateCommand(gw);
		accelerateBtn = new Button(ac);
		accelerateBtn = sideTop(accelerateBtn);
		leftContainer.add(accelerateBtn);
		//key listener
		addKeyListener('a',ac);
		
		//leftButton
		lc = new LeftCommand(gw);
		leftBtn = new Button(lc);
		leftBtn = sideBottom(leftBtn);
		leftContainer.add(leftBtn);
		//key listener
		addKeyListener('l',lc);
		
		//add the container to the view
		this.add(BorderLayout.WEST,leftContainer);
	}
	
	//function for the rightSide
	private void rightSideCommand() {
		Container rightContainer = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		//brake command
		bc = new BrakeCommand(gw);
		brakeBtn = new Button(bc);
		brakeBtn = sideTop(brakeBtn);
		rightContainer.add(brakeBtn);
		//keyListener
		addKeyListener('b',bc);
		
		//rightCommand
		rc = new RightCommand(gw);
		rightBtn = new Button(rc);
		rightBtn = sideBottom(rightBtn);
		rightContainer.add(rightBtn);
		//keyListener
		addKeyListener('r',rc);
		
		//add the container to the view
		this.add(BorderLayout.EAST,rightContainer);
	}
	
	//function for the bottom commands
	private void bottomSideCommand() {
		Container bottomContainer = new Container((new BoxLayout(BoxLayout.X_AXIS)));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		
		//position 
		poc= new PositionCommand(gw);
		positionBtn = new Button(poc);
		positionBtn.getAllStyles().setMarginLeft(400);
		positionBtn = bottom(positionBtn);
		bottomContainer.add(positionBtn);
		
		//pause
		pauseBtn = new Button("Pause");
		pauseBtn = bottom(pauseBtn);
		pc = new PauseCommand(gw,this,pauseBtn);
		pauseBtn.setCommand(pc);
		bottomContainer.add(pauseBtn);
		
		
		//add the container
		this.add(BorderLayout.SOUTH,bottomContainer);
	}
	
	
	/// styling the different buttons
	private Button sideTop(Button btn) {
		btn.getAllStyles().setBgTransparency(255);
		btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		btn.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		btn.getAllStyles().setMarginTop(100);
		btn.getAllStyles().setPadding(TOP, 5);
		btn.getAllStyles().setPadding(BOTTOM, 5);
		return btn;
	}
	
	private Button sideBottom(Button btn) {
		btn.getAllStyles().setBgTransparency(255);
		btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btn.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		btn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		btn.getAllStyles().setPadding(TOP, 5);
		btn.getAllStyles().setPadding(BOTTOM, 5);
		return btn;
	}
	
	private Button bottom(Button btn) {
		btn.getAllStyles().setBgTransparency(255);
		btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btn.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		btn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		btn.getAllStyles().setPadding(TOP, 5);
		btn.getAllStyles().setPadding(BOTTOM, 5);
		return btn;
	}
	
	//what to do if the pause is pressed;
	public void pausePressed() {
		
		//play or play
		gw.setPause(!gw.getPause());
		
		accelerateBtn.getDisabledStyle().setBgColor(ColorUtil.BLUE);
		accelerateBtn.setEnabled(!accelerateBtn.isEnabled());
		leftBtn.getDisabledStyle().setBgColor(ColorUtil.BLUE);
		leftBtn.setEnabled(!leftBtn.isEnabled());
		brakeBtn.getDisabledStyle().setBgColor(ColorUtil.BLUE);
		brakeBtn.setEnabled(!brakeBtn.isEnabled());
		rightBtn.getDisabledStyle().setBgColor(ColorUtil.BLUE);
		rightBtn.setEnabled(!rightBtn.isEnabled());
		
		//if getPaused is false
		if(gw.getPause()) {
			
			//cancel the timer
			timer.cancel();
			
			//turn the sound off
			gw.turnSoundOff();
			
			//change the text
			pauseBtn.setText("Play");
			
			//remove the key listeners
			this.removeKeyListener('a',ac);
			this.removeKeyListener('b',bc);
			this.removeKeyListener('l',lc);
			this.removeKeyListener('r',rc);			
						
		} else {
			//do the exact opposite of above enable disabled things and vice versa
			
			//enable the timer
			timer.schedule((int)GAMETIME,true,this);
			
			//turn the sound on
			gw.turnSoundOn();
			
			//change the text
			pauseBtn.setText("Pause");
			
			//add the key listeners
			addKeyListener('a',ac);
			addKeyListener('b',bc);
			addKeyListener('l',lc);
			addKeyListener('r',rc);
			
		}
	}


	@Override
	public void run() {
		gw.tick(GAMETIME);
		
	}
}
