package io.github.manasmods.tensura_opac;

import io.github.manasmods.manascore.config.api.Comment;
import io.github.manasmods.manascore.config.api.ManasConfig;

public class OpacConfig extends ManasConfig {
    @Comment("Should Opac party/team members to be counted as Ally for Tensura's abilities.")
    public boolean opacAllyTensura = true;
    @Comment("Allow harmful effect from others to be inflicted in non-pvp claims.")
    public boolean harmfulEffect = false;
    @Comment("Allow energy draining in non-pvp claims.")
    public boolean energyDrain = false;
    @Comment("Allow possession in non-pvp claims.")
    public boolean possession = false;
    @Comment("Allow spiritual damage in non-pvp claims.")
    public boolean spiritualDamage = false;
    @Comment("Allow teleportation forced by others' abilities in non-pvp claims.")
    public boolean forcedTeleportation = false;
    @Comment("Allow ability plundering in non-pvp claims.")
    public boolean abilityPlundering = false;
    @Comment("Allow ability griefing in claimed chunks.")
    public boolean abilityGrief = false;

    public String getFileName() {
        return "tensura/opac_config";
    }
}
