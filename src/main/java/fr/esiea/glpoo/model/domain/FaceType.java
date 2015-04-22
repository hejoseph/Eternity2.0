package fr.esiea.glpoo.model.domain;

public enum FaceType {

	FACE("F"),
	BORD("B");
	
	private final String code;
	
	private FaceType(final String code) {
		this.code = code;
	}
	
}
