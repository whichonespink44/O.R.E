package com.acronym.ore.world.generators.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by EwyBoy
 **/
public class WorldGenFlatBedrock implements IWorldGenerator {

    public static WorldGenFlatBedrock instance = new WorldGenFlatBedrock();

    IBlockState bedrock = Blocks.BEDROCK.getDefaultState();

    public boolean canGenerate(World world, int chunkX, int chunkZ) {
        return world.getWorldType() != WorldType.FLAT;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (canGenerate(world, chunkX, chunkZ)) generateFlatBedrock(world, chunkX, chunkZ);
    }

    public void generateFlatBedrock(World world, int chunkX, int chunkZ) {
        generateFlatBedrockBottom(world, chunkX, chunkZ, false);
    }

    public void retroGenerateFlatBedrock(World world, int chunkX, int chunkZ) {
        generateFlatBedrockBottom(world, chunkX, chunkZ, true);
    }

    private void generateFlatBedrockBottom(World world, int chunkX, int chunkZ, boolean retroGen) {
        int flag = retroGen ? 3 : 2;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 5; y++) {
                    BlockPos pos = new BlockPos(chunkX * 16 + x, y, chunkZ * 16 + z);
                    if (!world.getBlockState(pos).equals(bedrock)) world.setBlockState(pos, bedrock, flag);
                }
            }
        }
    }

    private void generateFlatBedrockTop(World world, int chunkX, int chunkZ, boolean retroGen) {
        int flag = retroGen ? 3 : 2;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 121; y < 126; y++) {
                    BlockPos pos = new BlockPos(chunkX * 16 + x, y, chunkZ * 16 + z);
                    if (!world.getBlockState(pos).equals(bedrock)) world.setBlockState(pos, bedrock, flag);
                }
            }
        }
    }
}