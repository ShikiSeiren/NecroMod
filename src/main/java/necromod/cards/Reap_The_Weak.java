package necromod.cards;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import necromod.NecroMod;
import necromod.actions.common.BloodAction;
import necromod.actions.unique.EpidemicAction;
import necromod.patches.AbstractCardEnum;

public class Reap_The_Weak extends AbstractNecromancerCards {
	
	public static final String ID = "Reap_The_Weak";
	public static final String NAME = "Reap the Weak";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 5;
	public static final String DESCRIPTION = "Lose 3 HP. Deal !D! damage to ALL enemies.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	public int counter = 0;
	
	public Reap_The_Weak() {
		super (ID, NAME, NecroMod.makePath(NecroMod.REAP_THE_WEAK), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.isMultiDamage = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(m.powers != null) {	
			for(AbstractPower power : m.powers) {
				if(power.type == PowerType.DEBUFF) {
					counter++;
				}
			}
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, (this.damage*counter), this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        counter = 0;	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Reap_The_Weak();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
