/*
 * Satin
 * Copyright (C) 2019-2023 Ladysnake
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
package ladysnake.satin;

import ladysnake.satin.api.event.ResolutionChangeCallback;
import ladysnake.satin.impl.ReloadableShaderEffectManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.STABLE;

@Mod(Satin.MOD_ID)
public class Satin {
    public static final String MOD_ID = "satin";
    public static final Logger LOGGER = LogManager.getLogger("Satin");

    /**
     * Checks if OpenGL shaders are disabled in the current game instance.
     * Currently, this only checks if the hardware supports them, however
     * in the future it may check a client option as well.
     */
    @API(status = STABLE)
    public static boolean areShadersDisabled() {
        return false;
    }

    public Satin() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            initClient();
        }
    }

    private static void initClient() {
        ResolutionChangeCallback.EVENT.register(ReloadableShaderEffectManager.INSTANCE);
        if (ModList.get().isLoaded("optifabric") || ModList.get().isLoaded("optifine")) {
            LOGGER.warn("[Satin] Optifine present in the instance, custom entity post process shaders will not work");
        }
    }
}
