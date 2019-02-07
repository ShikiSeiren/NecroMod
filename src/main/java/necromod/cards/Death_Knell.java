package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.actions.unique.DeathKnellAction;
import necromod.patches.AbstractCardEnum;

public class Death_Knell extends AbstractNecromancerCards {
	
	public static final String ID = "Death_Knell";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public int HPThreshhold;
	
	public Death_Knell() {
		super (ID, NAME, NecroMod.makePath(NecroMod.ACID_JAVELIN), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 8;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		this.HPThreshhold = ((int) ((m.maxHealth*0.5)));
		if(m.currentHealth <= HPThreshhold) {
			AbstractDungeon.actionManager.addToBottom(new DeathKnellAction(m, p, new DamageInfo(p, this.damage+4, this.damageTypeForTurn), this.magicNumber, this.magicNumber));
		}
		else AbstractDungeon.actionManager.addToBottom(new DeathKnellAction(m, p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, this.magicNumber));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Death_Knell();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(2);
        }
	
    }

}
