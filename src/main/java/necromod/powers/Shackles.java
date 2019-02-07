package necromod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.monsters.*;

import necromod.NecroMod;



public class Shackles extends AbstractPower {
	public static final String POWER_ID = "Shackles";
	public static final String NAME = "Shackles of Pain";
	public static final String[] DESCRIPTIONS = new String[] {
			"Damage-Link : Any damage taken is also applied to the target."
	};
	
	public int DAMAGE_AMT;
	public int TOTAL_DAMAGE = 0;
	public AbstractMonster target;
	
	public Shackles(AbstractCreature owner, AbstractCreature target, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.target = (AbstractMonster)target;
		this.amount = amount;
		this.DAMAGE_AMT= 5;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.img = NecroMod.getShacklesPowerTexture();

	}
	
	@Override
	public int onAttacked(final DamageInfo info, int damageAmount) {
/**		
            if (	this.target.hasPower("OfPain") 
            		&& !this.target.isDying 
            		&& this.target.currentHealth > 0 
            		&& !this.target.isEscaping
            		&& damageAmount > 0) {
            	
            	AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, new DamageInfo(this.owner, damageAmount, DamageInfo.DamageType.THORNS), 0));
            	
            }        
**/
		
		for (int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size(), i = 0; i < temp; ++i) {
			
            if (    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hasPower("OfPain") 
                    && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying 
                    && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0 
                    && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isEscaping) {
 
            	AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getCurrRoom().monsters.monsters.get(i), new DamageInfo(this.owner, damageAmount, DamageInfo.DamageType.THORNS), 0));
                
            }
		
		
		}
		return damageAmount;
	}
	
	@Override
	public void atStartOfTurn() {
		this.flash();		
		if(this.amount <1) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Shackles"));
			
			for (int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size(), i = 0; i < temp; ++i) {
				
                if (    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hasPower("OfPain") 
                        && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying 
                        && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0 
                        && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isEscaping) {
     
                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.getCurrRoom().monsters.monsters.get(i),AbstractDungeon.getCurrRoom().monsters.monsters.get(i), "OfPain"));
                    
                }
                    
            }   
                	      	
        	
        }
        else {
        	
        	AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, "Shackles", 1));
        	
        	for (int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size(), i = 0; i < temp; ++i) {
                if (    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hasPower("OfPain") 
                        && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying 
                        && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0 
                        && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isEscaping) {
                
                	AbstractDungeon.actionManager.addToTop(new ReducePowerAction(AbstractDungeon.getCurrRoom().monsters.monsters.get(i), AbstractDungeon.getCurrRoom().monsters.monsters.get(i), "OfPain", 1));
                }
                    
            }   
        	               			
        } 
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
}
