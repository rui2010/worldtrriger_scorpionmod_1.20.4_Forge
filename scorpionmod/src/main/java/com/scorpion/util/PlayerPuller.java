package com.scorpion.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.UUID;

public class PlayerPuller {

    private static class PullData {
        Vec3 target;
        int ticks = 0;
    }

    private static final HashMap<UUID, PullData> pulling = new HashMap<>();

    public static void startPull(Player player, Vec3 target) {
        PullData data = new PullData();
        data.target = target;
        pulling.put(player.getUUID(), data);
    }

    public static void tick(Player player) {
        PullData data = pulling.get(player.getUUID());
        if (data == null) return;

        data.ticks++;

        double speed = 0.15;

        Vec3 current = player.position();
        Vec3 diff = data.target.subtract(current);

        if (diff.length() < 0.2 || data.ticks > 40) {
            pulling.remove(player.getUUID());
            return;
        }

        Vec3 next = current.add(diff.scale(speed));
        player.setPos(next.x, next.y, next.z);
    }
}
