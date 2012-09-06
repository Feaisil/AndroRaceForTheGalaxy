/**
 * 
 */
package feaisil.raceforthegalaxy;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.gui.UserInterface;

/**
 * @author P. MEULEMAN
 *
 */
public final class CopyOfLocalPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInterface cli;

	public CopyOfLocalPlayer(UserInterface iCli) {
		super(true);
		cli = iCli;
	}
	
	public void submitRequestImpl(Request iReq)
	{
		Reply aRep = new Reply();
		
		cli.showQueryDetails(this, iReq);
		
		// TODO ERASE TEST
		Card aCard = cli.chooseDiscardCard(getHand());
		removeFromHand(aCard);
		
		aRep.setReplyText("Discarded card from hand: " + aCard);
		aRep.setProcessingDone(true);
		
		setReply(aRep);
	}

	public void notifyDefaultReply(Reply iRep)
	{
		System.out.println("Time out! Defaulting! Choose faster next time!");
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
