package necromod.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

public class BloodAction extends AbstractGameAction {
	

    
    public BloodAction(final AbstractCreature target, final AbstractCreature source, final int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        
    }
    
    @Override
    public void update() {
		if(this.target.hasPower("Blood")){
			AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.source, "Blood", 1));
		} else {
			AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.target, this.source, this.amount));
		}
		
		this.isDone = true;
		
    }
}
