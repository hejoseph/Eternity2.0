package fr.esiea.glpoo.model.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.service.FaceService;

public class FaceServiceTest {

	private static final Logger LOGGER = Logger.getLogger(FaceServiceTest.class);
	
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PIECES_FILE_NAME = "pieces-01.csv";
	public final static String FACES_FILE_NAME = "faces-01.csv";
	
	private FaceService faceService;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");
		faceService = FaceService.getInstance();
		LOGGER.debug("doBefore Fin");
	}
	
	@Test
	public void testListFace(){
		LOGGER.debug("Testing ...");
		final List<Face> faces = faceService.findAllFaces(RESOURCES_PATH+FACES_FILE_NAME);
		for(Face face : faces){
			LOGGER.debug(face.getFace_id() + " " + face.getBg_color() + " " +face.getForm() + " "+face.getForm_color());
		}
		LOGGER.debug("size =" + faces.size());
		Assert.assertNotSame(0,faces.size());
	}
	
//	@Test
//	public void testListFacesOfAPiece(){
//		final String bg_color_waited = "noir";
//		doTestFirstElementListFacesOfAPiece(bg_color_waited);
//	}
//	
//	private void doTestFirstElementListFacesOfAPiece(String bg_color){
//		
//		final List<Piece> pieces = dao.findAllPieces();
//		LOGGER.debug("size = "+pieces.get(0).getFaces().size());
//		assertEquals(pieces.get(0).getFaces().get(0).getBg_color(),bg_color);
//		
//		final String img_name = "noir.png";
//		doTestNameOfImgFace(img_name);
//	}
//	
//	private void doTestNameOfImgFace(String name){
//		LOGGER.debug("Test Img name");
//		final List<Piece> pieces = dao.findAllPieces();
//		for(Piece piece : pieces){
//			LOGGER.debug(piece.getFaces().get(0).getImg_name());
//		}
//		assertEquals(pieces.get(0).getFaces().get(0).getImg_name(),name);
//	}
	
}
