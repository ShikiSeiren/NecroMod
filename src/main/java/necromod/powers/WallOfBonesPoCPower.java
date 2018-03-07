package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

import com.megacrit.cardcrawl.actions.common.*;

public class WallOfBonesPoCPower extends AbstractPower{
	public static final String POWER_ID = "WallOfBonesPoCPower";
	public static final String NAME = "Wall of Bones";
	public static final String[] DESCRIPTIONS = new String[] {
			"Proof of Concept for a 1-Turn indesctructable damage absorbing wall."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	private Boolean justApplied;
	
	public WallOfBonesPoCPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getBoneArmoryPowerTexture();
		this.justApplied = true;

	}
	/**
	@Override
	 public float atDamageReceive(float damage, final DamageInfo.DamageType type) {
        if (damage > 0.0f) {
        	TOTAL_DAMAGE += (int) damage;
            damage = 0.0f;
        }
        return damage;
	}
	**/
	
	@Override
	public int onAttacked(final DamageInfo info, int damageAmount) {
		//TOTAL_DAMAGE +=damageAmount;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new WallOfBonesPoCPower2(this.owner, damageAmount), TOTAL_DAMAGE));
		damageAmount = 0;
		return damageAmount;
	}
	
	@Override
	public void atStartOfTurn() {
		this.flash();		
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "WallOfBonesPoCPower"));
	}
	
		
	/**
	@Override
	public void atEndOfTurn(boolean isPlayer){
		if (this.justApplied) {
            this.justApplied = false;
            return;
        }
		this.flash();		
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "WallOfBonesPoCPower"));
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new WallOfBonesPoCPower2(this.owner, this.TOTAL_DAMAGE), this.TOTAL_DAMAGE));
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "WallOfBonesPoCPower"));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "WallOfBonesPoCPower", 1));
        }
	}	
	**/		
		
	
	
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
