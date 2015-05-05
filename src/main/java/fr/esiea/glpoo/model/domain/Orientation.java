package fr.esiea.glpoo.model.domain;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */
public enum Orientation {

	NORD(0),
	EST(1),
	SUD(2),
	OUEST(3);
	
	private int direction;
	
	Orientation(int i){
		this.direction = i;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
