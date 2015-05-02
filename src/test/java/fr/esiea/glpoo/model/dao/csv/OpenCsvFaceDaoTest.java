package fr.esiea.glpoo.model.dao.csv;

import org.apache.log4j.Logger;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class OpenCsvFaceDaoTest extends AbstractCsvFaceDaoTest{
	private static final Logger LOGGER = Logger.getLogger(OpenCsvFaceDaoTest.class);

	public OpenCsvFaceDaoTest() {
		LOGGER.debug("Constructeur...");

		dao = new OpenCsvFaceDao();
	}
}
