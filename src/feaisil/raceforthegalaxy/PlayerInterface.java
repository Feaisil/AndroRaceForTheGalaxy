package feaisil.raceforthegalaxy;

import feaisil.raceforthegalaxy.common.Action;

public interface PlayerInterface {

	public abstract Action selectAction(boolean prestigeActionUsed);
	
	public abstract void responseTimeOut();

	public abstract void switchToPlayer(final Player player);

}