package com.iekeiclient.management.mod.impl.hud;

import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.api.hud.SimpleHUDMod;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.language.I18n;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArmorStatusMod extends SimpleHUDMod {

	public ArmorStatusMod() {
		super("mod.armorstatus.name", "mod.armorstatus.description", Icon.INVENTORY_2);
	}

	public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> draw();

	@Override
	public String getText() {
		if (client.player == null) {
			return I18n.get("text.armor") + ": -";
		}

		return I18n.get("text.armor") + ": " + getDurability(EquipmentSlot.HEAD) + " | "
				+ getDurability(EquipmentSlot.CHEST) + " | " + getDurability(EquipmentSlot.LEGS) + " | "
				+ getDurability(EquipmentSlot.FEET);
	}

	@Override
	public String getIcon() {
		return Icon.INVENTORY_2;
	}

	private String getDurability(EquipmentSlot slot) {
		ItemStack stack = client.player.getEquippedStack(slot);
		if (stack.isEmpty() || !stack.isDamageable()) {
			return "-";
		}

		int remaining = stack.getMaxDamage() - stack.getDamage();
		return Math.max(0, remaining * 100 / stack.getMaxDamage()) + "%";
	}
}
