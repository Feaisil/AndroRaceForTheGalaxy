package feaisil.raceforthegalaxy.android.guielements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import feaisil.raceforthegalaxy.android.R;
import feaisil.raceforthegalaxy.android.LocalGameActivity;
import feaisil.raceforthegalaxy.card.Card;
import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
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

	private void initIdDefinitions(Context context)
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

			Scanner scanner;
			scanner = new Scanner(context.getResources().openRawResource(R.raw.rftg_card_reference));
			try {
				while ( scanner.hasNextLine() ){
					String aLine = (scanner.nextLine());
					String []values = aLine.split(";");
					
					if(values[0].equals("Name "))
						continue;
					if(values.length > 2 )
					{
						String aId = "rftg_card_";
						String graphicId = values[1];
						for(;graphicId.length()<3;)
							graphicId = "0"+graphicId;
						aId += graphicId;
						String name = values[0];
						try{
							idDefinitions.put(name, R.drawable.class.getField(aId).getInt(null));
						}catch (NoSuchFieldException e) {

							System.out.println("Could not find field... "+e.toString());
						}
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				scanner.close();
			}
		}
	}

	public SelectableCard(Context context, Card iCard) {
		super(context);
		setAdjustViewBounds(true);
		this.setPadding(0, 0, 0, 0);
		card = iCard;

		if(idDefinitions == null)
			initIdDefinitions(context);

		if(idDefinitions.containsKey(iCard.getName()))
		{
			id = idDefinitions.get(iCard.getName());
			setImageResource(id);
		}
		setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				v.setSelected(!v.isSelected());
				if(v.isSelected())
					((SelectableCard)v).setColorFilter(Color.argb(100, 0, 200, 0));
				else
					((SelectableCard)v).setColorFilter(Color.argb(0, 0, 200, 0));
				((LocalGameActivity) v.getContext()).cardSelected((SelectableCard)(v));
			}
			
		});
		setOnLongClickListener(new OnLongClickListener()
		{
			public boolean onLongClick(View v) {
		        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		        ImageButton image = new ImageButton(v.getContext());
		        image.setImageResource(idDefinitions.get(((SelectableCard)v).getCard().getName()));
		        builder.setView(image);
		        final AlertDialog dialog = builder.create();
		        dialog.show();
		        image.setOnClickListener(new OnClickListener()
		        {
		        	public void onClick(View v2)
		        	{
		        		dialog.cancel();
		        	}
		        });
				return false;
			}
			
		});
	}
}
