package feaisil.androraceforthegalaxy;

import java.util.List;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.LocalPlayer;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.BaseCardList;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.card.CardList;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;
import feaisil.raceforthegalaxy.gui.UserInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v4.app.NavUtils;

public class LocalGameActivity extends Activity implements UserInterface {

	private Game game;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        
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
		game.startGame();
	}
    
	public void showQueryDetails(Player iPlayer, Request iRequest) {
		// TODO Auto-generated method stub
		
	}

	synchronized public Card chooseDiscardCard(List<Card> iCards) {
		displayCardList(iCards);
		
		// wait for answer...
		return null;
	}

	private void displayCardList(List<Card> iCards) {
		LinearLayout aLayout = (LinearLayout)this.findViewById(R.id.chosecardlistlayout);
		
		for(Card aCard : iCards)
		{
			ImageButton aImg = new ImageButton(this);
			if(aCard.getName() == "Old Earth")
			{
				aImg.setImageResource(R.drawable.rftg_card_000);
			}
			else if(aCard.getName() == "Epsilon Eridani")
			{
				aImg.setImageResource(R.drawable.rftg_card_001);
			}
			else if(aCard.getName() == "Alpha Centauri")
			{
				aImg.setImageResource(R.drawable.rftg_card_002);
			}
			else if(aCard.getName() == "New Sparta")
			{
				aImg.setImageResource(R.drawable.rftg_card_003);
			}
			else if(aCard.getName() == "Earth's Lost Colony")
			{
				aImg.setImageResource(R.drawable.rftg_card_004);
			}
			aLayout.addView(aImg);
		}
	}

}
