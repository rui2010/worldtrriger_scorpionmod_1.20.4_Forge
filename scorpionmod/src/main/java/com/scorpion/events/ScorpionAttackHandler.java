package com.scorpion.events;

import com.scorpion.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ScorpionAttackHandler {

    @SubscribeEvent
    public static void onLeftClick(AttackEntityEvent event) {

        var player = event.getEntity();
        var level = player.level();

        //メインハンド or オフハンドにスコーピオンを持っているか？
        boolean hasScorpion =
                player.getMainHandItem().getItem() == ModItems.SCORPION.get() ||
                player.getOffhandItem().getItem() == ModItems.SCORPION.get();

        if (!hasScorpion)
            return;

        //攻撃ゲージが溜まっていないときはメッセージを出して攻撃できないようにする
        if (player.getAttackStrengthScale(0.0F) < 1.0F) {
            player.displayClientMessage(
                    Component.literal("スコーピオン通常攻撃クールタイム"),
                    true // ActionBar に表示
            );
            return;
        }

        // エンティティがクロスヘアに向いているか
        HitResult hit = player.pick(3.0, 0.0F, false);
        //向いているとき
        if (hit instanceof EntityHitResult ehr) {
            ehr.getEntity().hurt(
                    player.damageSources().playerAttack(player),
                    10.0F
            );
            return;
        }

        //向いていないとき用の画面内の3m以内の敵に弱攻撃
        double range = 3.0;

        var list = level.getEntities(player,
                player.getBoundingBox().inflate(range),
                e -> e != player && e.isAlive()
        );

        var look = player.getLookAngle().normalize();

        for (var target : list) {

            var vec = target.position().subtract(player.position()).normalize();
            double dot = look.dot(vec);

            if (dot > 0.5 && player.hasLineOfSight(target)) {
                target.hurt(
                        player.damageSources().playerAttack(player),
                        6.0F
                );
            }
        }
    }
}
