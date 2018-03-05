package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Negative_Energy_Arrows extends CustomCard{
	
	public static final String ID = "Negative_Energy_Arrows";
	public static final String NAME = "Negative_Energy_Arrows";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int TIMES = 3;
	public static final String DESCRIPTION = "Deal !D! damage "+ TIMES + " times.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 0;
	
	public Negative_Energy_Arrows() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NEGATIVE_ENERGY_ARROWS), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = TIMES;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Negative_Energy_Arrows();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }
	
}
