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
    public static final List<ConfigBase<?>> player;
    public static final List<ConfigTab> configTabs = new ArrayList<>();
    public static final ConfigInteger TimeSpeed = new ConfigInteger("瞬息万变", 1, 1, 16, "时间流逝速度倍速(最高16倍)");
    public static final ConfigInteger WalkSpeed = new ConfigInteger("长路漫漫", 0, -8, 8, "移动速度加成，每级加成10%，可以负数减缓");
    public static final ConfigInteger MaxHealth = new ConfigInteger("血量上限", 10, 1, 10, "玩家的血量上限(单位：心)(不可低于血量下限)");
    public static final ConfigInteger InitialHealth = new ConfigInteger("血量下限", 3, 1, 10, "玩家0等级时的血量上限(单位：心)(不可高于血量上限)");
    public static final ConfigInteger ExperienceLevelForHealth = new ConfigInteger("心之经验", 5, 1, 40, "玩家每多此等级时加一颗心(单位：等级)");
    public static final ConfigInteger MaxNutrition = new ConfigInteger("饱食度上限", 10, 1, 10, "玩家的鸡腿/饱食度上限(单位：格)(不可低于饱食度下限)");
    public static final ConfigInteger InitialNutrition = new ConfigInteger("饱食度下限", 3, 1, 10, "玩家0等级时的鸡腿/饱食度上限(单位：格)(不可高于饱食度上限)");
    public static final ConfigInteger ExperienceLevelForNutrition = new ConfigInteger("饱之经验", 5, 1, 40, "玩家每多此等级时加一格鸡腿/饱食度(单位：等级)");
    static {
        general = List.of(TimeSpeed);
        player = List.of(WalkSpeed,MaxHealth,InitialHealth,ExperienceLevelForHealth,MaxNutrition,InitialNutrition,ExperienceLevelForNutrition);
        values = new ArrayList<>();
        values.addAll(general);
        values.addAll(player);
        Instance = new FunnierMITEConfig(Start.MOD_ID, null, values);
        configTabs.add(new ConfigTab("通用", general));
        configTabs.add(new ConfigTab("玩家", player));
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