package feaisil.raceforthegalaxy.card;

import java.util.List;
 
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.VictoryPointBonus;

public abstract class Card {
	private String _name;
	private int _cost;
	private int _victoryPoints;
	private List<Power> _powers;
	private List<VictoryPointBonus> _victoryPointBonus;
	private Object _owner;

	public Card(
			String iName, 
			int iCost, 
			int iVictoryPoints) {
		super();
		this._name = iName;
		this._cost = iCost;
		this._victoryPoints = iVictoryPoints;
	}

	public final String getName()
	{
		return _name;
	}
	public final int getCost()
	{
		return _cost;
	}
	public final List<Power> getPowers() {
		return _powers;
	}
	public final List<VictoryPointBonus> getVictoryPointBonus() {
		return _victoryPointBonus;
	}
	
	public final Object getOwner() {
		return _owner;
	}
	public final void setOwner(Object _owner) {
		this._owner = _owner;
	}
	public final int getVictoryPoints() {
		return _victoryPoints;
	}
	
	public final void addPower(Power iPower) {
		_powers.add(iPower);
	}
	public final void addVictoryPointBonus(VictoryPointBonus iVPB){
		_victoryPointBonus.add(iVPB);
	}

	@Override
	public String toString() {
		final int _maxLen = 10;
		StringBuilder _builder = new StringBuilder();
		_builder.append("Card [");
		if (_name != null) {
			_builder.append("_name=");
			_builder.append(_name);
			_builder.append(", ");
		}
		_builder.append("_cost=");
		_builder.append(_cost);
		_builder.append(", _victoryPoints=");
		_builder.append(_victoryPoints);
		_builder.append(", ");
		if (_powers != null) {
			_builder.append("_powers=");
			_builder.append(_powers.subList(0,
					Math.min(_powers.size(), _maxLen)));
			_builder.append(", ");
		}
		if (_victoryPointBonus != null) {
			_builder.append("_victoryPointBonus=");
			_builder.append(_victoryPointBonus.subList(0,
					Math.min(_victoryPointBonus.size(), _maxLen)));
			_builder.append(", ");
		}
		if (_owner != null) {
			_builder.append("_owner=");
			_builder.append(_owner);
		}
		_builder.append("]");
		return _builder.toString();
	}
}
