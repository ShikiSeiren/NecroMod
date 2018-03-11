package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class BloodMagicPower extends AbstractPower{
	public static final String POWER_ID = "BloodMagicPower";
	public static final String NAME = "Blood Magic";
	public static final String[] DESCRIPTIONS = new String[] {
			"You can play cards for 3 HP times their Energy cost if you do not have enough energy to play them."
	};
	
	public BloodMagicPower(AbstractCreature owner, int amount) {
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
