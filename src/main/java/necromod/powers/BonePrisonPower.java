package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.monsters.*;

import necromod.NecroMod;

public class BonePrisonPower extends AbstractPower{
	public static final String[] DESCRIPTIONS = new String[] {
			"Target is stunned for 1 turn."
		};
	
	private byte moveByte;
	private AbstractMonster.Intent moveIntent;
	
	
	public BonePrisonPower(AbstractCreature owner) {
		this.name = "Bone Prison";
		this.ID = "BonePrisonPower";
		this.owner = owner;
		this.amount = -1;
		this.updateDescription();
	 
		this.type = AbstractPower.PowerType.DEBUFF;
		this.img = NecroMod.getBonePrisonPowerTexture();
		
		moveByte = 1;
		moveIntent = AbstractMonster.Intent.UNKNOWN;
		
		if (owner instanceof AbstractMonster) {
			AbstractMonster m = (AbstractMonster)owner;
			
			moveByte = Byte.valueOf(m.nextMove);
			moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());
			
			m.setMove(Byte.MAX_VALUE, AbstractMonster.Intent.STUN);
			m.createIntent();
			AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, Byte.MAX_VALUE, AbstractMonster.Intent.STUN));
		}
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	@Override
	public void atEndOfRound() {
		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		
		if(owner instanceof AbstractMonster) {
			AbstractMonster m = (AbstractMonster)owner;
			
			m.setMove(moveByte, moveIntent);
			m.createIntent();
			AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, moveByte, moveIntent));
			m.updatePowers();
		}
	}	

}
