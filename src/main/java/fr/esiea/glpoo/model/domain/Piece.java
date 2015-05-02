package fr.esiea.glpoo.model.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.service.FaceService;

/**
 * Cette classe represente une piece.
 * Une piece est composee de 4 faces.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class Piece {
	
	private static final Logger LOGGER = Logger.getLogger(Piece.class);

	private List<Face> faces;
	private FaceService faceService = FaceService.getInstance();
	
	private int piece_id;
	private int north_face_id;
	private int south_face_id;
	private int east_face_id;
	private int west_face_id;
	
	/* pour la sauvegarde*/
//	private Orientation o;
	
	

	public Piece(int piece_id, int north_face_id, int east_face_id,
			int south_face_id, int west_face_id) {
		super();
		this.piece_id = piece_id;
		this.north_face_id = north_face_id;
		this.south_face_id = south_face_id;
		this.east_face_id = east_face_id;
		this.west_face_id = west_face_id;
	}
	
	/**
	 * Retourne la liste des faces d'une piece
	 * @return Liste de faces
	 */
	public List<Face> getFaces() {
		return faces;
	}
	
	public void setFaces(List<Face> faces) {
		this.faces = faces;
	}
	public int getPiece_id() {
		return piece_id;
	}
	public void setPiece_id(int piece_id) {
		this.piece_id = piece_id;
	}
	public int getNorth_face_id() {
		return north_face_id;
	}
	public void setNorth_face_id(int north_face_id) {
		this.north_face_id = north_face_id;
	}
	public int getSouth_face_id() {
		return south_face_id;
	}
	public void setSouth_face_id(int south_face_id) {
		this.south_face_id = south_face_id;
	}
	public int getEast_face_id() {
		return east_face_id;
	}
	public void setEast_face_id(int east_face_id) {
		this.east_face_id = east_face_id;
	}
	public int getWest_face_id() {
		return west_face_id;
	}
	public void setWest_face_id(int west_face_id) {
		this.west_face_id = west_face_id;
	}
	
	/**
	 * Retourne une liste d'entiers à partir des attributs de la piece
	 * @return Liste d'entiers
	 */
	public List<Integer> get_faces_id(){
		LOGGER.debug("returning list of face_id");
		List<Integer> l = new ArrayList<Integer>();
		l.add(this.north_face_id);
		l.add(this.east_face_id);
		l.add(this.south_face_id);
		l.add(this.west_face_id);
		return l;
	}

	
	/**
	 * Retourne le nombre de bord "noir" que continent une piece.
	 * @return un nombre entier
	 */
	public int nbBord(){
		if (faces == null) {
			throw new IllegalStateException(
					"La liste n'a pas encore ete initialisee...");
		}
		int nbBord = 0;
		for(Face f : faces){
			if(f.getFace_type() == FaceType.BORD){
				nbBord +=1;
			}
		}
		return nbBord;
	}
	
}
