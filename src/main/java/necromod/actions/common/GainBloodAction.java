package necromod.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import necromod.powers.BloodPower;

public class GainBloodAction extends AbstractGameAction {
	
	
	public GainBloodAction(final AbstractCreature target, final AbstractCreature source, final int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        
    }
	
    
    @Override
    public void update() {
    	
		if((this.target.getPower("Blood") == null) || (this.target.getPower("Blood").amount <= 3)){
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new BloodPower(this.target, 1), 1));
		}
		
		this.isDone = true;
		
    }

}
