package fr.esiea.glpoo.model.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

public class PieceServiceTest {
private static final Logger LOGGER = Logger.getLogger(FaceServiceTest.class);
	
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PIECES_FILE_NAME = "pieces-01.csv";
	public final static String FACES_FILE_NAME = "faces-01.csv";
	
	private PieceService pieceService;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");
		pieceService = PieceService.getInstance();
		LOGGER.debug("doBefore Fin");
	}
	
	@Test
	public void testListPiece(){
		LOGGER.debug("Testing ...");
		final List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH+PIECES_FILE_NAME);
		for(Piece piece : pieces){
			LOGGER.debug(piece.getPiece_id() + " " + piece.getNorth_face_id() + " " +piece.getEast_face_id() + " "+piece.getSouth_face_id()+" "+piece.getWest_face_id());
		}
		LOGGER.debug("size =" + pieces.size());
		Assert.assertNotSame(0,pieces.size());
	}
	
}
