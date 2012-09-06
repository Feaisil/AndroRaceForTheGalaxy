package feaisil.raceforthegalaxy.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Request;

public class CommandLineInterface implements UserInterface {
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
	
	/* (non-Javadoc)
	 * @see feaisil.raceforthegalaxy.gui.UserInterface#chooseDiscardCard(java.util.List)
	 */
	public Card chooseDiscardCard(List<Card> iCards)
	{
		System.out.println("Choose a card to discard from: ");
		
		for(int i = 0; i < iCards.size(); i++)
			System.out.println(" " + (i) + " - "+ iCards.get(i).toString());
		System.out.print("Card: ");
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String aBuffer = "";
		try {
			aBuffer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return iCards.get( Integer.getInteger(aBuffer, 0));
	}
}
