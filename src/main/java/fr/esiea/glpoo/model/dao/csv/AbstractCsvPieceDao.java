package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

/**
 * Classe qui definit le comportement abstraite du Dao de Piece utilisant le Csv
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public abstract class AbstractCsvPieceDao extends AbstractCsvDao implements CsvPieceDao{
	
	private static final Logger LOGGER = Logger.getLogger(AbstractCsvPieceDao.class);
	protected List<Piece> pieces;
	protected Map<Integer,Piece> pieceMapById;
	
	protected CsvFaceDao dao;
	
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
	
	@Override
	public Piece getPieceById(final int id){

		if (pieces == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return pieceMapById.get(id);
	}
	
}
