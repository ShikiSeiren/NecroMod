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
import necromod.powers.ZombiePower;
import necromod.actions.unique.UncharnelAction;

public class Uncharnel extends AbstractNecromancerCards {
	
	public static final String ID = "Uncharnel";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int POOL = 1;
	
	public Uncharnel() {
		super(ID, NAME, NecroMod.makePath(NecroMod.UNCHARNEL), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);

		this.exhaust = true;
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new UncharnelAction(false));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ZombiePower(p, this.magicNumber), this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Uncharnel();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}	

}
