package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.actions.common.*;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.GraspHeartPower;

public class Stop_Heart extends AbstractNecromancerCards {
	public static final String ID = "Stop_Heart";
	public static final String NAME = "Stop Heart";
	private static final int COST = 2;
	public static final String DESCRIPTION = "Apply 1 CrushedHeart. Apply !M! Weak and Vulnerable. Exhaust.";
	private static final int POOL = 1;
	public final int AMOUNT = 1;

	
	public Stop_Heart() {
		super (ID, NAME, NecroMod.makePath(NecroMod.STOP_HEART), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GraspHeartPower(m, p, 1), 1));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
		
        
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Stop_Heart();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
	
    }

}
