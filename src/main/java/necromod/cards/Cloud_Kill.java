package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;


import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Cloud_Kill extends AbstractNecromancerCards {
	public static final String ID = "Cloudkill";
	public static final String NAME = "Cloudkill";
	private static final int COST = 1;
	public static final String DESCRIPTION = "Apply !M! Poison to all enemies.";
	private static final int POOL = 1;
	public final int AMOUNT = 7;
	
	public Cloud_Kill() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BONE_SPIKES), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY, POOL);
		
		this.baseMagicNumber = this.magicNumber = AMOUNT;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		 for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
		 }
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Cloud_Kill();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
	
    }

}
