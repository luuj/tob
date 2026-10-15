package net.runelite.client.plugins.theatre;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.FontType;
import net.runelite.client.config.Range;

@ConfigGroup("Theatre")
public interface TheatreConfig extends Config
{
	/*====== General Section ======*/

	@ConfigSection(
		name = "General",
		description = "General settings",
		position = 0
	)
	String generalSection = "General";

	@ConfigItem(
		keyName = "font",
		name = "Font",
		description = "Font used for all text overlays",
		position = 1,
		section = generalSection
	)
	default FontType font()
	{
		return FontType.BOLD.withSize(16);
	}

	/*====== Maiden Section ======*/


	@ConfigSection(
		name = "Maiden",
		description = "Maiden settings",
		position = 200,
		closedByDefault = true
	)
	String maidenSection = "Maiden";

	@ConfigItem(
		position = 201,
		keyName = "maidenBlood",
		name = "Blood attack marker",
		description = "Highlights Maiden's blood pools",
		section = maidenSection
	)
	default boolean maidenBlood()
	{
		return true;
	}

	@ConfigItem(
		position = 202,
		keyName = "maidenSpawns",
		name = "Blood spawn marker",
		description = "Highlights the blood spawns (tomatoes)",
		section = maidenSection
	)
	default boolean maidenSpawns()
	{
		return true;
	}

	@ConfigItem(
		position = 203,
		keyName = "maidenReds",
		name = "Reds health",
		description = "Shows the health of each red crab",
		section = maidenSection
	)
	default boolean maidenRedsHealth()
	{
		return true;
	}

	@ConfigItem(
		position = 204,
		keyName = "maidenRedsDistance",
		name = "Reds distance",
		description = "Shows how far each red crab is from Maiden",
		section = maidenSection
	)
	default boolean maidenRedsDistance()
	{
		return true;
	}

	@ConfigItem(
		position = 205,
		keyName = "MaidenTickCounter",
		name = "Tank tick counter",
		description = "Shows the ticks until Maiden chooses who tanks",
		section = maidenSection
	)
	default boolean maidenTickCounter()
	{
		return true;
	}



	/*====== Bloat Section ======*/


	@ConfigSection(
		name = "Bloat",
		description = "Bloat settings",
		position = 300,
		closedByDefault = true
	)
	String bloatSection = "Bloat";

