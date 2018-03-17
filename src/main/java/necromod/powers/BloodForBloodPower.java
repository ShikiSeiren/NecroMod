package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.*;

import necromod.NecroMod;

public class BloodForBloodPower extends AbstractPower {
	
	public static final String POWER_ID = "BloodForBloodPower";
	public static final String NAME = "Blood for Blood";
	
	public static final String[] DESCRIPTIONS = new String[] {
			"Gain 1 Blood when you lose HP."
	};
	
	public int bloodCounter = 0;
	
    public BloodForBloodPower(final AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = NecroMod.getBloodForBloodPowerTexture();
    }
    
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0] + this.amount;
    }
    	
    
    
    @Override
    public int onAttacked(final DamageInfo info, final int damageAmount) {
        if (info.owner != null  && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            
            if(this.owner.hasPower("Blood")) {
            	if(this.owner.getPower("Blood").amount < 3) {
            		this.flash();
            		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new BloodPower(this.owner, 1), 1));
            	}
            }
            else {
            	this.flash();
            	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new BloodPower(this.owner, 1), 1));
            }
            
        }
        return damageAmount;
    }


}
