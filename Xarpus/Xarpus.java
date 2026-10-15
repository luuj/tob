package net.runelite.client.plugins.theatre.Xarpus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import com.google.common.collect.ImmutableSet;
import lombok.Getter;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.GroundObject;
import net.runelite.api.NPC;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.api.WorldView;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.theatre.Room;
import net.runelite.client.plugins.theatre.TheatreConfig;
import net.runelite.client.plugins.theatre.TheatrePlugin;
import net.runelite.client.ui.overlay.infobox.Counter;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import org.apache.commons.lang3.tuple.Pair;

public class Xarpus extends Room
{
	@Inject
	private Client client;

	@Inject
	private XarpusOverlay xarpusOverlay;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private TheatrePlugin p; //DO NOT USE. Just here for the exhumedCounter constructor.

	@Inject
	protected Xarpus(TheatrePlugin plugin, TheatreConfig config)
	{
		super(plugin, config);
	}

	@Getter
	private boolean xarpusActive;
	private boolean xarpusStarted = false;

	@Getter
	private NPC xarpusNPC;

	@Getter
	private int instanceTimer = 0;

	@Getter
	private boolean isInstanceTimerRunning = false;
	private boolean nextInstance = true;

	@Getter
	private boolean exhumedSpawned = false;

	@Getter
	private final Map<Long, Pair<GroundObject, Integer>> xarpusExhumeds = new HashMap<>();

	@Getter
	private Counter exhumedCounter;

	private int exhumedCount;

	@Getter
	private int xarpusTicksUntilAttack;

	@Getter
	private boolean postScreech = false;

	private boolean xarpusStare;

	private static BufferedImage EXHUMED_COUNT_ICON;
	private static final int GROUNDOBJECT_ID_EXHUMED = 32743;

	@Getter
	private boolean isHM;
	private static final Set<Integer> XARPUS_HM_ID = ImmutableSet.of(10770, 10771, 10772, 10773);

	@Override
	public void init()
	{
		EXHUMED_COUNT_ICON = createExhumedIcon();
	}

