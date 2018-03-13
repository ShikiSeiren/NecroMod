package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BloodMagicPower;

public class Blood_Magic extends AbstractNecromancerCards{
	public static final String ID = "Blood_Magic";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static String NAME = "Blood Magic";
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 5;
	private static final int POOL = 1;	
	
	public Blood_Magic() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BLOOD_CURSE), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, POOL);
		
		//this.baseDamage = this.damage = 3;
		this.baseMagicNumber = this.magicNumber = 5;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodMagicPower(p, 1), 1));
	
			
	}
	
	public AbstractCard makeCopy() {
		return new Blood_Magic();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.baseMagicNumber = this.magicNumber = 3;
		}
	}
	

}
