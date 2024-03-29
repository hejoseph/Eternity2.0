package fr.esiea.glpoo.model.dao.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;
import fr.esiea.glpoo.model.domain.Piece;

/**
 * Classe de test generique pour les DAOs de Piece en CSV.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public abstract class AbstractCsvPieceDaoTest {

	private static final Logger LOGGER = Logger.getLogger(AbstractCsvPieceDaoTest.class);
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PIECES_FILE_NAME = "pieces-01.csv";
	
	protected CsvPieceDao dao;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");

		final File file = new File(RESOURCES_PATH + PIECES_FILE_NAME);
		dao.init(file);

		LOGGER.debug("doBefore Fin");
	}
	
//	@Test
	public void testListPiecesNonVide(){
		LOGGER.debug("Taille de la liste de pieces : ");
		final int tailleAttendue = 3;
		doTestTailleListe(tailleAttendue);
		
	}
	
	private void doTestTailleListe(int size){
		final List<Piece> pieces = dao.findAllPieces();
		assertEquals(size,pieces.size());
	}
	
	
}
