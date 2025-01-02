package com.github.manasmods.tensura_opac.command;

import com.github.manasmods.tensura_opac.TensuraOpac;
import com.github.manasmods.tensura_opac.capability.OpacCapability;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = TensuraOpac.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OpacCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent e) {
        e.getDispatcher().register(
                Commands.literal("opac-perm")
                        .requires((stack) -> stack.hasPermission(3))
                        .then(Commands.argument("players", EntityArgument.players())
                                .then(Commands.literal("bonusClaim")
                                        .then(Commands.literal("get")
                                                .executes((context) -> getBonusClaim(context.getSource(), EntityArgument.getPlayers(context, "players"))))
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> setBonusClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                        .then(Commands.literal("add")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> addBonusClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                        .then(Commands.literal("remove")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> removeBonusClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                )
                                .then(Commands.literal("bonusForce")
                                        .then(Commands.literal("get")
                                                .executes((context) -> getBonusForce(context.getSource(), EntityArgument.getPlayers(context, "players"))))
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> setBonusForceClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                        .then(Commands.literal("add")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> addBonusForceClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                        .then(Commands.literal("remove")
                                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                                        .executes((context) -> removeBonusForceClaim(context.getSource(),
                                                                EntityArgument.getPlayers(context, "players"), IntegerArgumentType.getInteger(context, "number"))))
                                        )
                                )
                        )
        );
    }

    private static int getBonusClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets) {
        for (ServerPlayer player : pTargets) {
            stack.sendSuccess(Component.translatable("opac.bonus_claim.get", player.getName(), OpacCapability.getBonusClaimChunk(player)), false);
        }
        return Command.SINGLE_SUCCESS;
    }
    
    private static int setBonusClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.setBonusClaimChunk(player, number);
            stack.sendSuccess(Component.translatable("opac.bonus_claim.set", player.getName(), number), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int addBonusClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.getFrom(player).ifPresent(cap -> {
                cap.setBonusClaimChunk(cap.getBonusClaimChunk() + number);
                stack.sendSuccess(Component.translatable("opac.bonus_claim.set", player.getName(), cap.getBonusClaimChunk()), true);
                OpacCapability.sync(player);
            });
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int removeBonusClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.getFrom(player).ifPresent(cap -> {
                cap.setBonusClaimChunk(Math.max(0, cap.getBonusClaimChunk() - number));
                stack.sendSuccess(Component.translatable("opac.bonus_claim.set", player.getName(), cap.getBonusClaimChunk()), true);
                OpacCapability.sync(player);
            });
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int getBonusForce(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets) {
        for (ServerPlayer player : pTargets) {
            stack.sendSuccess(Component.translatable("opac.bonus_force.get", player.getName(), OpacCapability.getBonusForceChunk(player)), false);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int setBonusForceClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.setBonusForceChunk(player, number);
            stack.sendSuccess(Component.translatable("opac.bonus_force.set", player.getName(), number), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int addBonusForceClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.getFrom(player).ifPresent(cap -> {
                cap.setBonusForceChunk(cap.getBonusForceChunk() + number);
                stack.sendSuccess(Component.translatable("opac.bonus_force.set", player.getName(), cap.getBonusForceChunk()), true);
                OpacCapability.sync(player);
            });
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int removeBonusForceClaim(CommandSourceStack stack, Collection<? extends ServerPlayer> pTargets, int number) {
        for (ServerPlayer player : pTargets) {
            OpacCapability.getFrom(player).ifPresent(cap -> {
                cap.setBonusForceChunk(Math.max(0, cap.getBonusForceChunk() - number));
                stack.sendSuccess(Component.translatable("opac.bonus_force.set", player.getName(), cap.getBonusForceChunk()), true);
                OpacCapability.sync(player);
            });
        }
        return Command.SINGLE_SUCCESS;
    }
}
