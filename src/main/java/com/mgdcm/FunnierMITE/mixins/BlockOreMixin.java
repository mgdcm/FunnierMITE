package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockOre.class)
public abstract class BlockOreMixin extends Block {
    @Shadow public abstract int dropBlockAsEntityItem(BlockBreakInfo info);

    protected BlockOreMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
    @Unique
    private void ChainOreDestroy(BlockBreakInfo info,int r, int x, int y, int z){
        World world = info.world;
        info.x=x;
        info.y=y;
        info.z=z;
        if(r>=0&&world.getBlock(x,y,z)==this){
            if(info.getHarvesterItemStack()!=null&&info.getHarvesterItemStack().getItem() instanceof ItemTool itemTool){
                world.setBlockToAir(info.x,info.y,info.z);
                this.dropBlockAsEntityItem(info);
                info.getHarvesterItemStack().tryDamageItem(DamageSource.generic,itemTool.getToolDecayFromBreakingBlock(info), info.getHarvester());
                if(world.getBlock(x+1,y,z)==this)this.ChainOreDestroy(info,r-1,x+1,y,z);
                if(world.getBlock(x-1,y,z)==this)this.ChainOreDestroy(info,r-1,x-1,y,z);
                if(world.getBlock(x,y+1,z)==this)this.ChainOreDestroy(info,r-1,x,y+1,z);
                if(world.getBlock(x,y-1,z)==this)this.ChainOreDestroy(info,r-1,x,y-1,z);
                if(world.getBlock(x,y,z+1)==this)this.ChainOreDestroy(info,r-1,x,y,z+1);
                if(world.getBlock(x,y,z-1)==this)this.ChainOreDestroy(info,r-1,x,y,z-1);
            }
        }
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Inject(method = "onBlockAboutToBeBroken",at=@At("TAIL"))
    private void onBlockAboutToBeBroken(BlockBreakInfo info, CallbackInfo ci){
        World world = info.world;
        int x=info.x,y=info.y,z=info.z;
        if(world.getBlock(x,y,z)==this) {
            int r = FunnierMITEConfig.ChainOreDestroy.getIntegerValue();
            if(r>0)this.ChainOreDestroy(info, r, x, y, z);
        }
    }
}
