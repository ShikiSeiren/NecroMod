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
	
	public String cb;
	
	public CorruptAction(final AbstractCreature target, final AbstractCreature source, String buff) {
        this.target = target;
        this.source = source;
        this.cb = buff;
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
		/**
		ArrayList<String> currentBuffs = new ArrayList<String>();
		
		for(String b : Buff) {
			if(this.target.getPower(b) != null) {
				currentBuffs.add(b);
			}
			
		}
		
		
		for(String cb : currentBuffs) {**/
			
			switch(this.cb){
			case "Metallicize" : 
				amount = this.target.getPower("Metallicize").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
				break;
				
			case "Curl Up" :
				amount = this.target.getPower("Curl Up").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
				break;
				
			case "Frail" :
				amount = this.target.getPower("Frail").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new FrailPower(this.target, amount, false),amount, true, AbstractGameAction.AttackEffect.NONE));
				break;
				
			case "Plated Armor" :
				amount = this.target.getPower("Plated Armor").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new DexterityPower(this.target, -amount), -amount));
				break;
				
			case "Thorns" :
				amount = this.target.getPower("Thorns").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source, amount), amount, AbstractGameAction.AttackEffect.POISON));
				break;
				
			case "Explosive" :
				amount = -1*(this.target.getPower("Explosive").amount);
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new HellFlamePower(this.target, this.source, 2), 2));
				break;
				
			case "Artifact" :
				amount = this.target.getPower("Artifact").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new VulnerablePower(this.target, amount, false), amount));
				break;
				
			case "Barricade" :
				amount = this.target.getPower("Barricade").amount;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new GraspHeartPower(this.target, this.source, 1), 1));
				break;
				
			case "Strength" : 
				amount = this.target.getPower("Strength").amount;
				if(amount > 0) {
					AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new StrengthPower(this.target, -amount), -amount));
				}
				
				break;
			
			case "Dexterity" :
				amount = this.target.getPower("Dexterity").amount;
				if(amount > 0) {
					AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.cb));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new DexterityPower(this.target, -amount), -amount));
				}
				break;
				
			}
		//}
    	    		
		this.isDone = true;
    }

}
