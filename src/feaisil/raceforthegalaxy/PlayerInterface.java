package feaisil.raceforthegalaxy;

import java.util.List;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;

public interface PlayerInterface {

	public abstract Action selectAction(boolean prestigeActionUsed);

	public abstract Card chooseCardToDiscard(List<Card> cards);
	
	public abstract void responseTimeOut();

	public abstract void switchToPlayer(final Player player);

}