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
		return "Card [name=" + name + ", graphicId=" + graphicId + ", cost="
				+ cost + ", victoryPoints=" + victoryPoints + ", prestige="
				+ prestige + ", powers=" + powers + ", endGameBonus="
				+ endGameBonus + ", keywords=" + keywords + ", owner=" + owner
				+ "]\n";
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
