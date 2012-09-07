package feaisil.raceforthegalaxy.common;

import java.io.Serializable;

public class Reply implements Serializable {
	long id;
	public Reply(Request iReq) {
		id = iReq.id;
	}

	private Reply(){}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Reply [");
		if (ReplyText != null) {
			_builder.append("ReplyText=");
			_builder.append(ReplyText);
			_builder.append(", ");
		}
		_builder.append("processingDone=");
		_builder.append(processingDone);
		_builder.append("]");
		return _builder.toString();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 	ReplyText;
	private boolean	processingDone;

	public String getReplyText() {
		return ReplyText;
	}

	public void setReplyText(String replyText) {
		ReplyText = replyText;
	}

	public boolean isProcessingDone() {
		return processingDone;
	}

	public void setProcessingDone(boolean processingDone) {
		this.processingDone = processingDone;
	}
}
