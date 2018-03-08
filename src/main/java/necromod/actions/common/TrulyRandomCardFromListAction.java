package necromod.actions.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.megacrit.cardcrawl.helpers.*;




public class TrulyRandomCardFromListAction {
	
	public static ArrayList<AbstractCard> SummonCardPool = new ArrayList<AbstractCard>();
	
	
	public static void CreateLists() {
		SummonCardPool.add(CardLibrary.getCard("Summon_Death_Knight"));
		SummonCardPool.add(CardLibrary.getCard("Summon_Lich"));
		SummonCardPool.add(CardLibrary.getCard("Summon_Undead_Army"));
		SummonCardPool.add(CardLibrary.getCard("Summon_Vampire_Lady"));
		}
		
	public static AbstractCard returnTrulyRandomCard(ArrayList<AbstractCard> list) {
		CreateLists();
        final ArrayList<AbstractCard> thisList = list; 
        return thisList.get(MathUtils.random(list.size() - 1));
	}

}
