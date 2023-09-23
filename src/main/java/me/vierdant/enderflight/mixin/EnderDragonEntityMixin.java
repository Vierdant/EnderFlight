package me.vierdant.enderflight.mixin;

import me.vierdant.enderflight.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin extends MobEntity {
    @Shadow
    public int ticksSinceDeath;

    public EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "updatePostDeath", at = @At("HEAD"))
    protected void updatePostDeathMixin(CallbackInfo info) {
        boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        if (!this.getWorld().isClient() && this.ticksSinceDeath == 150) {
            if (bl) {
                int dropAmount = new Random().ints(3, 5)
                        .findFirst().getAsInt();

                for (int i = 0; i < dropAmount; ++i) {
                    this.dropStack(new ItemStack(ModItems.DRAGON_SCALE));
                }
            }
        }
    }

}
