package necromod;

import java.nio.charset.StandardCharsets;

//import com.megacrit.cardcrawl.helpers.GameDictionary;
import necromod.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;/**
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;**/
import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
//import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModPanel;
//import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
//import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
//import basemod.interfaces.SetUnlocksSubscriber;

import necromod.characters.TheNecromancer;
import necromod.patches.AbstractCardEnum;
import necromod.patches.TheNecromancerEnum;
import necromod.relics.Vampire_Amulet;
//import necromod.relics.Phylactery;

@SpireInitializer
public class NecroMod implements PostInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, EditKeywordsSubscriber {             // , OnCardUseSubscriber, SetUnlocksSubscriber, 
	
	public static final Logger logger = LogManager.getLogger(NecroMod.class.getName());
	
	private static final String MODNAME = "NecroMod";
	private static final String AUTHOR = "ShikiSeiren";
	private static final String DESCRIPTION = "v0.0.1\n Adds The Necromancer as a playable third character";
	
	private static final Color WHITE = CardHelper.getColor(255.0f, 250.0f, 250.0f);
	private static final String NECROMOD_ASSETS_FOLDER = "img";
	
	// card backgrounds
	private static final String ATTACK_WHITE = "512/bg_attack_white.png";
	private static final String SKILL_WHITE = "512/bg_skill_white.png";
	private static final String POWER_WHITE = "512/bg_power_white.png";
	private static final String ENERGY_ORB_WHITE = "512/card_white_orb.png";
	
	private static final String ATTACK_WHITE_PORTRAIT = "1024/bg_attack_white.png";
	private static final String SKILL_WHITE_PORTRAIT = "1024/bg_skill_white.png";
    private static final String POWER_WHITE_PORTRAIT = "1024/bg_power_white.png";
    private static final String ENERGY_ORB_WHITE_PORTRAIT = "1024/card_white_orb.png";
    
    //card images
    
    public static final String BONE_WALL = "cards/bone_wall.png";
    public static final String DEFEND_WHITE = "cards/defend_white.png";
    public static final String NEGATIVE_ENERGY_ARROWS = "cards/negative_energy_arrows.png";
    public static final String STRIKE_WHITE = "cards/strike_white.png";
    
