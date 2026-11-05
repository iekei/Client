package com.iekeiclient.addons.rejects.mixin.iekei;

import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.renderer.Renderer2D;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiRenderer.class)
public interface GuiRendererAccessor {
    @Accessor(value = "r", remap = false)
    Renderer2D getRenderer2D();
}
