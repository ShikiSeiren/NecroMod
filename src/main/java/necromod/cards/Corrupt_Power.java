package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.actions.common.DoubleDebuffAction;
import necromod.patches.AbstractCardEnum;

public class Corrupt_Power extends AbstractNecromancerCards {
	public static final String ID = "Corrupt_Power";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Corrupt_Power() {
		super(ID, NAME, NecroMod.makePath(NecroMod.VIOLENT_NECROSIS), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new DoubleDebuffAction(m, p));

	}
	
	public AbstractCard makeCopy() {
		return new Violent_Necrosis();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}

}
