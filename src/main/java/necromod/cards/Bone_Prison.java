package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.vfx.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;
import necromod.powers.BonePrisonPower;

public class Bone_Prison extends AbstractNecromancerCards {
	public static final String ID = "Bone_Prison";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int POOL = 1;
	
	
	public Bone_Prison() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BONE_PRISON), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.exhaust = true;
	
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if (m != null && (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND)) {
			if(m.hasPower("Artifact")){
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BonesPower(p, 2), 2));
				if(m.getPower("Artifact").amount == 1) {
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, "Artifact"));
				}
				else {
					AbstractDungeon.actionManager.addToTop(new ReducePowerAction(m, m, "Artifact", 1));
				}
				
				
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BonePrisonPower(m), 1));
			
                    }
        else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0f, OpeningAction.TEXT[0], true));
        }
		
	}
	
	public AbstractCard makeCopy() {
		return new Bone_Prison();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}

}
