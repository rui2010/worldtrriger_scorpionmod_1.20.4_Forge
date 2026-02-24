//src/main/java/com/scorpionファイル内にあるということ
package com.scorpion;
//forgeやmodと認識するために必要なインポート
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod("scorpion")
public class ScorpionMod {
    public ScorpionMod() {
        //初期化
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        //アイテム登録の初期化
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //エンティティ登録の処理
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
