package com.mgdcm.FunnierMITE;

import com.google.common.eventbus.Subscribe;
import net.minecraft.ChatMessageComponent;
import net.minecraft.EntityPlayer;
import net.xiaoyu233.fml.reload.event.*;

public class EventListen {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        //物品被注册事件
    }

    @Subscribe
    public void onRecipeRegister(RecipeRegistryEvent event) {
        //合成方式被注册事件
    }

    //玩家登录事件
    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {

    }

    //指令事件
    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        if (par2Str.startsWith("Hello")) {    //当玩家输入 /Hello
            player.sendChatToPlayer(ChatMessageComponent.createFromText("你好，FML！"));
            event.setExecuteSuccess(true);
        }
    }
}
