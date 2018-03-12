package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Acid_Javelin extends AbstractNecromancerCards {
	public static final String ID = "Acid_Javelin";
	public static final String NAME = "Acid Javelin";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	public static final String DESCRIPTION = "Deal !D! damage. Apply !M! Poison. Apply 1 Weak, 1 Vulnerable.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Acid_Javelin() {
		super (ID, NAME, NecroMod.makePath(NecroMod.ACID_JAVELIN), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Acid_Javelin();
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
