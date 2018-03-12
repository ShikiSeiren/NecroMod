package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.ToxicScreenPower;

public class Toxic_Smoke_Screen extends AbstractNecromancerCards {
	public static final String ID = "Toxic_Smoke_Screen";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 6;
	private static final int POISON_AMT = 2;
	private static final int UPGRADE_BLOCK_AMT = 2;
	private static final int POOL = 1;
	
	public Toxic_Smoke_Screen() {
		super(ID, NAME, NecroMod.makePath(NecroMod.TOXIC_SMOKE_SCREEN), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
		this.baseMagicNumber = this.magicNumber = POISON_AMT;
		this.baseBlock = BLOCK_AMT;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ToxicScreenPower(m, 1, this.magicNumber),1));
				
	}
	
	public AbstractCard makeCopy() {
		return new Toxic_Smoke_Screen();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.upgradeBlock(UPGRADE_BLOCK_AMT);
		}
	}

}
