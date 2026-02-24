//src/main/java/com/scorpion/Itemsファイル内にあるということ
package com.scorpion.items;
////インポート類
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

            if (onlyOneHand) {
                // ★ 片手だけ持っているときの処理
                player.sendSystemMessage(Component.literal("片手だけに持っている！"));
            }

            else if (bothHands) {
                // ★ 両手に持っているときの処理
                player.sendSystemMessage(Component.literal("両手に持っている！"));
            }

            else {
                // ★ どちらにも持っていない
                player.sendSystemMessage(Component.literal("どちらの手にも持っていない"));
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
