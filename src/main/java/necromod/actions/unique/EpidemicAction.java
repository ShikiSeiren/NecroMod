package necromod.actions.unique;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import necromod.powers.GraspHeartPower;
import necromod.powers.HellFlamePower;
import necromod.powers.NegativeLevelsPower;
import necromod.powers.OfPain;
import necromod.powers.Shackles;

public class EpidemicAction extends AbstractGameAction{
	
	public EpidemicAction(final AbstractCreature target, final AbstractCreature source) {
        this.target = target;
        this.source = source;
       
    }
    
    @Override
    public void update() {
    	ArrayList<String> Debuff = new ArrayList<String>();
		Debuff.add("Poison");
		Debuff.add("Weakened");
		Debuff.add("Frail");
		Debuff.add("Vulnerable");
		Debuff.add("HellFlamePower");
		Debuff.add("GraspHeartPower");
		Debuff.add("NegativeLevelsPower");
		Debuff.add("OfPain");
		
		int amount;
		
		
		if(this.target.hasPower("Strength") && (this.target.getPower("Strength").amount < 0)){
			Debuff.add("Strength");
		}
			
		if(this.target.hasPower("Dexterity") && (this.target.getPower("Dexterity").amount < 0)){
				Debuff.add("Dexterity");
		}
		
		ArrayList<String> currentDebuffs = new ArrayList<String>();
		
		for(String d : Debuff) {
			if(this.target.getPower(d) != null) {
				currentDebuffs.add(d);
			}
			
		}
		
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(!mo.equals(this.target)) {
				for(String cd : currentDebuffs) {
			
					switch(cd){
					case "Poison" : 
						amount = this.target.getPower("Poison").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new PoisonPower(mo, this.source, amount), amount, AbstractGameAction.AttackEffect.POISON));
						break;
				
					case "Weakened" :
						amount = this.target.getPower("Weakened").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new WeakPower(mo, amount, false), amount));
						break;
				
					case "Frail" :
					amount = this.target.getPower("Frail").amount;
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new FrailPower(mo, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
					break;
				
					case "Vulnerable" :
						amount = this.target.getPower("Vulnerable").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new VulnerablePower(mo, amount, false), amount));
						break;
				
					case "Strength" :
						amount = -1*(this.target.getPower("Strength").amount);
						if(amount > 0) {
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new StrengthPower(mo, -amount), -amount));
						}						
						break;
				
					case "Dexterity" :
						amount = -1*(this.target.getPower("Dexterity").amount);
						if(amount > 0) {
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new DexterityPower(mo, -amount), -amount));
						}		
						break;
				
					case "HellFlamePower" :
						amount = this.target.getPower("HellFlamePower").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new HellFlamePower(mo, this.source, amount), amount));
						break;
				
					case "GraspHeartPower" :
						amount = this.target.getPower("GraspHeartPower").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new GraspHeartPower(mo, this.source, amount), amount));
						break;
				
					case "NegativeLevelsPower" : 
						amount = this.target.getPower("NegativeLevelsPower").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new NegativeLevelsPower(mo, this.source, amount), amount));
						break;
			
					case "OfPain" :
						amount = this.target.getPower("OfPain").amount;
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.source, new OfPain(mo, amount), amount));
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source, new Shackles(this.source, this.source, amount), amount));
						break;
				
					}
				}
			}
		}
		
    	    		
		this.isDone = true;
    }

}
