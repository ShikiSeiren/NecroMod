package necromod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.monsters.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.powers.*;
import org.apache.logging.log4j.*;
import com.megacrit.cardcrawl.actions.common.*;

public class NegativeLevelAction extends AbstractGameAction {

	
	public NegativeLevelAction(final AbstractCreature target, final AbstractCreature source, final int amount) {
        this.setValues(target, source, amount);

    }
	
	@Override
	public void update() {
		if(this.amount > 0) {
			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, -this.amount), -this.amount));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, (this.amount*2), false),
					(this.amount*2), true, AbstractGameAction.AttackEffect.NONE));
			
		}
		
		
	}
	

}
