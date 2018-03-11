package necromod.actions.common;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.powers.*;

public class DoubleDebuffAction extends AbstractGameAction {
	
	public DoubleDebuffAction(final AbstractCreature target, final AbstractCreature source) {
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
		
    	
		for(String cd : currentDebuffs) {
			
			switch(cd){
			case "Poison" : 
				amount = this.target.getPower("Poison").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source, amount), amount, AbstractGameAction.AttackEffect.POISON));
				break;
				
			case "Weakened" :
				amount = this.target.getPower("Weakened").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new WeakPower(this.target, amount, false), amount));
				break;
				
			case "Frail" :
				amount = this.target.getPower("Frail").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
				break;
				
			case "Vulnerable" :
				amount = this.target.getPower("Vulnerable").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, amount, false), amount));
				break;
				
			case "Strength" :
				amount = -1*(this.target.getPower("Strength").amount);
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, -amount), -amount));
				break;
				
			case "Dexterity" :
				amount = -1*(this.target.getPower("Dexterity").amount);
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new DexterityPower(this.target, -amount), -amount));
				break;
				
			case "HellFlamePower" :
				amount = this.target.getPower("HellFlamePower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new HellFlamePower(this.target, this.source, amount), amount));
				break;
				
			case "GraspHeartPower" :
				amount = this.target.getPower("GraspHeartPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new GraspHeartPower(this.target, this.source, amount), amount));
				break;
				
			case "NegativeLevelsPower" : 
				amount = this.target.getPower("NegativeLevelsPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new NegativeLevelsPower(this.target, this.source, amount), amount));
				break;
			
			case "OfPain" :
				amount = this.target.getPower("OfPain").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new OfPain(this.target, amount), amount));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source, new Shackles(this.source, this.target, amount), amount));
				break;
				
			}
		}
    	    		
		this.isDone = true;
    }
}
