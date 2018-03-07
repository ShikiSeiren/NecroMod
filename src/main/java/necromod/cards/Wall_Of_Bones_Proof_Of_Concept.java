package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.WallOfBonesPoCPower;


public class Wall_Of_Bones_Proof_Of_Concept extends CustomCard {
	
	public static final String ID = "WallConcept";
	public static final	String NAME = "Wall Concept";
	public static final	String DESCRIPTION = "Add WallOfBones.";
	private static final int COST = 1;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int POOL = 0;	
	
	public Wall_Of_Bones_Proof_Of_Concept() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BONE_WALL), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF, POOL);
		this.baseBlock = BLOCK_AMT;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WallOfBonesPoCPower(p, 1), 1));
	}
	
	public AbstractCard makeCopy() {
		return new Wall_Of_Bones_Proof_Of_Concept();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}


}
