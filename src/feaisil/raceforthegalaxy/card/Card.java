package feaisil.raceforthegalaxy.card;

import java.util.ArrayList;
import java.util.List;
 
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.VictoryPointBonus;

public class Card {
	private String name;
	private int cost;
	private int victoryPoints;
	private List<Power> powers;
	private List<VictoryPointBonus> victoryPointBonus;
	private Object owner;

	public Card(
			String iName, 
			int iCost, 
			int iVictoryPoints) {
		super();
		
		powers = new ArrayList<Power>();
		victoryPointBonus = new ArrayList<VictoryPointBonus>();		
		
		this.name = iName;
		this.cost = iCost;
		this.victoryPoints = iVictoryPoints;
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
	public final List<VictoryPointBonus> getVictoryPointBonus() {
		return victoryPointBonus;
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
	
	public final void addPower(Power iPower) {
		powers.add(iPower);
	}
	public final void addVictoryPointBonus(VictoryPointBonus iVPB){
		victoryPointBonus.add(iVPB);
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
		if (victoryPointBonus != null) {
			_builder.append("_victoryPointBonus=");
			_builder.append(victoryPointBonus.subList(0,
					Math.min(victoryPointBonus.size(), _maxLen)));
			_builder.append(", ");
		}
		if (owner != null) {
			_builder.append("_owner=");
			_builder.append(owner);
		}
		_builder.append("]");
		return _builder.toString();
	}
}
