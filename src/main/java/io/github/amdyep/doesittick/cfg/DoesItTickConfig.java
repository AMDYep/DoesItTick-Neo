package io.github.amdyep.doesittick.cfg;

import com.google.common.collect.Lists;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.function.Predicate;

public class DoesItTickConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue MAX_HORIZONTAL_TICK_DISTANCE;
    public static final ModConfigSpec.IntValue MAX_VERTICAL_TICK_DISTANCE;
    public static final ModConfigSpec.BooleanValue OPTIMIZE_ITEM_MOVEMENT;
    public static final ModConfigSpec.BooleanValue IGNORE_DEAD_ENTITIES;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ENTITIES_WHITELIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_LIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ENTITIES_MOD_ID_LIST;

    static {
        var cfgBuilder = new ModConfigSpec.Builder();
        cfgBuilder.comment("DoesItTick-Neo").push("LivingEntities");
        cfgBuilder.push("Distance");
        MAX_HORIZONTAL_TICK_DISTANCE = cfgBuilder
                .comment("Max Horizontal Tick Distance for Living Entities")
                .defineInRange("max_horizontal_dist", 64, 1, Integer.MAX_VALUE);
        MAX_VERTICAL_TICK_DISTANCE = cfgBuilder
                .comment("Max Vertical Tick Distance for Living Entities")
                .defineInRange("max_vertical_dist", 32, 1, Integer.MAX_VALUE);
        cfgBuilder.pop();
        Predicate<Object> isString = o -> o instanceof String;
        cfgBuilder.push("Whitelist");
        ENTITIES_WHITELIST = cfgBuilder
                .comment("The entities whose types are listed below won't be affected by tick interception.")
                .defineList(
                        "ignored_entity_types",
                        Lists.newArrayList(
                                "minecraft:ender_dragon", "minecraft:ghast", "minecraft:wither",
                                "alexsmobs:void_worm", "alexsmobs:void_worm_part", "alexsmobs:spectre",
                                "twilightforest:naga", "twilightforest:lich", "twilightforest:yeti",
                                "twilightforest:snow_queen", "twilightforest:minoshroom", "twilightforest:hydra",
                                "twilightforest:knight_phantom", "twilightforest:ur_ghast",
                                "atum:pharaoh",
                                "mowziesmobs:barako", "mowziesmobs:ferrous_wroughtnaut", "mowziesmobs:frostmaw", "mowziesmobs:naga",
                                "aoa3:skeletron", "aoa3:smash", "aoa3:baroness", "aoa3:clunkhead",
                                "aoa3:corallus", "aoa3:cotton_candor", "aoa3:craexxeus", "aoa3:xxeus",
                                "aoa3:creep", "aoa3:crystocore", "aoa3:dracyon", "aoa3:graw",
                                "aoa3:gyro", "aoa3:hive_king", "aoa3:kajaros", "aoa3:miskel",
                                "aoa3:harkos", "aoa3:raxxan", "aoa3:okazor", "aoa3:king_bambambam",
                                "aoa3:king_shroomus", "aoa3:kror", "aoa3:mechbot", "aoa3:nethengeic_wither",
                                "aoa3:red_guardian", "aoa3:blue_guardian", "aoa3:green_guardian", "aoa3:yellow_guardian",
                                "aoa3:rock_rider", "aoa3:shadowlord", "aoa3:tyrosaur", "aoa3:vinecorne",
                                "aoa3:visualent", "aoa3:voxxulon", "aoa3:bane", "aoa3:elusive",
                                "gaiadimension:malachite_drone", "gaiadimension:malachite_guard",
                                "blue_skies:alchemist", "blue_skies:arachnarch", "blue_skies:starlit_crusher", "blue_skies:summoner",
                                "stalwart_dungeons:awful_ghast", "stalwart_dungeons:nether_keeper",
                                "stalwart_dungeons:shelterer_without_armor",
                                "dungeonsmod:extrapart", "dungeonsmod:king", "dungeonsmod:deserted", "dungeonsmod:crawler",
                                "dungeonsmod:ironslime", "dungeonsmod:kraken", "dungeonsmod:voidmaster", "dungeonsmod:lordskeleton",
                                "dungeonsmod:winterhunter", "dungeonsmod:sun",
                                "forestcraft:beequeen", "forestcraft:iguana_king", "forestcraft:cosmic_fiend",
                                "forestcraft:nether_scourge",
                                "cataclysm:ender_golem", "cataclysm:ender_guardian", "cataclysm:ignis",
                                "cataclysm:ignited_revenant", "cataclysm:netherite_monstrosity",
                                "iceandfire:fire_dragon", "iceandfire:ice_dragon", "iceandfire:lightning_dragon",
                                "iceandfire:dragon_multipart"
                        ),
                        isString
                );
        ENTITIES_MOD_ID_LIST = cfgBuilder
                .comment("The entities from mods whose mod-ids are listed below won't be affected by tick interception.")
                .defineList(
                        "ignored_entity_namespaces",
                        Lists.newArrayList("create", "witherstormmod"),
                        isString
                );
        IGNORE_DEAD_ENTITIES = cfgBuilder
                .comment("If enabled, ticking of dead entities out of range will be ignored. Weird but performant.")
                .define("ignore_the_dead", true);
        cfgBuilder.pop();
        cfgBuilder.pop();
        cfgBuilder.push("ItemEntities");
        OPTIMIZE_ITEM_MOVEMENT = cfgBuilder
                .comment("Item Tick Optimization: Item entities tick and move at 1/4 speed")
                .define("item_tick_optimization", false);
        ITEM_LIST = cfgBuilder
                .comment("Item entities of items listed below won't be affected by Item Tick Optimization")
                .defineList(
                        "ignored_items",
                        Lists.newArrayList("minecraft:cobblestone"),
                        isString
                );
        cfgBuilder.pop();
        SPEC = cfgBuilder.build();
    }
}
