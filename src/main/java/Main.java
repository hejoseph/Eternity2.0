import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import fr.esiea.glpoo.model.dao.csv.CsvFaceDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvFaceDao;
import fr.esiea.glpoo.model.domain.Face;


public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		/*exemple d'utilisation: findAllFaces*/
		CsvFaceDao faceDao = new OpenCsvFaceDao();
		faceDao.init(new File("src/main/resources/faces-01.csv"));
		List<Face> faces = faceDao.findAllFaces();
		
		LOGGER.debug(faces.size());
	}
}
