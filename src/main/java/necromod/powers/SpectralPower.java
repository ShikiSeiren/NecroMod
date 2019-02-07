package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

import com.megacrit.cardcrawl.actions.common.*;

public class SpectralPower extends AbstractPower {
	
	public static final String POWER_ID = "SpectralPower";
	public static final String NAME = "Spectral";
	public static final String[] DESCRIPTIONS = new String[] {
			"Take halved unblocked damage."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	
	public SpectralPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getSpectralPowerTexture();
		this.priority = 99;

	}
	/**
	@Override
	public int onAttacked(final DamageInfo info, int damageAmount) {
		damageAmount = (int) (damageAmount*0.5);
		return damageAmount;
	}
	**/
	
	@Override
	public float atDamageReceive(float damage, final DamageInfo.DamageType type) {
		if(damage > 1.0f) {
			damage = damage*0.5f;
		}
		
		return damage;
	}
	
	
	@Override
	public void atEndOfRound() {
		this.flash();		
		if(this.amount <1) {
        	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "SpectralPower"));
        }
        else {
        	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "SpectralPower", 1));
        } 
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	

}
