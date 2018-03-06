package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.*;


import necromod.NecroMod;
import necromod.actions.common.GainBloodAction;


public class BloodForBloodPower extends AbstractPower {
	
	public static final String POWER_ID = "BloodForBloodPower";
	public static final String NAME = "Blood for Blood";
	
	public static final String[] DESCRIPTIONS = new String[] {
			"Gain !M! Blood at the start of your turn."
	};
	
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
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new GainBloodAction(this.owner, this.owner, this.amount));
        }
        return damageAmount;
    }

}
