package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Requiem extends AbstractNecromancerCards {
	public static final String ID = "Requiem";
	public static final String NAME = "Requiem";
	private static final int COST = 1;
	public static final String DESCRIPTION = "Deals damage equal to 50% of missing health. Affects ALL enemies.";
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Requiem() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BONE_SPIKES), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY, POOL);

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int percentDamage = (int) ((m.maxHealth-m.currentHealth)*0.5);
			
		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.25f));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(percentDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));

        	
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