	/**
	 * Draws the exhumed counter icon in code (a green poison-style orb) so the plugin
	 * does not depend on an external image resource being on the classpath.
	 */
	private static BufferedImage createExhumedIcon()
	{
		final int size = 26;
		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		try
		{
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(0, 120, 0));
			g.fillOval(1, 1, size - 3, size - 3);
			g.setColor(new Color(60, 220, 60));
			g.fillOval(4, 4, size - 9, size - 9);
			g.setColor(new Color(220, 255, 220, 200));
			g.fillOval(8, 7, 6, 5);
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(1.5f));
			g.drawOval(1, 1, size - 3, size - 3);
		}
		finally
		{
			g.dispose();
		}
		return image;
	}

	@Override
	public void load()
	{
		overlayManager.add(xarpusOverlay);
	}

	@Override
	public void unload()
	{
		overlayManager.remove(xarpusOverlay);

		infoBoxManager.removeInfoBox(exhumedCounter);

		exhumedCounter = null;
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		NPC npc = npcSpawned.getNpc();
		switch (npc.getId())
		{
			case NpcID.TOB_XARPUS_STATIC:
			case NpcID.TOB_XARPUS_FEEDING:
			case NpcID.TOB_XARPUS_COMBAT:
			case NpcID.XARPUS_DEATH:
			case 10766:
			case 10767:
			case 10768:
			case 10769:
			case 10770:
			case 10771:
			case 10772:
			case 10773:
				isHM = XARPUS_HM_ID.contains(npc.getId());
				xarpusActive = true;
				xarpusNPC = npc;
				xarpusStare = false;
				xarpusTicksUntilAttack = 9;
				exhumedSpawned = false;
				postScreech = false;
				break;
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		NPC npc = npcDespawned.getNpc();
		switch (npc.getId())
		{
			case NpcID.TOB_XARPUS_STATIC:
			case NpcID.TOB_XARPUS_FEEDING:
			case NpcID.TOB_XARPUS_COMBAT:
			case NpcID.XARPUS_DEATH:
			case 10766:
			case 10767:
			case 10768:
			case 10769:
			case 10770:
			case 10771:
			case 10772:
			case 10773:
				isHM = false;
				xarpusActive = false;
				xarpusNPC = null;
				xarpusStare = false;
				xarpusTicksUntilAttack = 9;
				xarpusExhumeds.clear();
				infoBoxManager.removeInfoBox(exhumedCounter);
				exhumedCounter = null;
				isInstanceTimerRunning = false;
				exhumedSpawned = false;
				postScreech = false;
				exhumedCount = -1;
				xarpusStarted = false;
				break;
		}
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		if (xarpusActive)
		{
			GroundObject o = event.getGroundObject();
			if (o.getId() == GROUNDOBJECT_ID_EXHUMED)
			{
				long hash = o.getHash();
				if (xarpusExhumeds.containsKey(hash))
				{
					return;
				}
				exhumedSpawned = true;

				if (exhumedCounter == null)
				{
					switch (TheatrePlugin.partySize)
					{
						case 5:
							exhumedCount = isHM ? 24 : 18;
							break;
						case 4:
							exhumedCount = isHM ? 20 : 15;
							break;
						case 3:
							exhumedCount = isHM ? 16 : 12;
							break;
						case 2:
							exhumedCount = isHM ? 13 : 9;
							break;
						default:
							exhumedCount = isHM ? 9 : 7;
					}

					if (config.xarpusExhumedCount() != TheatreConfig.XARPUS_EXHUMED_COUNT.OFF)
					{
						exhumedCounter = new Counter(EXHUMED_COUNT_ICON, p, config.xarpusExhumedCount() == TheatreConfig.XARPUS_EXHUMED_COUNT.DOWN ? exhumedCount - 1 : 1);
						infoBoxManager.addInfoBox(exhumedCounter);
					}
				}
				else
				{

					exhumedCounter.setCount(config.xarpusExhumedCount() == TheatreConfig.XARPUS_EXHUMED_COUNT.DOWN ? exhumedCounter.getCount() - 1 : exhumedCounter.getCount() + 1);
				}

				xarpusExhumeds.put(hash, Pair.of(o, isHM ? 9 : 11));
			}
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (!xarpusStarted && inRoomRegion(TheatrePlugin.XARPUS_REGION) && client.getVarbitValue(VarbitID.TOB_CLIENT_WAVEPROGRESS_TYPE) == 2 && client.getVarbitValue(VarbitID.MULTIWAY_INDICATOR) == 1)
		{
			xarpusStarted = true;
			isInstanceTimerRunning = false;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (xarpusActive)
		{
			if (!xarpusExhumeds.isEmpty())
			{
				xarpusExhumeds.replaceAll((k, v) -> Pair.of(v.getLeft(), v.getRight() - 1));
				xarpusExhumeds.values().removeIf((p) -> p.getRight() <= 0);
			}

			if (xarpusNPC.getOverheadText() != null && !xarpusStare)
			{
				xarpusStare = true;
				xarpusTicksUntilAttack = 9;
			}

			if (xarpusStare)
			{
				xarpusTicksUntilAttack--;
				if (xarpusTicksUntilAttack <= 0)
				{
					if (!postScreech)
					{
						postScreech = true;
					}
					xarpusTicksUntilAttack = 8;
				}
			}
			else if (xarpusNPC.getId() == NpcID.TOB_XARPUS_COMBAT || xarpusNPC.getId() == 10768 || xarpusNPC.getId() == 10772)
			{
				xarpusTicksUntilAttack--;
				if (xarpusTicksUntilAttack <= 0)
				{
					xarpusTicksUntilAttack = 4;
				}
			}

		}

		if (isInstanceTimerRunning)
		{
			instanceTimer = (instanceTimer + 1) % 4;
		}
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		if (client.getLocalPlayer() == null || !nextInstance || !isInSotetsegRegion())
		{
			return;
		}

		WorldView wv = client.getTopLevelWorldView();
		for (Player player : wv.players())
		{
			if (player.getWorldLocation() != null)
			{
				WorldPoint wpPlayer = player.getWorldLocation();
				LocalPoint lpPlayer = LocalPoint.fromWorld(wv, wpPlayer.getX(), wpPlayer.getY());

				WorldPoint wpChest = WorldPoint.fromRegion(player.getWorldLocation().getRegionID(), 17, 5, player.getWorldLocation().getPlane());
				LocalPoint lpChest = LocalPoint.fromWorld(wv, wpChest.getX(), wpChest.getY());
				if (lpChest != null && lpPlayer != null)
				{
					Point point = new Point(lpChest.getSceneX() - lpPlayer.getSceneX(), lpChest.getSceneY() - lpPlayer.getSceneY());

					if (isInSotetsegRegion() && point.getY() == 1 && (point.getX() == 1 || point.getX() == 2 || point.getX() == 3) && nextInstance)
					{
						client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Xarpus instance timer started", "");
						instanceTimer = 2;
						isInstanceTimerRunning = true;
						nextInstance = false;
					}
				}
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN)
		{
			nextInstance = true;
		}
	}

	boolean isInSotetsegRegion()
	{
		return inRoomRegion(TheatrePlugin.SOTETSEG_REGION_OVERWORLD) || inRoomRegion(TheatrePlugin.SOTETSEG_REGION_UNDERWORLD);
	}
}
