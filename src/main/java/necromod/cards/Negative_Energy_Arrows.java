package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.actions.unique.NegativeLevelAction;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;
import necromod.powers.NegativeLevelsPower;

public class Negative_Energy_Arrows extends CustomCard{
	
	public static final String ID = "Negative_Energy_Arrows";
	public static final String NAME = "Negative_Energy_Arrows";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int TIMES = 3;
	public static final String DESCRIPTION = "Deal !D! damage " + TIMES + " times.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 0;
	
	private final int AMOUNT = 1;
	
	public Negative_Energy_Arrows() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_ARROWS), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		
		// this.baseMagicNumber = TIMES;
		// this.magicNumber = TIMES;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.AMOUNT), -this.AMOUNT));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, (this.AMOUNT*2), false),
				(this.AMOUNT*2), true, AbstractGameAction.AttackEffect.NONE));
        //AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(this.owner, this.source, this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new NegativeLevelsPower(m, p, AMOUNT), AMOUNT));
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Negative_Energy_Arrows();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }
	
}
