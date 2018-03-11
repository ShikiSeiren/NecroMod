package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.core.*;
//import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.cards.AbstractNecromancerCards;

public class Blood_Curse extends AbstractNecromancerCards {
	public static final String ID = "Blood_Curse";
	public static final String NAME = "Blood Curse";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	public static final String DESCRIPTION = "Deal !D! damage. NL Apply !M! Weak. Apply !M! Vulnerable.";
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Blood_Curse() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BLOOD_CURSE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = AMOUNT;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Blood_Curse();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(1);
        }
	
    }

}
