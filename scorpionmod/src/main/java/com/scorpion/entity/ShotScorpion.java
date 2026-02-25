package com.scorpion.entity;

import com.scorpion.ModEntities;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import com.scorpion.ModItems;

public class ShotScorpion extends ThrowableItemProjectile {

    public ShotScorpion(EntityType<? extends ShotScorpion> type, Level level) {
        super(type, level);
    }

    public ShotScorpion(Level level, LivingEntity shooter) {
        super(ModEntities.SHOTSCORPION.get(), shooter, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        result.getEntity().hurt(damageSources().thrown(this, this.getOwner()), 4.0F);
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    @Override
    public void tick() {
        super.tick();

        // 空気抵抗無効
        this.setDeltaMovement(this.getDeltaMovement());

        // ★ プレイヤーから10ブロック以上離れたら消える
        if (this.getOwner() instanceof Player player) {
            double distance = this.distanceTo(player);
            if (distance > 10.0) {
                this.discard();
            }
        }
    }
    //消えたらアイテムを返す
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();

        if (this.getOwner() instanceof Player player) {
            player.addItem(new ItemStack(ModItems.SCORPION.get()));
        }
    }
}
