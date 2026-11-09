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
package org.ladysnake.satin.mixin.client.render;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderType.class)
public abstract class RenderLayerMixin extends RenderStateShard {
    public RenderLayerMixin(String name, Runnable beginAction, Runnable endAction) {
        super(name, beginAction, endAction);
    }

    @Mixin(RenderType.CompositeState.class)
    public interface MultiPhaseParametersAccessor {
        @Accessor
        EmptyTextureStateShard getTextureState();

        @Accessor
        RenderStateShard.ShaderStateShard getShaderState();

        @Accessor
        RenderStateShard.TransparencyStateShard getTransparencyState();

        @Accessor
        RenderStateShard.DepthTestStateShard getDepthTestState();

        @Accessor
        RenderStateShard.CullStateShard getCullState();

        @Accessor
        RenderStateShard.LightmapStateShard getLightmapState();

        @Accessor
        RenderStateShard.OverlayStateShard getOverlayState();

        @Accessor
        RenderStateShard.LayeringStateShard getLayeringState();

        @Accessor
        RenderStateShard.OutputStateShard getOutputState();

        @Accessor
        RenderStateShard.TexturingStateShard getTexturingState();

        @Accessor
        RenderStateShard.WriteMaskStateShard getWriteMaskState();

        @Accessor
        RenderStateShard.LineStateShard getLineState();

        @Accessor
        RenderType.OutlineProperty getOutlineProperty();
    }
}
