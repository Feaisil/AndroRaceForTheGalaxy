package feaisil.raceforthegalaxy.card;

import java.util.ArrayList;
import java.util.List;

public abstract class CardList {
	private List<Card> Deck;
	private List<Card> StartingWorlds;
	private List<Card> StartingBlueWorlds;
	private List<Card> StartingRedWorlds;
	
	public CardList()
	{
		Deck = new ArrayList<Card>(300);
		StartingWorlds = new ArrayList<Card>(300);
		StartingBlueWorlds = new ArrayList<Card>(10);
		StartingRedWorlds = new ArrayList<Card>(10);
	}
	public abstract void initCardList();
	
	public final void addCard(Card iCard)
	{
		Deck.add(iCard);
	}
	public final  void addStartingBlueWorld(Card iCard)
	{
		addCard(iCard);
		StartingWorlds.add(iCard);
		StartingBlueWorlds.add(iCard);
	}
	public final void addStartingRedWorld(Card iCard)
	{
		addCard(iCard);
		StartingWorlds.add(iCard);
		StartingRedWorlds.add(iCard);
	}
	public final List<Card> getDeck() {
		return Deck;
	}

	public final List<Card> getStartingWorlds() {
		return StartingWorlds;
	}

	public final List<Card> getStartingBlueWorlds() {
		return StartingBlueWorlds;
	}

	public final List<Card> getStartingRedWorlds() {
		return StartingRedWorlds;
	}

	@Override
	public String toString() {
		final int _maxLen = 10;
		StringBuilder _builder = new StringBuilder();
		_builder.append("CardList [");
		if (Deck != null) {
			_builder.append("Deck=");
			_builder.append(Deck.subList(0, Math.min(Deck.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingWorlds != null) {
			_builder.append("StartingWorlds=");
			_builder.append(StartingWorlds.subList(0,
					Math.min(StartingWorlds.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingBlueWorlds != null) {
			_builder.append("StartingBlueWorlds=");
			_builder.append(StartingBlueWorlds.subList(0,
					Math.min(StartingBlueWorlds.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingRedWorlds != null) {
			_builder.append("StartingRedWorlds=");
			_builder.append(StartingRedWorlds.subList(0,
					Math.min(StartingRedWorlds.size(), _maxLen)));
		}
		_builder.append("]");
		return _builder.toString();
	}
}