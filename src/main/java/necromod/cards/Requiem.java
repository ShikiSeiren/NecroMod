package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Requiem extends AbstractNecromancerCards {
	public static final String ID = "Requiem";
	public static final String NAME = "Requiem";
	private static final int COST = 3;
	public static final String DESCRIPTION = "Deals damage equal to 50% of missing health. Affects ALL enemies.";
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Requiem() {
		super (ID, NAME, NecroMod.makePath(NecroMod.REQUIEM), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY, POOL);

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			int percentDamage = (int) ((mo.maxHealth-mo.currentHealth)*0.5);
		
			if(percentDamage != 0) {
				this.damage = percentDamage;
				AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
				
			}

		}
		
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Requiem();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
	
    }

}
