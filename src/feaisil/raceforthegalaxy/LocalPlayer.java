/**
 * 
 */
package feaisil.raceforthegalaxy;

import java.util.List;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.exception.ActiveGameException;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;
import feaisil.raceforthegalaxy.gui.ChooseAction;
import feaisil.raceforthegalaxy.gui.UserInterface;

/**
 * @author P. MEULEMAN
 *
 */
public final class LocalPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInterface ui;

	public LocalPlayer(UserInterface iUi, Game iGame) throws TwoManyPlayersException, ActiveGameException {
		super(iUi, iGame, false);
		ui = iUi;
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("LocalPlayer [");
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
	}
}
