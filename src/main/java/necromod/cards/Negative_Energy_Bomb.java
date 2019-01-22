package necromod.cards;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.unique.NegativeEnergyBombAction;

public class Negative_Energy_Bomb extends AbstractNecromancerCards{
	
	private static final String ID = "Negative_Energy_Bomb";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = new String[] {
			"This Enemy does not have any Negative Levels."
	};
	public static final String UPGRADE_DESCRIPTION = "Remove all NegativeLevels. NL Deal 8 damage for each stack of NegativeLevel removed to ALL enemies.";
	
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Negative_Energy_Bomb() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_BOMB), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
	
		this.isMultiDamage = true;
		
		
	}
	
	@Override
	public void use (AbstractPlayer p, AbstractMonster m) {
		if (m.getPower("Negative_Level") == null) {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, Negative_Energy_Bomb.EXTENDED_DESCRIPTION[0], true));
            return;
        }
		else {
		this.baseDamage = m.getPower("Negative_Level").amount;
		
		if(!this.upgraded) {
			this.baseDamage *= 6;
		}
		else {
			this.baseDamage *= 8;
		}
		
		this.calculateCardDamage(m);
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new StrengthPower(m, this.baseMagicNumber), this.baseMagicNumber));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, false));
		AbstractDungeon.actionManager.addToBottom(new NegativeEnergyBombAction(m, p));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Negative_Energy_Bomb();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}

}
