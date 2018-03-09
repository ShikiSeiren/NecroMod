package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.NecroMod;


public class HellFlamePower extends AbstractPower {
	public static final String POWER_ID = "HellFlamePower";
	public static final String NAME = "Hellfire";
	public static final String[] DESCRIPTIONS = new String[] {
			"Deal 8 Damage per turn. If the afflicted enemy casts a buff : Remove it."
	};
	public AbstractCreature source;
	
	public HellFlamePower(AbstractCreature owner, AbstractCreature source, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.DEBUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getHellFlamePowerTexture();
		this.source = source;
				
	}
	
	@Override
    public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
		
		if(power.type == PowerType.BUFF && (source != AbstractDungeon.player)) {
			this.flash();
			if(this.amount <1) {
	        	AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HellFlamePower"));
	        }
	        else {
	        	AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, "HellFlamePower", 1));
	        } 
		}
		
    }
	
	//Possibly damage atEndOfTurn of monster or at startOfTurn player.
	
	@Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.owner, (10*this.amount), AbstractGameAction.AttackEffect.FIRE));
        }
        
    }
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

}
