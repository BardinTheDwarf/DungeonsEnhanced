package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class DECellarStructure{
    public static boolean checkLocation(Structure.GenerationContext context, BlockPos placementPos, DETerrainAnalyzer.Settings checkSettings){
//        DungeonsEnhanced.LOGGER.info("Check Pos: " + DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()));
        return DETerrainAnalyzer.isFlatEnough(context.chunkPos(), context.chunkGenerator(), checkSettings, context.heightAccessor(), context.randomState());
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureTemplateManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox box) {
            super(structureManager, poolElement, pos, groundLevelDelta, rotation, box);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}
        @Override
        public StructurePieceType getType() {return DEStructures.Castle.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void init(){
        CastlePool.init();
        DruidCirclePool.init();
    }

    public static class CastlePool{
        public static final Holder<StructureTemplatePool> Root;
        public static void init() {}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"castle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top1", "top2").build());

            registry.register("bottom1", poolBuilder.clone().names("bottom1").build());
            registry.register("bottom2", poolBuilder.clone().names("bottom2").build());
        }
    }

    public static class DruidCirclePool{
        public static final Holder<StructureTemplatePool> Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"druid_circle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top_big", "small").build());

            registry.register("bottom_big", poolBuilder.clone().names("bottom_big").build());
        }
    }
}