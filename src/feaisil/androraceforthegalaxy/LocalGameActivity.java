package feaisil.androraceforthegalaxy;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import feaisil.raceforthegalaxy.Expansion;
import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.LocalPlayer;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.PlayerInterface;
import feaisil.raceforthegalaxy.card.CardList;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.PlayerColor;
import feaisil.raceforthegalaxy.exception.ActiveGameException;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;
import feaisil.raceforthegalaxy.gui.ChooseAction;
import feaisil.raceforthegalaxy.gui.UserInterface;
import feaisil.raceforthegalaxy.guielements.CardAdapter;
import feaisil.raceforthegalaxy.guielements.SelectableCard;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class LocalGameActivity extends Activity  implements UserInterface{
	
	private Game game;
	
	private LocalGameActivity current = this;
	
	Handler showQueryDetailsHandler, displayCardToChooseHandler;
	private int numberOfCardsToChoose;
	private int numberOfCardsChosen;
	private List<SelectableCard> cardImages;

    GridView vue;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        
        try {
        	if(Game.getCurrentInstance() == null)
        	{
                cardImages = new ArrayList<SelectableCard>(12);
        		initGame();
        	}
		} catch (TwoManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ActiveGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


    private void initGame() throws TwoManyPlayersException, ActiveGameException {
    	game = new Game();
    	
		LocalPlayer aPl2 = new LocalPlayer(this, game);
		LocalPlayer aPl3 = new LocalPlayer(this, game);
		LocalPlayer aPl4 = new LocalPlayer(this, game);

		CardList aCl = new CardList();
		
		try {
			aCl.initFromCsv(getResources().openRawResource(R.raw.rftg_card_reference), Expansion.BaseGame);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(aCl.toString());
		
		for(Card aCard: aCl.getStartingBlueWorlds())
			aPl2.addToHand(aCard);
		for(Card aCard: aCl.getStartingRedWorlds())
			aPl3.addToHand(aCard);
		for(Card aCard: aCl.getStartingWorlds())
			aPl4.addToHand(aCard);
				
		game.init();
		
		vue = (GridView) findViewById(R.id.handlist);
		ListAdapter adapter = new CardAdapter(aPl2.getHand(), getLayoutInflater());
		vue.setAdapter(adapter);
		
		new Thread(game).start();
    }

	public void cardSelected(boolean selected) {
		if(selected)
			numberOfCardsChosen++;
		else
			numberOfCardsChosen--;
		this.findViewById(R.id.button1).setEnabled(numberOfCardsChosen == numberOfCardsToChoose);
	}

	synchronized public Action selectAction(boolean prestigeActionUsed) {
		numberOfCardsToChoose = 1;
		numberOfCardsChosen = 0;
		// TODO Create true action card list
		List<Card> cards = new ArrayList<Card>();
		
		cards.add(new Card("Draw explore", "Draw explore", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Keep explore", "Keep explore", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Develop", "Develop", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Settle", "Settle", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Consume trade", "Consume trade", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Consume 2x VPs", "Consume 2x VPs", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Produce", "Produce", 0, 0, prestigeActionUsed, null, null, null));
		cards.add(new Card("Prestige/Search", "Prestige/Search", 0, 0, prestigeActionUsed, null, null, null));

		final List<Card> finalCards = cards;
		
    	displayCardChooser(finalCards);
		
		try {
			// Todo register begin time and verify that action was completed
			wait(game.getMaxDecisionTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.exploreKeep;
	}
	
	public void displayCardChooser(final List<Card> iCards)
	{
		runOnUiThread(new Runnable() {
            public void run() {
				LinearLayout aLayout = (LinearLayout)findViewById(R.id.choosecardlistlayout);
	
				aLayout.removeAllViewsInLayout();
	
				cardImages = new ArrayList<SelectableCard>( iCards.size());
				for(Card aCard : iCards)
				{
					SelectableCard aImg = new SelectableCard(current, aCard);
	
					cardImages.add(aImg);
					aLayout.addView(aImg);
				}
	
				findViewById(R.id.button1).setEnabled(false);
            }
		});
	}
	
	public void responseTimeOut() {
		runOnUiThread(new Runnable() {
            public void run() {
              Toast.makeText(LocalGameActivity.this, "You reached the response timeout, default action will be used!", Toast.LENGTH_SHORT).show();
            }
		});
	}

	int getPlayerColorConverted(final Player iPlayer)
	{
		switch(iPlayer.getColor())
		{
		case Red:
			return android.graphics.Color.RED;
		case Blue:
			return (android.graphics.Color.BLUE);
		case Yellow:
			return (android.graphics.Color.YELLOW);
		case Green:
			return (android.graphics.Color.GREEN);
		case Pink:
			return (android.graphics.Color.argb(0, 255, 100, 100));
		case LightBlue:
			return (android.graphics.Color.argb(0, 100, 100, 255));
		default:
			return 0;
		}
	}
	
	synchronized public void switchToPlayer(final Player player) {
		runOnUiThread(new Runnable() {
            public void run() {
				Toast.makeText(LocalGameActivity.this, "Changed to player "+player.getColor(), Toast.LENGTH_SHORT).show();
				              
				TextView aPlayerText = (TextView) findViewById(R.id.PlayerName);
				aPlayerText.setText(player.getColor().toString());
				aPlayerText.setTextColor(getPlayerColorConverted(player));
            }
		});
	}
	
	synchronized public void onCardChoosed(View aView)
	{
		LinearLayout aLayout = (LinearLayout)findViewById(R.id.choosecardlistlayout);

		aLayout.removeAllViewsInLayout();

		notifyAll();
	}

	synchronized public Card chooseCardToDiscard(List<Card> cards) {
		displayCardChooser(cards);

		numberOfCardsToChoose = 1;
		numberOfCardsChosen = 0;
		
		try {
			// Todo register begin time and verify that action was completed
			wait(game.getMaxDecisionTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
