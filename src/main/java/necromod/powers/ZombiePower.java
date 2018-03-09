package necromod.powers;

//import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.monsters.*;

import necromod.NecroMod;
import necromod.actions.common.CheckIfDeadAction;

public class ZombiePower extends AbstractPower {
	public static final String POWER_ID = "ZombiePower";
	public static final String NAME = "Zombie";
	public static final String[] DESCRIPTIONS = new String[] {
			"A risen corpse. Deals 4 damge at the end of the turn."
	};
	public AbstractMonster m;
	
	public int DAMAGE_AMT;
	
	public ZombiePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.DAMAGE_AMT= 4;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getZombiePowerTexture();

	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
    public void atEndOfTurn(boolean isPlayer) {
		
		this.flash();
		
		//for(int i = 0; i < this.owner.getPower("ZombiePower").amount; i++) {
			
		final AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);

	    AbstractDungeon.actionManager.addToBottom(new CheckIfDeadAction(randomMonster, this.owner, 3, this.owner.getPower("ZombiePower").amount, this.ID, true));
	    
			/**
			m = AbstractDungeon.getMonsters().getRandomMonster(true);
			while(m.isDying || m.currentHealth <0) {
				m = AbstractDungeon.getMonsters().getRandomMonster(true);
			}
	        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(this.owner, this.DAMAGE_AMT, DamageInfo.DamageType.THORNS), 0));
			
		}**/

    }

}
