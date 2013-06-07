package feaisil.raceforthegalaxy.android;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import feaisil.raceforthegalaxy.android.R;
import feaisil.raceforthegalaxy.Expansion;
import feaisil.raceforthegalaxy.android.guielements.CardAdapter;
import feaisil.raceforthegalaxy.android.guielements.SelectableCard;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.exception.ActiveGameException;
import feaisil.raceforthegalaxy.exception.TooManyPlayersException;
import feaisil.raceforthegalaxy.game.Game;
import feaisil.raceforthegalaxy.game.Player;
import feaisil.raceforthegalaxy.gui.PlayerUserInterface;
import feaisil.raceforthegalaxy.gui.QueryType;
import feaisil.raceforthegalaxy.gui.Warning;
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

public class LocalGameActivity extends Activity  implements PlayerUserInterface{
	
	private Game game;
	private Player currentPl;
	private LocalGameActivity current = this;
	
	Handler showQueryDetailsHandler, displayCardToChooseHandler;
	private int numberOfCardsToChooseMin, numberOfCardsToChooseMax;
	private List<SelectableCard> cardImages;
	private List<Card> selectedCards;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        
    	if(game != null)
    		return;

        cardImages = new ArrayList<SelectableCard>(12);
        selectedCards = new ArrayList<Card>(12);
        currentPl = null;
		ArrayList<Expansion> expList = new ArrayList<Expansion>();
                expList.add(Expansion.BaseGame);
                expList.add(Expansion.RebelVsImperium);
                expList.add(Expansion.TheBrinkOfWard);
                expList.add(Expansion.TheGatheringStorm);
		Game.Configuration aConfig = new Game.Configuration(
				expList,
				false,
				false,
				false, 
				getResources().openRawResource(R.raw.rftg_card_reference));
		final Game aGame = new Game(aConfig);
		
		try {
			@SuppressWarnings("unused")
			Player aPl1 = new Player(aGame, "God", this);
			@SuppressWarnings("unused")
			Player aPl2 = new Player(aGame, "Blatte", this);
                        @SuppressWarnings("unused")
                        Player aPl3 = new Player(aGame, "Mathieu", this);
                        @SuppressWarnings("unused")
                        Player aPl4 = new Player(aGame, "DD", this);
                        @SuppressWarnings("unused")
                        Player aPl5 = new Player(aGame, "Fanny", this);
                        @SuppressWarnings("unused")
                        Player aPl6 = new Player(aGame, "MLo", this);
		} catch (TooManyPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ActiveGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			game = aGame;

			this.findViewById(R.id.button1).setEnabled(false);
			new Thread( new Runnable() {
				public void run() 
				{
					try {
						aGame.playGame();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		
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

	public void cardSelected(SelectableCard cardView) {
		if(cardView.isSelected())
			selectedCards.add(cardView.getCard());
		else
			selectedCards.remove(cardView.getCard());
		this.findViewById(R.id.button1).setEnabled(
				selectedCards.size() >= numberOfCardsToChooseMin &&
				selectedCards.size() <= numberOfCardsToChooseMax);
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
	
				findViewById(R.id.button1).setEnabled(
						selectedCards.size() >= numberOfCardsToChooseMin &&
						selectedCards.size() <= numberOfCardsToChooseMax);
            }
		});
	}

	public void displayHand()
	{
		runOnUiThread(new Runnable() {
            public void run() {
				LinearLayout aLayout = (LinearLayout)findViewById(R.id.handlayout);
	
				aLayout.removeAllViewsInLayout();
	
				cardImages = new ArrayList<SelectableCard>( currentPl.getHand().size());
				for(Card aCard : currentPl.getHand())
				{
					SelectableCard aImg = new SelectableCard(current, aCard);
	
					aImg.setEnabled(false);
					cardImages.add(aImg);
					aLayout.addView(aImg);
				}
            }
		});
	}

	public void displayBoard()
	{
		runOnUiThread(new Runnable() {
            public void run() {
				LinearLayout aLayout = (LinearLayout)findViewById(R.id.boardlayout);
	
				aLayout.removeAllViewsInLayout();
	
				cardImages = new ArrayList<SelectableCard>( currentPl.getBoard().size());
				for(Card aCard : currentPl.getBoard())
				{
					SelectableCard aImg = new SelectableCard(current, aCard);
	
					aImg.setEnabled(false);
					cardImages.add(aImg);
					aLayout.addView(aImg);
				}
            }
		});
	}

	private int getPlayerColorConverted(final Player iPlayer)
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
			return (android.graphics.Color.argb(255, 255, 100, 100));
		case LightBlue:
                default:
			return (android.graphics.Color.argb(255, 100, 100, 255));
		}
	}
	
	public void switchToPlayer(final Player player) {
		if(player == currentPl)
			return;
		currentPl = player;
		runOnUiThread(new Runnable() {
            public void run() {
				Toast.makeText(LocalGameActivity.this, "Changed to player "+currentPl.getClientId(), Toast.LENGTH_SHORT).show();
				              
				TextView aPlayerText = (TextView) findViewById(R.id.PlayerName);
				aPlayerText.setText(currentPl.getClientId());
				aPlayerText.setTextColor(getPlayerColorConverted(currentPl));
            }
		});
	}
	
	synchronized public void onCardChoosed(View aView)
	{
		LinearLayout aLayout = (LinearLayout)findViewById(R.id.choosecardlistlayout);

		aLayout.removeAllViewsInLayout();

		notifyAll();
	}

	synchronized public List<Card> query(Player p, QueryType query, List<Card> cards,
			int minCards, int maxCards)
	{
		System.out.println(""+p+cards);
		switchToPlayer(p);

		final String aStr;
		switch(query)
		{
		case startingPhaseChooseWorld:
			aStr = "Choose your starting world";
			break;
		case startingPhaseDiscardHand:
			aStr = "Discard two cards from your starting hand";
			break;
		case chooseAction:
			aStr = "Choose an action to play";
			break;
		case exploreDiscard:
			aStr = "Explore phase, discard cards";
			break;
		case developChoose:
			aStr = "You may choose a card to develop";
			break;
		case developDiscard:
			aStr = "Choose paiement for your development";
			break;
		case settleChoose:
			aStr = "You may choose a card to settle";
			break;
		case settleDiscard:
			aStr = "Explore phase, discard cards";
			break;
		case finalizeDiscardHand:
			aStr = "Choose paiement for your settlement";
			break;			
		default:
			aStr = "";
		}

		runOnUiThread(new Runnable() {
            public void run() {				              
				TextView aPlayerText = (TextView) findViewById(R.id.CurrentAction);
				aPlayerText.setText(aStr);
            }
		});
		
		selectedCards.clear();
		
		displayCardChooser(cards);
		displayBoard();
		displayHand();

		numberOfCardsToChooseMin = minCards;
		numberOfCardsToChooseMax = maxCards;
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedCards;
	}

	public void sendWarning(Player p, Warning warning) {
    	final String aStr;
		switch(warning)
		{
		case PrestigeActionAlreadyUsed:
			aStr="Prestige action already used!";
			break;
		case TwoActionSelectedButNotPrestige:
			aStr="Two actions selected but none prestige!";
			break;
		default:
			aStr="Unknown warning";
		}
		runOnUiThread(new Runnable() {
            public void run()
            {
				Toast.makeText(LocalGameActivity.this, aStr, Toast.LENGTH_SHORT).show();
            }
		});
		
	}
}
