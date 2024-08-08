# Bot

SLS addition provides a bot system, with better permission control and more features.

## Permission

You can use LuckPerms or any other permission plugin to control the permission of the bot.
The permission node of the bot is `slsaddition.command.bot`.

## Carpet rules

### botPrefix (carpet rule)

The name prefix of bots. set to `#none` to disable.

### botMaxOnlineTime (carpet rule)

The maximum online time of a bot in seconds. set to 0 to disable.
The bot will be kicked if it exceeds this time.

# Rules
### obtainableReinforcedDeepSlate

If a falling anvil lands on 9 stacks of deepslate block, it will turn into a reinforced deepslate block.

### creativeNoInfinitePickup

In creative mode, you can't pick up items if your inventory is full.

### noBatSpawning

Bats will not spawn naturally.

### canUseHatCommand

Players can use the `/hat` command to wear a block on their head.

### canUseSitCommand

Players can use the `/sit` command to sit on a stair block.

### creativeObeyEnchantmentRule

In creative mode, the enchantment rule will be obeyed as in survival mode.

### fakePlayersNotOccupiedSleepQuota

Fake players will not occupy the sleep quota, and sleeping fake players will not skip the night.

### emptyShulkerBoxStack

Shulker boxes will be empty when stacked.

### playerSit

Players can sit by pressing sneaking key three times.

### endermanCanPickUpMushroom

Endermen can pick up mushrooms. Default is true.

### oldRedstoneConnectionLogic

Use the old redstone connection logic, the redstone can connect to redstone wries on trapdoors.

### optimizedOnDragonRespawn

Optimized the dragon respawn logic, for endstone farms. Ported from Carpet AMS Addition.

### elytraCraftable

Elytra in crafting recipes won't be consumed. This allows players to craft extra elytras.

This will **NOT** add a recipe for elytra, to enable the default recipe by StarLight Server, execute:
```mcfunction
datapack enable elytra-craftable
```

### spectatorCannotUseLeash

When player becomes spectator, the leash will be detached.

### armadilloImmediateDespawns

Armadillos will despawn immediately if they are 128 blocks away from the player.
