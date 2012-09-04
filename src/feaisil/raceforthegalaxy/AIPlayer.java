package feaisil.raceforthegalaxy;

import java.io.IOException;

import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;

public final class AIPlayer extends Player {

	public void submitRequestImpl(Request iReq)
	{
		byte buffer[] = new byte[10];
		System.out.print(iReq.getQueryText());

		Reply aRep = new Reply();
		
		aRep.setReplyText("Im a PC");
		aRep.setProcessingDone(true);

		this.setChanged();
		notifyObservers(aRep);
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
