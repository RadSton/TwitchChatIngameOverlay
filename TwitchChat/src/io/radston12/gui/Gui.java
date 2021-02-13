package io.radston12.gui;

import static io.radston12.vars.GuiVariables.jf;

import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

import io.radston12.Main;
import io.radston12.draw.Drawer;
import io.radston12.vars.GuiVariables;
public class Gui {

	public Gui() {
		jf = new JFrame();
		jf.setFocusable(false);
		jf.setSize(GuiVariables.WIDTH, GuiVariables.HEIGHT);
		jf.setLayout(null);
		jf.setAlwaysOnTop(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setUndecorated(true);
		jf.setBackground(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		jf.setVisible(true);
		jf.addMouseMotionListener(new Gui.DragListener());
		jf.addMouseListener(new Gui.EnterListener());
		JLabel comp = new Drawer();
		comp.setBounds(0, 0, GuiVariables.WIDTH, GuiVariables.HEIGHT);
		jf.add(comp);
		Loop.start(comp);
	}
	
	public static void updateScreen() {
		if(GuiVariables.mouseInScreen) {
			jf.setBackground(new Color(0.7f, 0.7f, 0.7f, 0.5f));
		} else {
			jf.setBackground(new Color(0.3f, 0.3f, 0.3f, 0.2f));
		}
	}
	
	public class DragListener extends MouseMotionAdapter {

	    @Override
	    public void mouseDragged(MouseEvent e) {
	    	GuiVariables.mouseInScreen = true;
	        jf.setLocation(MouseInfo.getPointerInfo().getLocation());
	        updateScreen();
	    }
	    
	    @Override
	    public void mouseMoved(MouseEvent e) {
	    	Point p = new Point(e.getLocationOnScreen().x - jf.getLocationOnScreen().x,e.getLocationOnScreen().y - jf.getLocationOnScreen().y);
	    	if(p.x >= (GuiVariables.WIDTH - 50) && p.x < (GuiVariables.WIDTH - 20)) {
	    		if(p.y >= 10 && p.y < 35) {
		    		GuiVariables.hoversExit = true;
		    	} else {
		    		GuiVariables.hoversExit = false;
		    	}
	    	} else {
	    		GuiVariables.hoversExit = false;
	    	}
	    }
	}
	
	public class EnterListener extends MouseAdapter {
		@Override   
		public void mouseEntered( MouseEvent e ) {
			GuiVariables.mouseInScreen = true;
			updateScreen();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			GuiVariables.mouseInScreen = false;
			updateScreen();
			
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getLocationOnScreen().x - jf.getLocationOnScreen().x,e.getLocationOnScreen().y - jf.getLocationOnScreen().y);
			if(p.x >= (GuiVariables.WIDTH - 50) && p.x < (GuiVariables.WIDTH - 20)) {
	    		if(p.y >= 10 && p.y < 35) {
		    		System.exit(0);
		    	} 
	    	}
		}
	}
	
	public static class Loop {

		public final static int DELAY = 1000;
		
		public static void start(final Component c) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Main.instance.update();
					c.repaint();
				}
			},(long) DELAY, DELAY);
		}
	}
}
