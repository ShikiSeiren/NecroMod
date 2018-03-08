package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;

import necromod.NecroMod;

public class VampireLadyPower extends AbstractPower {
	public static final String POWER_ID = "VampireLadyPower";
	public static final String NAME = "Vampire Lady";
	public static final String[] DESCRIPTIONS = new String[] {
			"A powerful Vampire. Deals !D! damge to a random enemy at the end of each turn. Heals for unblocked damage deal."
	};
	
	public int DAMAGE_AMT;
	
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
		for(int i = 0; i < this.owner.getPower("VampireLadyPower").amount; i++) {
			
			AbstractDungeon.actionManager.addToBottom(new VampireDamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(this.owner, this.DAMAGE_AMT, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
	        
		}

    }

}
