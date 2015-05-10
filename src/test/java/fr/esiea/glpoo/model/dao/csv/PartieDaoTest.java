package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.glpoo.model.dao.PartieDao;
import fr.esiea.glpoo.model.domain.Partie;

public class PartieDaoTest {

	private static final Logger LOGGER = Logger.getLogger(AbstractCsvPieceDaoTest.class);
	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PARTIE_FILE_NAME = "partie-01.csv";
	public final static String PARTIES_FILE_NAME = "parties.csv";
	
	private CsvPartieDao dao;
	
	@Before
	public void doBefore() {
		LOGGER.debug("doBefore Debut");
		dao = new OpenCsvPartieDao();
		final File file = new File(RESOURCES_PATH + PARTIES_FILE_NAME);
		dao.init(file);

		LOGGER.debug("doBefore Fin");
	}
	
	@Test
	public void testNomPremierFichier(){
		final List<Partie> parties = dao.findAllParties();
		String nom_fichier = parties.get(0).getFilename();
		Assert.assertEquals("partie-01.csv",nom_fichier);
	}
	
	@Test
	public void testNomFichierPieceDeLaPartie(){
		final List<Partie> parties = dao.findAllParties();
		Partie premier = parties.get(0);
		File file = new File(RESOURCES_PATH + premier.getFilename());
		premier = (Partie) dao.loadPiecesFromPartie(file);
		String result = premier.getFilename_pieces();
		Assert.assertEquals("pieces-01.csv", result);
	}
	
	
}
