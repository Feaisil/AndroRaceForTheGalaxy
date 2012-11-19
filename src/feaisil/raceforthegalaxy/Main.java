package feaisil.raceforthegalaxy;

import feaisil.raceforthegalaxy.card.BaseCardList;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.card.CardList;
import feaisil.raceforthegalaxy.gui.CommandLineInterface;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		CommandLineInterface cli = new CommandLineInterface();

		Game aGame = new Game();
		
		LocalPlayer aPl2 = new LocalPlayer(cli, aGame);
		LocalPlayer aPl3 = new LocalPlayer(cli, aGame);
		
		CardList aCl = new BaseCardList();
		
		aCl.initCardList();
		
		for(Card aCard: aCl.getStartingBlueWorlds())
			aPl2.addToHand(aCard);
		for(Card aCard: aCl.getStartingRedWorlds())
			aPl3.addToHand(aCard);
		
		aGame.init();
		aGame.playGame();
		
		System.out.println(aGame);
	}

}
