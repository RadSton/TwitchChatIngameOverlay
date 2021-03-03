package io.radston12.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import io.radston12.Main;
import io.radston12.vars.GuiVariables;

public class Drawer extends JLabel {

	private static final long serialVersionUID = 1L;
	
	int i = 0;
	@Override
	protected void paintComponent(Graphics g) {
		i++;
		if(GuiVariables.mouseInScreen) {
			if(GuiVariables.hoversExit)
				g.setColor(Color.RED);
			g.drawLine(GuiVariables.WIDTH - 50, 10, GuiVariables.WIDTH - 20, 35);
			g.drawLine(GuiVariables.WIDTH - 20, 10, GuiVariables.WIDTH - 50, 35);
		}
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.setColor(GuiVariables.fontColor);
		int height = 20;
		int fontheigth = g.getFontMetrics().getHeight();
		for(String s : Main.instance.messageString.split(System.getProperty("line.separator"))) {
			g.drawString(s, 10, height);
			height += (GuiVariables.LINESEPARATORHEIGHT) + fontheigth;
		}
		
	}
	
}
