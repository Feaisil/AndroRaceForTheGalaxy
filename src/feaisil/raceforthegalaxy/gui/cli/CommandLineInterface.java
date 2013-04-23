package feaisil.raceforthegalaxy.gui.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.game.Action;
import feaisil.raceforthegalaxy.game.Player;

public class CommandLineInterface implements UserInterface {
	private List<Card> cards;
	/* (non-Javadoc)
	 * @see feaisil.raceforthegalaxy.gui.UserInterface#showQueryDetails(feaisil.raceforthegalaxy.Player, feaisil.raceforthegalaxy.common.Request)
	 */
	public void displayCardToChoose(List<Card> iCards, int iNumber) {
		cards = iCards;
		
		System.out.println("Choose "+iNumber+" cards from: ");
		
		for(int i = 0; i < iCards.size(); i++)
			System.out.println(" " + (i) + " - "+ iCards.get(i).toString());
		System.out.print("Card: ");
	}

	public List<Card> getChoosenCards(int iNumber) {
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String aBuffer = "";
		List<Card> aResult= new ArrayList<Card>();
		try {
			while(aResult.size() < iNumber)
			{
				aBuffer = reader.readLine();
				aResult.add(cards.get( Integer.getInteger(aBuffer, 0)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aResult;
	}

	public void responseTimeOut() {
		System.out.println("Time out! Defaulting! Choose faster next time!");
		
	}

	public Action selectAction(boolean prestigeActionUsed) {

		List<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card("Draw explore", "Draw explore", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Keep explore", "Keep explore", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Develop", "Develop", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Settle", "Settle", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Consume trade", "Consume trade", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Consume 2x VPs", "Consume 2x VPs", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Produce", "Produce", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Prestige/Search", "Prestige/Search", 0, 0, prestigeActionUsed, null, null, null));

		
		displayCardToChoose(cards, 1);
		
		cards = getChoosenCards(1);
		return Action.exploreDraw;
	}

	public void switchToPlayer(Player player) {
		System.out.flush();
		System.out.println("Player "+player.getColor());
	}

	public Card chooseCardToDiscard(List<Card> cards) {
		displayCardToChoose(cards, 2);
		
		cards = getChoosenCards(2);
		return cards.get(0);
	}
}
