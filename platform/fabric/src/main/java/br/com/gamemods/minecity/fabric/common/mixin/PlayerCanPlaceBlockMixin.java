package br.com.gamemods.minecity.fabric.common.mixin;

import br.com.gamemods.minecity.fabric.event.BlockCanPlaceCallback;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class PlayerCanPlaceBlockMixin {
    @Inject(method = "canPlace(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void restrict(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!BlockCanPlaceCallback.EVENT.invoker().canPlace(context, state)) {
            cir.setReturnValue(false);
        }
    }
}
