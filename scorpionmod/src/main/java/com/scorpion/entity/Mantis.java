package com.scorpion.entity;

import com.scorpion.util.PlayerPuller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.nbt.CompoundTag;

public class Mantis extends Entity {

    public Vec3 targetPos;
    public LivingEntity owner;

    private int lifeTicks = -1;

    public Mantis(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public void tick() {
        super.tick();

        // ★ 寿命カウント
        if (lifeTicks >= 0) {
            lifeTicks++;
            if (lifeTicks > 10) { // 0.5秒後
                this.discard();
                return;
            }
        }

        if (targetPos == null) return;

        Vec3 current = this.position();
        Vec3 diff = targetPos.subtract(current);

        // ★ 伸び終わり
        if (diff.length() < 0.3) {
            lifeTicks = 0;
            return;
        }

        // ★ 滑らかに伸びる
        double speed = 0.25;
        Vec3 next = current.add(diff.scale(speed));
        this.setPos(next.x, next.y, next.z);

        // ★ ブロックに当たった
        if (!level().noCollision(this)) {

            if (owner instanceof Player player) {
                PlayerPuller.startPull(player, this.position());
            }

            lifeTicks = 0;
            return;
        }

        // ★ プレイヤーに当たった
        for (Player p : level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(0.5))) {
            if (p != owner) {
                p.hurt(damageSources().mobAttack(owner), 12.0F);
                lifeTicks = 0;
                return;
            }
        }
    }
}
