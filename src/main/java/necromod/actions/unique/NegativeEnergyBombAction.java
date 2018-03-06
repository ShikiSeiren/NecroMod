package necromod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;

public class NegativeEnergyBombAction extends AbstractGameAction{
	
	//Retrain of CorpseExplosionAction
	
	public NegativeEnergyBombAction(final AbstractCreature target, final AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.target.hasPower("Negative_Level")) {
            	
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "Negative_Level"));

            }
            else {
                this.isDone = true;
            }
        }
        this.tickDuration();
    }

}
