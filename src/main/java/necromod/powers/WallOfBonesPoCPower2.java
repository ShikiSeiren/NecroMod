package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import necromod.NecroMod;

import com.megacrit.cardcrawl.actions.common.*;

public class WallOfBonesPoCPower2 extends AbstractPower{
	public static final String POWER_ID = "WallOfBonesPoCPower2";
	public static final String NAME = "Wall of Bones 2";
	public static final String[] DESCRIPTIONS = new String[] {
			"Proof of Concept for a 1-Turn indesctructable damage absorbing wall."
	};
	
	public int DAMAGE_AMT;
	public int DAMAGE_TO_TAKE = 0;
	
	public WallOfBonesPoCPower2(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getBoneArmoryPowerTexture();

	}
	
	@Override
	 public int onAttacked(final DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
        	this.flash();
            if (this.amount <= damageAmount) {
            	DAMAGE_TO_TAKE = damageAmount - this.amount;
            	damageAmount = 0;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "WallOfBonesPoCPower2"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, this.DAMAGE_TO_TAKE, DamageInfo.DamageType.THORNS), 0));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "WallOfBonesPoCPower2", damageAmount));
                damageAmount = 0;
            }
        }
        return damageAmount;
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