	@ConfigItem(
		position = 301,
		keyName = "bloatIndicator",
		name = "Tile indicator",
		description = "Highlights Bloat's tile",
		section = bloatSection
	)
	default boolean bloatIndicator()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 302,
		keyName = "bloatIndicatorColorUP",
		name = "Up color",
		description = "Tile color while Bloat is up",
		section = bloatSection
	)
	default Color bloatIndicatorColorUP()
	{
		return Color.CYAN;
	}

	@Alpha
	@ConfigItem(
		position = 303,
		keyName = "bloatIndicatorColorTHRESH",
		name = "Threshold color",
		description = "Tile color once Bloat has been up for over 37 ticks and can go down",
		section = bloatSection
	)
	default Color bloatIndicatorColorTHRESH()
	{
		return Color.ORANGE;
	}

	@Alpha
	@ConfigItem(
		position = 304,
		keyName = "bloatIndicatorColorDOWN",
		name = "Down color",
		description = "Tile color while Bloat is down",
		section = bloatSection
	)
	default Color bloatIndicatorColorDOWN()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
		position = 305,
		keyName = "bloatIndicatorColorWARN",
		name = "Warning color",
		description = "Tile color when Bloat is down and about to get up",
		section = bloatSection
	)
	default Color bloatIndicatorColorWARN()
	{
		return Color.RED;
	}

	@ConfigItem(
		position = 306,
		keyName = "bloatTickCounter",
		name = "Tick counter",
		description = "Shows how long Bloat has been up or down",
		section = bloatSection
	)
	default boolean bloatTickCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 307,
		keyName = "BloatTickCountStyle",
		name = "Tick counter style",
		description = "Whether the down timer counts up or down",
		section = bloatSection
	)
	default BLOATTIMEDOWN BloatTickCountStyle()
	{
		return BLOATTIMEDOWN.COUNTDOWN;
	}

	@ConfigItem(
		position = 308,
		keyName = "bloatHands",
		name = "Hands overlay",
		description = "Highlights the tiles where Bloat's hands will fall",
		section = bloatSection
	)
	default boolean bloatHands()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		position = 309,
		keyName = "bloatHandsColor",
		name = "Hands color",
		description = "Color of the hands overlay",
		section = bloatSection
	)
	default Color bloatHandsColor()
	{
		return Color.CYAN;
	}

	@Range(max = 10)
	@ConfigItem(
		position = 310,
		keyName = "bloatHandsWidth",
		name = "Hands border width",
		description = "Thickness of the hands overlay outline",
		section = bloatSection
	)
	default int bloatHandsWidth()
	{
		return 2;
	}

	/*====== Nylocas Section ======*/

	@ConfigSection(
		name = "Nylocas",
		description = "Nylocas settings",
		position = 400,
		closedByDefault = true
	)
	String nylocasSection = "Nylocas";

	@ConfigItem(
		position = 401,
		keyName = "nyloPillars",
		name = "Pillar health",
		description = "Shows the health percentage of the pillars",
		section = nylocasSection
	)
	default boolean nyloPillars()
	{
		return true;
	}

	@ConfigItem(
		position = 402,
		keyName = "nyloExplosions",
		name = "Explosion warning",
		description = "Highlights Nylocas that are about to explode",
		section = nylocasSection
	)
	default boolean nyloExplosions()
	{
		return true;
	}

	@Range(max = 52)
	@ConfigItem(
		position = 403,
		keyName = "nyloExplosionDisplayTicks",
		name = "Explosion warning ticks",
		description = "How many of the last ticks before an explosion to warn for. For example, 10 warns for the last 10 ticks",
		section = nylocasSection
	)
	default int nyloExplosionDisplayTicks()
	{
		return 46;
	}

	@ConfigItem(
		position = 404,
		keyName = "nyloExplosionDisplayStyle",
		name = "Explosion warning style",
		description = "How to show a Nylocas that is about to explode",
		section = nylocasSection
	)
	default EXPLOSIVENYLORENDERSTYLE nyloExplosionOverlayStyle()
	{
		return EXPLOSIVENYLORENDERSTYLE.RECOLOR_TICK;
	}

	@ConfigItem(
		position = 405,
		keyName = "nyloTimeAlive",
		name = "Time alive",
		description = "Shows how many ticks each Nylocas has been alive (they explode at 52)",
		section = nylocasSection
	)
	default boolean nyloTimeAlive()
	{
		return false;
	}

	@ConfigItem(
		position = 406,
		keyName = "nyloTimeAliveCountStyle",
		name = "Time alive style",
		description = "Whether the time alive counts up or down",
		section = nylocasSection
	)
	default NYLOTIMEALIVE nyloTimeAliveCountStyle()
	{
		return NYLOTIMEALIVE.COUNTDOWN;
	}

	@ConfigItem(
		position = 408,
		keyName = "nyloHighlightOverlay",
		name = "Role highlight",
		description = "Highlights the Nylocas your role should attack",
		section = nylocasSection
	)
	default boolean nyloHighlightOverlay()
	{
		return false;
	}

	@ConfigItem(
		position = 409,
		keyName = "nyloAliveCounter",
		name = "Alive counter panel",
		description = "Shows how many Nylocas are alive",
		section = nylocasSection
	)
	default boolean nyloAlivePanel()
	{
		return false;
	}

	@ConfigItem(
		position = 410,
		keyName = "nyloAggressiveOverlay",
		name = "Aggressive highlight",
		description = "Highlights aggressive Nylocas after they spawn",
		section = nylocasSection
	)
	default boolean nyloAggressiveOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 411,
		keyName = "nyloAggressiveOverlayStyle",
		name = "Aggressive highlight style",
		description = "How aggressive Nylocas are highlighted",
		section = nylocasSection
	)
	default AGGRESSIVENYLORENDERSTYLE nyloAggressiveOverlayStyle()
	{
		return AGGRESSIVENYLORENDERSTYLE.TILE;
	}

	@ConfigItem(
		position = 413,
		keyName = "nylocasWavesHelper",
		name = "Waves helper",
		description = "Shows the upcoming wave numbers on the entry bridges",
		section = nylocasSection
	)
	default boolean nyloWavesHelper()
	{
		return false;
	}

	@ConfigItem(
		position = 414,
		keyName = "nylocasTicksUntilWave",
		name = "Ticks until wave",
		description = "Shows how many ticks until the next wave can spawn",
		section = nylocasSection
	)
	default boolean nyloTicksUntilWaves()
	{
		return false;
	}

	@ConfigItem(
		position = 415,
		keyName = "nyloInstanceTimer",
		name = "Instance timer",
		description = "Shows an instance timer for when the next set can spawn. Enter on zero",
		section = nylocasSection
	)
	default boolean nyloInstanceTimer()
	{
		return false;
	}

	@ConfigItem(
		position = 416,
		keyName = "nyloStallMessage",
		name = "Stall messages",
		description = "Sends a chat message when the next wave is stalled because the Nylocas cap was reached",
		section = nylocasSection
	)
	default boolean nyloStallMessage()
	{
		return false;
	}

	@ConfigItem(
		position = 417,
		keyName = "nylocasBigSplitsHelper",
		name = "Big splits",
		description = "Shows when big Nylocas will split into small ones",
		section = nylocasSection
	)
	default boolean bigSplits()
	{
		return false;
	}

	@ConfigItem(
		position = 418,
		keyName = "nylocasBigSplitsHighlightColor",
		name = "Big splits highlight color",
		description = "Highlight color of big Nylocas about to split",
		section = nylocasSection,
		hidden = true
	)
	@Alpha
	default Color getBigSplitsHighlightColor()
	{
		return Color.YELLOW;
	}

	@ConfigItem(
		position = 419,
		keyName = "nylocasBigSplitsTileColor2",
		name = "Big splits tile color 2",
		description = "Tile color two ticks before a split",
		section = nylocasSection,
		hidden = true
	)
	@Alpha
	default Color getBigSplitsTileColor2()
	{
		return Color.ORANGE;
	}

	@ConfigItem(
		position = 420,
		keyName = "nylocasBigSplitsTileColor1",
		name = "Big splits tile color 1",
		description = "Tile color one tick before a split",
		section = nylocasSection,
		hidden = true
	)
	@Alpha
	default Color getBigSplitsTileColor1()
	{
		return Color.RED;
	}

	@ConfigItem(
		position = 421,
		keyName = "nylocasBigSplitsTextColor2",
		name = "Big splits text color 2",
		description = "Text color two ticks before a split",
		section = nylocasSection,
		hidden = true
	)
	@Alpha
	default Color getBigSplitsTextColor2()
	{
		return Color.ORANGE;
	}

	@ConfigItem(
		position = 422,
		keyName = "nylocasBigSplitsTextColor1",
		name = "Big splits text color 1",
		description = "Text color one tick before a split",
		section = nylocasSection,
		hidden = true
	)
	@Alpha
	default Color getBigSplitsTextColor1()
	{
		return Color.RED;
	}

	@ConfigItem(
		position = 423,
		keyName = "nyloBossAttackTickCount",
		name = "Boss attack tick counter",
		description = "Shows the ticks until the Nylocas boss attacks next (left number)",
		section = nylocasSection
	)
	default boolean nyloBossAttackTickCount()
	{
		return false;
	}

	@ConfigItem(
		position = 424,
		keyName = "nyloBossSwitchTickCount",
		name = "Boss switch tick counter",
		description = "Shows the ticks until the Nylocas boss switches style next (middle number)",
		section = nylocasSection
	)
	default boolean nyloBossSwitchTickCount()
	{
		return true;
	}

	@ConfigItem(
		position = 425,
		keyName = "nyloBossTotalTickCount",
		name = "Boss total tick counter",
		description = "Shows the total ticks since the Nylocas boss spawned (right number)",
		section = nylocasSection
	)
	default boolean nyloBossTotalTickCount()
	{
		return false;
	}


	/*====== Sotetseg Section ======*/

	@ConfigSection(
		name = "Sotetseg",
		description = "Sotetseg settings",
		position = 500,
		closedByDefault = true
	)
	String sotetsegSection = "Sotetseg";

	@ConfigItem(
		position = 501,
		keyName = "sotetsegMaze",
		name = "Maze",
		description = "Memorizes solo mazes and shows the tiles of other chosen players",
		section = sotetsegSection
	)
	default boolean sotetsegMaze()
	{
		return true;
	}

	@ConfigItem(
		position = 503,
		keyName = "sotetsegAutoAttacksTicks",
		name = "Auto attack ticks",
		description = "Shows the ticks until Sotetseg attacks next",
		section = sotetsegSection
	)
	default boolean sotetsegAutoAttacksTicks()
	{
		return true;
	}


	/*====== Xarpus Section ======*/

	@ConfigSection(
		name = "Xarpus",
		description = "Xarpus settings",
		position = 600,
		closedByDefault = true
	)
	String xarpusSection = "Xarpus";

	@ConfigItem(
		position = 601,
		keyName = "xarpusInstanceTimer",
		name = "Instance timer",
		description = "Shows the instance timer so you can be tick-efficient with the first exhumed spawn. Enter on zero",
		section = xarpusSection
	)
	default boolean xarpusInstanceTimer()
	{
		return true;
	}

	@ConfigItem(
		position = 602,
		keyName = "xarpusExhumed",
		name = "Exhumed markers",
		description = "Highlights the tiles of exhumed spawns",
		section = xarpusSection
	)
	default boolean xarpusExhumed()
	{
		return true;
	}

	@ConfigItem(
		position = 603,
		keyName = "xarpusExhumedTick",
		name = "Exhumed ticks",
		description = "Shows how many ticks until the exhumeds despawn",
		section = xarpusSection
	)
	default boolean xarpusExhumedTick()
	{
		return true;
	}

	@ConfigItem(
		position = 604,
		keyName = "xarpusExhumedCount",
		name = "Exhumed count",
		description = "Counts the exhumeds",
		section = xarpusSection
	)
	default XARPUS_EXHUMED_COUNT xarpusExhumedCount()
	{
		return XARPUS_EXHUMED_COUNT.DOWN;
	}

	@ConfigItem(
		position = 605,
		keyName = "xarpusTickP2",
		name = "Attack tick (P2)",
		description = "Shows the ticks until Xarpus turns to a new target to spit at",
		section = xarpusSection
	)
	default boolean xarpusTickP2()
	{
		return true;
	}

	@ConfigItem(
		position = 606,
		keyName = "xarpusTickP3",
		name = "Attack tick (P3)",
		description = "Shows the ticks until Xarpus rotates",
		section = xarpusSection
	)
	default boolean xarpusTickP3()
	{
		return true;
	}

	@ConfigItem(
		position = 607,
		name = "Line of sight",
		keyName = "xarpusLineOfSight",
		description = "Shows Xarpus's line of sight in P3<br>Melee tiles: only the melee tiles Xarpus can see<br>Square: the whole area Xarpus can see",
		section = xarpusSection
	)
	default XARPUS_LINE_OF_SIGHT xarpusLineOfSight()
	{
		return XARPUS_LINE_OF_SIGHT.OFF;
	}

	@Alpha
	@ConfigItem(
		position = 608,
		name = "Line of sight color",
		keyName = "xarpusLineOfSightColor",
		description = "Color of the line of sight overlay",
		section = xarpusSection
	)
	default Color xarpusLineOfSightColor()
	{
		return Color.RED;
	}


	/*====== Verzik Section ======*/


	@ConfigSection(
		name = "Verzik",
		description = "Verzik settings",
		position = 700,
		closedByDefault = true
	)
	String verzikSection = "Verzik";

	@ConfigItem(
		position = 701,
		keyName = "verzikTileOverlay",
		name = "Tile indicator",
		description = "Highlights Verzik's tile. If you're next to or inside it, you can be meleed",
		section = verzikSection
	)
	default boolean verzikTileOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 702,
		keyName = "verzikProjectiles",
		name = "Range tile markers",
		description = "Highlights the tiles hit by Verzik's ranged attacks",
		section = verzikSection
	)
	default boolean verzikProjectiles()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 703,
		keyName = "verzikProjectilesColor",
		name = "Range tile markers color",
		description = "Color of the range tile markers",
		section = verzikSection
	)
	default Color verzikProjectilesColor()
	{
		return new Color(255, 0, 0, 50);
	}

	@ConfigItem(
		position = 704,
		keyName = "VerzikRedHP",
		name = "Reds health",
		description = "Shows the health of the red crabs",
		section = verzikSection
	)
	default boolean verzikReds()
	{
		return true;
	}

	@ConfigItem(
		position = 705,
		keyName = "verzikAutosTick",
		name = "Attack tick counter",
		description = "Shows the ticks until Verzik attacks next",
		section = verzikSection
	)
	default boolean verzikAutosTick()
	{
		return true;
	}

	@ConfigItem(
		position = 706,
		keyName = "verzikAttackCounter",
		name = "Attack counter",
		description = "Counts Verzik's attacks (useful in P2, as reds despawn after the 7th attack)",
		section = verzikSection
	)
	default boolean verzikAttackCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 707,
		keyName = "verzikTotalTickCounter",
		name = "Total tick counter",
		description = "Shows how many ticks Verzik has been alive",
		section = verzikSection
	)
	default boolean verzikTotalTickCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 708,
		keyName = "verzikNyloPersonalWarning",
		name = "Nylo direct aggro warning",
		description = "Highlights Nylocas that are targeting only you",
		section = verzikSection
	)
	default boolean verzikNyloPersonalWarning()
	{
		return true;
	}

	@ConfigItem(
		position = 709,
		keyName = "verzikNyloOtherWarning",
		name = "Nylo indirect aggro warnings",
		description = "Highlights Nylocas that are targeting other players",
		section = verzikSection
	)
	default boolean verzikNyloOtherWarning()
	{
		return true;
	}

	@ConfigItem(
		position = 710,
		keyName = "lightningAttackHelper",
		name = "Lightning attack helper",
		description = "Shows how many attacks until a lightning ball",
		section = verzikSection
	)
	default boolean lightningAttackHelper()
	{
		return false;
	}

	@ConfigItem(
		position = 711,
		keyName = "lightningAttackTick",
		name = "Lightning attack tick",
		description = "Shows how many ticks until a lightning ball hits you",
		section = verzikSection
	)
	default boolean lightningAttackTick()
	{
		return false;
	}

	@ConfigItem(
		position = 714,
		keyName = "verzikNyloExplodeAOE",
		name = "Nylo explosion area",
		description = "Highlights the Nylocas explosion area. Needs one of the aggro warnings turned on",
		section = verzikSection
	)
	default boolean verzikNyloExplodeAOE()
	{
		return true;
	}

	@ConfigItem(
		position = 715,
		keyName = "verzikDisplayTank",
		name = "Display tank",
		description = "Highlights the tile of the player tanking",
		section = verzikSection
	)
	default boolean verzikDisplayTank()
	{
		return true;
	}

	@ConfigItem(
		position = 716,
		keyName = "verzikYellows",
		name = "Yellows overlay",
		description = "Highlights the yellow pools and shows the ticks until you can move away or tick eat",
		section = verzikSection
	)
	default boolean verzikYellows()
	{
		return true;
	}

	@ConfigItem(
		position = 717,
		keyName = "verzikGreenBall",
		name = "Green ball tank",
		description = "Shows who the green ball is targeting",
		section = verzikSection
	)
	default boolean verzikGreenBall()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 718,
		keyName = "verzikGreenBallColor",
		name = "Green ball highlight color",
		description = "Color of the green ball tile overlay",
		section = verzikSection
	)
	default Color verzikGreenBallColor()
	{
		return new Color(59, 140, 83, 255);
	}

	@ConfigItem(
		position = 719,
		keyName = "verzikGreenBallMarker",
		name = "Green ball marker",
		description = "Whether the green ball marker is a single tile or a 3x3 area",
		section = verzikSection
	)
	default VERZIKBALLTILE verzikGreenBallMarker()
	{
		return VERZIKBALLTILE.TILE;
	}

	@ConfigItem(
		position = 720,
		keyName = "verzikGreenBallTick",
		name = "Green ball tick",
		description = "Shows the ticks until the green ball hits you",
		section = verzikSection
	)
	default boolean verzikGreenBallTick()
	{
		return false;
	}

	@ConfigItem(
		position = 721,
		keyName = "verzikTornado",
		name = "Personal tornado highlight",
		description = "Highlights the tornado that is targeting you",
		section = verzikSection
	)
	default boolean verzikTornado()
	{
		return true;
	}

	@ConfigItem(
		position = 722,
		keyName = "verzikPersonalTornadoOnly",
		name = "Only highlight personal tornado",
		description = "Only highlights your tornado once it's known which one is targeting you",
		section = verzikSection
	)
	default boolean verzikPersonalTornadoOnly()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		position = 723,
		keyName = "verzikTornadoColor",
		name = "Tornado highlight color",
		description = "Color of the tornado highlight",
		section = verzikSection
	)
	default Color verzikTornadoColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		position = 724,
		keyName = "verzikPoisonTileHighlight",
		name = "Poison tile highlight",
		description = "Highlights tiles with disappearing poison",
		section = verzikSection
	)
	default boolean verzikPoisonTileHighlight()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		position = 725,
		keyName = "verzikPoisonTileHighlightColor",
		name = "Poison tile highlight color",
		description = "Color of the poison tile highlight",
		section = verzikSection,
		hidden = true
	)
	default Color verzikPoisonTileHighlightColor()
	{
		return new Color(184, 246, 196, 152);
	}


	@ConfigItem(
		keyName = "highlightMelee",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 801
	)
	default boolean getHighlightMeleeNylo()
	{
		return false;
	}

	@ConfigItem(
		keyName = "highlightMelee",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 802
	)
	void setHighlightMeleeNylo(boolean set);

	@ConfigItem(
		keyName = "highlightMage",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 803
	)
	default boolean getHighlightMageNylo()
	{
		return false;
	}

	@ConfigItem(
		keyName = "highlightMage",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 804
	)
	void setHighlightMageNylo(boolean set);

	@ConfigItem(
		keyName = "highlightRange",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 805
	)
	default boolean getHighlightRangeNylo()
	{
		return false;
	}

	@ConfigItem(
		keyName = "highlightRange",
		name = "",
		description = "",
		hidden = true,
		section = generalSection,
		position = 806
	)
	void setHighlightRangeNylo(boolean set);

	enum BLOATTIMEDOWN
	{
		COUNTUP,
		COUNTDOWN
	}

	enum NYLOTIMEALIVE
	{
		COUNTUP,
		COUNTDOWN
	}

	enum EXPLOSIVENYLORENDERSTYLE
	{
		TILE,
		RECOLOR_TICK
	}

	enum AGGRESSIVENYLORENDERSTYLE
	{
		TILE,
		HULL
	}

	enum XARPUS_EXHUMED_COUNT
	{
		OFF,
		DOWN,
		UP;
	}

	enum XARPUS_LINE_OF_SIGHT
	{
		OFF,
		SQUARE,
		MELEE_TILES;
	}

	enum VERZIKBALLTILE
	{
		TILE,
		AREA
	}
}
