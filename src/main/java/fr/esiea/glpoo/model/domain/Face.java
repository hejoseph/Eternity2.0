package fr.esiea.glpoo.model.domain;

public class Face {

	private FaceType face_type;
	private int id_face;
	private String couleur_fond;
	private String forme;
	private String couleur_forme;
	
	
	public FaceType getFace_type() {
		return face_type;
	}
	public void setFace_type(FaceType face_type) {
		this.face_type = face_type;
	}
	public int getId_face() {
		return id_face;
	}
	public void setId_face(int id_face) {
		this.id_face = id_face;
	}
	public String getCouleur_fond() {
		return couleur_fond;
	}
	public void setCouleur_fond(String couleur_fond) {
		this.couleur_fond = couleur_fond;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public String getCouleur_forme() {
		return couleur_forme;
	}
	public void setCouleur_forme(String couleur_forme) {
		this.couleur_forme = couleur_forme;
	}
	
	
}
