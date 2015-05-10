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
	
	/**
	 * Recherche une orientation a partir de son code.
	 * 
	 * @param code
	 * @return L'orientation.
	 * @throws IllegalArgumentException
	 *             si le code est "vide" ou ne permet pas de trouver l'orientation
	 *             correspondante.
	 * 
	 */
	public static Orientation valueOfByCode(final String code) {

		// On verifie que le code n'est ni null ni vide.
		if (code == null || code.isEmpty()) {
			throw new IllegalArgumentException("Le code ne peut pas etre vide.");
		}

		// Note : La methode "values()" renvoie la liste de toutes les
		// "instances" ce cette enum (ie. BASSET_ALPES, CANICHE, HARRIER, etc.)

		for (Orientation o : values()) {
			if (o.code.equalsIgnoreCase(code)) {
				return o;
			}
		}

		// Si on n'a pas trouve alors on lance une exception.
		throw new IllegalArgumentException("La race de chien demandee n'existe pas.");
	}

	
}
