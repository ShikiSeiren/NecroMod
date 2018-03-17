package necromod.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;

public class ModifyValueAction extends AbstractGameAction
{
    AbstractCard cardToModify;
    
    public ModifyValueAction(final AbstractCard card, final int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.cardToModify = card;
    }
    
    @Override
    public void update() {
        final AbstractCard cardToModify = this.cardToModify;
        cardToModify.baseHeal += this.amount;
        if (this.cardToModify.baseHeal < 0) {
            this.cardToModify.baseHeal = 0;
        }
        this.isDone = true;
    }
}