package necromod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class BoneArmoryPower extends AbstractPower {
	
	public static final String POWER_ID = "BoneArmoryPower";
	public static final String NAME = "Bone Armory";
	
	public static final String[] DESCRIPTIONS = new String[] {
			"Gain !M! Bones at the start of your turn."
	};
	
    public BoneArmoryPower(final AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = NecroMod.getBoneArmoryPowerTexture();
    }
    
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0] + this.amount;
    }
    
    @Override
    public void atStartOfTurn() {
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new BonesPower(this.owner, this.amount), this.amount));
    }

}
