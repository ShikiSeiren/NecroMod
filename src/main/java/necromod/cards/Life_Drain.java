package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Life_Drain extends AbstractNecromancerCards {
	
	public static final String ID = "Life_Drain";
	public static final String NAME = "Life Drain";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	public static final String DESCRIPTION = "Deal !D! damage. Heal for unblocked damage dealt. Deals !M! less damage this combat.";
	private static final int UPGRADE = 1;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Life_Drain() {
		super (ID, NAME, NecroMod.makePath(NecroMod.LIFE_DRAIN), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 2;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			
        AbstractDungeon.actionManager.addToBottom(new VampireDamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this, -this.magicNumber));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Life_Drain();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseMagicNumber = this.magicNumber = UPGRADE;
        }
	
    }

}
