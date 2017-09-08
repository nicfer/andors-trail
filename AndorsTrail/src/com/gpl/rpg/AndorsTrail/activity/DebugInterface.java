package com.gpl.rpg.AndorsTrail.activity;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.gpl.rpg.AndorsTrail.AndorsTrailApplication;
import com.gpl.rpg.AndorsTrail.R;
import com.gpl.rpg.AndorsTrail.context.ControllerContext;
import com.gpl.rpg.AndorsTrail.context.WorldContext;
import com.gpl.rpg.AndorsTrail.model.map.PredefinedMap;
import com.gpl.rpg.AndorsTrail.model.map.MapObject;

public final class DebugInterface {
	private final ControllerContext controllerContext;
	private final MainActivity mainActivity;
	private final Resources res;
	private final WorldContext world;

	public DebugInterface(ControllerContext controllers, WorldContext world, MainActivity mainActivity) {
		this.controllerContext = controllers;
		this.world = world;
		this.res = mainActivity.getResources();
		this.mainActivity = mainActivity;
	}

	public void addDebugButtons() {
		if (!AndorsTrailApplication.DEVELOPMENT_DEBUGBUTTONS) return;
		if (!world.model.player.getName().startsWith("debug")) return;

		addDebugButtons(new DebugButton[] {
			/*new DebugButton("dmg", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					world.model.player.damagePotential.max += 500;
					world.model.player.damagePotential.current += 500;
					world.model.player.attackChance += 500;
					showToast(mainActivity, "DEBUG: damagePotential=500, chance=500%, cost=1", Toast.LENGTH_SHORT);
				}
			})
			,new DebugButton("dmg=1", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					world.model.player.damagePotential.set(1, 1);
					showToast(mainActivity, "DEBUG: damagePotential=1", Toast.LENGTH_SHORT);
				}
			})
			,*/
			new DebugButton("items", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (!AndorsTrailApplication.DEVELOPMENT_DEBUGRESOURCES) {
						world.model.player.inventory.addItem(world.itemTypes.getItemType("ring_shadow0"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("bonemeal_potion"), 10);
						world.model.player.inventory.addItem(world.itemTypes.getItemType("club_wood2"), 2);
						world.model.player.inventory.addItem(world.itemTypes.getItemType("rapier_lifesteal"), 2);
						world.model.player.inventory.addItem(world.itemTypes.getItemType("remgard_shield_2"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("haub_serp"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("helm_protector"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("gloves_arulir"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("boots_guard1"));
						world.model.player.inventory.addItem(world.itemTypes.getItemType("ring_protector"));
					}
					world.model.player.inventory.addItem(world.itemTypes.getItemType("jewel_fallhaven_dr"));
					showToast(mainActivity, "DEBUG: added items", Toast.LENGTH_SHORT);
				}
			})
			,new DebugButton("prim", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "blackwater_mountain29", "south", 0, 0);
				}
			})
			,new DebugButton("lvlUp", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					int xpToAdd = 10000;
					if (world.model.player.levelExperience.max > 100000) {
						xpToAdd = (int) Math.pow(10, (int) Math.log10(world.model.player.levelExperience.max));
					}
					controllerContext.actorStatsController.addExperience(xpToAdd);
					showToast(mainActivity, "DEBUG: given " + xpToAdd + " exp", Toast.LENGTH_SHORT);
				}
			})
			,new DebugButton("reset", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					for(PredefinedMap map : world.maps.getAllMaps()) {
						map.resetTemporaryData();
					}
					showToast(mainActivity, "DEBUG: maps respawned", Toast.LENGTH_SHORT);
				}
			})
			/*,new DebugButton("hp", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					world.model.player.health.max += 500;
					controllerContext.actorStatsController.setActorMaxHealth(world.model.player);
					world.model.player.conditions.clear();
					showToast(mainActivity, "DEBUG: hp set to max", Toast.LENGTH_SHORT);
				}
			})*/

			,new DebugButton("cg", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "crossglen", "hall", 0, 0);
				}
			})
			,new DebugButton("vg", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "vilegard_s", "tavern", 0, 0);
				}
			})/*
			,new DebugButton("cr", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "houseatcrossroads4", "down", 0, 0);
				}
			})
			,new DebugButton("lf", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "loneford9", "south", 0, 0);
				}
			})
			,new DebugButton("fh", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "fallhaven_ne", "clothes", 0, 0);
				}
			})*/
			,new DebugButton("rc", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					controllerContext.movementController.placePlayerAsyncAt(MapObject.MapObjectType.newmap, "roadtocarntower1", "left3", 0, 0);
				}
			})
		});
	}

	private void showToast(Context context, String msg, int duration) {
		Toast.makeText(context, msg, duration).show();
	}

	private static class DebugButton {
		public final String text;
		public final OnClickListener listener;
		public DebugButton(String text, OnClickListener listener) {
			this.text = text;
			this.listener = listener;
		}
	}

	private void addDebugButton(DebugButton button, int id, RelativeLayout layout) {
		if (!AndorsTrailApplication.DEVELOPMENT_DEBUGBUTTONS) return;

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, res.getDimensionPixelSize(R.dimen.smalltext_buttonheight));
		if (id == 1)
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		else
			lp.addRule(RelativeLayout.RIGHT_OF, id - 1);
		lp.addRule(RelativeLayout.ABOVE, R.id.main_statusview);
		Button b = new Button(mainActivity);
		b.setText(button.text);
		b.setTextSize(10);//res.getDimension(R.dimen.actionbar_text));
		b.setId(id);
		b.setLayoutParams(lp);
		b.setOnClickListener(button.listener);
		layout.addView(b);
	}

	private void addDebugButtons(DebugButton[] buttons) {
		if (!AndorsTrailApplication.DEVELOPMENT_DEBUGBUTTONS) return;

		if (buttons == null || buttons.length <= 0) return;
		RelativeLayout layout = (RelativeLayout) mainActivity.findViewById(R.id.main_container);

		int id = 1;
		for (DebugButton b : buttons) {
			addDebugButton(b, id, layout);
			++id;
		}
	}
}
