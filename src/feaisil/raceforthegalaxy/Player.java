package feaisil.raceforthegalaxy;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;

abstract public class Player extends Observable implements Observer, Runnable, Serializable {
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
	private static final long kMaxDecisionTime = 10000;
	private int victoryPoints;
	private Request request;
	private Reply reply;
	private Thread playerThread;
	private Timer timer;
	private StopTask task;
	
	public Player()
	{
		timer = new Timer();
		task = new StopTask(this);
		playerThread = new Thread(this);
		request = new Request();
		reply = new Reply();
		addObserver(this);
	}
	
	public void addVp(int iNumber) {
		victoryPoints += iNumber;
	}

	public void submitRequest(Request iRequest)
	{
		request = iRequest;
		reply.setProcessingDone(false);
		playerThread.start();
		timer.schedule(task, kMaxDecisionTime);
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
	}
	
	synchronized private void setDefaultReply() {
		if(!reply.isProcessingDone())
		{
			reply.setProcessingDone(true);
			reply.setReplyText("default");
		}
	}
	synchronized private void setReply(Reply iReply) {
		if(!reply.isProcessingDone())
		{
			reply = iReply;
		}
	}
	
	public Reply getReply() {
		return reply;
	}

	synchronized public void update(Observable iObs, Object iReply)
	{
		timer.cancel();
		
		setReply((Reply) iReply);
		notifyAll();
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
			e.printStackTrace();
		}
	}

}
