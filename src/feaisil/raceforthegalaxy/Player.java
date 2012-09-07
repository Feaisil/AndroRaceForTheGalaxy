package feaisil.raceforthegalaxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;

abstract public class Player implements Runnable, Serializable {
	private class StopTask extends TimerTask
	{
		private Player originator;
		
		public StopTask(Player iOrig)
		{
			originator = iOrig;
		}
		
		@Override
		public void run() {
			originator.handleRequestTimeOut();
		}
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final long kMaxDecisionTime = 10000;
	
	private int victoryPoints;
	private List<Card> hand;
	private List<Card> board;
	
	private Request request;
	private Reply reply;
	private Thread playerThread;
	private Timer timer;
	private StopTask task;
	private PlayerColor color;
	private boolean simultaneous;
	
	public Player(boolean iSimultaneous)
	{
		hand = new ArrayList<Card>(12);
		board = new ArrayList<Card>(14);
		timer = new Timer();
		task = new StopTask(this);
		playerThread = new Thread(this);
		request = new Request();
		reply = new Reply(request);
		simultaneous = iSimultaneous;
	}
	
	public void addVp(int iNumber) {
		victoryPoints += iNumber;
	}

	synchronized public void submitRequest(Request iRequest)
	{
		request = iRequest;
		reply = new Reply(request);
		reply.setProcessingDone(false);
		if(simultaneous)
		{
			playerThread.start();
			timer.schedule(task, kMaxDecisionTime);
		}
		else
		{
			playerThread.start();
			try {
				wait(kMaxDecisionTime);
			} catch (InterruptedException e) {
				// Too bad... Default reply!
			}
			if(!reply.isProcessingDone())
				setDefaultReply();
		}
	}
	synchronized protected void handleRequestTimeOut() {
		timer.cancel();
		if(!reply.isProcessingDone())
			setDefaultReply();
		notifyAll();
	}

	protected abstract void submitRequestImpl( Request iRequest);
	

	public void run() {
		submitRequestImpl( request);

		notifyAll();
	}
	
	synchronized private void setDefaultReply() {
		if(!reply.isProcessingDone())
		{
			reply.setProcessingDone(true);
			reply.setReplyText("default");
			notifyDefaultReply(reply);
		}
	}
	abstract public void notifyDefaultReply(Reply iRep);

	synchronized public void setReply(Reply iReply) {
		if(!reply.isProcessingDone())
		{
			reply = iReply;
		}
	}
	
	public Reply getReply() {
		return reply;
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Player [victoryPoints=");
		_builder.append(victoryPoints);
		_builder.append("]");
		return _builder.toString();
	}

	synchronized public void waitResponse() {
		try {
			wait(100);
		} catch (InterruptedException e) {
			// Got interrupted... ignore
		}
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

}
