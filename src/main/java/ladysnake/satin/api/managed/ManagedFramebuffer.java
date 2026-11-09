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
package ladysnake.satin.api.managed;

import org.apiguardian.api.API;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Window;
import java.util.function.Consumer;

@API(status = API.Status.EXPERIMENTAL, since = "1.4.0")
public interface ManagedFramebuffer {
    @Nullable
    RenderTarget getFramebuffer();

    /**
     * Begins a write operation on this framebuffer.
     *
     * <p>If the operation is successful, every subsequent draw call will write to this framebuffer.
     *
     * @param updateViewport whether binding this framebuffer should call {@link com.mojang.blaze3d.systems.RenderSystem#viewport(int, int, int, int)}
     */
    void beginWrite(boolean updateViewport);

    /**
     * Copies the depth texture from another framebuffer to this framebuffer.
     *
     * @param buffer the framebuffer to copy depth from
     */
    void copyDepthFrom(RenderTarget buffer);

    /**
     * Draws this framebuffer, scaling to the default framebuffer's
     * {@linkplain Window#getWidth() width} and {@linkplain Window#getHeight() height}.
     */
    void draw();

    void draw(int width, int height, boolean disableBlend);

    /**
     * Clears the content of this framebuffer.
     */
    void clear();

    void clear(boolean swallowErrors);

    /**
     * Gets a simple {@link RenderType} that is functionally identical to {@code baseLayer},
     * but with a different {@link RenderStateShard.OutputStateShard} that binds this framebuffer.
     *
     * @param baseLayer the layer to copy
     * @return a render layer using this framebuffer
     * @see ladysnake.satin.api.util.RenderLayerHelper#copy(RenderType, String, Consumer)
     */
    RenderType getRenderLayer(RenderType baseLayer);
}
