package net.firiz.atelierconstruction.utils;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class LocationComponent {

    private final World world;
    private final List<Location> locationList;
    private Location lastLocation;

    public LocationComponent(@NotNull World world) {
        this.world = world;
        this.locationList = new ObjectArrayList<>();
    }

    public LocationComponent(@NotNull World world, double x, double y, double z) {
        this.world = world;
        this.locationList = new ObjectArrayList<>();
        this.lastLocation = new Location(world, x, y, z);
        this.locationList.add(lastLocation);
    }

    public LocationComponent(@NotNull List<Location> locationList) {
        if (locationList.isEmpty()) {
            throw new IllegalArgumentException("locationList is empty.");
        }
        this.world = locationList.get(0).getWorld();
        this.locationList = locationList;
        this.lastLocation = locationList.get(locationList.size() - 1);
    }

    public List<Location> get() {
        return locationList;
    }

    public LocationComponent add(@NotNull Location location) {
        return add(location, true);
    }

    public LocationComponent add(@NotNull Location location, boolean refresh) {
        this.locationList.add(location);
        if (refresh) {
            this.lastLocation = location;
        }
        return this;
    }

    public LocationComponent add(double x, double y, double z) {
        return add(x, y, z, true);
    }

    public LocationComponent add(double x, double y, double z, boolean refresh) {
        final Location location = new Location(world, x, y, z);
        this.locationList.add(lastLocation);
        if (refresh) {
            this.lastLocation = location;
        }
        return this;
    }

    public LocationComponent add2add(double x, double y, double z) {
        return add2add(x, y, z, true);
    }

    public LocationComponent add2add(double x, double y, double z, boolean refresh) {
        final Location location = lastLocation.clone().add(x, y, z);
        this.locationList.add(lastLocation);
        if (refresh) {
            this.lastLocation = location;
        }
        return this;
    }

    public LocationComponent finely(int splitCount) {
        final List<Location> temp = new ObjectArrayList<>(locationList);
        final List<Location> result = new ObjectArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            int b = i - 1;
            if (b < 0) {
                b = temp.size() - 1;
            }
            final Location before = temp.get(b);
            final Location target = temp.get(i);
            final List<Location> l = new ObjectArrayList<>();
            boolean bx = before.getX() > target.getX();
            double ax = Math.abs(before.getX() - target.getX()) / splitCount * (bx ? -1 : 1);
            boolean by = before.getY() > target.getY();
            double ay = Math.abs(before.getY() - target.getY()) / splitCount * (by ? -1 : 1);
            boolean bz = before.getZ() > target.getZ();
            double az = Math.abs(before.getZ() - target.getZ()) / splitCount * (bz ? -1 : 1);
            for (int m = 0; m < splitCount; m++) {
                l.add(new Location(
                        before.getWorld(),
                        before.getX() + (m * ax),
                        before.getY() + (m * ay),
                        before.getZ() + (m * az)
                ));
            }
            result.addAll(l);
        }
        locationList.clear();
        locationList.addAll(result);
        return this;
    }

    public LocationComponent line(double x, double y, double z, int maxY) {

        return this;
    }

    public LocationComponent gravity(double x, double y, double z, double a1, double a2, int count) {
        final Location start = lastLocation.clone();
        final Location end = new Location(world, x, y, z);
        final double nx = (end.getX() - start.getX()) / count;
        final double nz = (end.getZ() - start.getZ()) / count;
        for (int i = -(count / 2); i <= count / 2; i++) {
            if (i == 0) {
                continue;
            }
            double ny = Math.pow(i * a1, 2) / a2;
            if (i > 0) {
                ny *= -1;
            }
            add2add(nx, ny, nz);
        }
        return this;
    }

    public LocationComponent circle(double radius, int count, boolean y) {
        locationList.clear();
        final Location center = lastLocation;
        final Location l1 = center.clone();
        l1.setX(l1.getX() - radius);
        lastLocation = l1;
        final double r = count / radius;
        for (int plus = 0; plus < 2; plus++) {
            a(plus, count, radius, r, y, false);
        }
        final Location l2 = center.clone();
        l2.setZ(l2.getZ() + radius);
        lastLocation = l2;
        for (int plus = 0; plus < 2; plus++) {
            a(plus, count, radius, r, y, true);
        }
        return this;
    }

    private void a(int plus, int count, double radius, double r, boolean y, boolean rotate) {
        // x^2 + y^2 = r^2
        final double m = (plus == 0 ? 1D : -1D) * (rotate ? -1D : 1D);
        final int start = -count + 1;
        double oldNV = 0;
        for (int i = start; i <= count; i++) {
            double nn = 1 / r * m;
            double nv = Math.sqrt(Math.pow(radius, 2) - Math.pow(i / r, 2)) * m;
            if (rotate) {
                if (y) {
                    add2add(0, nv - oldNV, nn);
                } else {
                    add2add(nv - oldNV, 0, nn);
                }
            } else {
                if (y) {
                    add2add(nn, nv - oldNV, 0);
                } else {
                    add2add(nn, 0, nv - oldNV);
                }
            }
            oldNV = nv;
        }
    }

}
