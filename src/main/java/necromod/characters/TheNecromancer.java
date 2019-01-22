package necromod.characters;

import java.util.ArrayList;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
//import com.badlogic.gdx.math.*;
//import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomPlayer;
import necromod.NecroMod;
import necromod.cards.Negative_Energy_Arrows;
import necromod.patches.TheNecromancerEnum;
import necromod.patches.AbstractCardEnum;
//import src.main.java.necromancer.characters.Override;
//import src.main.java.necromancer.characters.PlayerClass;
//import src.main.java.necromancer.characters.String;
//import src.main.java.necromancer.characters.TheNecromancer;
import necromod.actions.common.CheckIfDeadAction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;






public class TheNecromancer extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;
    
    public static final String NAME = "The Necromancer";

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
		super(name, setClass, orbTextures, "img/char/necromancer/orb/vfx.png", "img/necromancer.g3dj", "Necromancer_Render|idle"); //super(name, setClass, orbTextures, "img/char/necromancer/orb/vfx.png", null, null);
		
		this.dialogX = this.drawX + 0.0f * Settings.scale;
		this.dialogY = this.drawY + 170.0f * Settings.scale;
		
		initializeClass(null, NecroMod.makePath(NecroMod.NECROMANCER_SHOULDER_2),
				NecroMod.makePath(NecroMod.NECROMANCER_SHOULDER_1),
				NecroMod.makePath(NecroMod.NECROMANCER_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		
				//this.loadAnimation("img/char/necromancer/skeleton.atlas", "img/char/necromancer/skeleton.json", 1.0F);

				//AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
				//e.setTime(e.getEndTime() * MathUtils.random());
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : this.powers) {
			p.atEndOfTurn(true);
		}

		AbstractDungeon.actionManager.addToBottom(new CheckIfDeadAction(AbstractDungeon.getMonsters().getRandomMonster(true), AbstractDungeon.player, 0, 0, "", true));
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Strike_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Defend_W");
		retVal.add("Negative_Energy_Arrows");
		retVal.add("Bone_Wall");
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Vampire_Amulet");
		UnlockTracker.markRelicAsSeen("Vampire_Amulet");
		return retVal;
	}
	
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Necromancer", "A powerful mage that channels dark energies to summon the dead.",
				75, 75, 0, 99, 5,
			this, getStartingRelics(), getStartingDeck(), false);
	}
	
	@Override
	public String getTitle(PlayerClass playerClass) {
		return NAME;
	}

	@Override
	public AbstractCard.CardColor getCardColor() {
		return AbstractCardEnum.WHITE;
	}

	@Override
	public Color getCardRenderColor() {
		return Color.MAROON;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new Negative_Energy_Arrows();
	}

	@Override
	public Color getCardTrailColor() {
		return NecroMod.WHITE;
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 4;
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontRed;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_HEAVY";
	}

	@Override
	public String getLocalizedCharacterName() {
		return NAME;
	}

	@Override
	public AbstractPlayer newInstance() {
		return new TheNecromancer(NAME, TheNecromancerEnum.NECROMANCER);
	}

	@Override
	public String getSpireHeartText() {
		return "NL You ready your Weapon...";
	}

	@Override
	public Color getSlashAttackColor() {
		return NecroMod.WHITE;
	}

	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[]{
			AbstractGameAction.AttackEffect.SLASH_DIAGONAL
			, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
			, AbstractGameAction.AttackEffect.SLASH_VERTICAL
			, AbstractGameAction.AttackEffect.SLASH_HEAVY
		};
	}

	//TODO: Character Specific Dialog
	@Override
	public String getVampireText() {
		return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
	}
	
}
