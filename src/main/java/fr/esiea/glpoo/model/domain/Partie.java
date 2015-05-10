package fr.esiea.glpoo.model.domain;

import java.util.List;

public class Partie {

	private int id_partie;
	private String filename;
	private String filename_pieces;
	private String filename_faces;
	private List<PieceSaved> pieces;
	
	public Partie(){
		
	}
	
	public Partie(int id_partie, String filename) {
		this.id_partie = id_partie;
		this.filename = filename;
	}
	public int getId_partie() {
		return id_partie;
	}
	public String getFilename_pieces() {
		return filename_pieces;
	}
	public void setFilename_pieces(String filename_pieces) {
		this.filename_pieces = filename_pieces;
	}
	public String getFilename_faces() {
		return filename_faces;
	}
	public void setFilename_faces(String filename_faces) {
		this.filename_faces = filename_faces;
	}
	public List<PieceSaved> getPieces() {
		return pieces;
	}
	public void setPieces(List<PieceSaved> pieces) {
		this.pieces = pieces;
	}
	public void setId_partie(int id_partie) {
		this.id_partie = id_partie;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
