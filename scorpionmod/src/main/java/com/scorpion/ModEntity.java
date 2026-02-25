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
        public static final RegistryObject<EntityType<ShotScorpion>> ShotSCORPION =
                ENTITIES.register("shotscorpion",
                        () -> EntityType.Builder.<ScorpionProjectile>of(ScorpionProjectile::new, MobCategory.MISC)
                                // 当たり判定の大きさ(横,縦)
                                .sized(0.8f, 0.25f) 
                                .build("shotscorpion"));
                                
        //マンティス
        public static final RegistryObject<EntityType<Mantis>> Mantis =
                ENTITIES.register("mantis",
                        () -> EntityType.Builder.<ScorpionProjectile>of(ScorpionProjectile::new, MobCategory.MISC)
                                // 当たり判定の大きさ(横,縦)
                                .sized(0.8f, 0.8f) 
                                .build("mantis"));
}
