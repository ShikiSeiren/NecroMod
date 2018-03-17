package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.NegativeLevelsPower;
import necromod.actions.common.NegativeLevelAction;

public class Negative_Energy_Burst extends AbstractNecromancerCards{
	
	public static final String ID = "Negative_Energy_Burst";
	public static final String NAME = "Negative Energy Burst";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 15;
	public static final String DESCRIPTION = "Deal !D! damage to ALL enemies. Apply 2 NegativeLevels. NL Exhaust.";
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int POOL = 1;
	public final int AMOUNT = 2;
	
	public Negative_Energy_Burst() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_BURST), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = this.AMOUNT;
		this.isMultiDamage = true;
		this.exhaust = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    		/**
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.AMOUNT), -this.AMOUNT));
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, (this.AMOUNT*2), false),
    				(this.AMOUNT*2), true, AbstractGameAction.AttackEffect.NONE));
    		**/
		
            
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            	AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(mo, p, this.baseMagicNumber));
            	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new NegativeLevelsPower(mo, p, this.baseMagicNumber), this.baseMagicNumber));
            }
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Negative_Energy_Burst();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
