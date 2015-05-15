package fr.esiea.glpoo.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Cette classe permet de dessiner les faces d'une piece
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class ImageLoader extends JPanel{
	
	BufferedImage bf;
	Double angle;
	
	public ImageLoader(BufferedImage bf){
		super();
		this.bf = bf;
		this.angle = null;
	}
	
	public ImageLoader(BufferedImage bf,Double angle) {
		super();
		this.bf = bf;
		this.angle = angle;
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(bf.getWidth(), bf.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(this.angle != null){
        	g2.rotate(angle, bf.getWidth() / 2, bf.getHeight() / 2);
        }
        g2.drawImage(bf, 0,0, null);
    }
	
}