    public static final String BONE_ARMOR = "cards/bone_armor.png";
    public static final String NEGATIVE_ENERGY_JAVELIN = "cards/negative_energy_javelin.png";
    public static final String NEGATIVE_ENERGY_BURST = "cards/negative_energy_burst.png";
    public static final String NEGATIVE_ENERGY_SPHERE = "cards/negative_energy_sphere.png";
    public static final String THOUSAND_BONE_KNIVES = "cards/thousand_bone_knives.png";
    public static final String SHADE_STEP = "cards/shade_step.png";
    public static final String BONE_ARMORY = "cards/bone_armory.png";
    public static final String BONE_SPIKES = "cards/bone_spikes.png";
    public static final String NEGATIVE_ENERGY_BOMB = "cards/negative_energy_bomb.png";
    public static final String BLOOD_FOR_BLOOD = "cards/blood_for_blood.png";
    public static final String SUMMON_DEATH_KNIGHT = "cards/summon_death_knight.png";
    public static final String BLOOD_WAKE = "cards/blood_wake.png";
    public static final String LIFE_TAP = "cards/life_tap.png";
    public static final String LIFE_DRAIN = "cards/life_drain.png"; 
    public static final String HELL_FLAME = "cards/hell_flame.png";
    public static final String BONE_SHIFT = "cards/bone_shift.png";
    public static final String SPECTRAL_WALK = "cards/spectral_walk.png";
    public static final String BLOOD_SHIELD = "cards/blood_shield.png";
    public static final String FEAR = "cards/scare.png";
    public static final String SHACKLES_OF_PAIN = "cards/shackles_of_pain.png";
    public static final String VAMPIRIC_STRIKE = "cards/vampiric_strike.png";
    public static final String SUMMON_LICH = "cards/summon_lich.png";
    public static final String SUMMON_VAMPIRE_LADY = "cards/summon_vampire_lady.png";
    public static final String BLOOD_IS_POWER = "cards/blood_is_power.png";   
    public static final String SUMMON_UNDEAD_ARMY = "cards/summon_undead_army.png";
    public static final String FINGER_OF_DEATH = "cards/finger_of_death.png";
    public static final String BLOOD_WHIP = "cards/blood_whip.png";
    public static final String SUMMON_UNDEAD = "cards/summon_undead.png";
    public static final String BONE_PRISON ="cards/bone_prison.png";
    public static final String DETONATE_BONES = "cards/detonate_bones.png";
    public static final String SPECTRAL_ARMOR = "cards/spectral_armor.png";
    public static final String GRASP_HEART = "cards/grasp_heart.png";
    public static final String NEGATIVE_ENERGY_MIST = "cards/negative_energy_mist.png";
    public static final String CONSUME_ILLNESS = "cards/consume_illness.png";
    public static final String BLOOD_CURSE = "cards/blood_curse.png";
    public static final String NEGATIVE_ENERGY_SHIELD = "cards/negative_energy_shield.png";
    public static final String UNCHARNEL = "cards/uncharnel.png";
    public static final String SLAY_LIVING = "cards/slay_living.png";
    public static final String PLAGUE_BOLT = "cards/plague_bolt.png";
    public static final String BLOOD_MAGIC = "cards/blood_magic.png";
    public static final String VIOLENT_NECROSIS = "cards/violent_necrosis.png";
    public static final String CORRUPT = "cards/corrupt.png";
    public static final String REAP_THE_WEAK = "cards/reap_the_weak.png";
    public static final String EPIDEMIC = "cards/epidemic.png";
    public static final String ACID_JAVELIN = "cards/acid_javelin.png";
    public static final String NECROTIC_CLAWS = "cards/necrotic_claws.png";
    public static final String STOP_HEART = "cards/stop_heart.png";
    public static final String STRIKE_FROM_BEYOND = "cards/strike_from_beyond.png";
    public static final String TOXIC_SMOKE_SCREEN = "cards/toxic_smoke_screen.png";
    public static final String SIPHON_STRENGTH = "cards/siphon_strength.png";
    public static final String PANDEMIC = "cards/pandemic.png";
    public static final String SKELETON_DRAGON = "cards/skeleton_dragon.png";
    public static final String CLOUDKILL = "cards/cloudkill.png";
    public static final String REQUIEM = "cards/requiem.png";
    
    
    //power images
    
    public static final String BONES_POWER = "powers/bones.png"; 
    public static final String ARMORY_POWER = "powers/armory.png";
    public static final String NEGATIVE_LEVEL = "powers/negative_level.png";
    public static final String BLOOD_POWER = "powers/blood.png";
    public static final String BLOOD_FOR_BLOOD_POWER = "powers/blood_for_blood.png";
    public static final String DEATH_KNIGHT_POWER = "powers/death_knight.png";
    public static final String HELL_FLAME_POWER = "powers/hell_flame.png";
    public static final String SPECTRAL_POWER = "powers/spectral.png";
    public static final String SHACKLES_POWER = "powers/shackles.png";
    public static final String LICH_POWER = "powers/lich.png";
    public static final String ELDER_LICH_POWER = "powers/elder_lich.png";
    public static final String VAMPIRE_LADY_POWER = "powers/vampire_lady.png";
    public static final String VAMPIRE_PRINCESS_POWER = "powers/vampire_princess.png";
    public static final String ZOMBIE_POWER = "powers/zombie.png";
    public static final String BONE_PRISON_POWER ="powers/bone_prison.png";
    public static final String GRASP_HEART_POWER = "powers/crushed_heart.png";
    public static final String NEGATIVE_SHIELD_POWER = "powers/negative_shield.png";
    public static final String BLOOD_MAGIC_POWER = "powers/blood_magic.png";
    public static final String TOXIC_SCREEN_POWER = "powers/toxic_screen.png";
    public static final String SKELETON_DRAGON_POWER = "powers/skeleton_dragon.png";
        
