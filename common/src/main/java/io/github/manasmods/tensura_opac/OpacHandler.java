package io.github.manasmods.tensura_opac;

import dev.architectury.event.EventResult;
import io.github.manasmods.manascore.config.ConfigRegistry;
import io.github.manasmods.tensura.event.TensuraEntityEvents;
import io.github.manasmods.tensura.event.TensuraSkillEvents;
import io.github.manasmods.tensura.storage.player.WarpPoint;
import io.github.manasmods.tensura.util.ObjectSelectionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
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
import xaero.pac.common.server.world.ServerLevelHelper;

public class OpacHandler {
    public static void init() {
        TensuraEntityEvents.ENERGY_DRAIN_EVENT.register((target, drainer, drainType, gainType, amount, percentage) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).energyDrain) return EventResult.pass();
            if (drainer != null && drainer != target && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, drainer, drainer, target,
                        null, null, true, drainer instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraEntityEvents.FORCE_MOVEMENT_EVENT.register((target, mover, skill, vec3) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).forcedMovement) return EventResult.pass();
            if (mover != null && mover != target && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, mover, mover, target,
                        null, null, true, mover instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraEntityEvents.FORCE_TAME_EVENT.register((target, tamer, temporary) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).mindControl) return EventResult.pass();
            if (tamer != null && tamer != target && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, tamer, tamer, target,
                        null, null, true, tamer instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraEntityEvents.POSSESSION_EVENT.register((target, possessor) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).possession) return EventResult.pass();
            if (possessor != null && possessor != target && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, possessor, possessor, target,
                        null, null, true, possessor instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraEntityEvents.SPIRITUAL_HURT_EVENT.register((target, attacker, originalAmount, resistPercentage, amount, source) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).spiritualDamage) return EventResult.pass();
            if (attacker != null && attacker != target && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, attacker, attacker, target,
                        null, null, true, false, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraEntityEvents.INSTANT_TRANSMISSION_EVENT.register((target, teleporter, position, type) -> {
            if (!type.equals(WarpPoint.TransmissionType.ABILITY) || target == teleporter) return EventResult.pass();
            if (ConfigRegistry.getConfig(OpacConfig.class).forcedTeleportation) return EventResult.pass();

            if (teleporter != null && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, teleporter, teleporter, target,
                        null, null, true, teleporter instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraSkillEvents.SKILL_PLUNDER.register((target, owner, steal, skill) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).abilityPlundering) return EventResult.pass();
            if (owner != null && owner != target && target != null && target.getServer() != null) {
                IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                        IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
                if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, owner, owner, target,
                        null, null, true, owner instanceof Player, true))
                    return EventResult.interruptFalse();
            }
            return EventResult.pass();
        });

        TensuraSkillEvents.SKILL_GRIEF_PRE.register((instance, level, owner, x, y, z) -> {
            if (ConfigRegistry.getConfig(OpacConfig.class).abilityGrief) return EventResult.pass();

            ServerLevel serverLevel = ServerLevelHelper.getServerLevel(level);
            if (serverLevel == null) return EventResult.pass();
            BlockPos pos = ObjectSelectionHelper.getBlockPos(new Vec3(x, y, z));

            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(serverLevel.getServer());
            if (serverData != null && owner != null && serverData.getChunkProtection().onEntityDestroyBlock(serverData,
                    serverLevel.getBlockState(pos), owner, serverLevel, pos, false)) return EventResult.interruptFalse();
            return EventResult.pass();
        });
    }
}