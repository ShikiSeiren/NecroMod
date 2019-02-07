package necromod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.BaseModCardTags;

import necromod.NecroMod;
import necromod.patches.AbstractCardEnum;

public class Strike_White extends AbstractNecromancerCards{
	public static final String ID = "Strike_W";
	public static final String NAME = "Strike";
	public static final String DESCRIPTION = "Deal !D! damage.";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int POOL = 0;

	public Strike_White() {
		super(ID, NAME, NecroMod.makePath(NecroMod.STRIKE_WHITE), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.WHITE, AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.ENEMY, POOL);

		this.baseDamage = ATTACK_DMG;
		
		this.tags.add(BaseModCardTags.BASIC_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
		if (com.megacrit.cardcrawl.core.Settings.isDebug) {
			if (com.megacrit.cardcrawl.core.Settings.isInfo) {
				this.multiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
				for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
					this.multiDamage[i] = 150;
				}
				AbstractDungeon.actionManager.addToBottom(
						new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage,
								this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			} else {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
						new DamageInfo(p, 150, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	public AbstractCard makeCopy() {
		return new Strike_White();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}
