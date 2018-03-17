package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.animations.*;

import com.megacrit.cardcrawl.vfx.combat.*;

import necromod.NecroMod;
import necromod.powers.HellFlamePower;

public class SkeletonDragonPower extends AbstractPower {
	public static final String POWER_ID = "SkeletonDragonPower";
	public static final String NAME = "Skeleton Dragon";
	public static final String[] DESCRIPTIONS = new String[] {
			"A skeletal dragon assumed to be the pinnacle of Necromancy. Deals 5 damage to ALL enemies at the end of each turn. NL Applies Hellfire to those not already burning."
	};
	public static final String[] DESCRIPTIONS2 = new String[] {
			"A skeletal dragon assumed to be the pinnacle of Necromancy. Deals 7 damage to ALL enemies at the end of each turn. NL Applies Hellfire to those not already burning."
	};
	
	
	public int DAMAGE_AMT;
	public boolean upgraded;
	
	public SkeletonDragonPower(AbstractCreature owner, int amount, boolean isUpgraded, int damage) {
		
		this.name = NAME;		
		this.ID = POWER_ID;
		this.owner = owner;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getSkeletonDragonPowerTexture();
		this.DAMAGE_AMT = damage;
		this.amount = amount;
		this.upgraded = isUpgraded;

	}
	
	@Override
	public void updateDescription() {
		if(!this.upgraded) {
			this.description = DESCRIPTIONS[0];
		}
		else {
			this.description = DESCRIPTIONS2[0];
		}
		
	}
	
	@Override
    public void atEndOfTurn(boolean isPlayer) {
    	
		this.flash();
		for(int i = 0; i < this.owner.getPower("SkeletonDragonPower").amount; i++) {
			this.flash();
	        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.25f));
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.DAMAGE_AMT, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
	        
	        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	        	if(!(mo.hasPower("HellFlamePower"))){
	        		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, mo, new HellFlamePower(mo, mo, 1), 1));
	        	}
	        }
	        
		}

    }

}
