package fr.esiea.glpoo.model.dao.csv;

import org.apache.log4j.Logger;

/**
 * Classe de test generique pour les DAOs de Piece en CSV.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class OpenCsvPieceDaoTest extends AbstractCsvPieceDaoTest{
	private static final Logger LOGGER = Logger.getLogger(OpenCsvPieceDaoTest.class);

	public OpenCsvPieceDaoTest() {
		LOGGER.debug("Constructeur...");

		dao = new OpenCsvPieceDao();
	}
}
