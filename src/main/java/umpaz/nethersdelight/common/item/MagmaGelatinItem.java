package umpaz.nethersdelight.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class MagmaGelatinItem extends ConsumableItem {
	public MagmaGelatinItem(Properties properties) {
		super(properties);
	}

	@Override
	public SoundEvent getDrinkingSound() {
	      return SoundEvents.HONEY_DRINK;
	   }

	@Override
	public SoundEvent getEatingSound() {
	      return SoundEvents.HONEY_DRINK;
	   }
	   
		@Override
		public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity consumer) {
			if (!worldIn.isClientSide) {
				if (!consumer.fireImmune()) {
					consumer.setRemainingFireTicks(consumer.getRemainingFireTicks() + 1);
					consumer.setSecondsOnFire(60);
				}
				this.affectConsumer(stack, worldIn, consumer);
			}

			ItemStack containerStack = stack.getCraftingRemainingItem();

			if (stack.isEdible()) {
				super.finishUsingItem(stack, worldIn, consumer);
			} else {
				Player player = consumer instanceof Player ? (Player) consumer : null;
				if (player instanceof ServerPlayer) {
					CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
				}
				if (player != null) {
					player.awardStat(Stats.ITEM_USED.get(this));
					if (!player.getAbilities().instabuild) {
						stack.shrink(1);
					}
				}
			}

			if (stack.isEmpty()) {
				return containerStack;
			} else {
				if (consumer instanceof Player player && !((Player) consumer).getAbilities().instabuild) {
					if (!player.getInventory().add(containerStack)) {
						player.drop(containerStack, false);
					}
				}
				return stack;
			}
		}

		/**
		 * Override this to apply changes to the consumer (e.g. curing effects).
		 */
		public void affectConsumer(ItemStack stack, Level worldIn, LivingEntity consumer) {
		}
}