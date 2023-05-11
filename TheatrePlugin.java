package net.runelite.client.plugins.theatre;

import com.google.inject.Binder;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.NpcChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.theatre.Bloat.Bloat;
import net.runelite.client.plugins.theatre.Maiden.Maiden;
import net.runelite.client.plugins.theatre.Nylocas.Nylocas;
import net.runelite.client.plugins.theatre.Sotetseg.Sotetseg;
import net.runelite.client.plugins.theatre.Verzik.Verzik;
import net.runelite.client.plugins.theatre.Xarpus.Xarpus;

@PluginDescriptor(
	name = "<html><font color=#b82584>[J] Theatre of Blood",
	description = "All-in-one plugin for Theatre of Blood",
	tags = {"ToB", "Theatre", "raids", "bloat", "verzik", "nylo", "xarpus", "sotetseg", "maiden", "tob"},
	enabledByDefault = false
)

@Slf4j
public class TheatrePlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private Maiden maiden;

	@Inject
	private Bloat bloat;

	@Inject
	private Nylocas nylocas;

	@Inject
	private Sotetseg sotetseg;

	@Inject
	private Xarpus xarpus;

	@Inject
	private Verzik verzik;

	public static final Integer MAIDEN_REGION = 12869;
	public static final Integer BLOAT_REGION = 13125;
	public static final Integer NYLOCAS_REGION = 13122;
	public static final Integer SOTETSEG_REGION_OVERWORLD = 13123;
	public static final Integer SOTETSEG_REGION_UNDERWORLD = 13379;
	public static final Integer XARPUS_REGION = 12612;
	public static final Integer VERZIK_REGION = 12611;

	private Room[] rooms = null;

	private boolean tobActive;
	public static int partySize;

	@Override
	public void configure(Binder binder)
	{
		binder.bind(TheatreInputListener.class);
	}

	@Provides
	TheatreConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TheatreConfig.class);
	}

	@Override
	protected void startUp()
	{
		if (rooms == null)
		{
			rooms = new Room[]{maiden, bloat, nylocas, sotetseg, xarpus, verzik};

			for (Room room : rooms)
			{
				room.init();
			}
		}

		for (Room room : rooms)
		{
			room.load();
		}
	}

	@Override
	protected void shutDown()
	{
		for (Room room : rooms)
		{
			room.unload();
		}
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		maiden.onNpcSpawned(npcSpawned);
		bloat.onNpcSpawned(npcSpawned);
		nylocas.onNpcSpawned(npcSpawned);
		sotetseg.onNpcSpawned(npcSpawned);
		xarpus.onNpcSpawned(npcSpawned);
		verzik.onNpcSpawned(npcSpawned);
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		maiden.onNpcDespawned(npcDespawned);
		bloat.onNpcDespawned(npcDespawned);
		nylocas.onNpcDespawned(npcDespawned);
		sotetseg.onNpcDespawned(npcDespawned);
		xarpus.onNpcDespawned(npcDespawned);
		verzik.onNpcDespawned(npcDespawned);
	}

	@Subscribe
	public void onNpcChanged(NpcChanged npcChanged)
	{
		nylocas.onNpcChanged(npcChanged);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (tobActive)
		{
			partySize = 0;
			for (int i = 330; i < 335; i++)
			{
				if (client.getVarcStrValue(i) != null && !client.getVarcStrValue(i).equals(""))
				{
					partySize++;
				}
			}
		}

		maiden.onGameTick(event);
		bloat.onGameTick(event);
		nylocas.onGameTick(event);
		sotetseg.onGameTick(event);
		xarpus.onGameTick(event);
		verzik.onGameTick(event);
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		nylocas.onClientTick(event);
		xarpus.onClientTick(event);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		tobActive = client.getVarbitValue(Varbits.THEATRE_OF_BLOOD) > 1;

		bloat.onVarbitChanged(event);
		nylocas.onVarbitChanged(event);
		xarpus.onVarbitChanged(event);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		nylocas.onGameStateChanged(gameStateChanged);
		xarpus.onGameStateChanged(gameStateChanged);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged change)
	{
		if (!change.getGroup().equals("Theatre"))
		{
			return;
		}

		nylocas.onConfigChanged(change);
	}

	@Subscribe
	public void onGraphicsObjectCreated(GraphicsObjectCreated graphicsObjectC)
	{
		bloat.onGraphicsObjectCreated(graphicsObjectC);
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		sotetseg.onGroundObjectSpawned(event);
		xarpus.onGroundObjectSpawned(event);
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged animationChanged)
	{
		bloat.onAnimationChanged(animationChanged);
		nylocas.onAnimationChanged(animationChanged);
		sotetseg.onAnimationChanged(animationChanged);
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		verzik.onProjectileMoved(event);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned gameObject)
	{
		verzik.onGameObjectSpawned(gameObject);
	}
}

