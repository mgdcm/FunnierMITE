package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.EntityPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin {
    @Inject(method = "getHealthLimit(I)I",at=@At("HEAD"), cancellable = true)
    private static void getHealthLimit(int level, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Math.max(Math.min(FunnierMITEConfig.InitialHealth.getIntegerValue() * 2
                + level / FunnierMITEConfig.ExperienceLevelForHealth.getIntegerValue() * 2,
                FunnierMITEConfig.MaxHealth.getIntegerValue()*2), FunnierMITEConfig.InitialHealth.getIntegerValue()*2));
    }
}
