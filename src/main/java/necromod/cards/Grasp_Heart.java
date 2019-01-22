package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.GraspHeartPower;

public class Grasp_Heart extends AbstractNecromancerCards {
	public static final String ID = "Grasp_Heart";
	public static final String NAME = "Grasp Heart";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 20;
	public static final String DESCRIPTION = "Deal !D! damage. Apply 1 CrushedHeart . Exhaust.";
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int POOL = 1;
	public final int AMOUNT = 1;

	
	public Grasp_Heart() {
		super (ID, NAME, NecroMod.makePath(NecroMod.GRASP_HEART), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.exhaust = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GraspHeartPower(m, p, AMOUNT), AMOUNT));
        
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Grasp_Heart();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
