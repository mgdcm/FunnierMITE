package com.mgdcm.FunnierMITE.ManyLib;

import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.gui.button.SliderButton;
import net.minecraft.MathHelper;

public abstract class SpeedSliderButton extends SliderButton {
    public SpeedSliderButton(int index, int x, int y, int width, int height, ConfigBase config) {
        super(index, x, y, width, height, config);
    }
    @Override
    public float getRatioFromSlider(int mouseX) {
        return MathHelper.clamp_float((float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8), 0.0F, 1.0F);
    }
}
