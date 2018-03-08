package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.common.TrulyRandomCardFromListAction;

public class Summon_Undead extends CustomCard {
	
	public static final String ID = "Summon_Undead";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int POOL = 1;
	
	public Summon_Undead() {
		super(ID, NAME, NecroMod.makePath(NecroMod.SUMMON_UNDEAD), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;
		
	}
		
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		final AbstractCard c = TrulyRandomCardFromListAction.returnTrulyRandomCard(TrulyRandomCardFromListAction.SummonCardPool);
		c.setCostForTurn(0);
		c.use(p, m);			
	}
	
	public AbstractCard makeCopy() {
		return new Summon_Undead();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}
	

}
