package necromod.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;

public class CheckIfDeadAction extends AbstractGameAction {
    private int numTimes;
    public int DAMAGE_AMT;
    public AbstractCreature owner;
    public String ID;
    public boolean firstActivation;
    
    public CheckIfDeadAction(final AbstractCreature target, final AbstractCreature owner, int damage, final int numTimes, String ID, boolean firstActivation) {
        this.target = target;
        //this.actionType = ActionType.DEBUFF;
        this.duration = 0.01f;
        this.numTimes = numTimes;
        DAMAGE_AMT = damage;
        this.owner = owner;
        this.ID = ID;
        this.firstActivation = firstActivation;
    }
    
    @Override
    public void update() {
    	if (this.firstActivation) {
           
    		if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                final AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
                AbstractDungeon.actionManager.addToBottom(new CheckIfDeadAction(randomMonster, this.owner, this.DAMAGE_AMT, this.numTimes, this.ID, false));
            }
    	}
    	
    	else {
    		if (this.target == null) {
    			this.isDone = true;
    			return;
    		}
    		if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
    			AbstractDungeon.actionManager.clearPostCombatActions();
    			this.isDone = true;
    			return;
    		}
    		if (this.target.currentHealth > 0) {
    			if((this.ID.equals("ZombiePower")) || (this.ID.equals("DeathKnightPower"))) {
    				AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.owner, this.DAMAGE_AMT, DamageInfo.DamageType.THORNS), 0));
    			}
    			if((this.ID.equals("VampireLadyPower")) || (this.ID.equals("VampirePrincessPower"))){
    				AbstractDungeon.actionManager.addToBottom(new VampireDamageAction(this.target, new DamageInfo(this.owner, this.DAMAGE_AMT, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    			}
    			AbstractDungeon.actionManager.addToTop(new WaitAction(0.05f));
    			/**test : for(int i =0; i <= numTime; i++{
    			 * if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
    			 * 	do the above
    			 *  }
    			 * }**/
    		}
    		if (this.numTimes > 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
    			--this.numTimes;
    			final AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
    			AbstractDungeon.actionManager.addToBottom(new CheckIfDeadAction(randomMonster, this.owner, this.DAMAGE_AMT, this.numTimes, this.ID, false));
    		}
    	

    	}
        this.isDone = true;
    }

}

