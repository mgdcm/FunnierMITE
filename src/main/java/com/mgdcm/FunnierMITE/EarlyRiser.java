package com.mgdcm.FunnierMITE;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class EarlyRiser implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        FunnierMITEConfig.getInstance().load();
    }

}
