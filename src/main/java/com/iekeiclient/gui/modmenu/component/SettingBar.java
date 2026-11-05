package com.iekeiclient.gui.modmenu.component;

import java.io.File;

import com.iekeiclient.Iekei;
import com.iekeiclient.animation.SimpleAnimation;
import com.iekeiclient.libraries.material3.hct.Hct;
import com.iekeiclient.management.color.api.ColorPalette;
import com.iekeiclient.management.mod.settings.Setting;
import com.iekeiclient.management.mod.settings.impl.BooleanSetting;
import com.iekeiclient.management.mod.settings.impl.ComboSetting;
import com.iekeiclient.management.mod.settings.impl.FileSetting;
import com.iekeiclient.management.mod.settings.impl.HctColorSetting;
import com.iekeiclient.management.mod.settings.impl.KeybindSetting;
import com.iekeiclient.management.mod.settings.impl.NumberSetting;
import com.iekeiclient.management.mod.settings.impl.StringSetting;
import com.iekeiclient.skia.Skia;
import com.iekeiclient.skia.font.Fonts;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.ui.component.Component;
import com.iekeiclient.ui.component.handler.impl.ComboButtonHandler;
import com.iekeiclient.ui.component.handler.impl.FileSelectorHandler;
import com.iekeiclient.ui.component.handler.impl.HctColorPickerHandler;
import com.iekeiclient.ui.component.handler.impl.KeybindHandler;
import com.iekeiclient.ui.component.handler.impl.SliderHandler;
import com.iekeiclient.ui.component.handler.impl.SwitchHandler;
import com.iekeiclient.ui.component.handler.impl.TextHandler;
import com.iekeiclient.ui.component.impl.ComboButton;
import com.iekeiclient.ui.component.impl.FileSelector;
import com.iekeiclient.ui.component.impl.HctColorPicker;
import com.iekeiclient.ui.component.impl.Keybind;
import com.iekeiclient.ui.component.impl.Slider;
import com.iekeiclient.ui.component.impl.Switch;
import com.iekeiclient.ui.component.impl.text.TextField;
import com.iekeiclient.utils.language.I18n;

import net.minecraft.client.util.InputUtil;
import com.iekeiclient.engine.settings.BoolSetting;
import com.iekeiclient.engine.settings.DoubleSetting;
import com.iekeiclient.engine.settings.EnumSetting;
import com.iekeiclient.engine.settings.IntSetting;

public class SettingBar extends Component {

	private SimpleAnimation yAnimation = new SimpleAnimation();
	private String title, description, icon;
	private Component component;
	private com.iekeiclient.engine.settings.Setting<?> engineSetting;

	public SettingBar(Setting setting, float x, float y, float width) {
		super(x, y);
		this.title = setting.getName();
		this.description = setting.getDescription();
		this.icon = setting.getIcon();
		this.width = width;
		this.height = 68;

		if (setting instanceof BooleanSetting) {

			BooleanSetting bSetting = (BooleanSetting) setting;
			Switch switchComp = new Switch(x, y, bSetting.isEnabled());

			switchComp.setHandler(new SwitchHandler() {

				@Override
				public void onEnabled() {
					bSetting.setEnabled(true);
				}

				@Override
				public void onDisabled() {
					bSetting.setEnabled(false);
				}
			});

			component = switchComp;
		}

		if (setting instanceof NumberSetting) {

			NumberSetting nSetting = (NumberSetting) setting;
			Slider slider = new Slider(0, 0, 200, nSetting.getValue(), nSetting.getMinValue(), nSetting.getMaxValue(),
					nSetting.getStep());

			slider.setHandler(new SliderHandler() {

				@Override
				public void onValueChanged(float value) {
					nSetting.setValue(value);
				}
			});

			component = slider;
		}

		if (setting instanceof ComboSetting) {

			ComboSetting cSetting = (ComboSetting) setting;
			ComboButton button = new ComboButton(0, 0, cSetting.getOptions(), cSetting.getOption());

			button.setHandler(new ComboButtonHandler() {

				@Override
				public void onChanged(String option) {
					cSetting.setOption(option);
				}
			});

			component = button;
		}

		if (setting instanceof KeybindSetting) {

			KeybindSetting kSetting = (KeybindSetting) setting;
			Keybind bind = new Keybind(0, 0, kSetting.getKey());

			bind.setHandler(new KeybindHandler() {

				@Override
				public void onBinded(InputUtil.Key key) {
					kSetting.setKey(key);
				}
			});

			component = bind;
		}

		if (setting instanceof HctColorSetting) {

			HctColorSetting hSetting = (HctColorSetting) setting;
			HctColorPicker picker = new HctColorPicker(0, 0, hSetting.getHct());

			picker.setHandler(new HctColorPickerHandler() {

				@Override
				public void onPicking(Hct hct) {
					hSetting.setHct(hct);
				}
			});

			component = picker;
		}

		if (setting instanceof StringSetting) {

			StringSetting sSetting = (StringSetting) setting;
			TextField textField = new TextField(0, 0, 150, sSetting.getValue());

			textField.setHandler(new TextHandler() {

				@Override
				public void onTyped(String value) {
					sSetting.setValue(value);
				}
			});

			component = textField;
		}
		
		if(setting instanceof FileSetting) {
			
			FileSetting fSetting = (FileSetting) setting;
			FileSelector fileSelector = new FileSelector(0, 0, fSetting.getFile(), fSetting.getExtensions());
			
			fileSelector.setHandler(new FileSelectorHandler() {

				@Override
				public void onSelect(File file) {
					fSetting.setFile(file);
				}
			});
			
			component = fileSelector;
		}
	}

