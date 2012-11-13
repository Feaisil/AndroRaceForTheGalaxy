package feaisil.raceforthegalaxy.guielements;

import java.util.Hashtable;
import java.util.Map;

import feaisil.androraceforthegalaxy.LocalGameActivity;
import feaisil.androraceforthegalaxy.R;
import feaisil.raceforthegalaxy.card.Card;
import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

public class SelectableCard extends ImageButton {
	private Card card;
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	private Integer id;
	private static Map<String, Integer> idDefinitions;
	
	private void initIdDefinitions()
	{
		if(idDefinitions == null)
		{
			idDefinitions = new Hashtable<String, Integer>();

			// action cards
			idDefinitions.put("Draw explore", R.drawable.rftg_action_01);
			idDefinitions.put("Keep explore", R.drawable.rftg_action_02);
			idDefinitions.put("Develop", R.drawable.rftg_action_03);
			idDefinitions.put("Settle", R.drawable.rftg_action_05);
			idDefinitions.put("Consume trade", R.drawable.rftg_action_07);
			idDefinitions.put("Consume 2x VPs", R.drawable.rftg_action_08);
			idDefinitions.put("Produce", R.drawable.rftg_action_09);
			idDefinitions.put("Prestige/Search", R.drawable.rftg_action_10);
			
			// back
			idDefinitions.put("Back", R.drawable.rftg_back);			
			
			// playable cards
			idDefinitions.put("Old Earth", R.drawable.rftg_card_000);
			idDefinitions.put("Epsilon Eridani", R.drawable.rftg_card_001);
			idDefinitions.put("Alpha Centauri", R.drawable.rftg_card_002);
			idDefinitions.put("New Sparta", R.drawable.rftg_card_003);
			idDefinitions.put("Earth's Lost Colony", R.drawable.rftg_card_004);
			
			
		}
	}
	
	public SelectableCard(Context context, Card iCard) {
		super(context);

		card = iCard;
		
		if(idDefinitions == null)
			initIdDefinitions();
		
		id = idDefinitions.get(iCard.getName());
		setImageResource(id);
		setOnClickListener(new OnClickListener()
			{
				public void onClick(View v) {
					v.setSelected(!v.isSelected());
					if(v.isSelected())
						((SelectableCard)v).setColorFilter(Color.argb(100, 0, 200, 0));
					else
						((SelectableCard)v).setColorFilter(Color.argb(0, 0, 200, 0));
					((LocalGameActivity) v.getContext()).cardSelected(v.isSelected());
				}
			});
	}
}
