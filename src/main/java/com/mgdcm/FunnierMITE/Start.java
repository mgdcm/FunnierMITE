package com.mgdcm.FunnierMITE;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.fabricmc.api.ModInitializer;
import fi.dy.masa.malilib.config.ConfigManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class Start implements ModInitializer {
    public static final String MOD_ID = "FunnierMITE";

    @Override
    public void onInitialize() {   //相当于main函数，万物起源
        System.out.println(System.getProperty("file.encoding"));
        MITEEvents.MITE_EVENT_BUS.register(new EventListen());//注册一个事件监听类
        ConfigManager.getInstance().registerConfig(FunnierMITEConfig.getInstance());
    }

}