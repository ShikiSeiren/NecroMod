package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;
import necromod.actions.common.NegativeLevelAction;
import necromod.powers.NegativeLevelsPower;

import com.megacrit.cardcrawl.actions.common.*;

public class NegativeShieldPower extends AbstractPower {
	public static final String POWER_ID = "NegativeShieldPower";
	public static final String NAME = "Negative Energy Shield";
	public static final String[] DESCRIPTIONS = new String[] {
			"When attacked : Apply 1 Negative level to the attacker."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	
	
	public NegativeShieldPower(AbstractCreature owner, int amount){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getNegativeShieldPowerTexture();

	}
	@Override
	public int onAttacked(final DamageInfo info, int damageAmount) {
	       if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
	           this.flash();
	           AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(info.owner, this.owner, new NegativeShieldPower2(info.owner, this.owner, 1), 1));
	       }
	        
		damageAmount = 0;
		return damageAmount;
	}

	@Override
	public void atStartOfTurn() {
		this.flash();		
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "NegativeShieldPower"));
	}

	
	@Override
		public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
}
