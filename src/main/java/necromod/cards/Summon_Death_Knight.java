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
import necromod.powers.DeathKnightPower;

public class Summon_Death_Knight extends AbstractNecromancerCards{
	public static final String ID = "Summon_Death_Knight";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int POOL = 1;	
	
	public Summon_Death_Knight() {
		super(ID, NAME, NecroMod.makePath(NecroMod.SUMMON_DEATH_KNIGHT), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, POOL);
		
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DeathKnightPower(p, 1), 1));
			
	}
	
	public AbstractCard makeCopy() {
		return new Summon_Death_Knight();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(2);
		}
	}

}
