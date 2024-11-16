package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Unique private static boolean b = false;
    @Inject(method = "tick",at=@At("HEAD"), cancellable = true)
    private void tickI(CallbackInfo ci){
        if(FunnierMITEConfig.TimeStop.getBooleanValue())ci.cancel();
        if(!b) {
            b=true;
            for (int i = 0; i < FunnierMITEConfig.TimeSpeed.getIntegerValue() - 1; i++) {
                ((MinecraftServer) (Object) this).tick();
            }
            b=false;
        }
    }
}
