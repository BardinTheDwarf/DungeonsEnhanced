package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESwimmingStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

import static com.barion.dungeons_enhanced.DEUtil.location;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEPirateShip extends DESwimmingStructure {

    private static final ResourceLocation Back = location("pirate_ship/front");

    public DEPirateShip() {
        super(DEConfig.COMMON.PirateShip, true, pieceBuilder().offset(-7, -4, -25).add("pirate_ship/back").build());
        setSpawnList(EntityClassification.MONSTER, ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.PILLAGER, 5, 2, 4), new MobSpawnInfo.Spawners(EntityType.VINDICATOR, 3, 1, 2)));
    }

    @Override
    public void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex){
        rotation = Rotation.NONE;
        pieces.add(new Piece(templateManager, variant.Resource, pos, rotation));
        pieces.add(new Piece(templateManager, Back, pos.offset(0, 0, 26), rotation));
    }
}