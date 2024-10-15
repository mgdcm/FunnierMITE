package com.mgdcm.FunnierMITE.ManyLib;

import fi.dy.masa.malilib.config.options.ConfigDouble;

public class ConfigSpeed extends ConfigDouble {
    public ConfigSpeed(String name, float defaultValue) {
        super(name, defaultValue);
    }

    public ConfigSpeed(String name, float defaultValue, String comment) {
        super(name, defaultValue, comment);
    }

    public ConfigSpeed(String name, float defaultValue, float minValue, float maxValue) {
        super(name, defaultValue, minValue, maxValue);
    }

    public ConfigSpeed(String name, float defaultValue, float minValue, float maxValue, String comment) {
        super(name, defaultValue, minValue, maxValue, comment);
    }

    public ConfigSpeed(String name, float defaultValue, float minValue, float maxValue, boolean useSlider, String comment) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment);
    }
    public ConfigSpeed(String name, double defaultValue, double minValue, double maxValue, boolean useSlider, String comment) {
        super(name, defaultValue, minValue, maxValue, useSlider, comment);
    }
    public float getSpeedValue(){
        return (float) this.getDoubleValue();
    }
    @Override
    public String getDisplayTextFull() {
        String var10000 = this.getConfigGuiDisplayName();
        return var10000 + ": " + this.getSpeedValue() + "X";
    }
    @Override
    public String getDisplayText() {
        return this.getSpeedValue() + "X";
    }
}
