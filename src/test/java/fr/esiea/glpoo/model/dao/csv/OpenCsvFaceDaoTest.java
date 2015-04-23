package fr.esiea.glpoo.model.dao.csv;

import org.apache.log4j.Logger;

public class OpenCsvFaceDaoTest extends AbstractCsvFaceDaoTest{
	private static final Logger LOGGER = Logger.getLogger(OpenCsvFaceDaoTest.class);

	public OpenCsvFaceDaoTest() {
		LOGGER.debug("Constructeur...");

		dao = new OpenCsvFaceDao();
	}
}
