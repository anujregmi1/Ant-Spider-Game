package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	private static int height;
	private static int width;
	static private Point originView;
	private GameWorld gw;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
		
	}
	
	public static void setMapHeight(int height) {
		MapView.height = height;
	}
	
	public static int getMapHeight() {
		return height;
	}
	
	public static void setMapWidth(int width) {
		MapView.width = width;
	}
	
	public static int getMapWidth() {
		return width;
	}
	
	public void setMapOrigin(Point p) {
		MapView.originView = p;
	}
	
	public static Point getMapOrigin() {
		return originView;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data;
		this.repaint();
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = gw.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			if(object instanceof IDrawable) {
				((IDrawable) object).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	public void pointerPressed(int x, int y) {
		Point click = new Point(x - getParent().getAbsoluteX(), y - getParent().getAbsoluteY());
		Point originalPoint = new Point(getX(), getY());
		if(gw.getPause()) {
			gw.click(click, originalPoint);
		}
	}
	
}
