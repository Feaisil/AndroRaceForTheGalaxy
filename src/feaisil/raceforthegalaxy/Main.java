package feaisil.raceforthegalaxy;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		AIPlayer aPl1 = new AIPlayer();
		LocalPlayer aPl2 = new LocalPlayer();
		
		Game aGame = new Game();
		
		aGame.addPlayer(aPl1);
		aGame.addPlayer(aPl2);
		
		aGame.init();
		aGame.startGame();
		
		System.out.println(aGame);
	}

}
