package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;

public abstract class AbstractCsvFaceDao extends AbstractCsvDao implements CsvFaceDao {

	private static final Logger LOGGER = Logger
			.getLogger(AbstractCsvFaceDao.class);

	
	protected List<Face> faces;
	protected List<String> entetes;
	protected Map<Integer,String> img_names;

	
	
	@Override
	public List<Face> findAllFaces() {
		LOGGER.debug("findAllFaces");

		if (faces == null) {
			throw new IllegalStateException(
					"La liste n'a pas encore ete initialisee...");
		}
		for(Face face : faces){
			LOGGER.debug(face.getBg_color());
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
		genListImgNames();
	}
	
	@Override
	public Face getFaceById(int id){
		for(Face face : faces){
			if(face.getFace_id() == id){
				return face;
			}
		}
		/*face not found from id*/
		return null;
	}
	
	protected abstract void genListImgNames();

	protected abstract void reloadFaces();

	@Override
	public Map<Integer,String> getImg_names() {
		return img_names;
	}
	

}
