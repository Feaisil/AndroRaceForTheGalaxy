package feaisil.raceforthegalaxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;

abstract public class Player implements Runnable, Serializable, PlayerInterface {
//	private class StopTask extends TimerTask
//	{
//		private Player originator;
//		private long id;
//		
//		public StopTask(Player iOrig)
//		{
//			originator = iOrig;
//		}
//		
//		public void setId(long id)
//		{
//			this.id = id;
//		}
//		
//		@Override
//		public void run() {
//			originator.handleRequestTimeOut(id);
//		}
//		
//	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final long kMaxDecisionTime = 10000;
	private static final long kMaxTurnTime = 100000;
	
	private int victoryPoints;
	private int prestigePoints;
	private List<Card> hand;
	private List<Card> board;
	private Game game;
	
	private Phase currentPhase;
	private Action actionChosen;
	
	private Thread playerThread;
//	private Timer timer;
//	private StopTask task;

	private PlayerColor color;
	private boolean simultaneous;
	private boolean gameActive;
	private long currentId, lastExecutedId;
	private boolean prestigeActionUsed;
	private boolean isPrestige;
	
	public Player(Game iGame, boolean iSimultaneous) throws TwoManyPlayersException
	{
		game = iGame;
		iGame.addPlayer(this);
		
		hand = new ArrayList<Card>(12);
		board = new ArrayList<Card>(14);
		
//		timer = new Timer();
//		task = new StopTask(this);
		playerThread = new Thread(this);
		
		simultaneous = iSimultaneous;
		gameActive = true;
		
		currentId = 0;
		lastExecutedId = 0;
	}
	
	synchronized public void initPhase( Phase iPhase)
	{
		// Start thread if not running
		if(!playerThread.isAlive())
			playerThread.start();
		
		currentPhase = iPhase;
		
		switch(currentPhase)
		{
		case selectAction:
			initSelectAction();
			break;
		case search:
			initSearch();
			break;
		case explore:
			initExplore();
			break;
		case develop:
			initDevelop();
			break;
		case settle:
			initSettle();
			break;
		case consume:
			initConsume();
			break;
		case produce:
			initProduce();
			break;
		default:
			break; // ???
		}

		currentId++;
		
		// Start user interactions, notify thread
		notify();
		
		if(simultaneous)
		{
			try {
				wait(kMaxTurnTime);
			} catch (InterruptedException e) {
				// ok
				e.printStackTrace();
			}
		}
	}
	
	synchronized private void executePhase()
	{
		if(currentId <= lastExecutedId)
			return;
		lastExecutedId++;
		
		switch(currentPhase)
		{
		case selectAction:
			executeSelectAction();
			break;
		case search:
			executeSearch();
			break;
		case explore:
			executeExplore();
			break;
		case develop:
			executeDevelop();
			break;
		case settle:
			executeSettle();
			break;
		case consume:
			executeConsume();
			break;
		case produce:
			executeProduce();
			break;
		default:
			break; // ???
		}
		
		notify();
	}
	
	synchronized public void terminatePhase( )
	{
		if(!simultaneous)
		{
			try {
				wait(kMaxTurnTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch(currentPhase)
		{
		case selectAction:
			terminateSelectAction();
			break;
		case search:
			terminateSearch();
			break;
		case explore:
			terminateExplore();
			break;
		case develop:
			terminateDevelop();
			break;
		case settle:
			terminateSettle();
			break;
		case consume:
			terminateConsume();
			break;
		case produce:
			terminateProduce();
			break;
		default:
			break; // ???
		}
	}
	
	private void initProduce() {
		// TODO Auto-generated method stub
		
	}

	private void initConsume() {
		// TODO Auto-generated method stub
		
	}

	private void initSettle() {
		// TODO Auto-generated method stub
		
	}

	private void initDevelop() {
		// TODO Auto-generated method stub
		
	}

	private void initExplore() {
		// TODO Auto-generated method stub
		
	}

	private void initSearch() {
		// TODO Auto-generated method stub
		
	}

	private void initSelectAction() {
		// TODO Auto-generated method stub
		
	}

	private void executeProduce() {
		// TODO Auto-generated method stub
		
	}

	private void executeConsume() {
		// TODO Auto-generated method stub
		
	}

	private void executeSettle() {
		// TODO Auto-generated method stub
		
	}

	private void executeDevelop() {
		// TODO Auto-generated method stub
		
	}

	private void executeExplore() {
		// TODO Auto-generated method stub
		
	}

	private void executeSearch() {
		// TODO Auto-generated method stub
		
	}

	private void executeSelectAction() {
		actionChosen = selectAction(prestigeActionUsed);
		prestigeActionUsed = prestigeActionUsed || isPrestige;
	}
	
	private void terminateProduce() {
		// TODO Auto-generated method stub
		
	}

	private void terminateConsume() {
		// TODO Auto-generated method stub
		
	}

	private void terminateSettle() {
		// TODO Auto-generated method stub
		
	}

	private void terminateDevelop() {
		// TODO Auto-generated method stub
		
	}

	private void terminateExplore() {
		// TODO Auto-generated method stub
		
	}

	private void terminateSearch() {
		// TODO Auto-generated method stub
		
	}

	private void terminateSelectAction() {
		// TODO Auto-generated method stub
		
	}

	synchronized public void run() {
		while(gameActive)
		{
			executePhase();
			try {
				wait(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Player [victoryPoints=");
		_builder.append(victoryPoints);
		_builder.append("]");
		return _builder.toString();
	}
	
	public void addVp(int iNumber) {
		victoryPoints += iNumber;
	}
	
	public PlayerColor getColor() {
		return color;
	}

	public void setColor(PlayerColor color) {
		this.color = color;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void addToHand(Card iCard) {
		hand.add(iCard);
	}	
	public void removeFromHand(Card iCard) {
		hand.remove(iCard);
	}

	public List<Card> getBoard() {
		return board;
	}

	public void addToBoard(Card iCard) {
		board.add(iCard);
	}
	public void removeFromBoard(Card iCard) {
		board.remove(iCard);
	}

	protected boolean isSimultaneous() {
		return simultaneous;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Phase getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(Phase currentPhase) {
		this.currentPhase = currentPhase;
	}

	public Action getActionChosen() {
		return actionChosen;
	}

	public void setActionChosen(Action actionChosen) {
		this.actionChosen = actionChosen;
	}

	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isPrestige() {
		return isPrestige;
	}

	public void setPrestige(boolean isPrestige) {
		this.isPrestige = isPrestige && !prestigeActionUsed;
	}

	public int getPrestigePoints() {
		return prestigePoints;
	}

	public void setPrestigePoints(int prestigePoints) {
		this.prestigePoints = prestigePoints;
	}

}
