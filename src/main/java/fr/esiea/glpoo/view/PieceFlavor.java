package fr.esiea.glpoo.view;

import java.awt.datatransfer.DataFlavor;

import fr.esiea.glpoo.model.domain.Piece;

public class PieceFlavor extends DataFlavor{

	public static final DataFlavor pieceFlavor = new DataFlavor(Piece.class,"piece");
	
}
