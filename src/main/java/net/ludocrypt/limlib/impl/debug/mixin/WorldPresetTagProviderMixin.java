package net.ludocrypt.limlib.impl.debug.mixin;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.tags.WorldPresetTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ludocrypt.limlib.impl.debug.DebugWorld;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.data.DataPackOutput;
import net.minecraft.data.server.tag.AbstractTagProvider;
import net.minecraft.data.server.tag.WorldPresetTagProvider;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.HolderLookup.Provider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.WorldPresetTags;

@Mixin(WorldPresetTagsProvider.class)
public abstract class WorldPresetTagProviderMixin extends TagsProvider<WorldPreset> {

	protected WorldPresetTagProviderMixin(PackOutput output, ResourceKey<? extends Registry<WorldPreset>> key,
										  CompletableFuture<Provider> lookupProvider) {
		super(output, key, lookupProvider);
	}

	@Inject(method = "configure", at = @At("TAIL"))
	protected void limlib$configure(HolderLookup.Provider lookup, CallbackInfo ci) {
		this.getOrCreateTagBuilder(WorldPresetTags.EXTENDED).add(DebugWorld.DEBUG_KEY);
	}

}
