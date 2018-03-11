package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.common.BloodAction;

public class Blood_Is_Power extends AbstractNecromancerCards {
	
	public static final String ID = "Blood_Is_Power";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private final int STRENGTH_AMT = 2;
	private static final int POOL = 1;
	private String UPGRADE_DESCRIPTION = "Lose 2 HP. Gain 2 Strength.";
	
	public Blood_Is_Power() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BLOOD_IS_POWER), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, (2)));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.STRENGTH_AMT), this.STRENGTH_AMT));
	}
	
	public AbstractCard makeCopy() {
		return new Blood_Is_Power();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
	        this.exhaust = false;
	        this.rawDescription = this.UPGRADE_DESCRIPTION;
	        this.initializeDescription();
		}
	}

}
