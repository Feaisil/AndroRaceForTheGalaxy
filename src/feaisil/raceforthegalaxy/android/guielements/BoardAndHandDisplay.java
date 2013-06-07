package feaisil.raceforthegalaxy.android.guielements;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BoardAndHandDisplay extends LinearLayout {
	public class BoardView extends android.widget.GridView
	{
		public BoardView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
	    public BoardView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        init();
	    }
		
		public void init()
		{
			setNumColumns(7);
		}
	}
	
	public BoardAndHandDisplay(Context context) {
		super(context);
		init();
	}
    public BoardAndHandDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
	
	public void init()
	{
		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		
	}
}
