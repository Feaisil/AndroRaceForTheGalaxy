package feaisil.raceforthegalaxy.common;

import java.io.Serializable;

public class Request implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String queryText;
	
	private Phase phase;
	public long id;
	static long lastId = 0;
	
	public Request()
	{
		id = lastId++;
	}

	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Request [");
		if (queryText != null) {
			_builder.append("queryText=");
			_builder.append(queryText);
		}
		_builder.append("]");
		return _builder.toString();
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
}
