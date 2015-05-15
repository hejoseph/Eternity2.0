package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Score;
import fr.esiea.glpoo.model.service.ScoreService;

public class FinishedGameJDialog extends JDialog {
	
	private static final Logger LOGGER = Logger.getLogger(LoadGameJDialog.class);
	private ScoreService scoreService = ScoreService.getInstance();
	
	private TimerGame timerGame;
	private String level;
	
	private JTextField namejtf;
	
	public FinishedGameJDialog(TimerGame tg, int lvl){
		super();
		this.timerGame = tg;
		this.level = ""+lvl;
		
		initContent();
	}

	private void initContent() {
		setLayout(null);
		setTitle("Fin de la partie");
		JLabel msg = new JLabel("Votre score : "
				+ TimerGame.convertSeconds(timerGame.getS()));
		msg.setBounds(10, 10, msg.getPreferredSize().width, msg.getPreferredSize().height);
		
		JLabel msg2 = new JLabel("Entrez votre pseudo : ");
		msg2.setBounds(10, 50, msg2.getPreferredSize().width, msg2.getPreferredSize().height);
		
		namejtf = new JTextField(20);
		namejtf.setBounds(10, 70, namejtf.getPreferredSize().width, namejtf.getPreferredSize().height);
		
		JButton saveScoreButton = new JButton(new SaveScoreAction(this));
		saveScoreButton.setBounds(30, 120, saveScoreButton.getPreferredSize().width, saveScoreButton.getPreferredSize().height);
		
		JButton cancelButton = new JButton(new CancelSaveAction());
		cancelButton.setBounds(150, 120, cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height);
		
		add(msg);
		add(msg2);
		add(namejtf);
		add(saveScoreButton);
		add(cancelButton);
		
		
		setPreferredSize(new Dimension(260, 200));
		pack();
		setLocationRelativeTo(null);
	}
	
	class SaveScoreAction extends AbstractAction{
		
		private FinishedGameJDialog parentWindow;
		
		public SaveScoreAction(FinishedGameJDialog finishedGameJDialog){
			super("Save Score");
			this.parentWindow = finishedGameJDialog;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("inside click Save Score");
			String name = namejtf.getText();
			System.out.println("is something? : "+name);
			JDialog savingGameJDialog = new JDialog();
			savingGameJDialog.setTitle("Saving message");
			JLabel msg = new JLabel();
			msg.setText("Veuillez entrer un pseudo valide");
			if(!name.trim().equals("")){
				LOGGER.debug("saving ...");
				Score score = new Score(1, name, level, timerGame.getS());
				scoreService.save(score, "src/main/resources/scores.csv");
				msg.setText("Votre score est sauvegardee ! Consulter votre nouveau score dans le menu High Scores");
				savingGameJDialog.setLayout(new BorderLayout());
				this.parentWindow.dispose();
			}
			savingGameJDialog.setPreferredSize(new Dimension(msg.getPreferredSize().width+25,75));
			savingGameJDialog.pack();
			savingGameJDialog.setLocationRelativeTo(null);
			savingGameJDialog.add(msg,BorderLayout.CENTER);
			savingGameJDialog.setVisible(true);
		}
		
	}
	
	class CancelSaveAction extends AbstractAction{

		public CancelSaveAction(){
			super("Cancel");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
