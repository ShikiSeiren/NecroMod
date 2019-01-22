package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.NegativeShieldPower;

public class Negative_Energy_Shield extends AbstractNecromancerCards {
	public static final String ID = "Negative_Energy_Shield";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int POOL = 1;	
	
	public Negative_Energy_Shield() {
		super(ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_SHIELD), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
		this.baseBlock = BLOCK_AMT;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NegativeShieldPower(p, 1), 1));
	}
	
	public AbstractCard makeCopy() {
		return new Negative_Energy_Shield();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}

}
