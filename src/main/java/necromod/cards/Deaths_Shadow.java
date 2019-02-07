package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.DeathsShadowPower;
import necromod.powers.TimerPower;

public class Deaths_Shadow extends AbstractNecromancerCards {
	
	public static final String ID = "Deaths_Shadow";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 25;
	private static final int UPGRADE_PLUS_DMG = 10;
	private static final int POOL = 1;
	public final int AMOUNT = 3;
	
	
	public Deaths_Shadow() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_JAVELIN), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = AMOUNT;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TimerPower(m, this.AMOUNT, DeathsShadowPower.POWER_ID)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DeathsShadowPower(m, this.damage), this.damage));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Deaths_Shadow();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(1);
        }
	
    }

}
