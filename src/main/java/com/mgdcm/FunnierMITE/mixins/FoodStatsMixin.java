package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoodStats.class)
public class FoodStatsMixin {
    @Shadow private EntityPlayer player;

    @Inject(method = "getNutritionLimit",at=@At("HEAD"), cancellable = true)
    private void getNutritionLimit(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Math.max(Math.min(FunnierMITEConfig.InitialNutrition.getIntegerValue() * 2
                        + this.player.getExperienceLevel() / FunnierMITEConfig.ExperienceLevelForNutrition.getIntegerValue() * 2,
                FunnierMITEConfig.MaxNutrition.getIntegerValue()*2), FunnierMITEConfig.InitialNutrition.getIntegerValue()*2));
    }
}
