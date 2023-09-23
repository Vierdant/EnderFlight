package me.vierdant.enderflight.block.entity;

import me.vierdant.enderflight.block.ModBlocks;
import me.vierdant.enderflight.block.custom.FlightAnchorBlock;
import me.vierdant.enderflight.effect.ModStatusEffect;
import me.vierdant.enderflight.screen.FlightAnchorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlightAnchorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImpInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int maxTime = 1200;
    private int currentTime = 0;
    private int fuel = 0;

    public FlightAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLIGHT_ANCHOR, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FlightAnchorBlockEntity.this.currentTime;
                    case 1 -> FlightAnchorBlockEntity.this.maxTime;
                    case 2 -> FlightAnchorBlockEntity.this.fuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0: FlightAnchorBlockEntity.this.currentTime = value; break;
                    case 1: FlightAnchorBlockEntity.this.maxTime = value; break;
                    case 2: FlightAnchorBlockEntity.this.fuel = value; break;
                }

            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.enderflight.flight_anchor.inventory.name");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FlightAnchorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("flight_anchor.currentTime", currentTime);
        nbt.putInt("flight_anchor.maxTime", maxTime);
        nbt.putInt("flight_anchor.fuel", fuel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        currentTime = nbt.getInt("flight_anchor.currentTime");
        maxTime = nbt.getInt("flight_anchor.maxTime");
        fuel = nbt.getInt("flight_anchor.fuel");
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, FlightAnchorBlockEntity entity) {
        if (world.isClient()) { return; }

        if (entity.fuel >= 1) {
            applyFlightEffect(world, blockPos, 32 +
                    (shouldApplyModifier(world, blockPos) ? 32 : 0));

            entity.currentTime++;
            markDirty(world, blockPos, blockState);
            if (entity.currentTime >= entity.maxTime) {
                entity.fuel--;
                entity.currentTime = 0;


                if (!hasFuelConvertable(entity)) {
                    world.setBlockState(blockPos, blockState.cycle(FlightAnchorBlock.ACTIVE));
                }

                markDirty(world, blockPos, blockState);
            }
        } else {
            refuel(world, blockPos, blockState, entity);
        }

    }

    private static void refuel(World world, BlockPos blockPos, BlockState blockState, FlightAnchorBlockEntity entity) {
        if (hasFuelConvertable(entity)) {

            if (entity.fuel <= 0) {
                if (blockState.get(FlightAnchorBlock.ACTIVE)) {
                    world.setBlockState(blockPos, blockState.cycle(FlightAnchorBlock.ACTIVE));
                }
            }

            entity.removeStack(0, 1);
            entity.fuel++;
            markDirty(world, blockPos, blockState);
        }
    }

    private static boolean hasFuelConvertable(FlightAnchorBlockEntity entity) {
        return entity.getStack(0).getItem() == Items.DRAGON_BREATH;
    }

    private static List<PlayerEntity> getPlayersInRange(World world, BlockPos blockPos, int range) {
        Box box = new Box(blockPos).expand(range).stretch(0.0, world.getHeight(), 0.0);
        return world.getNonSpectatingEntities(PlayerEntity.class, box);
    }

    private static void applyFlightEffect(World world, BlockPos blockPos, int range) {
        List<PlayerEntity> list = getPlayersInRange(world, blockPos, range);
        for (PlayerEntity playerEntity : list) {
            playerEntity.addStatusEffect(new StatusEffectInstance(ModStatusEffect.FLIGHT, 100, 0, true, true));
        }
    }

    private static boolean shouldApplyModifier(World world, BlockPos blockPos) {
        return world.getBlockState(blockPos.down()).isOf(ModBlocks.CHORATED_SHULKER);
    }

}
