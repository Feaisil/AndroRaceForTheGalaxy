/**
 * 
 */
package feaisil.raceforthegalaxy;

import java.util.List;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;
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

	public LocalPlayer(UserInterface iUi) {
		super(false);
		ui = iUi;
	}
	
	public void submitRequestImpl(Request iReq)
	{
		Reply aRep = new Reply(iReq);
		
		ui.showQueryDetails(this, iReq);
		
		// TODO ERASE TEST
		ui.displayCardToChoose(getHand(), 1, ChooseAction.discard);
		
		List<Card> cards = ui.getChoosenCards();
		for(Card aCard: cards)
			removeFromHand(aCard);
		
		aRep.setReplyText("Discarded card from hand");
		aRep.setProcessingDone(true);
		
		setReply(aRep);
	}
	
	public void notifyDefaultReply(Reply iRep)
	{
		ui.responseTimeOut();
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
