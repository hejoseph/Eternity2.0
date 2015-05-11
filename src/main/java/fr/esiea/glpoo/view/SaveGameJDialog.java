package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Puzzle;
import fr.esiea.glpoo.model.service.PartieService;

public class SaveGameJDialog extends JDialog{

	private static final Logger LOGGER = Logger.getLogger(SaveGameJDialog.class);
	
	private JDialog savingGameJDialog;
	private SaveGameJDialog sgjd;
	private PartieService partieService = PartieService.getInstance();
	private Puzzle model_game;
	
	private JTextField filenamejtf;

	public SaveGameJDialog(Puzzle model_game){
		this.model_game = model_game;
		
		JPanel container = new JPanel();
		container.setLayout(null);
		setTitle("Sauvegarder une partie");
		container.setPreferredSize(new Dimension(240,140));
		
		JLabel instructionlbl = new JLabel("Entrez le nom du fichier : ");
		instructionlbl.setBounds(10, 20, instructionlbl.getPreferredSize().width, instructionlbl.getPreferredSize().height);
		
		filenamejtf = new JTextField(20);
		filenamejtf.setBounds(10, 60, filenamejtf.getPreferredSize().width, filenamejtf.getPreferredSize().height);
		
		JButton saveButton = new JButton(new SaveGame());
		saveButton.setBounds(10, 100, saveButton.getPreferredSize().width, saveButton.getPreferredSize().height);
		JButton cancelButton = new JButton(new CancelSave());
		cancelButton.setBounds(150, 100, cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height);
		
		container.add(instructionlbl);
		container.add(filenamejtf);
		container.add(saveButton);
		container.add(cancelButton);
		setContentPane(container);
		pack();
		
	}
	
	public void setPopup(SaveGameJDialog sgjd) {
		this.sgjd = sgjd;
	}
	
	class SaveGame extends AbstractAction{

//		private Puzzle model_game;
		
		private SaveGame(){
			super("Sauvegarder");
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("inside click Save Game");
			String filename = filenamejtf.getText();
			System.out.println("is something? : "+filename);
			if(!filename.trim().equals("")){
				LOGGER.debug("saving ...");
				savingGameJDialog = new JDialog();
				savingGameJDialog.setTitle("Saving message");
				JLabel msg = new JLabel();
				Boolean isSaved = model_game.save(filename);
				if(isSaved){
					msg.setText("Votre partie est sauvegardee");
				} else {
					msg.setText("Vous devez remplir toutes les cases du jeu avant de sauvegarder");
				}
				savingGameJDialog.setLayout(new BorderLayout());
				savingGameJDialog.add(msg,BorderLayout.CENTER);
				savingGameJDialog.setPreferredSize(new Dimension(400,75));
				savingGameJDialog.pack();
				savingGameJDialog.setLocationRelativeTo(null);
				savingGameJDialog.setVisible(true);
			}
		}
		
	}
	
	class CancelSave extends AbstractAction{
		
		private CancelSave() {
			super("Annuler");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
}
