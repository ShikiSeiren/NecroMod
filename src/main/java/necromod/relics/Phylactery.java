package necromod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.actions.common.*;

import com.megacrit.cardcrawl.rooms.*;

import basemod.abstracts.CustomRelic;
import necromod.NecroMod;

public class Phylactery extends CustomRelic{
	
	private static boolean usedThisCombat = false;
	
	private static final String ID = "Phylactery";
	public final String[] DESCRIPTIONS = new String[] {
			"Once per combat, if you would receive fatal damage: Revive with 10% of your max. HP "
	}; 
	//private static final int HP_TO_HEAL;
	
	public Phylactery() {
		super(ID, NecroMod.getPhylacteryTexture(),
				RelicTier.RARE, LandingSound.MAGICAL);
	}	

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    @Override
    public void atPreBattle() {
        Phylactery.usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }
    
    
    @Override
    public void onTrigger() {
    	if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !Phylactery.usedThisCombat) {
    		this.flash();
    		AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    		AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth / 10, true);
    	}
        
    }
    
    @Override
    public void onVictory() {
        this.pulse = false;
    }
	
	@Override
	public AbstractRelic makeCopy() {
		return new Phylactery();
	}	
}
