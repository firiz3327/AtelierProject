package net.firiz.ateliercommonapi.fen;

import net.firiz.ateliercommonapi.loop.LoopManager;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MainEntity {

    final HeadEntity head;
    final BodyEntity body;
    final EntityBase[] entities;

    float yaw;
    float pitch;

    int loopId;

    public MainEntity(HeadEntity head, BodyEntity body) {
        this.head = head;
        this.body = body;
        this.entities = new EntityBase[]{head, body};
    }

    public void animateAngle(float yaw, float pitch) {
        final int splitCount = 20;
        final float beforeYaw = this.yaw;
        final float beforePitch = this.pitch;
        this.yaw = yaw;
        this.pitch = pitch;
        final float iYaw = Math.abs(beforeYaw - yaw) / splitCount * (beforeYaw > yaw ? -1 : 1);
        final float iPitch = Math.abs(beforePitch - pitch) / splitCount * (beforePitch > pitch ? -1 : 1);
        final AtomicInteger status = new AtomicInteger(0);
        LoopManager.INSTANCE.removeTicks(this.loopId);
        this.loopId = LoopManager.INSTANCE.addTicks(() -> {
            for (final EntityBase entityBase : entities) {
                entityBase.anglePacket(yaw + iYaw, pitch + iPitch);
            }
            if (status.incrementAndGet() > 20) {
                LoopManager.INSTANCE.removeTicks(this.loopId);
            }
        });
    }

    public void angle(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        for (final EntityBase entityBase : entities) {
            entityBase.angle(yaw, pitch);
        }
    }

    public void location(@NotNull final Location location) {
        Objects.requireNonNull(location);
        for (final EntityBase entityBase : entities) {
            entityBase.setLocation(location);
        }
    }

}
