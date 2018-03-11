package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;
//import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.NegativeLevelsPower;
import necromod.actions.common.NegativeLevelAction;

public class Negative_Energy_Javelin extends AbstractNecromancerCards{
	
	public static final String ID = "Negative_Energy_Javelin";
	public static final String NAME = "Negative Energy Javelin";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 10;
	public static final String DESCRIPTION = "Deal !D! damage. NL Apply 1 NegativeLevel .";
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Negative_Energy_Javelin() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_JAVELIN), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    		/**
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.AMOUNT), -this.AMOUNT));
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, (this.AMOUNT*2), false),
    				(this.AMOUNT*2), true, AbstractGameAction.AttackEffect.NONE));
    		**/
		
            AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(m, p, this.AMOUNT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new NegativeLevelsPower(m, p, AMOUNT), AMOUNT));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Negative_Energy_Javelin();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }
}
