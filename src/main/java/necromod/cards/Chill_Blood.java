package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import com.evacipated.cardcrawl.mod.stslib.actions.common.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;
import necromod.powers.NegativeLevelsPower;

public class Chill_Blood extends AbstractNecromancerCards {
	public static final String ID = "Chill_Blood";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 7;
	private static final int COST = 1;
	private static final int POOL = 1;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	public Chill_Blood() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BONE_PRISON), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseDamage = this.damage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = 4;
		this.exhaust = true;
	
	}
	
	
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		
		if (m != null && m.hasPower(NegativeLevelsPower.POWER_ID)) {
			if(m.getPower(NegativeLevelsPower.POWER_ID).amount >= this.magicNumber) {
				
		
				if(m.hasPower("Artifact")){
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BonesPower(p, 2), 2));
					if(m.getPower("Artifact").amount == 1) {
						AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, "Artifact"));
					}
					else {
						AbstractDungeon.actionManager.addToTop(new ReducePowerAction(m, m, "Artifact", 1));
					}
				
				
				}
				else {
					//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BonePrisonPower(m), 1));
					AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(m, p ,1));
				}
            }
		}
	}
	
	public AbstractCard makeCopy() {
		return new Chill_Blood();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(-1);
		}
	}

}
