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
package ladysnake.satin.api.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;

@FunctionalInterface
public interface PostWorldRenderCallbackV2 {
    /**
     * Fired after Minecraft has rendered everything in the world, before it renders hands, HUDs and GUIs.
     */
    Event<PostWorldRenderCallbackV2> EVENT = EventFactory.createArrayBacked(PostWorldRenderCallbackV2.class,
            (listeners) -> (matrices, camera, tickDelta, nanoTime) -> {
                PostWorldRenderCallback.EVENT.invoker().onWorldRendered(camera, tickDelta, nanoTime);
                for (PostWorldRenderCallbackV2 handler : listeners) {
                    handler.onWorldRendered(matrices, camera, tickDelta, nanoTime);
                }
            });

    void onWorldRendered(PoseStack matrices, Camera camera, float tickDelta, long nanoTime);
}
