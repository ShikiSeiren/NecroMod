package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;

import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;

import necromod.NecroMod;

public class LichPower extends AbstractPower {
	public static final String POWER_ID = "LichPower";
	public static final String NAME = "Lich";
	public static final String[] DESCRIPTIONS = new String[] {
			"A powerful undead mage. Deals 5 damage to ALL enemies at the end of each turn."
	};
	
	public int DAMAGE_AMT;
	
	public LichPower(AbstractCreature owner, int amount, boolean isUpgraded, int damage) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getLichPowerTexture();
		this.DAMAGE_AMT = damage;

	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
    public void atEndOfTurn(boolean isPlayer) {
    	
		this.flash();
		for(int i = 0; i < this.owner.getPower("LichPower").amount; i++) {
			
			this.flash();
	        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.25f));
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.DAMAGE_AMT, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
	        
		}

    }	

}
