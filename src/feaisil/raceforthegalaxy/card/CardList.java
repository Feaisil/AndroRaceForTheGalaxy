package feaisil.raceforthegalaxy.card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.res.Resources;

import feaisil.androraceforthegalaxy.R;
import feaisil.raceforthegalaxy.Expansion;
import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.power.BonusMilitary;
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public abstract class CardList {
	private List<Card> Deck;
	private List<Card> StartingWorlds;
	private List<Card> StartingBlueWorlds;
	private List<Card> StartingRedWorlds;
	
	public CardList()
	{
		Deck = new ArrayList<Card>(300);
		StartingWorlds = new ArrayList<Card>(300);
		StartingBlueWorlds = new ArrayList<Card>(10);
		StartingRedWorlds = new ArrayList<Card>(10);
	}
	public abstract void initCardList();
	
	public final void addCard(Card iCard)
	{
		Deck.add(iCard);
	}
	public final  void addStartingBlueWorld(Card iCard)
	{
		addCard(iCard);
		StartingWorlds.add(iCard);
		StartingBlueWorlds.add(iCard);
	}
	public final void addStartingRedWorld(Card iCard)
	{
		addCard(iCard);
		StartingWorlds.add(iCard);
		StartingRedWorlds.add(iCard);
	}
	public final List<Card> getDeck() {
		return Deck;
	}

	public final List<Card> getStartingWorlds() {
		return StartingWorlds;
	}

	public final List<Card> getStartingBlueWorlds() {
		return StartingBlueWorlds;
	}

	public final List<Card> getStartingRedWorlds() {
		return StartingRedWorlds;
	}

	@Override
	public String toString() {
		final int _maxLen = 10;
		StringBuilder _builder = new StringBuilder();
		_builder.append("CardList [");
		if (Deck != null) {
			_builder.append("Deck=");
			_builder.append(Deck.subList(0, Math.min(Deck.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingWorlds != null) {
			_builder.append("StartingWorlds=");
			_builder.append(StartingWorlds.subList(0,
					Math.min(StartingWorlds.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingBlueWorlds != null) {
			_builder.append("StartingBlueWorlds=");
			_builder.append(StartingBlueWorlds.subList(0,
					Math.min(StartingBlueWorlds.size(), _maxLen)));
			_builder.append(", ");
		}
		if (StartingRedWorlds != null) {
			_builder.append("StartingRedWorlds=");
			_builder.append(StartingRedWorlds.subList(0,
					Math.min(StartingRedWorlds.size(), _maxLen)));
		}
		_builder.append("]");
		return _builder.toString();
	}
	

	protected void initFromCsv(Resources res, Expansion iExp) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(res.openRawResource(R.raw.rftg_card_reference));
	    try {
	      while ( scanner.hasNextLine() ){
	    	  try{
	    		  processLineFromCsv( scanner.nextLine() , iExp);
	    	  }
	    	  catch(Exception e)
	    	  {
	    	  }
	      }
	    }
	    finally {
	    	scanner.close();
	    }
	}
	private GoodType getGoodType(String str)
	{
		if(str.contains("Novelty"))
			return GoodType.Novelty;
		if(str.contains("Alien"))
			return GoodType.Alien;
		if(str.contains("Rare"))
			return GoodType.Rare;
		if(str.contains("Genes"))
			return GoodType.Gene;
		if(str.contains("(any)"))
			return GoodType.Any;
		return GoodType.None;
	}
	private void processLineFromCsv(String nextLine, Expansion iExp) {
		System.out.println(nextLine);
		String[] values = nextLine.split(";");
		for(int i=0; i<values.length; i++)
			System.out.println(""+i+" : "+values[i]);
		if(values.length != 31)
		{
			System.out.println(nextLine);
			return;
		}
		if(values[0].equals("Name "))
			return;
		String name = values[0];
		String graphicId = values[1];
		Expansion exp = Expansion.BaseGame;
		if(values[2].equals("Base"))
			; // Default
		if(values[2].equals("BoW"))
			exp = Expansion.TheBrinkOfWard;
		if(values[2].equals("GS"))
			exp = Expansion.TheGatheringStorm;
		if(values[2].equals("RvI"))
			exp = Expansion.RebelVsImperium;
		if(!exp.equals(iExp))
			return;
		
		int quantity = Integer.parseInt(values[3]);
		boolean isWorld = false;
		boolean isMilitary = false;
		if(values[4].equals("Development"))
			; // Default
		if(values[4].equals("Military World"))
		{
			isWorld = true;
			isMilitary = true;
		}
		if(values[4].equals("Non-Military World"))
			isWorld = true;
		
		List<Keyword> keywords = new ArrayList<Keyword>();
		for(String aKeyword: values[5].split(" "))
		{
			if(aKeyword.equals("Alien"))
			{
				keywords.add(Keyword.Alien);
				continue;
			}
			if(aKeyword.equals("Uplift"))
			{
				keywords.add(Keyword.Uplift);
				continue;
			}
			if(aKeyword.equals("?"))
			{
				keywords.add(Keyword.Gene);
				continue;
			}
			if(aKeyword.equals("Terraforming"))
			{
				keywords.add(Keyword.Terraforming);
				continue;
			}
			if(aKeyword.equals("Rebel"))
			{
				keywords.add(Keyword.Rebel);
				continue;
			}
		}
		
		boolean isStartRed = (!values[6].equals(""));
		boolean isStartBlue = (!values[7].equals(""));
		
		boolean isProduction = (values[8] == "Production");
		
		GoodType goodType = getGoodType(values[9]);

		int cost = Integer.parseInt(values[10]);
		int vps = Integer.parseInt(values[11]);
		boolean prestige = (values[12].equals("©"));
		
		List<Power> powers = new ArrayList<Power>();
		for(String aStr: values[13].split(";"))
		{
			// TODO explore powers
		}
		for(String aStr: values[14].split(";"))
		{
			// TODO develop powers
		}
		for(String aStr: values[15].split(";"))
		{
			GoodType targetType = getGoodType(aStr);
			if(aStr.contains("Military"))
			{
				if(targetType.equals(GoodType.None))
					targetType = GoodType.Any;
				int index = aStr.indexOf("+");
				int bonusValue = 0;
				if(index != 0)
					bonusValue = Integer.parseInt(aStr.substring(index));
				index = aStr.indexOf("-");
				if(index != 0)
					bonusValue = -Integer.parseInt(aStr.substring(index));
				powers.add(new BonusMilitary(targetType, bonusValue));
			}
			// TODO settle powers
		}
		for(String aStr: values[16].split(";"))
		{
			// TODO consume trade powers
		}
		for(String aStr: values[17].split(";"))
		{
			// TODO consume powers
		}
		for(String aStr: values[18].split(";"))
		{
			// TODO produce powers
		}
		
		List<EndGameBonus> endGameBonuses = new ArrayList<EndGameBonus>();
		for(String aStr: values[19].split(";"))
		{
			// TODO game end bonus
		}
		
		for(int i=0; i<quantity; i++)
		{
			Card aCard;
			if(isWorld)
			{
				 aCard = new World(
						 name,
						 "rftg_card_"+graphicId,
						 cost,
						 vps,
						 prestige,
						 goodType,
						 isMilitary,
						 isProduction,
						 keywords,
						 powers,
						 endGameBonuses);
				 if(isStartBlue)
					 addStartingBlueWorld(aCard);
				 else if (isStartRed)
					 addStartingRedWorld(aCard);
				 else
					 addCard(aCard);
			}
			else
			{
				aCard = new Development(
						name,
						"rftg_card_"+graphicId,
						cost,
						vps,
						prestige,
						keywords,
						powers,
						endGameBonuses);
				 addCard(aCard);
			}
		}
	}
}