package fr.esiea.glpoo.model.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.PieceSaved;

/**
 * 
 * Classe de test generique pour les services Face et Piece
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class ServiceTest {
	private static final Logger LOGGER = Logger.getLogger(ServiceTest.class);
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PIECES_FILE_NAME = "pieces-01.csv";
	public final static String FACES_FILE_NAME = "faces-01.csv";
	
	private FaceService faceService;
	private PieceService pieceService;
	private PartieService partieService;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");
		faceService = FaceService.getInstance();
		pieceService = PieceService.getInstance();
		partieService = PartieService.getInstance();
		LOGGER.debug("doBefore Fin");
	}
	
	//@Test
	public void testListFace(){
		LOGGER.debug("Testing ...");
		final List<Face> faces = faceService.findAllFaces(RESOURCES_PATH+FACES_FILE_NAME);
		for(Face face : faces){
			LOGGER.debug(face.getFace_id() + " " + face.getBg_color() + " " +face.getForm() + " "+face.getForm_color());
		}
		LOGGER.debug("size =" + faces.size());
		Assert.assertNotSame(0,faces.size());
	}
	
	//@Test
	public void testListPiece(){
		LOGGER.debug("Testing ...");
		final List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH+PIECES_FILE_NAME);
		for(Piece piece : pieces){
			LOGGER.debug(piece.getPiece_id() + " " + piece.getNorth_face_id() + " " +piece.getEast_face_id() + " "+piece.getSouth_face_id()+" "+piece.getWest_face_id());
		}
		LOGGER.debug("size =" + pieces.size());
		Assert.assertNotSame(0,pieces.size());
	}
	
	//@Test
	public void testImgNameFirstFaceOfFirstPiece(){
		LOGGER.debug("test first face of a piece");
		final List<Face> faces = faceService.findAllFaces(RESOURCES_PATH+FACES_FILE_NAME);
		List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH+PIECES_FILE_NAME);
		
		for(Piece piece : pieces){
			List<Face> tmpfaces = new ArrayList<Face>();
			
			int north_id = piece.getNorth_face_id();
			int east_id = piece.getEast_face_id();
			int south_id = piece.getSouth_face_id();
			int west_id = piece.getWest_face_id();
			
			Face face = faceService.getFaceById(north_id);
			tmpfaces.add(face);
			face = faceService.getFaceById(east_id);
			tmpfaces.add(face);
			face = faceService.getFaceById(south_id);
			tmpfaces.add(face);
			face = faceService.getFaceById(west_id);
			tmpfaces.add(face);
			
			piece.setFaces(tmpfaces);
		}
		final String nameWaited = "noir.png";
		/*image name of first face of first piece in a list<Piece>*/
		final String image_name = pieces.get(0).getFaces().get(0).getImg_name();
		assertEquals(nameWaited,image_name);
	}
	
	
	@Test
	public void testPartieFirstPieceId(){
		final List<Partie> parties = partieService.findAllParties(RESOURCES_PATH+"parties.csv");
		Partie partie = partieService.loadPartieFromFile(RESOURCES_PATH+parties.get(0).getFilename());
		int result = partie.getPieces().get(0).getPiece_id();
		Assert.assertEquals(2,result);
	}
	
	/*premiere piece du JTable, doit avoir la forme (1..1) = (NESW)*/
	@Test
	public void testPartieFirstPieceGoodRotation(){
		final List<Face> faces = faceService.findAllFaces(RESOURCES_PATH+FACES_FILE_NAME);
		final List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH+PIECES_FILE_NAME);
		final List<Partie> parties = partieService.findAllParties(RESOURCES_PATH+"parties.csv");
		Partie partie = partieService.loadPartieFromFile(RESOURCES_PATH+parties.get(0).getFilename());
		List<PieceSaved> ps = new ArrayList<PieceSaved>();
		for(PieceSaved pieceSaved : partie.getPieces()){
			
			Piece tmpPiece = pieceService.getPieceById(pieceSaved.getPiece_id());
			List<Face> tmpFaces = new ArrayList<Face>();
			
			int north_id = tmpPiece.getNorth_face_id();
			int east_id = tmpPiece.getEast_face_id();
			int south_id = tmpPiece.getSouth_face_id();
			int west_id = tmpPiece.getWest_face_id();
			
			Face face = faceService.getFaceById(north_id);
			tmpFaces.add(face);
			face = faceService.getFaceById(east_id);
			tmpFaces.add(face);
			face = faceService.getFaceById(south_id);
			tmpFaces.add(face);
			face = faceService.getFaceById(west_id);
			tmpFaces.add(face);
			
			pieceSaved.setFaces(tmpFaces);
			
			pieceSaved.setNorth_face_id(north_id);
			pieceSaved.setEast_face_id(east_id);
			pieceSaved.setSouth_face_id(south_id);
			pieceSaved.setWest_face_id(west_id);
			LOGGER.debug(pieceSaved.getOrientation().getNbRotation());
			pieceSaved.rotate(pieceSaved.getOrientation().getNbRotation());
			
			ps.add(pieceSaved);
		}
		partie.setPieces(ps);
		
		String result = partie.getPieces().get(0).getFacesPattern2();
		
		Assert.assertEquals("1231",result);
	}
}
