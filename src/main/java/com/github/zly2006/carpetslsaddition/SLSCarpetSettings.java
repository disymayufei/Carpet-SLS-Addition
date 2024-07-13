package com.github.zly2006.carpetslsaddition;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class SLSCarpetSettings {
    public static final String NEED_CLIENT = "needClient";  // 需要客户端安装SLS-Addition或实现相关支持
    public static final String FROM_AMS = "AMS";
    public static final String SLSA = "SLS";

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.SURVIVAL})
    public static boolean obtainableReinforcedDeepSlate = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.CREATIVE})
    public static boolean creativeNoInfinitePickup = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.OPTIMIZATION})
    public static boolean noBatSpawning = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean canUseHatCommand = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean canUseSitCommand = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA}, strict = false, options = {"#none"})
    public static String botPrefix = "#none";

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA})
    public static long botMaxOnlineTime = -1;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean creativeObeyEnchantmentRule = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean fakePlayersNotOccupiedSleepQuota = false;

    /** From Plusls Carpet Addition **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE, NEED_CLIENT})
    public static boolean emptyShulkerBoxStack = false;

    /** From Plusls Carpet Addition **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean playerSit = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean endermanCanPickUpMushroom = true;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.CREATIVE})
    public static boolean oldRedstoneConnectionLogic = false;

    /** From Carpet AMS Addition **/
    @Rule(categories = {SLSA, FROM_AMS, RuleCategory.OPTIMIZATION})
    public static boolean optimizedOnDragonRespawn = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean elytraCraftable = false;

    /** Carpet SLS Addition original **/
    @Rule(categories = {SLSA, RuleCategory.FEATURE})
    public static boolean spectatorCannotUseLeash = false;
}