	public SettingBar(com.iekeiclient.engine.settings.Setting<?> setting, float x, float y, float width) {
		super(x, y);
		this.engineSetting = setting;
		this.title = setting.title;
		this.description = setting.description;
		this.icon = Icon.SETTINGS;
		this.width = width;
		this.height = 68;

		if (setting instanceof BoolSetting) {
			Switch switchComp = new Switch(x, y, (Boolean) setting.get());
			switchComp.setHandler(new SwitchHandler() {
				@Override
				public void onEnabled() {
					setting.parse("true");
				}

				@Override
				public void onDisabled() {
					setting.parse("false");
				}
			});
			component = switchComp;
		} else if (setting instanceof IntSetting intSetting
				&& !intSetting.noSlider
				&& isSliderRangeValid(intSetting.sliderMin, intSetting.sliderMax)) {
			Slider slider = new Slider(0, 0, 200, intSetting.get(), intSetting.sliderMin, intSetting.sliderMax, 1);
			slider.setHandler(new SliderHandler() {
				@Override
				public void onValueChanged(float value) {
					setting.parse(Integer.toString(Math.round(value)));
				}
			});
			component = slider;
		} else if (setting instanceof DoubleSetting doubleSetting
				&& !doubleSetting.noSlider
				&& isSliderRangeValid(doubleSetting.sliderMin, doubleSetting.sliderMax)) {
			float step = (float) Math.pow(10, -Math.max(0, Math.min(doubleSetting.decimalPlaces, 6)));
			Slider slider = new Slider(0, 0, 200, doubleSetting.get().floatValue(), (float) doubleSetting.sliderMin,
				(float) doubleSetting.sliderMax, step);
			slider.setHandler(new SliderHandler() {
				@Override
				public void onValueChanged(float value) {
					setting.parse(Float.toString(value));
				}
			});
			component = slider;
		} else if (setting instanceof EnumSetting<?>) {
			java.util.List<String> options = new java.util.ArrayList<>();
			for (String option : setting.getSuggestions()) {
				options.add(option);
			}
			if (!options.isEmpty()) {
				String selected = setting.get().toString();
				ComboButton button = new ComboButton(0, 0, options, selected);
				button.setHandler(new ComboButtonHandler() {
					@Override
					public void onChanged(String option) {
						setting.parse(option);
					}
				});
				component = button;
			}
		}

		if (component == null) {
			String value = setting.get() == null ? "" : setting.get().toString();
			TextField textField = new TextField(0, 0, 150, value);
			textField.setHandler(new TextHandler() {
				@Override
				public void onTyped(String value) {
					setting.parse(value);
				}
			});
			component = textField;
		}
	}

	private static boolean isSliderRangeValid(double min, double max) {
		return Double.isFinite(min) && Double.isFinite(max) && max > min;
	}

	@Override
	public void draw(double mouseX, double mouseY) {

		ColorPalette palette = Iekei.getInstance().getColorManager().getPalette();

		float itemY = y;

		yAnimation.onTick(itemY, 14);
		itemY = yAnimation.getValue();

		if (component != null) {
			component.setX(x + width - component.getWidth() - 22);
			component.setY(itemY + (height - component.getHeight()) / 2);
		}

		Skia.drawRoundedRect(x, itemY, width, height, 18, palette.getSurface());
		Skia.drawFullCenteredText(icon, x + 30, itemY + (height / 2), palette.getOnSurface(), Fonts.getIcon(32));
		Skia.drawText(I18n.get(title), x + 52, itemY + 20, palette.getOnSurface(), Fonts.getRegular(17));
		Skia.drawText(I18n.get(description), x + 52, itemY + 37, palette.getOnSurfaceVariant(), Fonts.getRegular(14));

		if (component != null) {
			component.draw(mouseX, mouseY);
		}
	}

	@Override
	public void mousePressed(double mouseX, double mouseY, int button) {

		if (component != null) {
			component.mousePressed(mouseX, mouseY, button);
		}
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int button) {

		if (component != null) {
			component.mouseReleased(mouseX, mouseY, button);
		}
	}

	@Override
	public void charTyped(char chr, int modifiers) {

		if (component != null) {
			component.charTyped(chr, modifiers);
		}
	}

	@Override
	public void keyPressed(int keyCode, int scanCode, int modifiers) {

		if (component != null) {
			component.keyPressed(keyCode, scanCode, modifiers);
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	public boolean isVisible() {
		return engineSetting == null || engineSetting.isVisible();
	}
}
