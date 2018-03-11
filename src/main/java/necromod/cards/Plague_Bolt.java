package necromod.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;


import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Plague_Bolt extends AbstractNecromancerCards {
	
	public static final String ID = "Plague_Bolt";
	public static final String NAME = "Plague Bolt";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	public static final String DESCRIPTION = "Deal !D! damage. NL Apply 3 Poison. NL Transfer 2 negative effects.";
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int POOL = 1;
	public final int AMOUNT = 3;
	
	public Plague_Bolt() {
		super (ID, NAME, NecroMod.makePath(NecroMod.PLAGUE_BOLT), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.WHITE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
		
		this.baseDamage = this.damage =  ATTACK_DMG;

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		ArrayList<String> Debuff = new ArrayList<String>();
		Debuff.add("Poison");
		Debuff.add("Weakened");
		Debuff.add("Frail");
		Debuff.add("Vulnerable");
		
		ArrayList<String> Random = new ArrayList<String>();
		
		//TODO : Make it happen 2 times. 
			
		for(String d : Debuff) {
			if (p.hasPower(d)){
				Random.add(d);
			}
		}
		if(p.hasPower("Strength") && (p.getPower("Strength").amount < 0)){
			Random.add("Strength");
		}
			
		if(p.hasPower("Dexterity") && (p.getPower("Dexterity").amount < 0)){
				Random.add("Dexterity");
		}
			
		if(!Random.isEmpty()) {
			int i = Random.size();
			int r = (int) Math.random()*i;
			int amount;
			
			String debuff = Random.get(r);
			
			switch(debuff){
			case "Poison" : 
				amount = p.getPower("Poison").amount;
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Poison"));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, amount), amount, AbstractGameAction.AttackEffect.POISON));
				break;
				
			case "Weakened" :
				amount = p.getPower("Weakened").amount;
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Weakened"));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, amount, false), amount));
				break;
				
			case "Frail" :
				amount = p.getPower("Frail").amount;
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Frail"));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
				break;
				
			case "Vulnerable" :
				amount = p.getPower("Vulnerable").amount;
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Vulnerable"));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, amount, false), amount));
				break;
				
			case "Strength" :
				amount = -1*(p.getPower("Strength").amount);
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -amount), -amount));
				break;
				
			case "Dexterity" :
				amount = -1*(p.getPower("Dexterity").amount);
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, amount), amount));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DexterityPower(m, -amount), -amount));
				break;
			}
			
							
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));	          
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.AMOUNT), this.AMOUNT, AbstractGameAction.AttackEffect.POISON));
        	
    }
	
	@Override
    public AbstractCard makeCopy() {
        return new Plague_Bolt();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }


}
