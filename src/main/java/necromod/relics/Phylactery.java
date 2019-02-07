package necromod.relics;

//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.actions.common.*;

//import com.megacrit.cardcrawl.rooms.*;

import basemod.abstracts.CustomRelic;
import necromod.NecroMod;

public class Phylactery extends CustomRelic{
/**	
	private static boolean usedThisCombat = false;
	

	public static final String[] DESCRIPTIONS = new String[] {
			"Once per combat, if you would receive fatal damage: Revive with 10% of your max. HP "
	}; 
	//private static final int HP_TO_HEAL;
	**/	
	private static final String ID = "Phylactery";
	
	public Phylactery() {
		super(ID, NecroMod.getPhylacteryTexture(),
				RelicTier.RARE, LandingSound.MAGICAL);
	}	
	/**
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public void atPreBattle() {
        Phylactery.usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }
    
    
    @Override
    public void onTrigger() {
    	if (Phylactery.usedThisCombat = false) { //AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && 
    		this.flash();
    		AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    		AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth / 10, true);
    		Phylactery.usedThisCombat = true;
    	}
        
    }
    
    @Override
    public void onVictory() {
        this.pulse = false;
    }
	
	@Override
	public AbstractRelic makeCopy() {
		return new Phylactery();
	}**/	
}
