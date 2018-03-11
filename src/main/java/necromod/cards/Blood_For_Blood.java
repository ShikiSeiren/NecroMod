package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import necromod.cards.AbstractNecromancerCards;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BloodForBloodPower;

public class Blood_For_Blood extends AbstractNecromancerCards{
	public static final String ID = "Blood_For_Blood";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;	
	
	public Blood_For_Blood() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BLOOD_FOR_BLOOD), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodForBloodPower(p, 1), 1));
			
	}
	
	public AbstractCard makeCopy() {
		return new Blood_For_Blood();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}

}
