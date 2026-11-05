package com.iekeiclient.addons.rejects.settings;

import com.iekeiclient.engine.gui.GuiTheme;
import com.iekeiclient.engine.gui.WindowScreen;
import com.iekeiclient.engine.gui.widgets.containers.WTable;
import com.iekeiclient.engine.gui.widgets.pressable.WCheckbox;
import com.iekeiclient.engine.utils.Utils;
import net.minecraft.world.GameMode;

import java.util.List;

public class GameModeListSettingScreen extends WindowScreen {
    private final GameModeListSetting setting;
    private final WTable table;

    public GameModeListSettingScreen(GuiTheme theme, GameModeListSetting setting) {
        super(theme, "Select Gamemodes");
        this.setting = setting;
        table = super.add(theme.table()).expandX().widget();
    }

    @Override
    public void initWidgets() {
        List<GameMode> gms = setting.get();
        for (GameMode gameMode : GameMode.values()) {
            table.add(theme.label(Utils.nameToTitle(gameMode.getId()))).expandCellX();

            boolean contains = setting.get().contains(gameMode);
            WCheckbox checkbox = table.add(theme.checkbox(contains)).widget();
            checkbox.action = () -> {
                if (contains) {
                    gms.remove(gameMode);
                } else {
                    gms.add(gameMode);
                }
            };

            table.row();
        }
    }
}
