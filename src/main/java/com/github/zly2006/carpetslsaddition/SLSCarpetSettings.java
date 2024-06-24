package com.github.zly2006.carpetslsaddition;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

import static com.github.zly2006.carpetslsaddition.ServerMain.CARPET_ID;

public class SLSCarpetSettings {
    public static final String NEED_CLIENT = "needClient";  // 需要客户端安装SLS-Addition或实现相关支持

    @Rule(categories = {CARPET_ID, RuleCategory.SURVIVAL})
    public static boolean obtainableReinforcedDeepSlate = false;

    @Rule(categories = {CARPET_ID, RuleCategory.OPTIMIZATION})
    public static int skipTicksForJoblessVillager = 0;

    @Rule(categories = {CARPET_ID, RuleCategory.CREATIVE})
    public static boolean creativeNoInfinitePickup = false;

    @Rule(categories = {CARPET_ID, RuleCategory.OPTIMIZATION})
    public static boolean noBatSpawning = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean canUseHatCommand = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean canUseSitCommand = false;

    @Rule(categories = {CARPET_ID}, strict = false, options = {"#none"})
    public static String botPrefix = "#none";

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean creativeObeyEnchantmentRule = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean fakePlayersNotOccupiedSleepQuota = false;

    @Rule(categories = {CARPET_ID, RuleCategory.CREATIVE})
    public static int maxUpdateQueueSize = -1;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE, NEED_CLIENT})
    public static boolean emptyShulkerBoxStack = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean playerSit = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean endermanCanPickUpMushroom = true;

    @Rule(categories = {CARPET_ID, RuleCategory.CREATIVE})
    public static boolean oldRedstoneConnectionLogic = false;

    @Rule(categories = {CARPET_ID, RuleCategory.OPTIMIZATION})
    public static boolean optimizedOnDragonRespawn = false;

    @Rule(categories = {CARPET_ID, RuleCategory.FEATURE})
    public static boolean elytraCraftable = false;
}
