package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;

public class Bones_to_Steel extends AbstractNecromancerCards {
	public static final String ID = "Bones_to_Steel";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Bones_to_Steel() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BONE_SHIFT), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower(BonesPower.POWER_ID)) {
			int i = p.getPower(BonesPower.POWER_ID).amount;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, i), i));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Bones"));
		}
			
	}
	
	public AbstractCard makeCopy() {
		return new Bones_to_Steel();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}
}
