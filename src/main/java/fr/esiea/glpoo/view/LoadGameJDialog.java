package fr.esiea.glpoo.view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.PieceSaved;
import fr.esiea.glpoo.model.domain.Puzzle;
import fr.esiea.glpoo.model.service.FaceService;
import fr.esiea.glpoo.model.service.PartieService;
import fr.esiea.glpoo.model.service.PieceService;

public class LoadGameJDialog extends JDialog{
	
	private static final Logger LOGGER = Logger.getLogger(LoadGameJDialog.class);
	
	private JList jl;
	private Puzzle model_game;
	private PartieService partieService = PartieService.getInstance();
	private PieceService pieceService = PieceService.getInstance();
	private FaceService faceService = FaceService.getInstance();
	private JFrame mainContainer;
	
	/*pour pouvoir fermer apres le click "charger"*/
	private JDialog lgjd;
	
	public LoadGameJDialog(Puzzle model_game, JFrame mainContainer){
		super();
		this.mainContainer = mainContainer;
		
		this.model_game = model_game;
		JPanel container = new JPanel();
		container.setLayout(null);
		setTitle("Charger une partie");
		
		container.setPreferredSize(new Dimension(225,300));
		List<Partie> parties = new ArrayList<Partie>();
		parties = partieService.findAllParties("src/main/resources/parties.csv");
		String[] model = new String[parties.size()];
		for(int i = 0 ; i < parties.size(); i ++){
			model[i] = parties.get(i).getFilename();
			System.out.println("oh " +model[i]);
		}
		jl = new JList(model);
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane js = new JScrollPane(jl);
		js.setBounds(10, 10, 200, 200);
		JButton load = new JButton(new LoadGame());
		load.setBounds(25, 250, load.getPreferredSize().width, load.getPreferredSize().height);
		
		JButton cancel = new JButton(new CancelLoad());
		cancel.setBounds(125, 250, cancel.getPreferredSize().width, cancel.getPreferredSize().height);
		
		container.add(js);
		container.add(load);
		container.add(cancel);
		
//		container.setLayout(new GridLayout(3,1));
		setContentPane(container);
		pack();
	}
	
	public void setPopup(LoadGameJDialog p){
		this.lgjd = p;
	}
	
	class LoadGame extends AbstractAction{
		
		private LoadGame() {
			super("Charger");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!jl.isSelectionEmpty()){
				LOGGER.debug("after Button Load is clicked");
				String filename = jl.getSelectedValue().toString();
				Partie partie = partieService.loadPartieFromFile("src/main/resources/"+filename);
				final List<Face> faces = faceService.findAllFaces("src/main/resources/faces-01.csv");
				final List<Piece> pieces = pieceService.findAllPieces("src/main/resources/pieces-01.csv");
				List<PieceSaved> ps = new ArrayList<PieceSaved>();
				for(PieceSaved pieceSaved : partie.getPieces()){
					
					Piece tmpPiece = pieceService.getPieceById(pieceSaved.getPiece_id());
					List<Face> tmpFaces = new ArrayList<Face>();
					
					int north_id = tmpPiece.getNorth_face_id();
					int east_id = tmpPiece.getEast_face_id();
					int south_id = tmpPiece.getSouth_face_id();
					int west_id = tmpPiece.getWest_face_id();
					
					Face face = faceService.getFaceById(north_id);
					tmpFaces.add(face);
					face = faceService.getFaceById(east_id);
					tmpFaces.add(face);
					face = faceService.getFaceById(south_id);
					tmpFaces.add(face);
					face = faceService.getFaceById(west_id);
					tmpFaces.add(face);
					
					pieceSaved.setFaces(tmpFaces);
					
					pieceSaved.setNorth_face_id(north_id);
					pieceSaved.setEast_face_id(east_id);
					pieceSaved.setSouth_face_id(south_id);
					pieceSaved.setWest_face_id(west_id);
					LOGGER.debug(pieceSaved.getOrientation().getNbRotation());
					pieceSaved.rotate(pieceSaved.getOrientation().getNbRotation());
					
					ps.add(pieceSaved);
				}
				partie.setPieces(ps);
				
				int sizegame = partie.getPieces().size();
				sizegame = (int)Math.floor(Math.sqrt(sizegame));
				Puzzle p = new Puzzle(sizegame, false);
				for(PieceSaved pieceSaved : partie.getPieces()){
					int x = pieceSaved.getX();
					int y = pieceSaved.getY();
					p.setValueAt(pieceSaved, x, y);
				}
				mainContainer.setVisible(false);
				mainContainer.dispose();
				JFrame newGame = new PuzzleJFrame(sizegame, false, p);
				newGame.setVisible(true);
				lgjd.dispose();
				System.out.println("charger");
				
			}
		}
		
	}
	
	class CancelLoad extends AbstractAction{
		
		private CancelLoad() {
			super("Annuler");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
