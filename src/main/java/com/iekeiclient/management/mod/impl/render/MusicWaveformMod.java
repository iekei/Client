package com.iekeiclient.management.mod.impl.render;

import com.iekeiclient.Iekei;
import com.iekeiclient.event.EventBus;
import com.iekeiclient.event.client.RenderSkiaEvent;
import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.management.music.Music;
import com.iekeiclient.management.music.MusicManager;
import com.iekeiclient.management.music.MusicPlayer;
import com.iekeiclient.skia.Skia;
import com.iekeiclient.skia.font.Icon;
import com.iekeiclient.utils.ColorUtils;

public class MusicWaveformMod extends Mod {

	public MusicWaveformMod() {
		super("mod.musicwaveform.name", "mod.musicwaveform.description", Icon.AIRWAVE, ModCategory.RENDER);
	}

	public EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {

		MusicManager musicManager = Iekei.getInstance().getMusicManager();
		Music m = musicManager.getCurrentMusic();

		int offsetX = 0;

		if (musicManager.isPlaying()) {

			for (int i = 0; i < MusicPlayer.SPECTRUM_BANDS; i++) {

				MusicPlayer.ANIMATIONS[i].onTick(MusicPlayer.VISUALIZER[i], 10);
				Skia.drawRect(offsetX, client.getWindow().getScaledHeight() + MusicPlayer.ANIMATIONS[i].getValue(), 10,
						client.getWindow().getScaledHeight(), ColorUtils.applyAlpha(m.getColor(), 80));

				offsetX += 10;
			}
		}
	};
}
