package feaisil.raceforthegalaxy.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Request;

public class CommandLineInterface implements UserInterface {
	private List<Card> cards;
	/* (non-Javadoc)
	 * @see feaisil.raceforthegalaxy.gui.UserInterface#showQueryDetails(feaisil.raceforthegalaxy.Player, feaisil.raceforthegalaxy.common.Request)
	 */
	public void showQueryDetails(Player iPlayer, Request iRequest)
	{
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("**************************************");
		System.out.println(" Player "+iPlayer.getColor());
		System.out.println(" Action "+iRequest.getQueryText());
	}

	public void displayCardToChoose(List<Card> iCards, int iNumber,
			ChooseAction iAction) {
		cards = iCards;
		
		System.out.println("Choose a card to discard from: ");
		
		for(int i = 0; i < iCards.size(); i++)
			System.out.println(" " + (i) + " - "+ iCards.get(i).toString());
		System.out.print("Card: ");
		
	}

	public List<Card> getChoosenCards() {
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String aBuffer = "";
		try {
			aBuffer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Card> aResult= new ArrayList<Card>();
		aResult.add(cards.get( Integer.getInteger(aBuffer, 0)));
		return aResult;
	}

	public void responseTimeOut() {
		System.out.println("Time out! Defaulting! Choose faster next time!");
		
	}

	public Action selectAction(boolean prestigeActionUsed) {
		// TODO Auto-generated method stub
		return null;
	}

	public void switchToPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
}
