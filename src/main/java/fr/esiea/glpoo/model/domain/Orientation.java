package fr.esiea.glpoo.model.domain;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */
public enum Orientation {
	
	NORD("N",0),
	EST("E",1),
	SUD("S",2),
	OUEST("O",3);
	
	private static Orientation[] listes = values();
	
	
	private String code;
	private int nbRotation;
	
	Orientation(String code, int rotate){
		this.code = code;
		this.nbRotation = rotate;
	}
	

    public Orientation next()
    {
        return listes[(this.ordinal()+1) % listes.length];
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNbRotation() {
		return nbRotation;
	}

	public void setNbRotation(int nbRotation) {
		this.nbRotation = nbRotation;
	}
	
}
