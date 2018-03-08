package necromod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;


import necromod.NecroMod;

public class DeathKnightPower extends AbstractPower {
	public static final String POWER_ID = "DeathKnightPower";
	public static final String NAME = "Death Knight";
	public static final String[] DESCRIPTIONS = new String[] {
			"A knight risen from the dead. Deals 5 damge at the end of the turn. Buffs the Necromancer with 5 Block at the end of the turn."
	};
	
	public int DAMAGE_AMT;
	
	public DeathKnightPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getDeathKnightPowerTexture();

	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
    public void atEndOfTurn(boolean isPlayer) {
    	
		this.flash();
		for(int i = 0; i < this.owner.getPower("DeathKnightPower").amount; i++) {
			
	        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(this.owner, this.DAMAGE_AMT, DamageInfo.DamageType.THORNS), 0));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 5));			
		}

    }	

}
