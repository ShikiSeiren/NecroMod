package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.actions.common.BloodAction;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BloodPower;

public class Blood_Shield extends AbstractNecromancerCards {
	public static final String ID = "Blood_Shield";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 12;
	private static final int HP_LOSS_AMT = 2;
	private static final int POOL = 1;
	
	public Blood_Shield() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BLOOD_SHIELD), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
		this.baseMagicNumber = this.magicNumber = HP_LOSS_AMT;
		this.baseBlock = BLOCK_AMT;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodPower(p, 1), 1));
				
	}
	
	public AbstractCard makeCopy() {
		return new Blood_Shield();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.baseMagicNumber = this.magicNumber = 1;
		}
	}

}
