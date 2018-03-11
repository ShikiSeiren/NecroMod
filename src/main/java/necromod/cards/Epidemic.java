package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.actions.unique.EpidemicAction;
import necromod.patches.AbstractCardEnum;

public class Epidemic extends AbstractNecromancerCards {
	public static final String ID = "Epidemic";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Epidemic() {
		super(ID, NAME, NecroMod.makePath(NecroMod.EPIDEMIC), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(m.powers != null) {
			AbstractDungeon.actionManager.addToBottom(new EpidemicAction(m, p));
		}			
	}
	
	public AbstractCard makeCopy() {
		return new Epidemic();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}


}
