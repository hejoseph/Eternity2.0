package fr.esiea.glpoo.model.dao.csv;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	public void testListFaces(){
		LOGGER.debug("Taille de la liste de faces : "+dao.findAllFaces().size());
		Assert.assertTrue(dao.findAllFaces().size() != 0);
	}
	
}
