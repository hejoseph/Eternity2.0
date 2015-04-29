package fr.esiea.glpoo.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageLoader extends JPanel{
	
	BufferedImage bf;
	
	
	
	public ImageLoader(BufferedImage bf) {
		super();
		this.bf = bf;
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(bf.getWidth(), bf.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.PI / 0.5, bf.getWidth() / 2, bf.getHeight() / 2);
        g2.drawImage(bf, 0,0, null);
    }
	
}
