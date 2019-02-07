package necromod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.monsters.*;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.*;

public class DeathKnellAction extends AbstractGameAction {
    private DamageInfo info;
    private int amount;
    
    public DeathKnellAction(final AbstractCreature target, final AbstractCreature source, final DamageInfo info, final int maxHPAmount, int amount) {
        this.setValues(target, this.info = info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1f;
        this.amount = amount;
    }
    
    @Override
    public void update() {
        if (this.duration == 0.1f && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            	AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(this.source, this.source, amount));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        this.tickDuration();
    }

}