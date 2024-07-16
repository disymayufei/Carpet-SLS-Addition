# Bot 系统

SLS 添加提供了一个机器人系统，具有更好的权限控制和更多功能。

## 权限

您可以使用 LuckPerms 或任何其他权限插件来控制机器人的权限。
机器人的权限节点为 `slsaddition.command.bot`。

## Carpet 规则

### botPrefix (carpet 规则)

机器人名称的前缀。设置为 `#none` 以禁用。

### botMaxOnlineTime (carpet 规则)

机器人的最大在线时间（秒）。设置为 0 以禁用。
如果超过此时间，机器人将被踢出。

# 规则
### obtainableReinforcedDeepSlate

如果一个下落的铁砧落在 9 堆深板岩块上，它将变成强化深板岩块。

### creativeNoInfinitePickup

在创造模式下，如果你的物品栏已满，你不能捡起物品。

### noBatSpawning

蝙蝠不会自然生成。

### canUseHatCommand

玩家可以使用 `/hat` 命令将一个方块戴在头上。

### canUseSitCommand

玩家可以使用 `/sit` 命令坐在楼梯方块上。

### creativeObeyEnchantmentRule

在创造模式下，将遵守生存模式下的附魔规则。

### fakePlayersNotOccupiedSleepQuota

假玩家不会占用睡眠配额，睡眠的假玩家不会跳过夜晚。

### emptyShulkerBoxStack

潜影盒堆叠时将为空。

### playerSit

玩家可以通过三次按下潜行键来坐下。

### endermanCanPickUpMushroom

末影人可以捡起蘑菇。默认为 true。

### oldRedstoneConnectionLogic

使用旧的红石连接逻辑，红石可以连接到活板门上的红石线。

### optimizedOnDragonRespawn

优化了龙的重生逻辑，用于末地石农场。从 Carpet AMS Addition 移植。

### elytraCraftable

在制作配方中，鞘翅不会被消耗。这允许玩家制作额外的鞘翅。

这不会为鞘翅添加配方，要启用 StarLight Server 的默认配方，请执行：

```mcfunction
datapack enable elytra-craftable
```

### spectatorCannotUseLeash

当玩家成为旁观者时，拴绳将断开。

### armadilloImmediateDespawns

当犰狳距离玩家超过 128 格时，它们将立即消失。
