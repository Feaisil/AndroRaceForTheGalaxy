package feaisil.raceforthegalaxy;

import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;

public final class AIPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AIPlayer() {
		super(false);
	}
	
	public void notifyDefaultReply(Reply iRep)
	{
		// Rebotic user doesn't care
	}

	public void submitRequestImpl(Request iReq)
	{
		System.out.print(iReq.getQueryText());

		Reply aRep = new Reply();
		
		aRep.setReplyText("Im a PC, I don't know what to do...");
		aRep.setProcessingDone(true);
		
		setReply(aRep);
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("AIPlayer [");
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
	}

}
