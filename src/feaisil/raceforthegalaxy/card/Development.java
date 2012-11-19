package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class Development extends Card {

	public Development(
			String iName, 
			String iGraphicId, 
			int iCost, 
			int iVictoryPoints, 
			boolean iPrestige,
			List<Keyword> iKeywords,
			List<Power> iPowers,
			List<EndGameBonus> iEgb) {
		super(iName, iGraphicId, iCost, iVictoryPoints, iPrestige, iKeywords, iPowers, iEgb);
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
