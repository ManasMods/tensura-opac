package io.github.manasmods.tensura_opac.core;

import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.tensura.util.SubordinateHelper;
import io.github.manasmods.tensura_opac.OpacConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xaero.pac.common.claims.player.IPlayerChunkClaim;
import xaero.pac.common.claims.player.IPlayerClaimPosList;
import xaero.pac.common.claims.player.IPlayerDimensionClaims;
import xaero.pac.common.parties.party.IPartyPlayerInfo;
import xaero.pac.common.parties.party.ally.IPartyAlly;
import xaero.pac.common.parties.party.member.IPartyMember;
import xaero.pac.common.server.IServerData;
import xaero.pac.common.server.ServerData;
import xaero.pac.common.server.claims.IServerClaimsManager;
import xaero.pac.common.server.claims.IServerDimensionClaimsManager;
import xaero.pac.common.server.claims.IServerRegionClaims;
import xaero.pac.common.server.claims.player.IServerPlayerClaimInfo;
import xaero.pac.common.server.parties.party.IServerParty;

@Mixin(SubordinateHelper.class)
public class MixinSubordinateHelper {

    @Inject(method = "isAlly", at = @At(value = "RETURN"), remap = false, cancellable = true)
    private static void isAlly(LivingEntity entity, LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) return;
        OpacConfig CONFIG = ConfigRegistry.getConfig(OpacConfig.class);
        if (CONFIG == null || !CONFIG.opacAllyTensura) return;

        MinecraftServer server = entity.getServer();
        if (server == null) return;
        IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(server);

        if(!serverData.getPlayerPartySystemManager().isInAParty(entity.getUUID()) || !serverData.getPlayerPartySystemManager().isInAParty(target.getUUID())) return;
        if (serverData.getPlayerPartySystemManager().areInSameParty(entity.getUUID(), target.getUUID())) cir.setReturnValue(true);
        if (serverData.getPlayerPartySystemManager().isPlayerAllying(entity.getUUID(), target.getUUID())) cir.setReturnValue(true);
    }
}
