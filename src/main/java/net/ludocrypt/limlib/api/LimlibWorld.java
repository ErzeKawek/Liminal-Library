package net.ludocrypt.limlib.api;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.registries.RegistryBuilder;

import java.rmi.registry.Registry;

public class LimlibWorld {

	public static final ResourceKey<net.minecraft.core.Registry<LimlibWorld>> LIMLIB_WORLD_KEY = ResourceKey
		.createRegistryKey(new ResourceLocation("limlib", "limlib_world"));
	public static final MappedRegistry<LimlibWorld> LIMLIB_WORLD = RegistryBuilder
		.createSimple(LIMLIB_WORLD_KEY)
		.attribute(RegistryAttribute.SYNCED)
		.buildAndRegister();

	private Supplier<DimensionType> dimensionTypeSupplier;
	private Function<RegistryProvider, LevelStem> dimensionOptionsSupplier;

	public LimlibWorld(Supplier<DimensionType> dimensionTypeSupplier,
			Function<RegistryProvider, LevelStem> dimensionOptionsSupplier) {
		this.dimensionTypeSupplier = Suppliers.memoize(dimensionTypeSupplier);
		this.dimensionOptionsSupplier = dimensionOptionsSupplier;
	}

	public Supplier<DimensionType> getDimensionTypeSupplier() {
		return dimensionTypeSupplier;
	}

	public Function<RegistryProvider, LevelStem> getDimensionOptionsSupplier() {
		return dimensionOptionsSupplier;
	}

	public static interface RegistryProvider {

		public <T> HolderGetter<T> get(RegistryKey<Registry<T>> key);

	}

	// Load the class early so our variables are set
	public static void load() {

	}

}
