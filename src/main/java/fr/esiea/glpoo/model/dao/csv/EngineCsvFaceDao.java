//package fr.esiea.glpoo.model.dao.csv;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import fr.esiea.glpoo.model.domain.Face;
//import fr.ybonnel.csvengine.CsvEngine;
//import fr.ybonnel.csvengine.factory.AbstractCsvReader;
//import fr.ybonnel.csvengine.factory.DefaultCsvManagerFactory;
//import fr.ybonnel.csvengine.factory.OpenCsvReader;
//import fr.ybonnel.csvengine.model.Result;
//import fr.ybonnel.csvengine.model.Error;
//
//public class EngineCsvFaceDao extends AbstractCsvFaceDao{
//
//	private static final Logger LOGGER = Logger.getLogger(EngineCsvFaceDao.class);
//	
//	private void setEngineFactory(final CsvEngine engine) {
//		engine.setFactory(new DefaultCsvManagerFactory() {
//			@Override
//			public AbstractCsvReader createReaderCsv(Reader reader, char separator) {
//				return new OpenCsvReader(reader, separator) {
//					@Override
//					public String[] readLine() throws IOException {
//						String[] nextLine = super.readLine();
//						if (isLineAComment(nextLine)) {
//							nextLine = readLine();
//						}
//						return nextLine;
//					}
//
//					private boolean isLineAComment(String[] line) {
//						return line != null && line.length > 0 && line[0].startsWith("#");
//					}
//				};
//			}
//		});
//
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	protected void reloadFaces() {
//		LOGGER.debug("reloadFaces");
//		
//		try {
//			final CsvEngine engine = new CsvEngine(Face.class);
//			setEngineFactory(engine);
//
//			final FileInputStream fis = new FileInputStream(file);
//
//			final Result<Face> resultat = engine.parseInputStream(fis, Face.class);
//
//			final List<Face> listfaces = resultat.getObjects();
//			faces = (List<Face>) listfaces;
////			chienMapByNom = new HashMap<String, Chien>(chiens.size());
//			for (Face face : faces) {
//				LOGGER.debug("[FaceType] " + face);
////				chienMapByNom.put(chien.getNom(), chien);
//			}
//
//			List<Error> errors = resultat.getErrors();
//			LOGGER.debug(errors);
//
//			entetes = engine.getColumnNames(Face.class);
//
////			LOGGER.debug("[entetes] " + entetes);
//
//		} catch (Exception e) {
//			LOGGER.error("Une erreur s'est produite...", e);
//		}
//		
//	}
//	
//}
