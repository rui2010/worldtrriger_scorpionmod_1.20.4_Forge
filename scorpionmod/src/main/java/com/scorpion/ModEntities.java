package com.scorpion;

import com.scorpion.entity.ShotScorpion;
import com.scorpion.entity.Mantis;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

        public static final DeferredRegister<EntityType<?>> ENTITIES =
                DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "scorpion");
        //片手のみに持ってるときのスコーピオンを投げたときのエンティティ
        public static final RegistryObject<EntityType<ShotScorpion>> SHOTSCORPION =
                ENTITIES.register("shotscorpion",
                        () -> EntityType.Builder.<ShotScorpion>of(ShotScorpion::new, MobCategory.MISC)
                                // 当たり判定の大きさ(横,縦)
                                .sized(0.4f, 0.2f) 
                                .build("shotscorpion"));

        //マンティス
        public static final RegistryObject<EntityType<Mantis>> MANTIS =
                ENTITIES.register("mantis",
                        () -> EntityType.Builder.<Mantis>of(Mantis::new, MobCategory.MISC)
                                // 当たり判定の大きさ(横,縦)
                                .sized(0.5f, 0.5f) 
                                .build("mantis"));
}
