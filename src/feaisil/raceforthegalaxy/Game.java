package feaisil.raceforthegalaxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Goal;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.exception.*;
import feaisil.raceforthegalaxy.power.ChooseActionLast;

public final class Game implements Serializable, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long kMaxDecisionTime = 10000;
	private static final long kMaxTurnTime = 100000;

	private static Game currentInstance;
	private boolean gameActive;
	
	// game real properties
	private int 		turn;
	private boolean 	advanced;
	private List<Expansion>  expansion;
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
		expansion 			= new ArrayList<Expansion>();
		expansion.add(Expansion.BaseGame);
		goalsDisabled 		= true;
		takeOverDisabled 	= true;
		
		players = new ArrayList<Player>();
		
		deck = new LinkedList<Card>();
		discardPile = new LinkedList<Card>();
		temporary = new LinkedList<Card>();

		turn = 0;
		remainingVp = 0;
		goals = new ArrayList<Goal>();
	}
	
	// Methods
	public void init()
	{
		randGen.setSeed(seed);
	}
	
	public Card drawCard() throws InactiveGameException
	{
		if(!isGameActive())
			throw new InactiveGameException();
		if(deck.size() == 0)
			discardPileToDeck();
		return deck.remove(0);
	}

	private void discardPileToDeck() {
		deck = discardPile;
	}

	public void drawVP(Player iPlayer, int iNumber) throws PlayerNotInGameException, InactiveGameException 
	{
		if(!isGameActive())
			throw new InactiveGameException();
		
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
	
	public void addPlayer(Player iPlayer) throws TwoManyPlayersException, ActiveGameException
	{
		if(isGameActive())
			throw new ActiveGameException();
		
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
	
	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	public static Game getCurrentInstance() {
		return currentInstance;
	}

	public static void setCurrentInstance(Game currentInstance) {
		Game.currentInstance = currentInstance;
	}

	private boolean hasPlayerAction(final Action iAction)
	{
		for(Player aP: players)
		{
			if(aP.getActionChosen() == iAction)
				return true;
		}
		return false;
	}
	private boolean isPhasePlayed(final Phase iPhase)
	{
		switch(iPhase)
		{
		case selectAction:
			return true;
		case search:
			if(hasPlayerAction(Action.search))
				return true;
			break;
		case explore:
			if(hasPlayerAction(Action.exploreDraw) || hasPlayerAction(Action.exploreKeep))
				return true;
			break;
		case develop:
			if(hasPlayerAction(Action.develop))
				return true;
			break;
		case settle:
			if(hasPlayerAction(Action.settle))
				return true;
			break;
		case consume:
			if(hasPlayerAction(Action.trade) || hasPlayerAction(Action.consume))
				return true;
			break;
		case produce:
			if(hasPlayerAction(Action.produce))
				return true;
			break;
		default:
			break;
		}
		return false;
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

	public void playGame() {
		executeStartingPhase();
		while(isGameActive()){
			executePhaseSelectAction();
			executePhaseSearch();
			executePhaseExplore();
			executePhaseDevelop();
			executePhaseSettle();
			executePhaseConsume();
			executePhaseProduce();
			checkGameEnd();
		}
	}

	private void executeStartingPhase() {
		// TODO
		
		// Draw starting world cards according to parameters
		// Draw starting hand cards according to parameters
		// Discards...
		// Reorder player list according to there primary world
	}


	synchronized private void executePhaseSelectAction()
	{
		if(!isPhasePlayed(Phase.selectAction))
			return;
		// Only one special case due to powers: select last according to others choices
		Player aChoosingLastPlayer = null;
		for (Player aP : players)
		{
			aP.setActionChosen(Action.none);
			if(!aP.hasPower(ChooseActionLast.class.getName()))
				aP.initPhase(Phase.selectAction);
			else
				aChoosingLastPlayer = aP;
		}
		try {
			for(Player aP : players)
				while(!aP.isActionExecuted() && aP != aChoosingLastPlayer)
					aP.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
		if(aChoosingLastPlayer != null)
		{
			aChoosingLastPlayer.initPhase(Phase.selectAction);
			try {
				while(!aChoosingLastPlayer.isActionExecuted())
					aChoosingLastPlayer.wait(250);
			} catch (InterruptedException e) {
				// Ignore
			}
			aChoosingLastPlayer.terminatePhase();
		}
	}
	synchronized private void executePhaseSearch()
	{
		if(!isPhasePlayed(Phase.search))
			return;
		// Execute search for each player that choosed this action in order
		for(Player aP: players)
		{
			if(aP.getActionChosen() == Action.search)
			{
				aP.initPhase(Phase.search);
				try {
					while(!aP.isActionExecuted())
						aP.wait(250);
				} catch (InterruptedException e) {
					// Ignore
				}
				aP.terminatePhase();
			}
		}
	}
	synchronized private void executePhaseExplore()
	{
		// Explore does not need any special action at game level
		if(!isPhasePlayed(Phase.explore))
			return;
		for (Player aP : players)
		{
			aP.initPhase(Phase.explore);
		}
		try {
			for(Player ap : players)
				while(!ap.isActionExecuted())
					ap.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
	}
	synchronized private void executePhaseDevelop()
	{
		// Develop does not need any special action at game level
		if(!isPhasePlayed(Phase.develop))
			return;
		for (Player aP : players)
		{
			aP.initPhase(Phase.develop);
		}
		try {
			for(Player ap : players)
				while(!ap.isActionExecuted())
					ap.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
	}
	synchronized private void executePhaseSettle()
	{
		// Settle is in fact two phases: settle first, take over second
		if(!isPhasePlayed(Phase.settle))
			return;
		
		for (Player aP : players)
		{
			aP.initPhase(Phase.selectAction);
		}

		try {
			for(Player ap : players)
				while(!ap.isActionExecuted())
					ap.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
		
		// Players who haven't settled can now use there take over powers
		// TODO 
	}
	synchronized private void executePhaseConsume()
	{
		// Consume does not need any special action at game level
		if(!isPhasePlayed(Phase.consume))
			return;
		
		for (Player aP : players)
		{
			aP.initPhase(Phase.develop);
		}
		try {
			for(Player ap : players)
				while(!ap.isActionExecuted())
					ap.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
	}
	synchronized private void executePhaseProduce()
	{
		// Produce does not need any special action at game level
		if(!isPhasePlayed(Phase.consume))
			return;
		
		for (Player aP : players)
		{
			aP.initPhase(Phase.develop);
		}
		try {
			for(Player ap : players)
				while(!ap.isActionExecuted())
					ap.wait(250);
		} catch (InterruptedException e) {
			// Ignore
		}
		for (Player aP : players)
		{
			aP.terminatePhase();
		}
	}
	private void checkGameEnd() {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {
		// If game active, don't run
		if(isGameActive())
			return;
		setGameActive(true);
		playGame();
		setGameActive(false);
	}

}
