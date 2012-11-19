package feaisil.raceforthegalaxy.card;

import java.util.ArrayList;
import java.util.List;
 
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class Card {
	private String name;
	private String graphicId;
	private int cost; // Cost or defense
	private int victoryPoints;
	private boolean prestige;
	private List<Power> powers;
	private List<EndGameBonus> endGameBonus;
	private List<Keyword> keywords;
	private Object owner;

	public Card(
			String iName, 
			String iGraphicId,
			int iCost, 
			int iVictoryPoints,
			boolean iPrestige,
			List<Keyword> iKeywords,
			List<Power> iPowers,
			List<EndGameBonus> iEgb) {
		super();
		
		powers = new ArrayList<Power>();
		endGameBonus = new ArrayList<EndGameBonus>();
		keywords = new ArrayList<Keyword>();

		if(iPowers != null)
			powers.addAll(iPowers);
		if(iEgb != null)
			endGameBonus.addAll(iEgb);
		if(iKeywords != null)
			keywords.addAll(iKeywords);
		
		graphicId = iGraphicId;
		prestige = iPrestige;
		name = iName;
		cost = iCost;
		victoryPoints = iVictoryPoints;
	}

	public final String getName()
	{
		return name;
	}
	public final int getCost()
	{
		return cost;
	}
	public final List<Power> getPowers() {
		return powers;
	}
	public final Object getOwner() {
		return owner;
	}
	public final void setOwner(Object _owner) {
		this.owner = _owner;
	}
	public final int getVictoryPoints() {
		return victoryPoints;
	}

	public boolean isPrestige() {
		return prestige;
	}
	public String getGraphicId() {
		return graphicId;
	}

	@Override
	public String toString() {
		final int _maxLen = 10;
		StringBuilder _builder = new StringBuilder();
		_builder.append("Card [");
		if (name != null) {
			_builder.append("_name=");
			_builder.append(name);
			_builder.append(", ");
		}
		_builder.append("_cost=");
		_builder.append(cost);
		_builder.append(", _victoryPoints=");
		_builder.append(victoryPoints);
		_builder.append(", ");
		if (powers != null) {
			_builder.append("_powers=");
			_builder.append(powers.subList(0,
					Math.min(powers.size(), _maxLen)));
			_builder.append(", ");
		}
		if (owner != null) {
			_builder.append("_owner=");
			_builder.append(owner);
		}
		_builder.append("]");
		return _builder.toString();
	}

	public boolean hasPower(String iPower) {
		for(Power aP: powers)
		{
			if(aP.getClass().getName()== iPower)
				return true;
		}
		return false;
	}

}
