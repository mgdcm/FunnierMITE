package com.mgdcm.FunnierMITE.mixins.ManyLib;

import com.mgdcm.FunnierMITE.ManyLib.ConfigSpeed;
import fi.dy.masa.malilib.config.interfaces.IConfigBase;
import fi.dy.masa.malilib.config.interfaces.IConfigDisplay;
import fi.dy.masa.malilib.config.interfaces.IConfigSlideable;
import fi.dy.masa.malilib.config.interfaces.IStringRepresentable;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.gui.button.InputBox;
import fi.dy.masa.malilib.gui.button.SliderButton;
import fi.dy.masa.malilib.gui.screen.LegacyValueScreen;
import fi.dy.masa.malilib.gui.screen.interfaces.GuiScreenPaged;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Map;

@Mixin(LegacyValueScreen.class)
public abstract class LegacyValueScreenMixin extends GuiScreenPaged {

    @Shadow @Final private Map<Integer, GuiButton> buttonMap;

    @Shadow @Final private Map<Integer, InputBox<?>> textConfigMap;

    @Shadow protected abstract <T extends ConfigBase<T> & IStringRepresentable> InputBox<T> getInputBox(int x, int y, T config);

    @Shadow protected abstract <T extends ConfigBase<T> & IConfigSlideable & IConfigDisplay> SliderButton<T> getSliderButton(int index, int x, int y, T config);

    @Shadow @Final private List<ConfigBase<?>> values;

    public LegacyValueScreenMixin(GuiScreen parentScreen, String screenTitle, int rows, int columns) {
        super(parentScreen, screenTitle, rows, columns);
    }

    @Inject(method = "addConfigItem",at= @At(value = "INVOKE", target = "Lfi/dy/masa/malilib/config/options/ConfigDouble;shouldUseSlider()Z"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void addConfigItemSpeed(int index, CallbackInfo ci, IConfigBase config, int x, int y, ConfigDouble configDouble){
        if (configDouble.shouldUseSlider()) {
            if(this.values.get(index) instanceof ConfigSpeed)this.buttonMap.put(index, this.getSliderButton(index, x, y, configDouble));
            else this.buttonMap.put(index, this.getSliderButton(index, x, y, configDouble));
        } else {
            this.textConfigMap.put(index, this.getInputBox(x, y, configDouble));
        }
        ci.cancel();
    }
}
