package com.mgdcm.FunnierMITE;

import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;

import java.util.ArrayList;
import java.util.List;

public class FunnierMITEConfig extends SimpleConfigs {
    public static final List<ConfigBase<?>> values;
    public static final List<ConfigBase<?>> general;
    public static final List<ConfigTab> configTabs = new ArrayList<>();
    public static final ConfigInteger TimeSpeed = new ConfigInteger("瞬息万变", 1, 1, 16, "时间流逝速度倍速");
    static {
        general = List.of(TimeSpeed);
        values = new ArrayList<>();
        values.addAll(general);
        Instance = new FunnierMITEConfig(Start.MOD_ID, null, values);
        configTabs.add(new ConfigTab("通用", general));
    }
    /* stuckTags */
    @Override
    public List<ConfigTab> getConfigTabs() {
        return configTabs;
    }
    public static final FunnierMITEConfig Instance;
    public FunnierMITEConfig(String name, List<ConfigHotkey> hotkeys, List<?> values) {
        super(name, hotkeys, values);
    }
    public static FunnierMITEConfig getInstance() {
        return Instance;
    }
}