package necromod.cards;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.utility.*;


import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.actions.common.BloodAction;

public class Blood_Wake extends CustomCard {
	
	public static final String ID = "Blood_Wake";
	public static final String NAME = "Blood Wake";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	public static final String DESCRIPTION = "Lose 3 HP. Deal !D! damage to ALL enemies.";
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int POOL = 1;
	public final int AMOUNT = 1;
	
	public Blood_Wake() {
		super (ID, NAME, NecroMod.makePath(NecroMod.BLOOD_WAKE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.isMultiDamage = true;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1f));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        
        AbstractDungeon.actionManager.addToBottom(new BloodAction(p, p, 3));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Blood_Wake();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }

}
