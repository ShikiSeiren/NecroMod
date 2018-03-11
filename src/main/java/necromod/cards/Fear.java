package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Fear extends AbstractNecromancerCards{
	public static final String ID = "Scare";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADE_AMT = 1;
	private static final int POOL = 1;
	
	public Fear() {
		super(ID, NAME, NecroMod.makePath(NecroMod.FEAR), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 2;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));

	}
	
	public AbstractCard makeCopy() {
		return new Fear();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPGRADE_AMT);;
		}
	}

}
