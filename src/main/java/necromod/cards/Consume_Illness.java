package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.DexterityPower;
import java.util.*;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Consume_Illness extends CustomCard{
	
		public static final String ID = "Consume_Illness";
		private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
		public static final	String NAME = cardStrings.NAME;
		public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
		private static final int COST = 1;
		private static final int POOL = 1;
		public static final String[] EXTENDED_DESCRIPTION = new String[] {
				"I do not have any negative effects."
		};
		
		public Consume_Illness() {
			super(ID, NAME, NecroMod.makePath(NecroMod.CONSUME_ILLNESS), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
					AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
			this.baseMagicNumber = this.magicNumber = 1;
			this.exhaust = true;
			
		}
		
		public void use(AbstractPlayer p, AbstractMonster m) {
				
			ArrayList<String> Debuff = new ArrayList<String>();
			Debuff.add("Poison");
			Debuff.add("Weakened");
			Debuff.add("Frail");
			Debuff.add("Vulnerable");
			
			ArrayList<String> Random = new ArrayList<String>();
				
			for(String d : Debuff) {
				if (p.hasPower(d)){
					Random.add(d);
				}
			}
			if(p.hasPower("Strength") && (p.getPower("Strength").amount < 0)){
				Random.add("Strength");
			}
				
			if(p.hasPower("Dexterity") && (p.getPower("Dexterity").amount < 0)){
					Random.add("Dexterity");
			}
				
			if(!Random.isEmpty()) {
				int i = Random.size();
				int r = (int) Math.random()*i;
					
				if(!Random.get(r).equals("Strength") && !Random.get(r).equals("Dexterity")) {
					AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, Random.get(r)));
					AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
				}
				else if(Random.get(r).equals("Strength")){
					int amount = -1*(p.getPower("Strength").amount);
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));
					AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
				}
				else {
					int amount = -1*(p.getPower("Strength").amount);
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, amount), amount));
					AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
				}
				
					
			}
			else {
				AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, Consume_Illness.EXTENDED_DESCRIPTION[0], true));
			}

		}
		
		public AbstractCard makeCopy() {
			return new Consume_Illness();
		}
		
		public void upgrade() {
			if (!this.upgraded) {
				this.upgradeName();
				this.upgradeBaseCost(0);
			}
		}

}