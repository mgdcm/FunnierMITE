package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerCapabilities.class)
public class PlayerCapabilitiesMixin {
    @Inject(method = "getWalkSpeed",at=@At("RETURN"),cancellable = true)
    private void getWalkSpeed(CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(cir.getReturnValueF()+cir.getReturnValueF()*FunnierMITEConfig.WalkSpeed.getIntegerValue()*0.1F);
    }
}
