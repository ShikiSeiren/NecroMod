package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;
import necromod.actions.common.NegativeLevelAction;
import necromod.powers.NegativeLevelsPower;

import com.megacrit.cardcrawl.actions.common.*;

public class NegativeShieldPower2 extends AbstractPower {
	public static final String POWER_ID = "NegativeShieldPower2";
	public static final String NAME = "Negative Energy Shield";
	public static final String[] DESCRIPTIONS = new String[] {
			"When attacked : Apply 1 Negative level to the attacker."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	public AbstractCreature target;
	
	public NegativeShieldPower2(AbstractCreature target, AbstractCreature owner, int amount){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getNegativeShieldPowerTexture();
		this.target = target;

	}

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		this.flash();
        AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(this.target, this.owner, this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new NegativeLevelsPower(this.target, this.owner, this.amount), this.amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.target, "NegativeShieldPower2"));
	}

	
	@Override
		public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
}
