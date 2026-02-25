package com.scorpion;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod("scorpion")
public class ScorpionMod {

    public ScorpionMod() {

        // アイテム登録
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // エンティティ登録
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // サーバー側イベント（必要なら）
        MinecraftForge.EVENT_BUS.register(this);
    }
}
