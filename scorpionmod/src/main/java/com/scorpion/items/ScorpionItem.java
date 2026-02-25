//src/main/java/com/scorpion/Itemsファイル内にあるということ
package com.scorpion.items;
////インポート類
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import com.scorpion.entity.ShotScorpion;
import com.scorpion.ModEntities;
import net.minecraft.world.phys.Vec3;

public class ScorpionItem extends Item {

    public ScorpionItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        boolean mainHas = player.getMainHandItem().getItem() instanceof ScorpionItem;
        boolean offHas  = player.getOffhandItem().getItem() instanceof ScorpionItem;

        boolean onlyOneHand = mainHas ^ offHas;   // 片手だけ
        boolean bothHands   = mainHas && offHas;  // 両手

        if (!level.isClientSide) {
            //片手だけ持っているときの処理
            if (onlyOneHand) {
                player.sendSystemMessage(Component.literal("片手だけに持っている！"));
                ShotScorpion proj = new ShotScorpion(level, player);
                //飛ばしたらスコーピオンを消す
                ItemStack stack = player.getItemInHand(hand);
                stack.shrink(1);
                proj.shootFromRotation(
                    player,
                    player.getXRot(),   // 上下の向き
                    player.getYRot(),   // 左右の向き
                    0.0F,               // 発射角度補正
                    0.9F,               // 速度（雪玉が1くらい）
                    0.0F                // ブレ（0はブレないが1でブレる）
                );
                //ワールドに出現させる
                level.addFreshEntity(proj);


            }
            //両手に持っているときの処理
            else if (bothHands) {
                player.sendSystemMessage(Component.literal("両手に持っている！"));
            
                var mantis = ModEntities.MANTIS.get().create(level);
            
                if (mantis != null) {
                
                    mantis.owner = player;
            
                    mantis.setPos(player.getX(), player.getY() + 1.0, player.getZ());
                    mantis.setYRot(player.getYRot());
                    mantis.setXRot(player.getXRot());
            
                    double distance = 10.0;
                    Vec3 dir = player.getLookAngle().normalize();
                    mantis.targetPos = mantis.position().add(dir.scale(distance));
            
                    level.addFreshEntity(mantis);
                }
            
                player.getMainHandItem().shrink(1);
                player.getOffhandItem().shrink(1);
            }

            else {
                //どちらにも持っていない
                player.sendSystemMessage(Component.literal("どちらの手にも持っていない"));
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}

