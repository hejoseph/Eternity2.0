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
	
	private Piece tmpPiece=null;
	
	protected Transferable createTransferable(JComponent c) {
		LOGGER.debug("createTransferable function");
		return new PieceTransferable(exportPiece(c));
	}

	public int getSourceActions(JComponent c) {
		LOGGER.debug("getSourceActions function");
		return COPY_OR_MOVE;
	}

	public boolean importData(JComponent c, Transferable t) {
		LOGGER.debug("importData function");
		if (canImport(c, t.getTransferDataFlavors())) {
			try {
//			Piece p = null;
				Piece p = (Piece) t.getTransferData(PieceFlavor.pieceFlavor);
				LOGGER.debug("before importing the piece");
//				LOGGER.debug(p.getNorth_face_id());
				importPiece(c, p);
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

	protected void exportDone(JComponent c, Transferable data, int action) {
		LOGGER.debug("exportDone function");
		cleanup(c, action == MOVE);
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		LOGGER.debug("canImport function");
		for (int i = 0; i < flavors.length; i++) {
			if (PieceFlavor.pieceFlavor.equals(flavors[i])) {
				LOGGER.debug(flavors[i]);
				return true;
			}
		}
		return false;
	}
	
	
	protected void importPiece(JComponent c, Piece p) {
		LOGGER.debug("importString function");
		JTable target = (JTable) c;
		Puzzle model = (Puzzle)target.getModel();
		rowDest = target.getSelectedRow();
		colDest = target.getSelectedColumn();
		LOGGER.debug("Dest (r;c) : ("+rowDest+";"+colDest+")");
		tmpPiece = (Piece) model.getValueAt(rowDest, colDest);
		model.setValueAt(p, rowDest, colDest);
		
	}
	
	protected void cleanup(JComponent c, boolean move) {
		LOGGER.debug("cleanup function");
		JTable source = (JTable) c;
		if (move) {
			Puzzle model = (Puzzle) source.getModel();
			model.setValueAt(tmpPiece, rowSource, colSource);
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
		Object val = table.getValueAt(rowSource, colSource);
		if(val!=null){
			LOGGER.debug("piece not null");
			p = (Piece)val;
		}
		return p;
	}
	
}
