package necromod.characters;

import java.util.ArrayList;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.badlogic.gdx.math.*;
import com.esotericsoftware.spine.*;
import java.util.*;

import basemod.abstracts.CustomPlayer;
import necromod.NecroMod;
import necromod.patches.TheNecromancerEnum;

public class TheNecromancer extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;

	public static final String[] orbTextures = {
			"img/char/necromancer/orb/layer1.png",
			"img/char/necromancer/orb/layer2.png",
			"img/char/necromancer/orb/layer3.png",
			"img/char/necromancer/orb/layer4.png",
			"img/char/necromancer/orb/layer5.png",
			"img/char/necromancer/orb/layer6.png",
			"img/char/necromancer/orb/layer1d.png",
			"img/char/necromancer/orb/layer2d.png",
			"img/char/necromancer/orb/layer3d.png",
			"img/char/necromancer/orb/layer4d.png",
			"img/char/necromancer/orb/layer5d.png"
	};

	
	public TheNecromancer(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "img/char/necromancer/orb/vfx.png", null, null);
		
		this.dialogX = this.drawX + 0.0f * Settings.scale;
		this.dialogY = this.drawY + 170.0f * Settings.scale;
		
		initializeClass(null, NecroMod.makePath(NecroMod.NECROMANCER_SHOULDER_2),
				NecroMod.makePath(NecroMod.NECROMANCER_SHOULDER_1),
				NecroMod.makePath(NecroMod.NECROMANCER_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		
				this.loadAnimation("img/char/necromancer/skeleton.atlas", "img/char/necromancer/skeleton.json", 1.0F);

				AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
				e.setTime(e.getEndTime() * MathUtils.random());
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Negative_Energy_Arrows");
		retVal.add("Bone_Wall");
		return retVal;
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Vampire_Amulet");
		UnlockTracker.markRelicAsSeen("Vampire_Amulet");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Necromancer", "A powerful mage that channels dark energies to summon the dead.",
				75, 75, 99, 5,
			TheNecromancerEnum.NECROMANCER, getStartingRelics(), getStartingDeck(), false);
	}
	

}
