package com.iekeiclient.addons.shama.modules;

import com.iekeiclient.engine.events.world.TickEvent;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.KeybindSetting;
import com.iekeiclient.engine.settings.Setting;
import com.iekeiclient.engine.settings.SettingGroup;
import com.iekeiclient.engine.settings.StringSetting;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.utils.misc.Keybind;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.slot.Slot;
import org.lwjgl.glfw.GLFW;
import com.iekeiclient.addons.shama.mixin.HandledScreenAccessor;

/**
 * NBT Adder — Horion-style item NBT tooling for creative, adapted to Java's
 * data-component system (since 1.20.5 items use components, not raw NBT):
 *  - Middle-click any item in an inventory to copy its full NBT to the clipboard.
 *  - Press apply-key to spawn that NBT onto the item in your hand (creative only).
 *  - Or type your own component SNBT in custom-nbt to add custom data / enchants.
 *
 * SNBT shape on Java looks like:
 *   {id:"minecraft:diamond_sword",count:1,components:{"minecraft:enchantments":{levels:{"minecraft:sharpness":255}}}}
 */
public class NbtAdder extends Module {
    private static String lastCopied = "";

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> copyMiddleClick = sgGeneral.add(new BoolSetting.Builder()
        .name("middle-click-copy")
        .description("Middle-click an item in any inventory to copy its full NBT to the clipboard.")
        .defaultValue(true)
        .build()
    );

    private final Setting<String> customNbt = sgGeneral.add(new StringSetting.Builder()
        .name("custom-nbt")
        .description("Component SNBT to apply with apply-key. Blank = use whatever you last middle-clicked / your clipboard.")
        .defaultValue("")
        .build()
    );

    private final Setting<Keybind> applyKey = sgGeneral.add(new KeybindSetting.Builder()
        .name("apply-key")
        .description("Apply the NBT to the item in your hand (creative only).")
        .defaultValue(Keybind.fromKey(GLFW.GLFW_KEY_N))
        .build()
    );

    private boolean applyWas;
    private boolean midWas;

    public NbtAdder() {
        super(com.iekeiclient.addons.shama.ShamaAddon.PLAYER, "nbt-adder++", "Copy item NBT by middle-click and apply custom NBT/components to held items (creative). Java component-system equivalent of Horion's NBT editor.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        boolean pressed = applyKey.get().isPressed();
        if (pressed && !applyWas) applyNbt();
        applyWas = pressed;

        // Middle-click copy via raw GLFW button state. Reading the physical button
        // and the hovered slot (HandledScreenAccessor) is gamemode-agnostic and works
        // on servers in survival — unlike the vanilla clone action, which is creative-only.
        if (copyMiddleClick.get() && mc.currentScreen instanceof HandledScreen<?> screen) {
            boolean midDown = GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS;
            if (midDown && !midWas) {
                Slot slot = ((HandledScreenAccessor) screen).shama$focusedSlot();
                if (slot != null && slot.hasStack()) copyStackNbt(slot.getStack());
            }
            midWas = midDown;
        } else {
            midWas = false;
        }
    }

    private void applyNbt() {
        if (mc.player == null || mc.interactionManager == null || mc.world == null) return;
        if (!mc.player.getAbilities().creativeMode) {
            error("Creative mode only — survival item NBT is server-authoritative.");
            return;
        }
        String snbt = customNbt.get();
        if (snbt == null || snbt.isBlank()) snbt = lastCopied;
        if (snbt == null || snbt.isBlank()) snbt = mc.keyboard.getClipboard();
        if (snbt == null || snbt.isBlank()) {
            error("Nothing to apply — middle-click an item first or set custom-nbt.");
            return;
        }
        try {
            NbtCompound nbt = com.iekeiclient.addons.shama.nbt.Snbt.parse(snbt);
            ItemStack stack = com.iekeiclient.addons.shama.nbt.Snbt.itemFromNbt(nbt);
            if (stack.isEmpty()) {
                error("That NBT didn't parse into an item.");
                return;
            }
            int slot = 36 + mc.player.getInventory().getSelectedSlot();
            mc.interactionManager.clickCreativeStack(stack, slot);
            info("Applied NBT to held item.");
        } catch (Exception e) {
            error("Bad NBT: " + e.getMessage());
        }
    }

    /** Copy an item's full NBT to the clipboard (invoked on a middle-click over a slot). */
    public static void copyStackNbt(ItemStack stack) {
        NbtAdder module = Modules.get().get(NbtAdder.class);
        if (module == null || !module.isActive() || !module.copyMiddleClick.get()) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || stack == null || stack.isEmpty()) return;
        try {
            NbtElement enbt = com.iekeiclient.addons.shama.nbt.Snbt.itemToNbt(stack);
            String snbt = enbt.toString();
            mc.keyboard.setClipboard(snbt);
            lastCopied = snbt;
            module.info("Copied NBT of " + stack.getName().getString() + " to clipboard.");
        } catch (Exception ignored) {
        }
    }
}
