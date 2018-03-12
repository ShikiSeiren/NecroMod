package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Strike_From_Beyond extends AbstractNecromancerCards {
	public static final String ID = "Strike_from_Beyond";
	public static final String NAME = "Strike from Beyond";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	public static final String DESCRIPTION = "Deal !D! damage times the number of exhausted cards.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Strike_From_Beyond() {
		super (ID, NAME, NecroMod.makePath(NecroMod.STRIKE_FROM_BEYOND), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			int ghost = p.exhaustPile.size()*this.damage;
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, ghost, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Strike_From_Beyond();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
