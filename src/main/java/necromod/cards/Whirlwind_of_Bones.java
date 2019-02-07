package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import necromod.NecroMod;
import necromod.actions.common.NegativeLevelAction;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;
import necromod.powers.NegativeLevelsPower;

public class Whirlwind_of_Bones extends AbstractNecromancerCards {
	
	public static final String ID = "Whirlwind_of_Bones";
	public static final String NAME = "Whirlwind of Bones";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 2;
	public static final String DESCRIPTION = "Shatter all : Deal !D! damage X times where X is the amount of Bones shattered. Add a NegativeLevel for every 3 hits.";
	private static final int UPGRADE_PLUS_DMG = 1;
	private static final int POOL = 0;
	public final int AMOUNT = 1;
	
	private int counter = 0;
	
	public static final String[] EXTENDED_DESCRIPTION = new String[] {
			"I have no Bones to use!"
	};
	
	public Whirlwind_of_Bones() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BONE_SPIKES), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.isMultiDamage = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Bones")) {
			for(int i = 0; i < p.getPower(BonesPower.POWER_ID).amount; i++) {

				AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
		        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.0f));
		        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
       
		        if(p.getPower(BonesPower.POWER_ID).amount <1) {
		        	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, BonesPower.POWER_ID));
		        }
		        else {
		        	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BonesPower.POWER_ID, 1));
		        }    
		 
				this.counter += 1;
				
				if((this.counter % 3)==0) {
					for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
						AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(mo, p, this.counter/3));
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new NegativeLevelsPower(mo, p, this.counter/3), this.counter/3));
						
					}	
				}
			}
			
			
			
		}
		else {
			AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, Whirlwind_of_Bones.EXTENDED_DESCRIPTION[0], true));
		}
		
		
		this.counter = 0;
		
		
/**			
		if(p.hasPower("Bones")) {
    		this.damage +=4;
    		AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "Bones", 1));
    	}

        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

**/        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Whirlwind_of_Bones();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
