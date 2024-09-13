package net.ludocrypt.limlib.api.effects.sky;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class SkyPropertiesCreator {

	public static DimensionSpecialEffects create(float cloudHeight, boolean alternateSkyColor, String skyType,
												 boolean brightenLighting, boolean darkened, boolean thickFog) {
		return new DimensionSpecialEffects(cloudHeight, alternateSkyColor, DimensionSpecialEffects.SkyType.valueOf(skyType), brightenLighting,
			darkened) {

			public Vec3 adjustFogColor(Vec3 color, float sunHeight) {
				return color;
			}

			public boolean useThickFog(int camX, int camY) {
				return thickFog;
			}

		};
	}

}
