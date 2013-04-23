package feaisil.raceforthegalaxy.gui;

import java.util.List;

import feaisil.raceforthegalaxy.card.Card;

public interface PlayerUserInterface {
	public List<Card> query(QueryType query, List<Card> cards, int minCards,
			int maxCards);

	public void sendWarning(Warning warning);
}
