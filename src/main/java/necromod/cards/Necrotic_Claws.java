package necromod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Necrotic_Claws extends AbstractNecromancerCards {
	public static final String ID = "Necrotic_Claws";
	public static final String NAME = "Necrotic Claws";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	public static final String DESCRIPTION = "Deal !D! damage 2 times. Apply 2 times 2 Poison.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Necrotic_Claws() {
		super (ID, NAME, NecroMod.makePath(NecroMod.NECROTIC_CLAWS), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
			
        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, 2), 2, AbstractGameAction.AttackEffect.POISON));

        	AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, 2), 2, AbstractGameAction.AttackEffect.POISON));

    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Necrotic_Claws();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
