package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class MobFrailPower extends AbstractPower {
	public static final String POWER_ID = "MobFrailPower";
	public static final String NAME = "Frail";
	public static final String[] DESCRIPTIONS = new String[] {
			"Reduces block gained by 25%."
	};
	
	public MobFrailPower(AbstractCreature owner, int amount) {
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
	public void onGainedBlock(int blockAmount){
		owner.currentBlock = owner.currentBlock * 3/4;
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
