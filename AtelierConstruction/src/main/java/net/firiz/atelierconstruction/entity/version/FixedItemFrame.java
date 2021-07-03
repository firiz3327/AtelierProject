package net.firiz.atelierconstruction.entity.version;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.EnumDirection;
import net.minecraft.world.entity.decoration.EntityItemFrame;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.CraftBlock;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.function.Consumer;

/**
 * ItemFrameをスポーンさせる時、生存可能でない場所ではsurvivesメソッドによってエラーを吐かれる
 * 原因はsurvivesメソッドはfixedがtrueの場合、survivesメソッドは生存可能と返すが、
 * World::createEntityではsurvivesメソッドが実行される以前にそのエンティティの変数はいじれない為、スポーンできない
 * この問題を解決する目的で作成されている
 */
public class FixedItemFrame {

    private final EntityItemFrame entity;

    private FixedItemFrame(Location location, BlockFace face) {
        // CraftWorld::createEntity
        Preconditions.checkArgument(face != BlockFace.SELF, "Cannot spawn hanging entity for %s at %s (no free face)", ItemFrame.class.getName(), location);
        EnumDirection dir = CraftBlock.blockFaceToNotch(face).opposite();
        this.entity = new EntityItemFrame(
                ((CraftWorld) location.getWorld()).getHandle(),
                new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()),
                dir
        );
        // EntityItemFrame.ar (1.17) = EntityItemFrame.fixed (1.16)
        this.entity.ar = true;
    }

    public static ItemFrame spawn(Location location, BlockFace face) {
        return spawn(location, face, CreatureSpawnEvent.SpawnReason.CUSTOM, null);
    }

    public static ItemFrame spawn(Location location, BlockFace face, CreatureSpawnEvent.SpawnReason reason) {
        return spawn(location, face, reason, null);
    }

    public static ItemFrame spawn(Location location, BlockFace face, Consumer<ItemFrame> consumer) {
        return spawn(location, face, CreatureSpawnEvent.SpawnReason.CUSTOM, consumer);
    }

    public static ItemFrame spawn(Location location, BlockFace face, CreatureSpawnEvent.SpawnReason reason, Consumer<ItemFrame> consumer) {
        return new FixedItemFrame(location, face).spawn(reason, consumer);
    }

    private ItemFrame spawn(CreatureSpawnEvent.SpawnReason reason, Consumer<ItemFrame> consumer) {
        final ItemFrame bukkitEntity = (ItemFrame) entity.getBukkitEntity();
        if (consumer != null) {
            consumer.accept(bukkitEntity);
        }
        entity.getWorld().addEntity(entity, reason);
        return bukkitEntity;
    }

}
