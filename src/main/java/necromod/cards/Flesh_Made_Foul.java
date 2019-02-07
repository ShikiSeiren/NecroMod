package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.NegativeLevelsPower;
import necromod.actions.common.BloodAction;
import necromod.actions.common.NegativeLevelAction;
import necromod.actions.unique.FoDAction;

public class Flesh_Made_Foul extends AbstractNecromancerCards {
	
	public static final String ID = "Flesh_Made_Foul";
	public static final String NAME = "Flesh Made Foul";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 14;
	public static final String DESCRIPTION = "Lose 2 HP. Deal !D! damage. NL Apply 1 NegativeLevel. NL If the target dies: Gain 1 Zombie";
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Flesh_Made_Foul() {
		super (ID, NAME, NecroMod.makePath(NecroMod.FINGER_OF_DEATH), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, (2)));
		AbstractDungeon.actionManager.addToBottom(new FoDAction(m, p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new NegativeLevelAction(m, p, this.AMOUNT));
	    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new NegativeLevelsPower(m, p, 1), 1));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Flesh_Made_Foul();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
