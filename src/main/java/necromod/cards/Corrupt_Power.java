package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.NecroMod;
import necromod.actions.common.CorruptAction;
import necromod.patches.AbstractCardEnum;

public class Corrupt_Power extends AbstractNecromancerCards {
	public static final String ID = "Corrupt";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Corrupt_Power() {
		super(ID, NAME, NecroMod.makePath(NecroMod.CORRUPT), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(m.powers != null) {
			for(AbstractPower power : m.powers) {
				String currentPower = power.ID;
				AbstractDungeon.actionManager.addToBottom(new CorruptAction(m, p, currentPower));
			}
			
		}
		
		

	}
	
	public AbstractCard makeCopy() {
		return new Corrupt_Power();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}

}
