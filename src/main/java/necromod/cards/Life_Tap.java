package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.actions.common.BloodAction;
import necromod.patches.AbstractCardEnum;

public class Life_Tap extends AbstractNecromancerCards {
	public static final String ID = "Life_Tap";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ENERGY_AMT = 2;
	private static final int POOL = 1;
	private final int energyGain;
	
	
	public Life_Tap() {
		super(ID, NAME, NecroMod.makePath(NecroMod.LIFE_TAP), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		this.energyGain = ENERGY_AMT;
		this.baseMagicNumber = this.magicNumber = 2;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, (this.magicNumber)));
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.energyGain));
		
	}
	
	public AbstractCard makeCopy() {
		return new Life_Tap();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.baseMagicNumber = this.magicNumber -= 1;
		}
	}

}
