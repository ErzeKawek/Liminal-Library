package net.ludocrypt.limlib.render.access;

import java.util.Map;

import net.minecraft.util.Identifier;

public interface UnbakedModelAccess {

	public Map<Identifier, Identifier> getSubModels();

}