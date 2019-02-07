package necromod.powers;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;

import necromod.NecroMod;

public class TimerPower extends AbstractPower {
	
	public static final String POWER_ID = "TimerPower";
	public static final String NAME = "Timer";
	public static final String[] DESCRIPTIONS = new String[] {
			"Timer until another power expires."
	};
	
	public String power;
	
	public TimerPower(AbstractCreature owner, int amount, String power) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getBonesPowerTexture();
		this.power = power;
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
    public void atEndOfTurn(final boolean isPlayer) {
    	
    	this.amount -= 1;
        this.flash();
        
        
    	
        if(this.amount <1) {
        	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, TimerPower.POWER_ID));
        	
        	if(this.power.equals(DeathsShadowPower.POWER_ID)) {
        		AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.owner.getPower(DeathsShadowPower.POWER_ID).amount));
        	}
        	
        	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.power));
        	if(this.power.equals(DeathsShadowPower.POWER_ID)) {
        		
        	}
        	
        } 	

    }

}
