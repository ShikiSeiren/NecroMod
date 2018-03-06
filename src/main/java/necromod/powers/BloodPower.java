package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class BloodPower extends AbstractPower{
	public static final String POWER_ID = "Blood";
	public static final String NAME = "Blood";
	public static final String[] DESCRIPTIONS = new String[] {
			"Negates the next card HP cost."
	};
	
	public BloodPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getBloodPowerTexture();
		
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
