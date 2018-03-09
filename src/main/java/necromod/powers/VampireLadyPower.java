package necromod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.monsters.*;

import necromod.NecroMod;


public class VampireLadyPower extends AbstractPower {
	public static final String POWER_ID = "VampireLadyPower";
	public static final String NAME = "Vampire Lady";
	public static final String[] DESCRIPTIONS = new String[] {
			"A powerful Vampire. Deals !D! damge to a random enemy at the end of each turn. Heals for unblocked damage deal."
	};
	
	public int DAMAGE_AMT;
	public AbstractMonster m;
	
	public VampireLadyPower(AbstractCreature owner, int amount, boolean isUpgraded, int damage) {
		
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.name = NAME;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getVampireLadyPowerTexture();
		this.DAMAGE_AMT = damage;

	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
    public void atEndOfTurn(boolean isPlayer) {
    	
		this.flash();
		//final AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);

	    //AbstractDungeon.actionManager.addToBottom(new CheckIfDeadAction(randomMonster, this.owner, this.DAMAGE_AMT, this.owner.getPower("VampireLadyPower").amount, this.ID, true));

    }

}
