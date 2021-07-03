package net.firiz.ateliercommonapi.nms.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import net.firiz.ateliercommonapi.MinecraftVersion;
import net.kyori.adventure.text.Component;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketListenerPlayOut;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public class WrapPacket<T extends Packet<PacketListenerPlayOut>> extends PacketDataSerializer {

    private final Class<T> packetClass;
    private T packet;

    public WrapPacket(Class<T> packetClass) {
        this(UnpooledByteBufAllocator.DEFAULT.buffer(), packetClass);
    }

    public WrapPacket(ByteBuf buffer, Class<T> packetClass) {
        super(buffer);
        this.packetClass = packetClass;
    }

    public T newInstance() {
        try {
            final Constructor<T> constructor = packetClass.getConstructor(PacketDataSerializer.class);
            return constructor.newInstance(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("not found constructor(PacketDataSerializer.class)");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("error packet constructor");
        }
    }

    public T getPacket() {
        if (packet == null) {
            packet = newInstance();
        }
        return packet;
    }

    public Object[] getFieldValues() {
        final Field[] fields = packetClass.getDeclaredFields();
        final Object[] array = new Object[fields.length];
        final T p = getPacket();
        for (int i = 0, size = fields.length; i < size; i++) {
            final Field field = fields[i];
            field.setAccessible(true);
            try {
                array[i] = field.get(p);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    // https://wiki.vg/Protocol#Data_types
    // https://wiki.vg/Protocol#VarInt_and_VarLong
    // PacketDataSerializer.j() readVarInt
    // PacketDataSerializer.d(int value) writeVarInt
    @MinecraftVersion("1.17")
    public WrapPacket<T> writeVarInt(int value) {
        d(value);
        return this;
    }

    // PacketDataSerializer.k() readVarLong
    // PacketDataSerializer.b(long value) writeVarLong
    @MinecraftVersion("1.17")
    public WrapPacket<T> writeVarLong(long value) {
        b(value);
        return this;
    }

    public @NotNull WrapPacket<T> writeItem(ItemStack item) {
        a(item);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeComponent(@NotNull Component component) {
        super.writeComponent(component);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeByte(int value) {
        super.writeByte(value);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeShort(int i) {
        super.writeShort(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeShortLE(int i) {
        super.writeShortLE(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeMedium(int i) {
        super.writeMedium(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeMediumLE(int i) {
        super.writeMediumLE(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeInt(int i) {
        super.writeInt(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeIntLE(int i) {
        super.writeIntLE(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeLong(long i) {
        super.writeLong(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeLongLE(long i) {
        super.writeLongLE(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeChar(int i) {
        super.writeChar(i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeFloat(float f) {
        super.writeFloat(f);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeDouble(double d0) {
        super.writeDouble(d0);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(@NotNull ByteBuf byteBuf) {
        super.writeBytes(byteBuf);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(@NotNull ByteBuf byteBuf, int i) {
        super.writeBytes(byteBuf, i);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(@NotNull ByteBuf byteBuf, int i, int j) {
        super.writeBytes(byteBuf, i, j);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(byte @NotNull [] bytes) {
        super.writeBytes(bytes);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(byte @NotNull [] bytes, int i, int j) {
        super.writeBytes(bytes, i, j);
        return this;
    }

    @Override
    public @NotNull WrapPacket<T> writeBytes(@NotNull ByteBuffer byteBuffer) {
        super.writeBytes(byteBuffer);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
