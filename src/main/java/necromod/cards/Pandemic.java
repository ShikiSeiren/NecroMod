package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Pandemic extends AbstractNecromancerCards {
	public static final String ID = "Pandemic";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public static final String UPGRADE_DESCRIPTION = "Apply 6 poison and 3 Vulnerable to the target. Apply 3 poison and 2 Vulnerable to ALL other enemies. Exhaust.";
	
	public Pandemic() {
		super(ID, NAME, NecroMod.makePath(NecroMod.PANDEMIC), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 4;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, (this.magicNumber/2), false), this.magicNumber/2));
        
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	if(!(mo.equals(m))) {
        		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, (this.magicNumber/2)), (this.magicNumber/2), AbstractGameAction.AttackEffect.POISON));
        		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, (this.magicNumber/2-1), false), (this.magicNumber/2-1)));
        	}
        }
		
	}
	
	public AbstractCard makeCopy() {
		return new Pandemic();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(2);
			this.rawDescription = Pandemic.UPGRADE_DESCRIPTION;
            this.initializeDescription();
		}
	}

}
