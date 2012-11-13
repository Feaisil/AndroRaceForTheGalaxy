package feaisil.raceforthegalaxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Goal;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.exception.*;

public final class Game implements Serializable, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long kMaxDecisionTime = 10000;
	private static final long kMaxTurnTime = 100000;
	
	// game real properties
	private boolean 	advanced;
	private Expansion   expansion;
	private boolean 	goalsDisabled;
	private boolean 	takeOverDisabled;
	
	// game settings
	private int 		seed;
	private Random		randGen;
	private long 		maxDecisionTime = kMaxDecisionTime;
	private long 		maxTurnTime = kMaxTurnTime;

	// game entities
	private List<Player> players;
	
	private List<Card> 	deck;
	private List<Card>	discardPile;
	private List<Card> 	temporary;
	
	private int 		remainingVp;
	
	private List<Goal>	goals;	
	
	public Game()
	{
		randGen 			= new Random();
		seed 				= randGen.nextInt();
		randGen.setSeed(seed);
		advanced 			= false;
		expansion 			= Expansion.BaseGame;
		goalsDisabled 		= true;
		takeOverDisabled 	= true;
		
		players = new ArrayList<Player>();
		
		deck = new LinkedList<Card>();
		discardPile = new LinkedList<Card>();
		temporary = new LinkedList<Card>();
		
		remainingVp = 0;
		goals = new ArrayList<Goal>();
	}
	
	// Methods
	public void init()
	{
		randGen.setSeed(seed);
	}
	
	public Card drawCard()
	{
		if(deck.size() == 0)
			discardPileToDeck();
		return deck.remove(0);
	}

	private void discardPileToDeck() {
		deck = discardPile;
	}

	public void drawVP(Player iPlayer, int iNumber) throws PlayerNotInGameException 
	{
		if(!players.contains(iPlayer))
			throw new PlayerNotInGameException();
		
		if(remainingVp < iNumber)
		{
			iPlayer.addVp(remainingVp);
			remainingVp = 0;
		}
		else
		{
			iPlayer.addVp(iNumber);
			remainingVp -= iNumber;
		}
	}
	
	public void addPlayer(Player iPlayer) throws TwoManyPlayersException
	{
		if(players.size() >= 6)
			throw new TwoManyPlayersException();
		
		if(iPlayer.isSimultaneous())
			players.add(0, iPlayer);
		else
			players.add(iPlayer);
		iPlayer.setColor(PlayerColor.values()[players.size()]);
	}
	
	public long getMaxDecisionTime() {
		return maxDecisionTime;
	}

	public void setMaxDecisionTime(long maxDecisionTime) {
		this.maxDecisionTime = maxDecisionTime;
	}

	public long getMaxTurnTime() {
		return maxTurnTime;
	}

	public void setMaxTurnTime(long maxTurnTime) {
		this.maxTurnTime = maxTurnTime;
	}
	
	@Override
	public String toString() {
		final int _maxLen = 10;
		StringBuilder _builder = new StringBuilder();
		_builder.append("Game [seed=");
		_builder.append(seed);
		_builder.append(", ");
		if (randGen != null) {
			_builder.append("randGen=");
			_builder.append(randGen);
			_builder.append(", ");
		}
		_builder.append("advanced=");
		_builder.append(advanced);
		_builder.append(", ");
		if (expansion != null) {
			_builder.append("expansion=");
			_builder.append(expansion);
			_builder.append(", ");
		}
		_builder.append("goalsDisabled=");
		_builder.append(goalsDisabled);
		_builder.append(", takeOverDisabled=");
		_builder.append(takeOverDisabled);
		_builder.append(", ");
		if (players != null) {
			_builder.append("players=");
			_builder.append(players.subList(0,
					Math.min(players.size(), _maxLen)));
			_builder.append(", ");
		}
		if (deck != null) {
			_builder.append("deck=");
			_builder.append(deck.subList(0, Math.min(deck.size(), _maxLen)));
			_builder.append(", ");
		}
		if (discardPile != null) {
			_builder.append("discardPile=");
			_builder.append(discardPile.subList(0,
					Math.min(discardPile.size(), _maxLen)));
			_builder.append(", ");
		}
		if (temporary != null) {
			_builder.append("temporary=");
			_builder.append(temporary.subList(0,
					Math.min(temporary.size(), _maxLen)));
			_builder.append(", ");
		}
		_builder.append("remainingVp=");
		_builder.append(remainingVp);
		_builder.append(", ");
		if (goals != null) {
			_builder.append("goals=");
			_builder.append(goals.subList(0, Math.min(goals.size(), _maxLen)));
		}
		_builder.append("]");
		return _builder.toString();
	}

	public void startGame() {
		// TODO Auto-generated method stub
		// TODO Remove this fake
		
		while(true){
			runPhase(Phase.selectAction);
			
			System.out.println();
			for (Player aP : players)
			{
				System.out.println("--------");
				System.out.println(aP);
				System.out.println(aP.getActionChosen());
			}
		}
	}

	synchronized private void runPhase(Phase selectaction) {
		for (Player aP : players)
		{
			aP.initPhase(Phase.selectAction);
		}
		
		try {
			for(Player ap : players)
			{
				if(ap.isActionExecuted())
					continue;
				ap.wait(250);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
	}

	public void run() {
		startGame();
	}

}
