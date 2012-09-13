package feaisil.raceforthegalaxy.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import feaisil.androraceforthegalaxy.R;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.guielements.SelectableCard;

public class UserInterfaceHandler implements UserInterface {
	private UserInterface destinationUi;

	 private Handler selectActionHandler;
	
	public UserInterfaceHandler(UserInterface iDestUi)
	{
		destinationUi = iDestUi;
		
		initHandlers();
	}
	private void initHandlers() {
		selectActionHandler = new SelectActionHandler();
	}


	public Action selectAction(boolean prestigeActionUsed) {
		Message aMsg = new Message();
		Bundle aBndle = new Bundle();
		aBndle.putBoolean("prestigeActionUsed", prestigeActionUsed);
		aMsg.setData(aBndle);
		
		
		return null;
	}

	public void showQueryDetails(Player iPlayer, Request iRequest) {
		// TODO Auto-generated method stub

	}

	public void responseTimeOut() {
		// TODO Auto-generated method stub

	}

	
	/// Handlers
	private class SelectActionHandler extends Handler
	{
		public void handleMessage(Message msg)
		{
			boolean prestigeActionUsed = msg.getData().getBoolean("prestigeActionUsed");
			destinationUi.selectAction(prestigeActionUsed);
		}
	}
	
}
