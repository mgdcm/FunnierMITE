package com.mgdcm.FunnierMITE.mixins;

import com.mgdcm.FunnierMITE.FunnierMITEConfig;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockGravel.class)
public abstract class BlockGravelMixin extends BlockFalling {

    @Shadow public abstract int dropBlockAsEntityItem(BlockBreakInfo info);

    @Shadow public abstract boolean isNetherGravel(int metadata);

    public BlockGravelMixin(int par1, Material material, BlockConstants constants) {
        super(par1, material, constants);
    }
    @Inject(method = "dropBlockAsEntityItem",at= @At(value = "INVOKE", target = "Lnet/minecraft/BlockFalling;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;)I"), cancellable = true)
    private void GravelNotSilkTouch(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if(info.getMetadata() == 1 || info.wasExploded() || !info.wasHarvestedByPlayer())return;
        Random rand = info.world.rand;
        int id_dropped;
        if (FunnierMITEConfig.GravelNotSilkTouch.getBooleanValue()) {
            if (rand.nextInt(3) > 0) {
                if (rand.nextInt(16) == 0) {
                    id_dropped = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
                } else {
                    id_dropped = Item.chipFlint.itemID;
                }
            } else {
                id_dropped = rand.nextInt(3) > 0 ? Item.copperNugget.itemID : (rand.nextInt(3) > 0 ? Item.silverNugget.itemID : (rand.nextInt(3) > 0 ? Item.goldNugget.itemID : (rand.nextInt(3) > 0 ? (info.wasExploded() ? -1 : Item.shardObsidian.itemID) : (rand.nextInt(3) > 0 ? (info.wasExploded() ? -1 : Item.shardEmerald.itemID) : (rand.nextInt(3) > 0 ? (info.wasExploded() ? -1 : Item.shardDiamond.itemID) : (rand.nextInt(3) > 0 ? Item.mithrilNugget.itemID : Item.adamantiumNugget.itemID))))));
            }
            if (this.isNetherGravel(info.getMetadata())) {
                if (id_dropped == Item.copperNugget.itemID || id_dropped == Item.silverNugget.itemID || id_dropped == Item.mithrilNugget.itemID || id_dropped == Item.adamantiumNugget.itemID) {
                    id_dropped = Item.goldNugget.itemID;
                } else if (id_dropped == Item.shardObsidian.itemID || id_dropped == Item.shardEmerald.itemID || id_dropped == Item.shardDiamond.itemID) {
                    id_dropped = Item.shardNetherQuartz.itemID;
                }
            }
            cir.setReturnValue(this.dropBlockAsEntityItem(info, id_dropped));
        }
    }
    @Unique
    private void ChainGravelDestroy(BlockBreakInfo info, int r, int x, int y, int z) {
        World world = info.world;
        info.x = x;
        info.y = y;
        info.z = z;
        if (r >= 0 && world.getBlock(x, y, z) == this) {
            if (info.getHarvesterItemStack() != null && info.getHarvesterItemStack().getItem() instanceof ItemTool itemTool) {
                info.getHarvesterItemStack().tryDamageItem(DamageSource.generic, itemTool.getToolDecayFromBreakingBlock(info), info.getHarvester());
            }
            if (FunnierMITEConfig.ChainGravelDestroyNotNeedTool.getBooleanValue() || info.getHarvesterItemStack() != null && info.getHarvesterItemStack().getItem() instanceof ItemTool) {
                world.setBlockToAir(info.x, info.y, info.z);
                this.dropBlockAsEntityItem(info);
                if (world.getBlock(x + 1, y, z) == this) this.ChainGravelDestroy(info, r - 1, x + 1, y, z);
                if (world.getBlock(x - 1, y, z) == this) this.ChainGravelDestroy(info, r - 1, x - 1, y, z);
                if (world.getBlock(x, y + 1, z) == this) this.ChainGravelDestroy(info, r - 1, x, y + 1, z);
                if (world.getBlock(x, y - 1, z) == this) this.ChainGravelDestroy(info, r - 1, x, y - 1, z);
                if (world.getBlock(x, y, z + 1) == this) this.ChainGravelDestroy(info, r - 1, x, y, z + 1);
                if (world.getBlock(x, y, z - 1) == this) this.ChainGravelDestroy(info, r - 1, x, y, z - 1);
            }
        }
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    @Override
    public void onBlockAboutToBeBroken(BlockBreakInfo info){
        World world = info.world;
        int x=info.x,y=info.y,z=info.z;
        if(world.getBlock(x,y,z)==this) {
            int r = FunnierMITEConfig.ChainGravelDestroy.getIntegerValue();
            if(r>0)this.ChainGravelDestroy(info, r, x, y, z);
        }
    }
}
