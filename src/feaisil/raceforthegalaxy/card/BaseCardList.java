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
import feaisil.raceforthegalaxy.power.*;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class BaseCardList extends CardList {
	public void initCardList(Resources res) {
		try {
			initFromCsv(res, Expansion.BaseGame);
		} catch (FileNotFoundException e) {
			System.out.println("Exception occured while retrieving cards");
		}
	}

}
