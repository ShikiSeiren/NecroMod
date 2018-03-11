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
import necromod.powers.Shackles;
import necromod.powers.OfPain;

public class Shackles_Of_Pain extends AbstractNecromancerCards {
	public static final String ID = "Shackles_Of_Pain";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADE_AMT = 1;
	private static final int POOL = 1;
	
	public Shackles_Of_Pain() {
		super(ID, NAME, NecroMod.makePath(NecroMod.SHACKLES_OF_PAIN), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 1;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(m.getPower("Artifact") == null) {	
			
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Shackles(p, m, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new OfPain(m, this.magicNumber), this.magicNumber));
		
		}
		else {
			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new OfPain(m, this.magicNumber), this.magicNumber));
			
		}
	}
	
	public AbstractCard makeCopy() {
		return new Shackles_Of_Pain();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPGRADE_AMT);;
		}
	}
}
