package fr.esiea.glpoo.view;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.StringReader;

import fr.esiea.glpoo.model.domain.Piece;

public class PieceTransferable implements Transferable , ClipboardOwner{

	private Piece piece;
	
	private static final DataFlavor[] flavors = {
        PieceFlavor.pieceFlavor
    };
	public PieceTransferable(Piece piece) throws Exception{
		if(piece == null){
			throw new Exception();
		}
		this.piece = piece;
	}
	
	
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		System.out.println("OH NOOO");
		return (DataFlavor[])flavors.clone();
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		System.out.println("OH Wait ..");
		if (flavor.equals(flavors[0])) {
            return true;
        }
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		System.out.println("getting data saved");
		if (flavor.equals(flavors[0])) {
			Piece p = piece;
            return (Object)piece;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
	}


	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}

//	public Object getDataSaved(){
//		return (Object)piece;
//	}
	
}
