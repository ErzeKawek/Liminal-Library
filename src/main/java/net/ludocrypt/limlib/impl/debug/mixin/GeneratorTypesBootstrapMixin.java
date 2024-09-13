package net.ludocrypt.limlib.impl.debug.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ludocrypt.limlib.impl.debug.DebugNbtChunkGenerator;
import net.ludocrypt.limlib.impl.debug.DebugWorld;

@Mixin(targets = "net/minecraft/client/world/GeneratorTypes$Bootstrap")
public abstract class GeneratorTypesBootstrapMixin {

	@Shadow
	private Biome biome;
	@Shadow
	private DimensionType overworld;

	@Inject(method = "Lnet/minecraft/client/world/GeneratorTypes$Bootstrap;method_41600()V", at = @At("TAIL"))
	public void limlib$addDimensionOpions(CallbackInfo ci) {
		this
			.addDimensionGenerator(DebugWorld.DEBUG_KEY, new WorldPreset(this.overworld,
				new DebugNbtChunkGenerator(this.biome.getHolderOrThrow(Biomes.THE_VOID))));
	}

	abstract void addDimensionGenerator(ResourceKey<WorldPreset> generator, LevelStem dimension);

}
