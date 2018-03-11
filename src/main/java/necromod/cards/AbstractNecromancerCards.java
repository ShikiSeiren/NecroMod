package necromod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.utility.*;

public abstract class AbstractNecromancerCards extends CustomCard{
	
	//private final AbstractPlayer p = AbstractDungeon.player;
	
	public int BloodCost;
	
	private boolean damageHappened;
	private boolean isActive;
	
	private final String savedDescription;
	
	public AbstractNecromancerCards(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
			int cardPool) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target, cardPool);
		
		this.damageHappened = false;
		this.isActive = false;
		this.savedDescription = rawDescription;
		this.BloodCost = this.cost*3;
	}

	@Override
	public void update() {
		super.update();
		
		if(AbstractDungeon.player.hasPower("BloodMagicPower")) {
			if((this.cost > EnergyPanel.totalCount || this.costForTurn > EnergyPanel.totalCount) && this.isActive == false) {
				this.costForTurn = 0;
				this.rawDescription = rawDescription + " NL Lose " + this.BloodCost + " HP";
				this.initializeDescription();
				this.isActive = true;
				
			}	
			
			if(this.cost > EnergyPanel.totalCount && this.isActive == true) {
				
			}
			
			if (this.cost <= EnergyPanel.totalCount) {
				this.isActive = false;
				this.costForTurn = this.cost;
				this.rawDescription = this.savedDescription;
				this.initializeDescription();
			}
			
		}
		
	}
	
	@Override
	public void onPlayCard(AbstractCard c, AbstractMonster m) {
		if(c == this) {
			if(AbstractDungeon.player.hasPower("BloodMagicPower")) {
				if((c.cost > EnergyPanel.totalCount) && this.isActive == true) {
					AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, (c.cost*3), DamageInfo.DamageType.HP_LOSS));
					AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
				}	
			
			}
		
		}
	}

	
}
