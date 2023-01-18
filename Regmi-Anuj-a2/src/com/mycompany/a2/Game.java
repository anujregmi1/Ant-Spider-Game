package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

import java.lang.String;

public class Game extends Form{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Toolbar myToolbar;
	
	private int xFlag = 0;
	
	//instantiate the GameWorld, call init() and play 
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		gw.init();
		//play();
		
		//layout and toolbar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		
		this.setLayout(new BorderLayout());
		
		//set the title of toolbar
		this.setTitle("ThePath Game");
		
		//create and style scoreView in top
		Container scoreContainer = new Container(new FlowLayout(Component.CENTER));
		add(BorderLayout.NORTH, scoreContainer);
		scoreContainer.getAllStyles().setPadding(Component.LEFT, 200);
		//scoreContainer.getAllStyles().setFgColor(ColorUtil.BLUE);
		scoreContainer.add(sv);
		
		//create and set the mapView
		Container mapContainer = new Container();
		add(BorderLayout.CENTER, mapContainer);
		mapContainer.add(mv);
		mapContainer.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(255, 0, 0)));
		
		//create instance of command
		AccelerateCommand accelerate = new AccelerateCommand("Accelerate", gw);
		SoundCommand toggleSound = new SoundCommand("Sound(ON/OFF)", gw);
		HelpCommand help = new HelpCommand("Help", gw);
		LeftCommand left = new LeftCommand("Left", gw);
		BreakCommand brk = new BreakCommand("Brake", gw);
		RightCommand right = new RightCommand("Right", gw);
		CollideWithFlagCommand flagCollide = new CollideWithFlagCommand("Collide with Flag", gw);
		CollideWithSpiderCommand spiderCollide = new CollideWithSpiderCommand("Collide with Spider", gw);
		CollideWithFSCommand FSCollide = new CollideWithFSCommand("Collide with Food Station", gw);
		TickCommand tick = new TickCommand("Tick", gw);
		ExitCommand exit = new ExitCommand("Exit", gw);
		AboutCommand about = new AboutCommand("About", gw);
		
		//create check box and add sound toggle
		CheckBox soundCheckBox = new CheckBox("Toggle Sound");
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCheckBox.setCommand(toggleSound);
		toggleSound.putClientProperty("SideComponent", soundCheckBox);
		
		//Add commands to side bar
		myToolbar.addComponentToSideMenu(soundCheckBox);
		myToolbar.addComponentToSideMenu(new SideBarButton(accelerate));
		myToolbar.addComponentToSideMenu(new SideBarButton(exit));
		myToolbar.addComponentToSideMenu(new SideBarButton(about));
		myToolbar.addCommandToRightBar(help);
		
		
		//add buttons to leftContainer
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 80);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		//add buttons
		leftContainer.add(new Buttons(accelerate));
		leftContainer.add(new Buttons(left));
		
		add(BorderLayout.WEST, leftContainer);
		
		//add buttons to rightContainer
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 80);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		//add butttons
		rightContainer.add(new Buttons(brk));
		rightContainer.add(new Buttons(right));
		
		add(BorderLayout.EAST, rightContainer);
		
		//add buttons to bottomContainer
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setPadding(Component.LEFT, 400);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		bottomContainer.add(new Buttons(flagCollide));
		bottomContainer.add(new Buttons(spiderCollide));
		bottomContainer.add(new Buttons(FSCollide));
		bottomContainer.add(new Buttons(tick));
		
		add(BorderLayout.SOUTH, bottomContainer);
		
		//Key Listeners
		addKeyListener('a', accelerate);
		addKeyListener('b', brk);
		addKeyListener('l', left);
		addKeyListener('r', right);
		addKeyListener('f', flagCollide);
		addKeyListener('g', spiderCollide);
		addKeyListener('t', tick);
		
		this.show();
		
	
		gw.setX(mapContainer.getWidth()*1.0);
		
		gw.setY(mapContainer.getHeight()*1.0);
	}
	
}
