package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

public abstract class AbstractCsvPieceDao implements CsvPieceDao{

	protected File file;
	
	public List<Piece> findAllPieces() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void init(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
}
