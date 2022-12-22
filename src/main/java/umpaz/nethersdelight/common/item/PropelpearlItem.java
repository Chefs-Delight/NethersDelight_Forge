package umpaz.nethersdelight.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PropelpearlItem extends Item {

	public PropelpearlItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
	super.finishUsingItem(stack, worldIn, entity);
		  int j = 1 + worldIn.random.nextInt(2);
		if (entity instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			
			if (!worldIn.isClientSide) {
				if (!entity.fireImmune()) {
					entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 1);
					entity.setSecondsOnFire(4);
				}
			}
		}

		return stack;
	}
}