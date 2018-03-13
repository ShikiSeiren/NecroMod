package necromod.cards;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.unique.DetonateBonesAction;

public class Detonate_Bones extends AbstractNecromancerCards {
	private static final String ID = "Detonate_Bones";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = new String[] {
			"You don't have any stacks of bones."
	};
	
	private static final int COST = 2;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_DMG_AMT = 1;
	private static final int POOL = 1;
	
	public Detonate_Bones() {
		super (ID, NAME, NecroMod.makePath(NecroMod.DETONATE_BONES), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
	
		this.baseDamage = this.damage = ATTACK_DMG;
		
		
	}
	
	@Override
	public void use (AbstractPlayer p, AbstractMonster m) {
		if (p.getPower("Bones") == null) {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0f, Detonate_Bones.EXTENDED_DESCRIPTION[0], true));
            return;
        }
		else {
		this.baseMagicNumber = p.getPower("Bones").amount;
		
		this.baseDamage = this.damage *= baseMagicNumber;
		
		this.calculateCardDamage(m);
		
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), 0));
		AbstractDungeon.actionManager.addToBottom(new DetonateBonesAction(p, p));
		
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Detonate_Bones();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_DMG_AMT);
		}
	}

}
