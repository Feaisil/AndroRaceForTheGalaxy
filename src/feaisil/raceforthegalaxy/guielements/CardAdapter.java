package feaisil.raceforthegalaxy.guielements;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import feaisil.androraceforthegalaxy.R;
import feaisil.raceforthegalaxy.LocalPlayer;
import feaisil.raceforthegalaxy.card.Card;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CardAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	List<Card> cards;
	
	public CardAdapter(List<Card> iCards, LayoutInflater aLi) {
		cards = iCards;
		mInflater = aLi;
	}

	public int getCount() {
		return cards.size();
	}

	public Object getItem(int id) {
		return cards.get(id);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView image = null;
		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.card_image, null);
		}
		image = (ImageView) convertView;
		
		if(idDefinitions == null)
			initIdDefinitions();
		
		image.setImageResource(idDefinitions.get(((Card)getItem(position)).getName()));
		return image;
	}

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
}
