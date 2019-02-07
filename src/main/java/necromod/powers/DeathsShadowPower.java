package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;


public class DeathsShadowPower extends AbstractPower {
	
	public static final String POWER_ID = "DeathsShadowPower";
	public static final String NAME = "Death's Shadow";
	public static final String[] DESCRIPTIONS = new String[] {
			"Heal for 1 hp per stack once the timer expires."
	};
	
	public DeathsShadowPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getBonesPowerTexture();
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
