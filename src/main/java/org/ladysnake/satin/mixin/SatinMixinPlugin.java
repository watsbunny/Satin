/*
 * Satin
 * Copyright (C) 2019-2024 Ladysnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; If not, see <https://www.gnu.org/licenses>.
 */
package org.ladysnake.satin.mixin;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class SatinMixinPlugin implements IMixinConfigPlugin {
    private static final Logger LOGGER = LogManager.getLogger("Satin");

    /**
     * Lazily resolved: this plugin can be constructed by Mixin during the early display window setup,
     * before {@link ModList} has been initialised (ModList.get() returns null at that point).
     */
    private static Boolean renderLayerMixinsAllowed;

    private static synchronized boolean renderLayerMixinsAllowed() {
        if (renderLayerMixinsAllowed != null) {
            return renderLayerMixinsAllowed;
        }

        boolean allow = true;
        ModList loaded = ModList.get();
        if (loaded != null) {
            if (loaded.isLoaded("canvas")) {
                LOGGER.warn("[Satin] Canvas is present, custom block renders will not work");
                allow = false;
            } else if (loaded.isLoaded("iris")) {
                LOGGER.warn("[Satin] Iris is present, custom block renders will not work");
                allow = false;
            } else if (loaded.isLoaded("sodium")) {
                LOGGER.warn("[Satin] Sodium is present, custom block renders may not work");
            }
        } else {
            // Mixin plugin loading happens before ModList is built, but the loading mod list is already populated.
            LoadingModList loading = LoadingModList.get();
            if (loading != null) {
                if (loading.getModFileById("canvas") != null) {
                    LOGGER.warn("[Satin] Canvas is present, custom block renders will not work");
                    allow = false;
                } else if (loading.getModFileById("iris") != null) {
                    LOGGER.warn("[Satin] Iris is present, custom block renders will not work");
                    allow = false;
                } else if (loading.getModFileById("sodium") != null) {
                    LOGGER.warn("[Satin] Sodium is present, custom block renders may not work");
                }
            }
        }

        renderLayerMixinsAllowed = allow;
        return allow;
    }

    @Override
    public void onLoad(String mixinPackage) {
        // NO-OP
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("blockrenderlayer")) {
            return renderLayerMixinsAllowed();
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // NO-OP
    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // NO-OP
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // NO-OP
    }
}
