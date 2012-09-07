package feaisil.androraceforthegalaxy;

import java.util.ArrayList;
import java.util.List;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.LocalPlayer;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.BaseCardList;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.card.CardList;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;
import feaisil.raceforthegalaxy.gui.ChooseAction;
import feaisil.raceforthegalaxy.gui.UserInterface;
import feaisil.raceforthegalaxy.guielements.SelectableCard;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class LocalGameActivity extends Activity implements UserInterface {

	private Game game;
	private Player currentPlayer;
	
	private LocalGameActivity current = this;
	
	Handler showQueryDetailsHandler, displayCardToChooseHandler;
	private int numberOfCardsToChoose;
	private int numberOfCardsChosen;
	private List<SelectableCard> cardImages;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        
        cardImages = new ArrayList<SelectableCard>(12);
        
        initGame();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_local_game, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initGame() {
    	game = new Game();
    	
		LocalPlayer aPl2 = new LocalPlayer(this);
		LocalPlayer aPl3 = new LocalPlayer(this);
		
		CardList aCl = new BaseCardList();
		
		aCl.initCardList();
		
		for(Card aCard: aCl.getStartingBlueWorlds())
			aPl2.addToHand(aCard);
		for(Card aCard: aCl.getStartingRedWorlds())
			aPl3.addToHand(aCard);

		try {
			game.addPlayer(aPl2);
			game.addPlayer(aPl3);
		} catch (TwoManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		game.init();
		
		showQueryDetailsHandler = new Handler()
		{
			public void handleMessage(Message msg) {
				TextView aPlayerText = (TextView) findViewById(R.id.PlayerName);
				aPlayerText.setText((String)msg.obj);
				aPlayerText.setTextColor(msg.arg1);
	        }
		};
		displayCardToChooseHandler = new Handler()
		{
			public void handleMessage(Message msg) {
				LinearLayout aLayout = (LinearLayout)findViewById(R.id.chosecardlistlayout);
				
				aLayout.removeAllViewsInLayout();
				for(Card aCard : (List<Card>)msg.obj)
				{
					SelectableCard aImg = new SelectableCard(current, aCard);
					aLayout.addView(aImg);
				}
				
				findViewById(R.id.button1).setEnabled(false);
	        }
		};
		new Thread(game).start();
	}

    public void showQueryDetails(Player iPlayer, Request iRequest) {
		TextView aPlayerText = (TextView)this.findViewById(R.id.PlayerName);

		Message msg = new Message();
		switch(iPlayer.getColor())
		{
		case Red:
			msg.obj = (String)"Red";
			msg.arg1 = android.graphics.Color.RED;
			break;
		case Blue:
			msg.obj = (String)("Blue");
			msg.arg1 = (android.graphics.Color.BLUE);
			break;
		case Yellow:
			msg.obj = (String)("Yellow");
			msg.arg1 = (android.graphics.Color.YELLOW);
			break;
		case Green:
			msg.obj = (String)("Green");
			msg.arg1 = (android.graphics.Color.GREEN);
			break;
		case Pink:
			msg.obj = (String)("Pink");
			msg.arg1 = (android.graphics.Color.argb(0, 255, 100, 100));
			break;
		case LightBlue:
			msg.obj = (String)("LightBlue");
			msg.arg1 = (android.graphics.Color.argb(0, 100, 100, 255));
			break;
		}
		showQueryDetailsHandler.sendMessage(msg);
	}

	public void displayCardToChoose(List<Card> iCards, int iN, ChooseAction iAction) {
		numberOfCardsToChoose = iN;
		numberOfCardsChosen = 0;
		
		Message msg = new Message();
		msg.obj = iCards;
		displayCardToChooseHandler.sendMessage(msg);
		
	}

	synchronized public List<Card> getChoosenCards() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Card> aResult = new ArrayList<Card>(numberOfCardsChosen);
		for(SelectableCard aCard: cardImages)
		{
			if(aCard.isSelected())
				aResult.add(aCard.getCard());
		}
		return aResult;
	}
	
	public void responseTimeOut()
	{
		notifyAll();
	}
	
	public void cardSelected(boolean selected) {
		if(selected)
			numberOfCardsChosen++;
		else
			numberOfCardsChosen--;
		this.findViewById(R.id.button1).setEnabled(numberOfCardsChosen == numberOfCardsToChoose);
	}

}
