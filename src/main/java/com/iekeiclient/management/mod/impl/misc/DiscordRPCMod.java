package com.iekeiclient.management.mod.impl.misc;

import java.time.OffsetDateTime;

import com.google.gson.JsonObject;
import com.iekeiclient.Iekei;
import com.iekeiclient.libraries.discordipc.IPCClient;
import com.iekeiclient.libraries.discordipc.IPCListener;
import com.iekeiclient.libraries.discordipc.entities.ActivityType;
import com.iekeiclient.libraries.discordipc.entities.Packet;
import com.iekeiclient.libraries.discordipc.entities.RichPresence;
import com.iekeiclient.libraries.discordipc.entities.User;
import com.iekeiclient.libraries.discordipc.entities.pipe.PipeStatus;
import com.iekeiclient.libraries.discordipc.exceptions.NoDiscordClientException;
import com.iekeiclient.management.mod.Mod;
import com.iekeiclient.management.mod.ModCategory;
import com.iekeiclient.skia.font.Icon;

public class DiscordRPCMod extends Mod {

	private IPCClient client;

	public DiscordRPCMod() {
		super("mod.discordrpc.name", "mod.discordrpc.description", Icon.VERIFIED, ModCategory.MISC);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		client = new IPCClient(1059341815205068901L);
		client.setListener(new IPCListener() {
			@Override
			public void onReady(IPCClient client) {

				RichPresence.Builder builder = new RichPresence.Builder();

				builder.setState("Playing Iekei Client v" + Iekei.getInstance().getVersion())
						.setStartTimestamp(OffsetDateTime.now().toEpochSecond()).setLargeImage("icon")
						.setActivityType(ActivityType.Playing);

				client.sendRichPresence(builder.build());
			}

			@Override
			public void onPacketSent(IPCClient client, Packet packet) {
			}

			@Override
			public void onPacketReceived(IPCClient client, Packet packet) {
			}

			@Override
			public void onActivityJoin(IPCClient client, String secret) {
			}

			@Override
			public void onActivitySpectate(IPCClient client, String secret) {
			}

			@Override
			public void onActivityJoinRequest(IPCClient client, String secret, User user) {
			}

			@Override
			public void onClose(IPCClient client, JsonObject json) {
			}

			@Override
			public void onDisconnect(IPCClient client, Throwable t) {
			}
		});

		try {
			client.connect();
		} catch (NoDiscordClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();

		if (client != null && client.getStatus() == PipeStatus.CONNECTED) {
			client.close();
			client = null;
		}
	}
}
