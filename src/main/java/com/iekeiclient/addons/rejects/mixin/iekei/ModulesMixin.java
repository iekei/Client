package com.iekeiclient.addons.rejects.mixin.iekei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iekeiclient.addons.rejects.utils.RejectsConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.iekeiclient.engine.systems.modules.Category;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;

@Mixin(Modules.class)
public class ModulesMixin {
    @Shadow(remap = false)
    @Final
    private Map<Category, List<Module>> groups;

    @Inject(method = "getGroup", at=@At("HEAD"), cancellable = true, remap = false)
    private void onGetGroup(Category category, CallbackInfoReturnable<List<Module>> cir) {
        Set<String> hiddenModules = RejectsConfig.get().hiddenModules;
        if (hiddenModules.isEmpty()) return;

        List<Module> foundModules = groups.computeIfAbsent(category, category1 -> new ArrayList<>());
        foundModules.removeIf(m -> hiddenModules.contains(m.name));

        cir.setReturnValue(foundModules);
    }
}
