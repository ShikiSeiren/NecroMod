package necromod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import necromod.NecroMod;


public class ToxicScreenPower extends AbstractPower {
	public static final String POWER_ID = "ToxicScreenPower";
	public static final String NAME = "Toxic Smoke Screen";
	public static final String[] DESCRIPTIONS = new String[] {
			"When attacked : Apply 1 Negative level to the attacker."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	public int stacks;
	
	public ToxicScreenPower(AbstractCreature owner, int amount, int stacks){
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getToxicScreenPowerTexture();
		this.stacks = stacks;

	}
	@Override
	public int onAttacked(final DamageInfo info, int damageAmount) {
	       if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
	           this.flash();
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, this.owner, new PoisonPower(info.owner, this.owner, this.stacks), this.stacks, AbstractGameAction.AttackEffect.POISON));

	       }
	        
		damageAmount = 0;
		return damageAmount;
	}

	@Override
	public void atStartOfTurn() {
		this.flash();		
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "ToxicScreenPower"));
	}

	
	@Override
		public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
