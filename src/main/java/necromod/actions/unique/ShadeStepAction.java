package necromod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class ShadeStepAction extends AbstractGameAction {
	
	private int energyGain;
	
	public ShadeStepAction(final int energyGain) {
		this.duration = 0.0f;
		this.actionType = ActionType.WAIT;
		this.energyGain = energyGain;
	}
	
	@Override
	public void update() {
		
		if(AbstractDungeon.player.drawPile.isEmpty()){
			this.isDone = true;
			return;
		}
		
		final AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
		if (card.type == AbstractCard.CardType.SKILL) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.energyGain));
        }
        this.isDone = true;

	}

}
