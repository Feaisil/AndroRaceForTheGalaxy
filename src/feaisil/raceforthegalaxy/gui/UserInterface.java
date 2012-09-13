package feaisil.raceforthegalaxy.gui;

import java.util.List;

import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.PlayerInterface;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Request;

public interface UserInterface extends PlayerInterface {
	
	public abstract void showQueryDetails(Player iPlayer, Request iRequest);

	public abstract void responseTimeOut();
}