    //relic images
    
    public static final String VAMPIRE_AMULET_RELIC = "relics/vampire_amulet.png";
    public static final String PHYLACTERY_RELIC = "relics/phylactery.png";
    
    //necromancer assets
    
    private static final String NECROMANCER_BUTTON = "charSelect/necromancerButton.png";
    private static final String NECROMANCER_PORTRAIT = "charSelect/necromancerPortrait.jpg";
    public static final String NECROMANCER_SHOULDER_1 = "char/necromancer/shoulder.png";
    public static final String NECROMANCER_SHOULDER_2 = "char/necromancer/shoulder2.png";
    public static final String NECROMANCER_CORPSE = "char/necromancer/corpse.png";
    public static final String NECROMANCER_SKELETON_ATLAS = "char/necromancer/skeleton.atlas";
    public static final String NECROMANCER_SKELETON_JSON = "char/necromancer/skeleton.json";
    
    //badge
    
    public static final String BADGE_IMG = "NRelicBadge.png";
    
    //texture loaders
    
    
    public static Texture getBonesPowerTexture() {
    return new Texture(makePath(BONES_POWER));
    }
    
    public static Texture getVampireAmuletTexture() {
    	return new Texture(makePath(VAMPIRE_AMULET_RELIC));
    }
    
    public static Texture getBoneArmoryPowerTexture() {
    	return new Texture(makePath(ARMORY_POWER));
    }
    
    public static Texture getNegativeLevelTexture() {
    	return new Texture(makePath(NEGATIVE_LEVEL));
    }
    
    public static Texture getBloodPowerTexture() {
    	return new Texture(makePath(BLOOD_POWER));
    }
    
    public static Texture getBloodForBloodPowerTexture() {
    	return new Texture(makePath(BLOOD_FOR_BLOOD_POWER));
    }
    
    public static Texture getDeathKnightPowerTexture() {
    	return new Texture(makePath(DEATH_KNIGHT_POWER));
    }
    
    public static Texture getHellFlamePowerTexture() {
    	return new Texture(makePath(HELL_FLAME_POWER));
    }
    
    public static Texture getSpectralPowerTexture() {
    	return new Texture(makePath(SPECTRAL_POWER));
    }
    
    public static Texture getShacklesPowerTexture() {
    	return new Texture(makePath(SHACKLES_POWER));
    }
    
    public static Texture getLichPowerTexture() {
    	return new Texture(makePath(LICH_POWER));
    }
    
    public static Texture getElderLichPowerTexture() {
    	return new Texture(makePath(ELDER_LICH_POWER));
    }
    
    public static Texture getVampireLadyPowerTexture() {
    	return new Texture(makePath(VAMPIRE_LADY_POWER));
    }
    
    public static Texture getVampirePrincessPowerTexture() {
    	return new Texture(makePath(VAMPIRE_PRINCESS_POWER));
    }
    
    public static Texture getZombiePowerTexture() {
    	return new Texture(makePath(ZOMBIE_POWER));
    }
    
    public static Texture getBonePrisonPowerTexture() {
    	return new Texture(makePath(BONE_PRISON_POWER));
    }
    
    public static Texture getGraspHeartPowerTexture() {
    	return new Texture(makePath(GRASP_HEART_POWER));
    }
    
    public static Texture getNegativeShieldPowerTexture() {
    	return new Texture(makePath(NEGATIVE_SHIELD_POWER));
    }
    
    public static Texture getBloodMagicPowerTexture() {
    	return new Texture(makePath(BLOOD_MAGIC_POWER));
    }
    
    public static Texture getToxicScreenPowerTexture() {
    	return new Texture(makePath(TOXIC_SCREEN_POWER));
    }
    
    public static Texture getPhylacteryTexture() {
    	return new Texture(makePath(PHYLACTERY_RELIC));
    }
    
