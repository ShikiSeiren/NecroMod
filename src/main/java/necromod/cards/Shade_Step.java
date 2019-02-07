package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.unique.ShadeStepAction;

public class Shade_Step extends AbstractNecromancerCards{
	
		public static final String ID = "Shade_Step";
		private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
		public static final	String NAME = cardStrings.NAME;
		public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
		private static final int COST = 1;
		private static final int ENERGY_AMT = 1;
		private static final int POOL = 1;
		private int energyGain;
		
		public Shade_Step() {
			super(ID, NAME, NecroMod.makePath(NecroMod.SHADE_STEP), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
					AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
			this.energyGain = ENERGY_AMT;
			this.baseMagicNumber = this.magicNumber = 2;
		}
		
		public void use(AbstractPlayer p, AbstractMonster m) {
			if (AbstractDungeon.player.drawPile.isEmpty()) {
	            AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
	        }
			for(int i = 0; i < this.magicNumber; i++) {
				AbstractDungeon.actionManager.addToBottom(new ShadeStepAction(this.energyGain));
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
			}
			
			
		}
		
		public AbstractCard makeCopy() {
			return new Shade_Step();
		}
		
		public void upgrade() {
			if (!this.upgraded) {
				this.upgradeName();
				this.upgradeMagicNumber(1);
			}
		}

}
