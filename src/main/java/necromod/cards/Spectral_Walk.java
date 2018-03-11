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
import necromod.powers.SpectralPower;

public class Spectral_Walk extends AbstractNecromancerCards {
	public static final String ID = "Spectral_Walk";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DRAW_AMT = 1;
	private static final int POOL = 1;
	
	public Spectral_Walk() {
		super(ID, NAME, NecroMod.makePath(NecroMod.SPECTRAL_WALK), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		this.baseMagicNumber = this.magicNumber = DRAW_AMT;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SpectralPower(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Spectral_Walk();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}

}
