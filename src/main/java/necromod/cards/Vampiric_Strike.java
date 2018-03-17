package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.core.*;
//import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Vampiric_Strike extends AbstractNecromancerCards {
	public static final String ID = "Vampiric_Strike";
	public static final String NAME = "Vampiric Strike";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	public static final String DESCRIPTION = "Deal !D! damage. If target was above 50% HP : Heal for 25% damage dealt.";
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	public int HealAmount = 0;
	
	public Vampiric_Strike() {
		super (ID, NAME, NecroMod.makePath(NecroMod.VAMPIRIC_STRIKE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		if (m.currentHealth >= (m.maxHealth * 0.5)) {
			if(this.damage > m.currentBlock) {
				this.HealAmount = (int) ((this.damage -= m.currentBlock)*0.25);
				if(this.HealAmount < 1) {
					this.HealAmount = 1;
				}
			}
			
			AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			if(HealAmount > 0) {
				AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, HealAmount));
			}
			
			
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}
			        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Vampiric_Strike();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
