package com.iekeiclient.mixin.mixins.minecraft.client.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.iekeiclient.libraries.browser.JCefBrowser;
import com.iekeiclient.management.mod.impl.hud.WebBrowserMod;
import com.iekeiclient.utils.mouse.MouseUtils;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;

@Mixin(ChatScreen.class)
public class MixinChatScreen extends Screen {

	@Unique
	private boolean isInWebBrowser;

	protected MixinChatScreen(Text title) {
		super(title);
	}

	@Inject(method = "mouseClicked", at = @At("HEAD"))
	private void onMouseClicked(Click click, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {

		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendMousePress(WebBrowserMod.getInstance().getMouseX(click.x()),
					WebBrowserMod.getInstance().getMouseY(click.y()), click.button());
			JCefBrowser.getBrowser().setFocus(true);
		}
	}

	@Override
	public boolean mouseReleased(Click click) {
		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendMouseRelease(WebBrowserMod.getInstance().getMouseX(click.x()),
					WebBrowserMod.getInstance().getMouseY(click.y()), click.button());
			JCefBrowser.getBrowser().setFocus(true);
		}
		return super.mouseReleased(click);
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendMouseMove(WebBrowserMod.getInstance().getMouseX(mouseX),
					WebBrowserMod.getInstance().getMouseY(mouseY));
			isInWebBrowser = MouseUtils.isInside(mouseX, mouseY, WebBrowserMod.getInstance().getX(),
					WebBrowserMod.getInstance().getY(), WebBrowserMod.getInstance().getPosition().getWidth(),
					WebBrowserMod.getInstance().getPosition().getHeight());
		} else {
			isInWebBrowser = false;
		}
		super.mouseMoved(mouseX, mouseY);
	}

	@Inject(method = "mouseScrolled", at = @At("HEAD"))
	private void onMouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount,
			CallbackInfoReturnable<Boolean> cir) {
		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendMouseWheel(WebBrowserMod.getInstance().getMouseX(mouseX),
					WebBrowserMod.getInstance().getMouseY(mouseY), verticalAmount);
		}
	}

	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void onKeyPressed(KeyInput input, CallbackInfoReturnable<Boolean> cir) {
		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendKeyPress(input.key(), input.scancode(), input.modifiers());
			JCefBrowser.getBrowser().setFocus(true);
			if(isInWebBrowser) {
				cir.setReturnValue(true);
			}
		}
	}

	@Override
	public boolean keyReleased(KeyInput input) {
		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendKeyRelease(input.key(), input.scancode(), input.modifiers());
			JCefBrowser.getBrowser().setFocus(true);
			if(isInWebBrowser) {
				return true;
			}
		}
		return super.keyReleased(input);
	}

	@Override
	public boolean charTyped(CharInput input) {

		if (!input.isValidChar()) {
			return false;
		}

		if (JCefBrowser.getBrowser() != null) {
			JCefBrowser.getBrowser().sendKeyTyped((char) input.codepoint(), input.modifiers());
			JCefBrowser.getBrowser().setFocus(true);
			if(isInWebBrowser) {
				return true;
			}
		}

		return super.charTyped(input);
	}
}
