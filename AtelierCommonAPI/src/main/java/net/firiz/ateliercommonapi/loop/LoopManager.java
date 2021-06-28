package net.firiz.ateliercommonapi.loop;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.firiz.ateliercommonapi.AtelierCommonAPI;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Collection;
import java.util.List;

/**
 * @author firiz
 */
public enum LoopManager {
    INSTANCE;

    private final AtelierCommonAPI plugin = AtelierCommonAPI.getPlugin();
    private final BukkitScheduler scheduler = plugin.getServer().getScheduler();
    private final Int2ObjectOpenHashMap<TickRunnable> ticks;
    private final Int2ObjectOpenHashMap<TickRunnable> seconds;
    private final Int2ObjectOpenHashMap<TickRunnable> halfSeconds;
    private final Int2ObjectOpenHashMap<TickRunnable> minutes;
    private boolean start;
    private int period = 0;
    private int secondsPeriod = 0;
    private int taskId;

    private int ticksId;
    private int halfSecondsId;
    private int secondsId;
    private int minutesId;

    LoopManager() {
        start = false;
        ticks = new Int2ObjectOpenHashMap<>();
        seconds = new Int2ObjectOpenHashMap<>();
        halfSeconds = new Int2ObjectOpenHashMap<>();
        minutes = new Int2ObjectOpenHashMap<>();
    }

    public void start() {
        if (!start) {
            loop();
            start = true;
        }
    }

    public void stop() {
        scheduler.cancelTask(taskId);
    }

    public List<Collection<TickRunnable>> getRunnableList() {
        final List<Collection<TickRunnable>> tickRunnableList = new ObjectArrayList<>();
        tickRunnableList.add(ticks.values());
        tickRunnableList.add(seconds.values());
        tickRunnableList.add(halfSeconds.values());
        tickRunnableList.add(minutes.values());
        return tickRunnableList;
    }

    // 厳密さは求めていないので大分適当。あっているかはわからない
    private void loop() {
        taskId = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            ticks.values().forEach(Runnable::run);
            if (period % 10 == 0) {
                new ObjectArrayList<>(halfSeconds.values()).forEach(Runnable::run);
            }
            if (period >= 20) {
                period = 0;
                seconds();
                return;
            }
            period++;
        }, 0L, 1L);
    }

    private void seconds() {
        secondsPeriod++;
        new ObjectArrayList<>(seconds.values()).forEach(Runnable::run);
        if (secondsPeriod % 60 == 0) {
            secondsPeriod = 0;
            new ObjectArrayList<>(minutes.values()).forEach(Runnable::run);
        }
    }

    private int put(final int mode, final TickRunnable runnable) {
        final Int2ObjectOpenHashMap<TickRunnable> map;
        final int id;
        switch (mode) {
            case 0 -> {
                id = ticksId++;
                map = ticks;
            }
            case 1 -> {
                id = halfSecondsId++;
                map = halfSeconds;
            }
            case 2 -> {
                id = secondsId++;
                map = seconds;
            }
            default -> {
                id = minutesId++;
                map = minutes;
            }
        }
        map.put(id, runnable);
        return id;
    }

    public int addTicks(final TickRunnable run) {
        return put(0, run);
    }

    public void removeTicks(final int id) {
        ticks.remove(id).removed();
    }

    public int addHalfSeconds(final TickRunnable run) {
        return put(1, run);
    }

    public void removeHalfSeconds(final int id) {
        halfSeconds.remove(id).removed();
    }

    public int addSeconds(final TickRunnable run) {
        return put(2, run);
    }

    public void removeSeconds(final int id) {
        seconds.remove(id).removed();
    }

    public int addMinutes(final TickRunnable run) {
        return put(3, run);
    }

    public void removeMinutes(final int id) {
        minutes.remove(id).removed();
    }

}
