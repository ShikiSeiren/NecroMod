package necromod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;
import necromod.powers.BonesPower;

public class Bone_Armor extends AbstractNecromancerCards {
	
	public static final String ID = "Bone_Armor";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final	String NAME = cardStrings.NAME;
	public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 6;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int POOL = 1;
	
	public Bone_Armor() {
		super(ID, NAME, NecroMod.makePath(NecroMod.BONE_ARMOR), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, POOL);
		
		this.baseBlock = BLOCK_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BonesPower(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
	}
	
	public AbstractCard makeCopy() {
		return new Bone_Armor();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}	

}
