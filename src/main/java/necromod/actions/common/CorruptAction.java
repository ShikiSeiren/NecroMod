package necromod.actions.common;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;

import necromod.powers.*;

public class CorruptAction extends AbstractGameAction {
	
	public CorruptAction(final AbstractCreature target, final AbstractCreature source) {
        this.target = target;
        this.source = source;
       
    }
	
	@Override
    public void update() {
    	ArrayList<String> Buff = new ArrayList<String>();
    	Buff.add("Metallicize");
    	Buff.add("Curl Up");
    	Buff.add("Frail");
    	Buff.add("Plated Armor");
    	Buff.add("Thorns");
    	Buff.add("Explosive");
    	Buff.add("Artifact");
    	Buff.add("Barricade");
		
		int amount;
		
		
		if(this.target.hasPower("Strength") && (this.target.getPower("Strength").amount > 0)){
			Buff.add("Strength");
		}
			
		if(this.target.hasPower("Dexterity") && (this.target.getPower("Dexterity").amount > 0)){
			Buff.add("Dexterity");
		}
		
		ArrayList<String> currentBuffs = new ArrayList<String>();
		
		for(String b : Buff) {
			if(this.target.getPower(b) != null) {
				currentBuffs.add(b);
			}
			
		}
		

		for(String cb : currentBuffs) {
			
			switch(cb){
			case "Metallicize" : 
				amount = this.target.getPower("Metallicize").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
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
