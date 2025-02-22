package com.github.manasmods.tensura_opac.handler;

import com.github.manasmods.tensura.event.*;
import com.github.manasmods.tensura.util.damage.TensuraDamageSource;
import com.github.manasmods.tensura_opac.TensuraOpac;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

@Mod.EventBusSubscriber(modid = TensuraOpac.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OpacHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onSkillGrief(final SkillGriefEvent event) {
        if (event.getSkillCaster() == null) return;
        ServerLevel serverLevel = ServerLevelHelper.getServerLevel(event.getSkillCaster().getLevel());
        if (serverLevel == null) return;

        BlockPos pos = event.getBlockPos();
        IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(serverLevel.getServer());
        if (serverData != null && serverData.getChunkProtection().onEntityDestroyBlock(serverData,
                serverLevel.getBlockState(pos), event.getSkillCaster(), serverLevel, pos, false)) event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onForceTeleport(final ForcedTeleportationEvent event) {
        Entity target = event.getEntity();
        Entity source = event.getTeleporter();

        if (source != null && source != target && target.getServer() != null) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, source, source, target, null,
                    InteractionHand.MAIN_HAND, false, source instanceof Player, false))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEnergyDrain(final EnergyDrainEvent event) {
        Entity target = event.getEntity();
        Entity source = event.getAttacker();

        if (source != null && source != target && target.getServer() != null) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, source, source, target, null,
                    InteractionHand.MAIN_HAND, false, source instanceof Player, false))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onSkillPlunder(final SkillPlunderEvent event) {
        Entity target = event.getTarget();
        Entity source = event.getPlunderer();

        if (source != null && source != target && target != null && target.getServer() != null) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, source, source, target, null,
                    InteractionHand.MAIN_HAND, false, source instanceof Player, false))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPossession(final PossessionEvent event) {
        Entity target = event.getEntity();
        Entity source = event.getAttacker();

        if (source != null && source != target && target.getServer() != null) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, source, source, target, null,
                    InteractionHand.MAIN_HAND, false, source instanceof Player, false))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onSpiritualHurt(final SpiritualHurtEvent event) {
        Entity target = event.getEntity();
        Entity source = event.getAttacker();

        if (source != null && source != target && target.getServer() != null) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData != null && serverData.getChunkProtection().onEntityInteraction(serverData, source, source, target, null,
                    InteractionHand.MAIN_HAND, true, source instanceof Player, false))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onSkillDamage(final LivingAttackEvent event) {
        if (event.isCanceled()) return;

        LivingEntity target = event.getEntity();
        if (target.getServer() == null) return;

        DamageSource source = event.getSource();
        if (source.getEntity() == null) return;

        if (source.getMsgId().contains("tensura.")
                || (source instanceof TensuraDamageSource damageSource && damageSource.getSkill() != null)) {
            if (source.getEntity() == target || source.getDirectEntity() == target) return;

            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>,
                    IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>> serverData = ServerData.from(target.getServer());
            if (serverData == null) return;

            if (serverData.getChunkProtection().onEntityInteraction(serverData, source.getEntity(), source.getDirectEntity(), target, null,
                    InteractionHand.MAIN_HAND, true, source.getDirectEntity() instanceof Player, false))
                event.setCanceled(true);
            else if (serverData.getChunkProtection().onEntityInteraction(serverData, target, target, source.getEntity(), null,
                    InteractionHand.MAIN_HAND, true, source.getDirectEntity() instanceof Player, false))
                event.setCanceled(true);
        }
    }
}
