package com.github.manasmods.tensura_opac.core;

import com.github.manasmods.tensura_opac.capability.OpacCapability;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xaero.pac.common.server.claims.player.ServerPlayerClaimInfoManager;
import xaero.pac.common.server.player.permission.api.IPermissionNodeAPI;
import xaero.pac.common.server.player.permission.api.UsedPermissionNodes;

import java.util.UUID;

@Mixin(ServerPlayerClaimInfoManager.class)
public class MixinServerPlayerClaimInfoManager {
    @Shadow @Final private MinecraftServer server;
    @Inject(method = "getPlayerBaseLimit(Ljava/util/UUID;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraftforge/common/ForgeConfigSpec$IntValue;Lxaero/pac/common/server/player/permission/api/IPermissionNodeAPI;)I",
            at = @At("RETURN"), cancellable = true, remap = false)
    private void isValidBlock(UUID playerId, ServerPlayer player, ForgeConfigSpec.IntValue limitConfig,
                              IPermissionNodeAPI<Integer> permissionNode, CallbackInfoReturnable<Integer> cir) {
        if (permissionNode == UsedPermissionNodes.MAX_PLAYER_CLAIMS) {
            ServerPlayer serverPlayer = player == null ? server.getPlayerList().getPlayer(playerId) : player;
            cir.setReturnValue(cir.getReturnValue() + OpacCapability.getBonusClaimChunk(serverPlayer));
        } else if (permissionNode == UsedPermissionNodes.MAX_PLAYER_FORCELOADS) {
            ServerPlayer serverPlayer = player == null ? server.getPlayerList().getPlayer(playerId) : player;
            cir.setReturnValue(cir.getReturnValue() + OpacCapability.getBonusForceChunk(serverPlayer));
        }
    }
}
