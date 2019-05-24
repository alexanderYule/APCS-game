import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main 
{
	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();

		JFrame window = (JFrame)canvas.getFrame();
	
		window.setSize(927, 750);
		window.setLocation(350, 0);
		window.setMinimumSize(new Dimension(927,750));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Hell Shadow");

		window.setVisible(true);
	}
}