    public static Texture getSkeletonDragonPowerTexture() {
    	return new Texture(makePath(SKELETON_DRAGON_POWER));
    }
    
    
    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
    	return NECROMOD_ASSETS_FOLDER + "/" + resource;
    }
    
    public NecroMod() {
    	logger.info("subscribing to postInitialize event");
        BaseMod.subscribeToPostInitialize(this);
        
        logger.info("subscribing to editCharacters event");
        BaseMod.subscribeToEditCharacters(this);
        
        logger.info("subscribing to editRelics event");
        BaseMod.subscribeToEditRelics(this);
        
        logger.info("subscribing to editCards event");
        BaseMod.subscribeToEditCards(this);

        logger.info("subscribing to editStrings event");
        BaseMod.subscribeToEditStrings(this);
        
        logger.info("subscribing to editKeywords event");
        BaseMod.subscribeToEditKeywords(this);
        
        /* Disable this during playtesting for being counterproductive */
//        logger.info("subscribing to setUnlocks event");
//        BaseMod.subscribeToSetUnlocks(this);
        
//        logger.info("subscribing to onCardUse event");
//        BaseMod.subscribeToOnCardUse(this);
        
        /*
         * Note that for now when installing NecroMod, in the `mods/` folder another folder named
         * the value of NECROMOD_ASSETS_FOLDER must be created into which all the contents of the
         * `images/` folder must be relocated
         */
        logger.info("creating the color " + AbstractCardEnum.WHITE.toString());
        BaseMod.addColor(AbstractCardEnum.WHITE.toString(),
        		WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE,
        		makePath(ATTACK_WHITE), makePath(SKILL_WHITE),
        		makePath(POWER_WHITE), makePath(ENERGY_ORB_WHITE),
        		makePath(ATTACK_WHITE_PORTRAIT), makePath(SKILL_WHITE_PORTRAIT),
        		makePath(POWER_WHITE_PORTRAIT), makePath(ENERGY_ORB_WHITE_PORTRAIT));
    }
    
    public static void initialize() {
    	logger.info("---NECROMOD INIT---");
    	
    	@SuppressWarnings("unused")
    	NecroMod necro = new NecroMod();
    	
    	logger.info("------");
    }
    
    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("NecroMod does not have any settings!", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }

	@Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");
		
		logger.info("add " + TheNecromancerEnum.NECROMANCER.toString());
		BaseMod.addCharacter(TheNecromancer.class, "The Necromancer", "Necromancer class string",
				AbstractCardEnum.WHITE.toString(), "The Necromancer",
				makePath(NECROMANCER_BUTTON), makePath(NECROMANCER_PORTRAIT),
				TheNecromancerEnum.NECROMANCER.toString());
		
		logger.info("done editting characters");
	}

	
	@Override
	public void receiveEditRelics() {
		logger.info("begin editting relics");
        
        // Add relics
        RelicLibrary.add(new Vampire_Amulet());
        
        //BaseMod.addRelicToCustomPool(new Phylactery(), "WHITE");
        
        logger.info("done editting relics");
	}

	@Override
	public void receiveEditCards() {
		logger.info("begin editting cards");
		
		logger.info("add cards for " + TheNecromancerEnum.NECROMANCER.toString());
		
		BaseMod.addCard(new Strike_White());
		BaseMod.addCard(new Defend_White());
		
		BaseMod.addCard(new Bone_Wall());
		BaseMod.addCard(new Negative_Energy_Arrows());
		
		BaseMod.addCard(new Negative_Energy_Javelin());
		BaseMod.addCard(new Thousand_Bone_Knives());
		BaseMod.addCard(new Bone_Armor());
		BaseMod.addCard(new Bone_Armory());
		BaseMod.addCard(new Shade_Step());
		BaseMod.addCard(new Bone_Spikes());		
		BaseMod.addCard(new Negative_Energy_Bomb());
		BaseMod.addCard(new Blood_For_Blood());
		BaseMod.addCard(new Summon_Death_Knight());
		BaseMod.addCard(new Blood_Wake());
		BaseMod.addCard(new Life_Tap());
		BaseMod.addCard(new Wall_Of_Bones_Proof_Of_Concept());
		BaseMod.addCard(new Life_Drain());
		BaseMod.addCard(new Negative_Energy_Burst());
		BaseMod.addCard(new Negative_Energy_Sphere());
		BaseMod.addCard(new Vampiric_Strike());
		BaseMod.addCard(new Hell_Flame());
		BaseMod.addCard(new Bone_Shift());
		BaseMod.addCard(new Spectral_Walk());
		BaseMod.addCard(new Blood_Shield());
		BaseMod.addCard(new Fear());
		BaseMod.addCard(new Shackles_Of_Pain());
		BaseMod.addCard(new Blood_Is_Power());
		BaseMod.addCard(new Bone_Prison());
		BaseMod.addCard(new Blood_Whip());
		BaseMod.addCard(new Summon_Lich());
		BaseMod.addCard(new Summon_Vampire_Lady());
		BaseMod.addCard(new Summon_Undead());
		BaseMod.addCard(new Summon_Undead_Army());
		BaseMod.addCard(new Finger_Of_Death());
		BaseMod.addCard(new Detonate_Bones());
		BaseMod.addCard(new Spectral_Armor());
		BaseMod.addCard(new Grasp_Heart());
		BaseMod.addCard(new Consume_Illness());
		BaseMod.addCard(new Negative_Energy_Mist());
		BaseMod.addCard(new Blood_Curse());
		BaseMod.addCard(new Negative_Energy_Shield());
		BaseMod.addCard(new Uncharnel());
		BaseMod.addCard(new Slay_Living());
		BaseMod.addCard(new Blood_Magic());
		BaseMod.addCard(new Blood_Magic());
		BaseMod.addCard(new Plague_Bolt());	
		BaseMod.addCard(new Violent_Necrosis());
		BaseMod.addCard(new Corrupt_Power());
		BaseMod.addCard(new Epidemic());		
		BaseMod.addCard(new Reap_The_Weak());
		
		logger.info("added tested cards");
		
		BaseMod.addCard(new Strike_From_Beyond());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Siphon_Strength());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Toxic_Smoke_Screen());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Pandemic());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Acid_Javelin());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Necrotic_Claws());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Stop_Heart());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Skeleton_Dragon());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Cloud_Kill());
		logger.info("added Test Case");
		
		BaseMod.addCard(new Requiem());
		logger.info("added Test Case");

				
		/****/
		/**	BaseMod.addCard(new ());
			logger.info("added Test Case");
		**/
		
		
		logger.info("done editting cards");
		
	}
	
	@Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
		
        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/NecroMod-RelicStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/NecroMod-CardStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		
		logger.info("done editting strings");
	}
	
	public void receiveEditKeywords() {
		String[] NegativeLevel = {"negativelevel", "negativelevels"};
        
		BaseMod.addKeyword(NegativeLevel, "Apply -Strength per level. Apply Frail per level.");
		
		String[] Bones = {"bone", "bones", "Bones"};
        
		BaseMod.addKeyword(Bones, "Buffs the next Bone-type Attack.");
		
		String[] Blood = {"blood", "bloods", "Blood"};
        
		BaseMod.addKeyword(Blood, "Prevents the next card HP cost. Max of 3 Stacks.");
		
		String[] Hellfire = {"hellfire", "hellfires", "Hellfire"};
        
		BaseMod.addKeyword(Hellfire, "Deal 10 damage per turn. Removed when the afflicted enemy gains a buff.");
		
		String[] ShacklesOfPain = {"shackles", "shackle", "shackles of pain", "shackle of pain", "Shackles of Pain"};
        
		BaseMod.addKeyword(ShacklesOfPain, "Apply Shackles of Pain. All Damage you take is also applied to the afflicted enemy.");
		
		String[] DeathKnight = {"deathknight", "deathknights", "death knight", "death knights", "Death Knight"};
        
		BaseMod.addKeyword(DeathKnight, "Summon. Deals 5 damage to a random enemy each turn. Buffs the summoner with 5 block.");
		
		String[] Spectral = {"spectral", "spectrals", "Spectral"};
        
		BaseMod.addKeyword(Spectral, "Take 50% less damage this turn.");
		
		String[] Zombie = {"zombie", "zombies"};
        
		BaseMod.addKeyword(Zombie, "Summon. Deals 3 damage to a random enemy each turn. Pack Tactics : Every 3 Zombies will stage a combined attack for 9 damage.");
		
		String[] CrushedHeart = {"crushedheart", "crushedhearts"};
        
		BaseMod.addKeyword(CrushedHeart, "Deals 3 damage each turn. Increases by 1 stack each turn.");
		
		String[] BloodMagic = {"bloodmagic", "bloodmagics"};
        
		BaseMod.addKeyword(BloodMagic, "If you do not have enough Energy to play a card: You can play it for 3 HP times the cost instead.");
		
		String[] Corrupt = {"corrupt", "corrupts"};
        
		BaseMod.addKeyword(Corrupt, "Turn a buff of the target into a debuff.");
		
		String[] SkeletonDragon = {"corrupt", "corrupts"};
        
		BaseMod.addKeyword(SkeletonDragon, "Deals damage to ALL enemies. Applies 1 Hellfire to those not already burning.");
		
		
	}
	/**
	@Override
	public void receiveSetUnlocks() {
		UnlockTracker.addCard("Brainstorm");
		UnlockTracker.addCard("VoidRay");
		UnlockTracker.addCard("Creativity");
		// seeker unlock 1
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Brainstorm", "VoidRay", "Creativity"
				), TheSeekerEnum.THE_SEEKER, 1);
		
		// seeker unlock 2
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Shimmer", "EtherBolt", "ChaosForm"
				), TheSeekerEnum.THE_SEEKER, 2);
		UnlockTracker.addCard("Shimmer");
		UnlockTracker.addCard("VoidRay");
		UnlockTracker.addCard("Creativity");
		
		// seeker unlock 3 (Vacuum tmp in place of Feedback)
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Transference", /*"Feedback",*/
	/**
				"Vacuum", "Flicker"
				), TheSeekerEnum.THE_SEEKER, 3);
		UnlockTracker.addCard("Transference");
		/*UnlockTracker.addCard("Feedback");*/
	/**
		UnlockTracker.addCard("Vacuum");
		UnlockTracker.addCard("Flicker");
		
		// seeker unlock 4
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Zenith", "Singularity", "Flow"
				), TheSeekerEnum.THE_SEEKER, 4);
		UnlockTracker.addCard("Zenith");
		UnlockTracker.addCard("Singularity");
		UnlockTracker.addCard("Flow");
	}
	**/
	/**
	// used by fruitymod.patches.com.megacrit.cards.AbstractCard.CanUsedDazed
	public static boolean hasRelicCustom(String relicID, AbstractCard card) {
		System.out.println("I was checked!");
		// if it's checking for relicID.equals("Medical Kit") then we know we're in the block where
		// we are saying if we can use a status card so also check if we have enigma and the card is Dazed
		if (relicID.equals("Medical Kit") && AbstractDungeon.player.hasPower("EnigmaPower") && card.cardID.equals("Dazed")) {
			return true;
		} else {
			// otherwise leave normal behavior intact
			return AbstractDungeon.player.hasRelic(relicID);
		}
	}
	
	
	@Override
	public void receiveCardUsed(AbstractCard c) {
		AbstractPlayer p = AbstractDungeon.player;
		if (p.hasPower("EnigmaPower") && c.cardID.equals("Dazed")) {
			int stacks = p.getPower("EnigmaPower").amount;
			AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, stacks));
			AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(null, 
					DamageInfo.createDamageMatrix(stacks, true),
					DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
			c.exhaustOnUseOnce = true;
		}
	}
	**/
}
