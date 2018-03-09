package necromod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;

public class DetonateBonesAction extends AbstractGameAction{
	
	//Retrain of CorpseExplosionAction
	
	public DetonateBonesAction(final AbstractCreature target, final AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.target.hasPower("Bones")) {
            	
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "Bones"));

            }
            else {
                this.isDone = true;
            }
        }
        this.tickDuration();
    }

}
