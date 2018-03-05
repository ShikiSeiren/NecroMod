package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

public class BonesPower extends AbstractPower{
	public static final String POWER_ID = "Bones";
	public static final String NAME = "Bones";
	public static final String[] DESCRIPTIONS = new String[] {
			"Empowers the next 'Bone' Attack."
	};
	
	public BonesPower(AbstractCreature owner, int amount) {
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
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
	
	
}
