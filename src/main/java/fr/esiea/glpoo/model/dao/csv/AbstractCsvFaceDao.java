package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;

public abstract class AbstractCsvFaceDao implements CsvFaceDao {

	private static final Logger LOGGER = Logger
			.getLogger(AbstractCsvFaceDao.class);

	protected File file;
	protected List<Face> faces;
	protected List<String> entetes;

	@Override
	public List<Face> findAllFaces() {
		LOGGER.debug("findAllFaces");

		if (faces == null) {
			throw new IllegalStateException(
					"La liste n'a pas encore ete initialisee...");
		}

		return faces;
	}
	
//	@Override
//	public List<String> getEntetes() {
//		return entetes;
//	}
	
	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void init(File file) {
		LOGGER.debug("init");
		this.file = file;
		
		reloadFaces();
	}
	
	protected abstract void reloadFaces();

}
