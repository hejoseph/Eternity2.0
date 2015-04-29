package fr.esiea.glpoo.model.dao.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;

public abstract class AbstractCsvFaceDaoTest {

	private static final Logger LOGGER = Logger.getLogger(AbstractCsvFaceDaoTest.class);
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String FACES_FILE_NAME = "faces-01.csv";
	
	protected CsvFaceDao dao;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");

		final File file = new File(RESOURCES_PATH + FACES_FILE_NAME);
		dao.init(file);

		LOGGER.debug("doBefore Fin");
	}
	
	@Test
	public void testListFacesNonVide(){
		LOGGER.debug("Taille de la liste de faces : ");
		final int tailleAttendue = 5;
		doTestTailleListe(tailleAttendue);
		
	}
	
	private void doTestTailleListe(int size){
		final List<Face> faces = dao.findAllFaces();
		LOGGER.debug("list size : "+faces.size());
		assertEquals(faces.size(),size);
	}
	
	@Test
	public void testPremiereFaceEstUnBord(){
		final FaceType ft = FaceType.BORD;
		doTestTypeFace(ft);
		
	}

	private void doTestTypeFace(FaceType f) {
		final List<Face> faces = dao.findAllFaces();
		assertEquals(faces.get(0).getFace_type().getCode(), f.getCode());
	}
	
	@Test
	public void testImgName(){
		final String img_name = "noir.png";
		doTestFirstImgName(img_name);
	}

	private void doTestFirstImgName(String img_name_waited) {
		final List<Face> faces = dao.findAllFaces();
		assertEquals(img_name_waited,faces.get(0).getImg_name());
	}
	
	@Test
	public void testGetFaceById(){
		final int id_asked = 1;
		final String color_waited = "noir";
		final Face face = dao.getFaceById(1);
		assertEquals(face.getBg_color(),color_waited);
	}

	
}
