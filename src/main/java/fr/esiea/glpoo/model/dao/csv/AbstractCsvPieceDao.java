package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

public abstract class AbstractCsvPieceDao extends AbstractCsvDao implements CsvPieceDao{
	
	private static final Logger LOGGER = Logger.getLogger(AbstractCsvPieceDao.class);
	protected List<Piece> pieces;
	
	public List<Piece> findAllPieces() {
		LOGGER.debug("findAllPieces");

		if (pieces == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return pieces;
	}
	
	public void init(File file) {
		this.file = file;
		
		reloadPieces();
	}
	
	public File getFile() {
		return file;
	}
	
	protected abstract void reloadPieces();
	
}
