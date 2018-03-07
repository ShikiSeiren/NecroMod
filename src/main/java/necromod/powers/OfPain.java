package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class OfPain extends AbstractPower {
	
	public static final String POWER_ID = "OfPain";
	public static final String NAME = "Shackles of Pain";
	public static final String[] DESCRIPTIONS = new String[] {
			"Damage-Link : Take damage the target takes."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	
	public OfPain(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.DEBUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getShacklesPowerTexture();

	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
