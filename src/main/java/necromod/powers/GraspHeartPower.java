package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.NecroMod;
import necromod.powers.GraspHeartPower;

public class GraspHeartPower extends AbstractPower {
	public static final String POWER_ID = "GraspHeartPower";
	public static final String NAME = "Crushed Heart";
	public static final String[] DESCRIPTIONS = new String[] {
			"Deal 3 damage per stack each turn. Increases by 1 stack each turn."
	};
	public AbstractCreature source;
	
	public GraspHeartPower(AbstractCreature owner, AbstractCreature source, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.DEBUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getGraspHeartPowerTexture();
		this.source = source;
				
	}
	

	//Possibly damage atEndOfTurn of monster or at startOfTurn player.
	
	public void atEndOfTurn(boolean isPlayer) {
		this.flash();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.source, new GraspHeartPower(this.owner, this.source, 1), 1));
		
	}
	
	@Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.source, (3*this.amount), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            //AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.source, 3*this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        
    }
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
