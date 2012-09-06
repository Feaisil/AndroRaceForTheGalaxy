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
		
		AIPlayer aPl1 = new AIPlayer();		
		LocalPlayer aPl2 = new LocalPlayer(cli);
		LocalPlayer aPl3 = new LocalPlayer(cli);
		CopyOfLocalPlayer aPl4 = new CopyOfLocalPlayer(cli);
		CopyOfLocalPlayer aPl5 = new CopyOfLocalPlayer(cli);
		
		CardList aCl = new BaseCardList();
		
		aCl.initCardList();
		
		for(Card aCard: aCl.getStartingBlueWorlds())
			aPl2.addToHand(aCard);
		for(Card aCard: aCl.getStartingRedWorlds())
			aPl3.addToHand(aCard);
		for(Card aCard: aCl.getStartingBlueWorlds())
			aPl4.addToHand(aCard);
		for(Card aCard: aCl.getStartingRedWorlds())
			aPl5.addToHand(aCard);
		
		Game aGame = new Game();
		
		aGame.addPlayer(aPl1);
		aGame.addPlayer(aPl2);
		aGame.addPlayer(aPl3);
//		aGame.addPlayer(aPl4);
//		aGame.addPlayer(aPl5);
		
		aGame.init();
		aGame.startGame();
		
		System.out.println(aGame);
	}

}
