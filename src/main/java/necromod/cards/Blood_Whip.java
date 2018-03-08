package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.actions.common.BloodAction;
import necromod.patches.AbstractCardEnum;

public class Blood_Whip extends CustomCard {
	public static final String ID = "Blood_Whip";
	public static final String NAME = "Blood Whip";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 13;
	public static final String DESCRIPTION = "Lose 2 HP. NL Deal !D! damage.";
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Blood_Whip() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BLOOD_WHIP), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			
		AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, (2)));
        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Blood_Whip();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
