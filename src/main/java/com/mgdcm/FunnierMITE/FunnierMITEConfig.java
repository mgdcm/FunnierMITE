package com.mgdcm.FunnierMITE;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mgdcm.FunnierMITE.ManyLib.ConfigSpeed;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.util.JsonUtils;
import net.minecraft.KeyBinding;

import java.util.ArrayList;
import java.util.List;

public class FunnierMITEConfig extends SimpleConfigs {
    public static final List<ConfigBase<?>> values;
    public static final List<ConfigBase<?>> general;
    public static final List<ConfigBase<?>> player;
    public static final List<ConfigBase<?>> dev;
    public static final List<ConfigTab> configTabs = new ArrayList<>();
    public static final ConfigInteger TimeSpeed = new ConfigInteger("瞬息万变", 1, 1, 16, "时间流逝速度倍速(最高16倍)");
    public static final ConfigBoolean TimeStop = new ConfigBoolean("时间暂停", "暂停世界的时间");
    public static final ConfigInteger ChainOreDestroy = new ConfigInteger("矿物连锁", 0, 0, 4, "矿物连锁挖掘的半径(默认0即为不连锁)，但连锁时挖掘附魔不生效");
    public static final ConfigInteger ChainGravelDestroy = new ConfigInteger("沙砾连锁", 0, 0, 4, "沙砾连锁挖掘的半径(默认0即为不连锁)，但连锁时挖掘附魔不生效");
    public static final ConfigBoolean ChainGravelDestroyNotNeedTool = new ConfigBoolean("沙砾连锁无需工具", "沙砾连锁无需工具");
    public static final ConfigBoolean GravelNotSilkTouch = new ConfigBoolean("宝藏沙砾", "挖掘沙砾必不出沙砾");
    public static final ConfigSpeed WalkSpeed = new ConfigSpeed("长路漫漫", 1.0f, 0.2f, 1.8f, "移动速度倍速");
    public static final ConfigSpeed HungerSpeed = new ConfigSpeed("饥荒之日", 1.0f, 0.01f, 100,false, "常规饥饿速度倍速(默认1.0x)");
    public static final ConfigSpeed FlySpeed = new ConfigSpeed("氮气加速", 1, 0.5f, 32, "飞行速度倍速");
    public static final ConfigInteger MaxHealth = new ConfigInteger("血量上限的上限", 10, 1, 10, "玩家的血量上限的上限(单位：心)(不可低于血量上限的下限)");
    public static final ConfigInteger InitialHealth = new ConfigInteger("血量上限的下限", 3, 1, 10, "玩家0等级时的血量上限(单位：心)(不可高于血量上限的上限)");
    public static final ConfigInteger ExperienceLevelForHealth = new ConfigInteger("心之经验", 5, 1, 40, "经验每达到此等级时血量上限加1心(单位：等级)");
    public static final ConfigInteger MaxNutrition = new ConfigInteger("饱食度上限的上限", 10, 1, 10, "玩家的鸡腿/饱食度上限的上限(单位：格)(不可低于饱食度上限的下限)");
    public static final ConfigInteger InitialNutrition = new ConfigInteger("饱食度上限的下限", 3, 1, 10, "玩家0等级时的鸡腿/饱食度上限(单位：格)(不可高于饱食度上限的上限)");
    public static final ConfigInteger ExperienceLevelForNutrition = new ConfigInteger("饱之经验", 5, 1, 40, "经验每达到此等级时饱食度上限加1格鸡腿/饱食度(单位：等级)");
    static {
        general = List.of(TimeSpeed,TimeStop,ChainOreDestroy,ChainGravelDestroy,ChainGravelDestroyNotNeedTool,GravelNotSilkTouch);
        player = List.of(WalkSpeed,MaxHealth,InitialHealth,ExperienceLevelForHealth,MaxNutrition,InitialNutrition,ExperienceLevelForNutrition,HungerSpeed);
        dev = List.of(FlySpeed);
        values = new ArrayList<>();
        values.addAll(general);
        values.addAll(player);
        values.addAll(dev);
        Instance = new FunnierMITEConfig(Start.MOD_ID, null, values);
        configTabs.add(new ConfigTab("通用", general));
        configTabs.add(new ConfigTab("玩家", player));
        configTabs.add(new ConfigTab("Dev专用", dev));
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
    @Override
    public void save() {
        JsonObject root = new JsonObject();
        ConfigUtils.writeConfigBase(root, "General", general);
        ConfigUtils.writeConfigBase(root, "Player", player);
        ConfigUtils.writeConfigBase(root, "Dev", dev);
        JsonUtils.writeJsonToFile(root, this.optionsFile);
    }

    @Override
    public void load() {
        if (!this.optionsFile.exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.optionsFile);
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject root = jsonElement.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "General", general);
                ConfigUtils.readConfigBase(root, "Player", player);
                ConfigUtils.readConfigBase(root, "Dev", dev);
                this.save();
                KeyBinding.resetKeyBindingArrayAndHash();
            }
        }
    }
}