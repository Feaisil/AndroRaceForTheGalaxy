package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.power.Power;

public class Development extends Card {

	public Development(String iName, int iCost, int iVictoryPoints) {
		super(iName, iCost, iVictoryPoints);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Development [");
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
	}

}
