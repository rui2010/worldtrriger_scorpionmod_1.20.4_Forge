//src/main/java/com/scorpionファイル内にあるということ
package com.scorpion;
//アイテムの作るために必要なインポート
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.scorpion.items.ScorpionItem;

public class ModItems {
    //DeferredRegisterの作成
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "scorpion");

    // アイテムの名前と機能（スタックを一つだけにしてる）
    public static final RegistryObject<Item> SCORPION =
            ITEMS.register("scorpion",
                    () -> new ScorpionItem(new Item.Properties().stacksTo(1)));
}