package com.iekeiclient.skia.context;

import java.util.function.Consumer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;

import com.mojang.blaze3d.opengl.GlStateManager;

import io.github.humbleui.skija.BackendRenderTarget;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.ColorSpace;
import io.github.humbleui.skija.DirectContext;
import io.github.humbleui.skija.Surface;
import io.github.humbleui.skija.SurfaceColorFormat;
import io.github.humbleui.skija.SurfaceOrigin;
import net.minecraft.client.texture.GlTexture;
import net.minecraft.client.MinecraftClient;

public class SkiaContext {

	private static DirectContext context = null;
	private static Surface surface;
	private static BackendRenderTarget renderTarget;
	private static int framebuffer;

	public static Canvas getCanvas() {
		return surface.getCanvas();
	}

	public static void createSurface(int width, int height) {

		if (context == null) {
			context = DirectContext.makeGL();
		}

		if (surface != null) {
			surface.close();
			surface = null;
		}

		if (renderTarget != null) {
			renderTarget.close();
			renderTarget = null;
		}

		if (framebuffer != 0) {
			GL30.glDeleteFramebuffers(framebuffer);
		}

		if (!(MinecraftClient.getInstance().getFramebuffer().getColorAttachment() instanceof GlTexture texture)) {
			throw new IllegalStateException("Minecraft framebuffer is not backed by an OpenGL texture");
		}

		int previousFramebuffer = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING);
		framebuffer = GL30.glGenFramebuffers();
		try {
			GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer);
			GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D,
					texture.getGlId(), 0);
			renderTarget = BackendRenderTarget.makeGL(width, height, 0, 8, framebuffer, GL11.GL_RGBA8);
			surface = Surface.wrapBackendRenderTarget(context, renderTarget, SurfaceOrigin.BOTTOM_LEFT,
					SurfaceColorFormat.RGBA_8888, ColorSpace.getSRGB());
		} finally {
			GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, previousFramebuffer);
		}
	}

	public static void draw(Consumer<Canvas> drawingLogic) {

		GlStateManager._pixelStore(GL11.GL_UNPACK_ROW_LENGTH, 0);
		GlStateManager._pixelStore(GL11.GL_UNPACK_SKIP_PIXELS, 0);
		GlStateManager._pixelStore(GL11.GL_UNPACK_SKIP_ROWS, 0);
		GlStateManager._pixelStore(GL11.GL_UNPACK_ALIGNMENT, 4);
		GL11.glClearColor(0f, 0f, 0f, 0f);
		context.resetGLAll();

		Canvas canvas = getCanvas();
		drawingLogic.accept(canvas);

		context.flush();

		GL33.glBindSampler(0, 0);
		GlStateManager._disableBlend();
		GlStateManager._blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL33.glBlendEquation(GL33.GL_FUNC_ADD);
		GlStateManager._colorMask(true, true, true, true);
		GlStateManager._depthMask(true);
		GlStateManager._disableScissorTest();
		GL11.glDisable(GL11.GL_STENCIL_TEST);
		GlStateManager._disableDepthTest();
		GlStateManager._activeTexture(GL13.GL_TEXTURE0);
		GlStateManager._disableCull();
	}

	public static DirectContext getContext() {
		return context;
	}
}
