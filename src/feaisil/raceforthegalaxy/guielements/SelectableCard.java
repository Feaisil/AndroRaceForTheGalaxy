package feaisil.raceforthegalaxy.guielements;

import java.util.Hashtable;
import java.util.Map;

import feaisil.androraceforthegalaxy.LocalGameActivity;
import feaisil.androraceforthegalaxy.R;
import feaisil.raceforthegalaxy.card.Card;
import android.content.Context;
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
		
		initIdDefinitions();
		id = idDefinitions.get(iCard.getName());
		setImageResource(id);
		setOnClickListener(new OnClickListener()
			{
				public void onClick(View v) {
					v.setSelected(!v.isSelected());
					((LocalGameActivity) v.getContext()).cardSelected(v.isSelected());
				}
			});
	}
}
