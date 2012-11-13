/**
 * 
 */
package feaisil.raceforthegalaxy;

import java.util.List;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;
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

	public LocalPlayer(UserInterface iUi, Game iGame) throws TwoManyPlayersException {
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
