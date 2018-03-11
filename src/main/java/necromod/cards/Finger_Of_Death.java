package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.unique.FoDAction;

public class Finger_Of_Death extends AbstractNecromancerCards {
	
	public static final String ID = "finger_Of_Death";
	public static final String NAME = "Finger of Death";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	public static final String DESCRIPTION = "Deal !D! damage. NL If the target dies: Gain 1 Zombie";
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Finger_Of_Death() {
		super (ID, NAME, NecroMod.makePath(NecroMod.FINGER_OF_DEATH), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			
		AbstractDungeon.actionManager.addToBottom(new FoDAction(m, p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Finger_Of_Death();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
