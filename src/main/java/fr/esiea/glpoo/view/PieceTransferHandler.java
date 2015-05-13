package fr.esiea.glpoo.view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.Puzzle;

public class PieceTransferHandler extends TransferHandler{

	private static final Logger LOGGER = Logger.getLogger(PieceTransferHandler.class);
	
	private int rowSource, colSource;
	private int rowDest, colDest;
	
	
	private Piece tmpPiece;
	
	private int i = 5;
	
	public PieceTransferHandler(){
		System.out.println("created right now :/");
	}
	
	
	@Override
	protected Transferable createTransferable(JComponent c){
		LOGGER.debug("createTransferable function");
		this.i = 4;
		LOGGER.debug("i is = "+this.i);
		try{
			return new PieceTransferable(exportPiece(c));
		} catch (Exception e){
			System.out.println("cannot drag the object");
			return null;
		}
	}

	@Override
	public int getSourceActions(JComponent c) {
		LOGGER.debug("getSourceActions function");
		LOGGER.debug("i is = "+this.i);
		return COPY_OR_MOVE;
	}

	@Override
	public boolean importData(JComponent c, Transferable t) {
		LOGGER.debug("importData function");
		LOGGER.debug("i is = "+this.i);
		if (canImport(c, t.getTransferDataFlavors())) {
			try {
//			Piece p = null;
				Piece p = (Piece) t.getTransferData(PieceFlavor.pieceFlavor);
				LOGGER.debug("before importing the piece");
//				LOGGER.debug(p.getNorth_face_id());
				LOGGER.debug("before importing data tmp is : "+this.tmpPiece);
				try {
					this.tmpPiece = importPiece(c, p);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				LOGGER.debug("after importing data tmp is : "+this.tmpPiece);
				
				return true;
			} catch (UnsupportedFlavorException ufe) {
				System.out.println("goddamn");
			} catch (IOException ioe) {
				System.out.println("fuck this shit ..");
		}
		}
		LOGGER.debug("cannot import sorry");
		return false;
	}

	
	@Override
	protected void exportDone(JComponent c, Transferable data, int action) {
		LOGGER.debug("exportDone function");
		cleanup(c, action == MOVE);
		LOGGER.debug("i is = "+this.i);
	}

	
	@Override
	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		LOGGER.debug("canImport function");
		LOGGER.debug("i is = "+this.i);
		for (int i = 0; i < flavors.length; i++) {
			if (PieceFlavor.pieceFlavor.equals(flavors[i])) {
				LOGGER.debug(flavors[i]);
				return true;
			}
		}
		return false;
	}
	
	public Piece importPiece(JComponent c, Piece p) throws Exception{
		LOGGER.debug("importString function");
		JTable target = (JTable) c;
		Puzzle model = (Puzzle)target.getModel();
		rowDest = target.getSelectedRow();
		colDest = target.getSelectedColumn();
		LOGGER.debug("Dest (r;c) : ("+rowDest+";"+colDest+")");
		Piece resultTmp = (Piece) model.getValueAt(rowDest, colDest);
		if(resultTmp != null){
			throw new Exception();
		}
		LOGGER.debug("is null dest ? "+resultTmp);
		model.setValueAt(p, rowDest, colDest);
		return resultTmp;
	}
	
	public void cleanup(JComponent c, boolean move) {
		LOGGER.debug("cleanup function");
		JTable source = (JTable) c;
		if (move) {
			Puzzle model = (Puzzle) source.getModel();
			LOGGER.debug("after importing tmp is null ? "+this.tmpPiece);
			LOGGER.debug("what is I : "+i);
			model.setValueAt(this.tmpPiece, rowSource, colSource);
		}
		rowSource = -1;
	}
	
	
	
	protected Piece exportPiece(JComponent c) {
		LOGGER.debug("exportString function");
		JTable table = (JTable) c;
		rowSource = table.getSelectedRow();
		colSource = table.getSelectedColumn();
		LOGGER.debug("Source (r;c) : ("+rowSource+";"+colSource+")");
		Piece p = null;
		try{
			Object val = table.getValueAt(rowSource, colSource);
			p = (Piece)val;
		}catch(Exception e){
			LOGGER.debug("piece not null");
		}
		return p;
	}
	
}
