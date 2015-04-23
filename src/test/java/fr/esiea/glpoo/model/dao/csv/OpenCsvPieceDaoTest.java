package fr.esiea.glpoo.model.dao.csv;

import org.apache.log4j.Logger;

public class OpenCsvPieceDaoTest extends AbstractCsvPieceDaoTest{
	private static final Logger LOGGER = Logger.getLogger(OpenCsvPieceDaoTest.class);

	public OpenCsvPieceDaoTest() {
		LOGGER.debug("Constructeur...");

		dao = new OpenCsvPieceDao();
	}
}
