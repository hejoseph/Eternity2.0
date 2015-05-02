package fr.esiea.glpoo.model.domain;

/**
 * Definit si une face doit être sur le bord du jeu ou non.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public enum FaceType {

	FACE("F"),
	BORD("B");
	
	private final String code;
	
	private FaceType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	
	/**
	 * Recherche une race a partir de son code.
	 * 
	 * @param code
	 * @return La race.
	 * @throws IllegalArgumentException
	 *             si le code est "vide" ou ne permet pas de trouver la race
	 *             correspondante.
	 * 
	 */
	public static FaceType valueOfByCode(final String code) {

		// On verifie que le code n'est ni null ni vide.
		if (code == null || code.isEmpty()) {
			throw new IllegalArgumentException("Le code ne peut pas etre vide.");
		}

		// Note : La methode "values()" renvoie la liste de toutes les
		// "instances" ce cette enum (ie. BASSET_ALPES, CANICHE, HARRIER, etc.)

		for (FaceType face : values()) {
			if (face.code.equalsIgnoreCase(code)) {
				return face;
			}
		}

		// Si on n'a pas trouve alors on lance une exception.
		throw new IllegalArgumentException("La race de chien demandee n'existe pas.");
	}
	
	
}
