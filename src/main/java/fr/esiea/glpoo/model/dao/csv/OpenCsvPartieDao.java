package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;
import fr.esiea.glpoo.model.dao.PartieDao;
import fr.esiea.glpoo.model.domain.Orientation;
import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.PieceSaved;

public class OpenCsvPartieDao extends AbstractCsvDao implements CsvPartieDao{
	
	private static final Logger LOGGER = Logger.getLogger(OpenCsvPartieDao.class);
	
	private List<Partie> parties;
	
	/**
	 * Return : List des objets "Partie" du fichier csv parties
	 */
	@Override
	public List<Partie> findAllParties() {
		LOGGER.debug("findAllParties");

		if (parties == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return parties;
	}

	
	/**
	 * return : la partie
	 */
	@Override
	public Partie loadPiecesFromPartie(File partieName) {
		this.file = partieName;
		return loadPartie();
	}

	private Partie loadPartie() {
		LOGGER.debug("charge la partie");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}
		Partie result = new Partie();
		try {
			final List<String[]> lignes = getLignesFromFile();
			List<PieceSaved> pieces = new ArrayList<PieceSaved>(lignes.size()-2);
			
			for(String[] s : lignes){
				System.out.println(s[0]+"   "+s[1]+"   "+s[2]);
			}
			
			LOGGER.debug("chargement des donnees de la partie");
			for(int i = 0 ; i < lignes.size(); i ++){
				String[] s = lignes.get(i);
				if(i == 0){
					LOGGER.debug("first line");
					result.setFilename_pieces(s[1]);
				} else if ( i == 1){
					LOGGER.debug("second line");
					LOGGER.debug(s[1]);
					result.setFilename_faces(s[1]);
				} else {
					LOGGER.debug("after second line");
					LOGGER.debug(s[1]);
					PieceSaved p = transformLigneToPiece(s);
					pieces.add(p);
				}
			}
			LOGGER.debug("done loading pieces");
			result.setPieces(pieces);
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		return result;
	}

	
	/**
	 * 
	 * Cette piece n'a pas les attributs Nord, Sud, Est, West
	 * 
	 * @param s
	 * @return Piece sauvegardee
	 */
	private PieceSaved transformLigneToPiece(String[] s) {
		return new PieceSaved(Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),Orientation.valueOfByCode(s[4]));
	}

	@Override
	public void init(File file) {
		this.file = file;
		
		reloadParties();
	}

	
	/**
	 * Charge la liste des parties
	 */
	private void reloadParties() {
		LOGGER.debug("reloadParties");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		try {
			final List<String[]> lignes = getLignesFromFile();
			parties = new ArrayList<Partie>(lignes.size());
			for (String[] ligne : lignes) {
				final Partie partie = transformLigneToPartie(ligne);
				parties.add(partie);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		
	}

	private Partie transformLigneToPartie(String[] ligne) {
		Partie result = new Partie(Integer.parseInt(ligne[0]),ligne[1]);
		return result;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void save(String filename, File file) {
		LOGGER.debug("Sauvegarde du nom de la partie");
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(file), ' ', '\0');
//			writer.writeNext(new String[]{"#", "Pieces:" , "nom_fichier"});
//			writer.writeNext(new String[]{"#", "Faces:" , "nom_fichier"});
//			writer.writeNext(new String[]{"#", "P", "id_piece:" , "position_X" , "position_Y", "orientation(Nord/Est/Sud/Ouest)"});
//			writer.writeNext(new String[]{"Pieces:", "pieces-01.csv" , "nom_fichier"});
//			writer.writeNext(new String[]{"Faces:", "faces-01.csv" , "nom_fichier"});
//			for(int i = 0 ; i < parties.size() ; i++){
//					String[] result = {parties.};
//					writer.writeNext(result);
//			}
			
			for(Partie p : parties){
				String[] result = {""+p.getId_partie(),""+p.getFilename()}; 
				writer.writeNext(result);
			}
			
			int save_id = parties.get(parties.size()-1).getId_partie();
			save_id++;
			String[] result = {""+save_id, filename+".csv"};
			writer.writeNext(result);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
