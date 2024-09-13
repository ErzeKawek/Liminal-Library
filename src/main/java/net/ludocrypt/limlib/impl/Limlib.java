package net.ludocrypt.limlib.impl;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ludocrypt.limlib.api.LimlibRegistrar;
import net.ludocrypt.limlib.api.LimlibWorld;
import net.ludocrypt.limlib.api.effects.post.EmptyPostEffect;
import net.ludocrypt.limlib.api.effects.post.PostEffect;
import net.ludocrypt.limlib.api.effects.post.StaticPostEffect;
import net.ludocrypt.limlib.api.effects.sky.DimensionEffects;
import net.ludocrypt.limlib.api.effects.sky.EmptyDimensionEffects;
import net.ludocrypt.limlib.api.effects.sky.StaticDimensionEffects;
import net.ludocrypt.limlib.api.effects.sound.distortion.DistortionEffect;
import net.ludocrypt.limlib.api.effects.sound.distortion.StaticDistortionEffect;
import net.ludocrypt.limlib.api.effects.sound.reverb.ReverbEffect;
import net.ludocrypt.limlib.api.effects.sound.reverb.StaticReverbEffect;
import net.ludocrypt.limlib.api.skybox.EmptySkybox;
import net.ludocrypt.limlib.api.skybox.Skybox;
import net.ludocrypt.limlib.api.skybox.TexturedSkybox;
import net.ludocrypt.limlib.impl.debug.DebugNbtChunkGenerator;

@Mod("limlib")
public class Limlib {

	public static final Logger LOGGER = LoggerFactory.getLogger("Limlib");

	public void onInitialize(ModContainer mod) {
		LimlibWorld.load();
		Registry.register(ReverbEffect.REVERB_EFFECT_CODEC, new ResourceLocation("limlib", "static"), StaticReverbEffect.CODEC);
		Registry
			.register(DistortionEffect.DISTORTION_EFFECT_CODEC, new ResourceLocation("limlib", "static"),
				StaticDistortionEffect.CODEC);
		Registry
			.register(DimensionEffects.DIMENSION_EFFECTS_CODEC, new ResourceLocation("limlib", "static"),
				StaticDimensionEffects.CODEC);
		Registry
			.register(DimensionEffects.DIMENSION_EFFECTS_CODEC, new ResourceLocation("limlib", "empty"),
				EmptyDimensionEffects.CODEC);
		Registry.register(PostEffect.POST_EFFECT_CODEC, new ResourceLocation("limlib", "static"), StaticPostEffect.CODEC);
		Registry.register(PostEffect.POST_EFFECT_CODEC, new ResourceLocation("limlib", "empty"), EmptyPostEffect.CODEC);
		Registry.register(Skybox.SKYBOX_CODEC, new ResourceLocation("limlib", "empty"), EmptySkybox.CODEC);
		Registry.register(Skybox.SKYBOX_CODEC, new ResourceLocation("limlib", "textured"), TexturedSkybox.CODEC);
		Registry
			.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation("limlib", "debug_nbt_chunk_generator"),
				DebugNbtChunkGenerator.CODEC);
//		QuiltLoader
//			.getEntrypoints(LimlibRegistrar.ENTRYPOINT_KEY, LimlibRegistrar.class)
//			.forEach(LimlibRegistrar::registerHooks);
	}

}
