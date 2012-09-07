package feaisil.raceforthegalaxy.gui;

import java.util.List;

import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Request;

public interface UserInterface {

	public abstract void showQueryDetails(Player iPlayer, Request iRequest);

	public abstract void displayCardToChoose(List<Card> iCards, int iNumber, ChooseAction iAction);
	public abstract List<Card> getChoosenCards();
	public abstract void responseTimeOut();
}