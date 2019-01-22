package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Thousand_Bone_Knives extends AbstractNecromancerCards{
	
	private static final String ID = "Thousand_Bone_Knives";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int TIMES = 3;
	private static final int UPGRADE_TIMES_AMT = 1;
	private static final int POOL = 1;
	
	public Thousand_Bone_Knives() {
		super (ID, NAME, NecroMod.makePath(NecroMod.THOUSAND_BONE_KNIVES), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = UPGRADE_TIMES_AMT;
		this.magicNumber = this.baseMagicNumber = TIMES;

	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if(p.hasPower("Bones")) {
    		this.magicNumber += 1;
    		AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "Bones", 1));
    	}
        for (int i = 0; i < this.magicNumber; i++) {
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }
	/**
	@Override
    public void triggerOnEndOfPlayerTurn() {
    	AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
	**/
	@Override
    public AbstractCard makeCopy() {
        return new Thousand_Bone_Knives();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_TIMES_AMT);
        }
    }

}
