# Changelog

## 1.1.2

### Additions
- Added nl_BE translation (thanks, Sv3r!);
- (1.19) Added Mangrove Cabinet;
- (1.19) Added a recipe for making Packed Mud with Straw. One unit can pack 2 Mud blocks, being twice as efficient than Wheat;
- (1.19) Added `farmersdelight:add_features_by_filter` biome modifier:
  - A more granular modifier than `forge:add_features`, with allowed and denied biome filters, as well as temperature ranges.
  - All extra filters aside from `allowed_biomes` are optional.

### Updates
- Updated the palette and textures for Ropes and Safety Nets a bit;
- Moved a few registrations to DeferredRegister, in preparation for future ports;
- Updated nl_NL translation (thanks, Sv3r!);

### Removals
- (1.19) Removed the config options for enabling/disabling Wild Crop generation, as they're now done via datapacks.

### Fixes
- Fix entities being able to pathfind over lit stoves, causing them to take damage (common victims: villagers, pets etc);
- Fix Cooking Pot resetting cooking progress, and stalling on a valid recipe, when unloading and reloading its chunk;
- Fix loot item function registries sometimes causing a race condition during mod loading;
- Fix Farmer villagers giving too much discount after every trade, when buying FD crops;
- Fix wrong lang keys in pt_BR translation;
