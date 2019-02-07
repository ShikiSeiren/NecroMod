package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.SkeletonDragonPower;

public class Skeleton_Dragon extends AbstractNecromancerCards {
	public static final String ID = "Skeleton_Dragon";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 10;
	private static final int POOL = 1;	
	
	public int DMG_AMT = 5;
	
	public Skeleton_Dragon() {
		super(ID, NAME, NecroMod.makePath(NecroMod.SKELETON_DRAGON), COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, POOL);
		this.cost = COST;
		this.baseDamage = this.damage = DMG_AMT;
				
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SkeletonDragonPower(p, 1, this.upgraded, this.damage), 1));
			
			if(p.hasPower("Bones")) {
				if(p.getPower("Bones").amount > (this.cost - this.costForTurn)) {
				AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "Bones", (this.cost - this.costForTurn)));
				}
				else {
					AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "Bones"));
				}
			}
			
			
	}
	
	@Override
	public void update() {
		super.update();
		
		if(this.costForTurn == 0) {
			
		}
		else {
		if(AbstractDungeon.player != null) {
			if(AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
				if(AbstractDungeon.getCurrRoom() == null || AbstractDungeon.getCurrRoom() instanceof MonsterRoom || AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite || AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
					if(AbstractDungeon.player.hasPower("Bones") && (this.cost - AbstractDungeon.player.getPower("Bones").amount) >= 0) {
						this.costForTurn = this.cost - AbstractDungeon.player.getPower("Bones").amount;
					}
					else if(AbstractDungeon.player.hasPower("Bones") && (this.cost - AbstractDungeon.player.getPower("Bones").amount) < 0) {
						this.costForTurn = 0;
					}	
				}
			}
		}
	}
	}
	public AbstractCard makeCopy() {
		return new Skeleton_Dragon();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(2);
		}
	}

}
