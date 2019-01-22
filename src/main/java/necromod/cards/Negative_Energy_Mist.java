package necromod.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

import necromod.NecroMod;
import necromod.actions.common.NegativeLevelAction;
import necromod.patches.AbstractCardEnum;
import necromod.powers.NegativeLevelsPower;

public class Negative_Energy_Mist extends AbstractNecromancerCards {
	public static final String ID = "Negative_Energy_Mist";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int POOL = 1;
	
	public static final String UPGRADE_DESCRIPTION = "Apply 2 NegativeLevels to ALL enemies.";
	
	public Negative_Energy_Mist() {
		super(ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_MIST), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY, POOL);
		this.baseMagicNumber = this.magicNumber = 2;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5f));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(mo, p, this.baseMagicNumber));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new NegativeLevelsPower(mo, p, this.baseMagicNumber), this.baseMagicNumber));
        }
		
	}
	
	public AbstractCard makeCopy() {
		return new Negative_Energy_Mist();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.exhaust = false;
			this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
		}
	}

}
