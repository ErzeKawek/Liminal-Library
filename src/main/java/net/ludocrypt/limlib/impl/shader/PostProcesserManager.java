package net.ludocrypt.limlib.impl.shader;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;

public final class PostProcesserManager implements ResourceManagerReloadListener {

	public static final PostProcesserManager INSTANCE = new PostProcesserManager();
	public static final ResourceLocation RESOURCE_KEY = new ResourceLocation("limlib:shaders");

	private final Set<PostProcesser> shaders = new ReferenceOpenHashSet<>();

	public PostProcesser find(ResourceLocation location) {
		PostProcesser ret = new PostProcesser(location);
		shaders.add(ret);
		return ret;
	}

	public void onResolutionChanged(int newWidth, int newHeight) {

		if (!shaders.isEmpty()) {

			for (PostProcesser shader : shaders) {

				if (shader.isInitialized()) {
					Minecraft client = Minecraft.getInstance();
					shader.shader
						.resize(client.getWindow().getWidth(),
							client.getWindow().getHeight());
				}

			}

		}

	}

	public ResourceLocation getQuiltId() {
		return RESOURCE_KEY;
	}

	public void reload(ResourceManager mgr) {

		for (PostProcesser shader : shaders) {
			shader.init(mgr);
		}

	}

	public void dispose(PostProcesser shader) {
		shader.release();
		shaders.remove(shader);
	}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {

	}
}
