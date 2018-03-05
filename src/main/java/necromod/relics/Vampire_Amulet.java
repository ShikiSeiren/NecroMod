package necromod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.*;

import basemod.abstracts.CustomRelic;
import necromod.NecroMod;

public class Vampire_Amulet extends CustomRelic{
	
	private static final String ID = "Vampire_Amulet";
	public static final String[] DESCRIPTIONS = new String[] {
			"Whenever you kill an enemy : Heal 2 HP. "
	}; 
	//private static final int HP_TO_HEAL;
	
	public Vampire_Amulet() {
		super(ID, NecroMod.getVampireAmuletTexture(),
				RelicTier.STARTER, LandingSound.MAGICAL);
	}	
	
	@Override
	public void onMonsterDeath(final AbstractMonster m) {
		if (m.currentHealth == 0){ //&& !(this.target.hasPower("Minion")) if too powerful
			this.flash();
			 AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
			 AbstractDungeon.player.heal(2, true);
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Vampire_Amulet();
	}	
}
