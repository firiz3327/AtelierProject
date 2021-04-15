package net.firiz.atelierconstruction.modelapi.math;

import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Quaternion {
    private double w;
    private Vector vec;

    public Quaternion(double w, Vector vec) {
        this.w = w;
        this.vec = vec;
    }

    public static Quaternion toQuaternion(EulerAngle e) {
        double c1 = Math.cos(e.getX() * 0.5D);
        double c2 = Math.cos(e.getY() * -0.5D);
        double c3 = Math.cos(e.getZ() * 0.5D);
        double s1 = Math.sin(e.getX() * 0.5D);
        double s2 = Math.sin(e.getY() * -0.5D);
        double s3 = Math.sin(e.getZ() * 0.5D);
        Vector vec = new Vector(s1 * c2 * c3 + c1 * s2 * s3, c1 * s2 * c3 - s1 * c2 * s3, c1 * c2 * s3 + s1 * s2 * c3);
        double w = c1 * c2 * c3 - s1 * s2 * s3;
        return new Quaternion(w, vec);
    }

    public static EulerAngle toEuler(Quaternion q) {
        double x = q.getVec().getX();
        double y = q.getVec().getY();
        double z = q.getVec().getZ();
        double w = q.getW();
        double x2 = x + x;
        double y2 = y + y;
        double z2 = z + z;
        double xx = x * x2;
        double xy = x * y2;
        double xz = x * z2;
        double yy = y * y2;
        double yz = y * z2;
        double zz = z * z2;
        double wx = w * x2;
        double wy = w * y2;
        double wz = w * z2;
        double m11 = 1.0D - (yy + zz);
        double m12 = xy - wz;
        double m13 = xz + wy;
        double m22 = 1.0D - (xx + zz);
        double m23 = yz - wx;
        double m32 = yz + wx;
        double m33 = 1.0D - (xx + yy);
        double ey = Math.asin(clamp(m13, -1.0D, 1.0D));
        double ex;
        double ez;
        if (Math.abs(m13) < 0.99999D) {
            ex = Math.atan2(-m23, m33);
            ez = Math.atan2(-m12, m11);
        } else {
            ex = Math.atan2(m32, m22);
            ez = 0.0D;
        }

        return new EulerAngle(ex, -ey, ez);
    }

    public static Quaternion multiply(Quaternion a, Quaternion b) {
        double qax = a.getVec().getX();
        double qay = a.getVec().getY();
        double qaz = a.getVec().getZ();
        double qaw = a.getW();
        double qbx = b.getVec().getX();
        double qby = b.getVec().getY();
        double qbz = b.getVec().getZ();
        double qbw = b.getW();
        Vector vec = new Vector(qax * qbw + qaw * qbx + qay * qbz - qaz * qby, qay * qbw + qaw * qby + qaz * qbx - qax * qbz, qaz * qbw + qaw * qbz + qax * qby - qay * qbx);
        double w = qaw * qbw - qax * qbx - qay * qby - qaz * qbz;
        return new Quaternion(w, vec);
    }

    public static Quaternion multiply(Quaternion a, double b) {
        return new Quaternion(a.getW() * b, a.getVec().multiply(b));
    }

    public static double dot(Quaternion a, Quaternion b) {
        return a.getW() * b.getW() + a.getVec().dot(b.getVec());
    }

    public static Quaternion add(Quaternion a, Quaternion b) {
        return new Quaternion(a.getW() + b.getW(), a.getVec().add(b.getVec()));
    }

    public static Quaternion subtract(Quaternion a, Quaternion b) {
        return new Quaternion(a.getW() - b.getW(), a.getVec().subtract(b.getVec()));
    }

    public static EulerAngle combine(EulerAngle origin, EulerAngle delta) {
        return toEuler(multiply(toQuaternion(origin), toQuaternion(delta)));
    }

    public static EulerAngle slerp(EulerAngle a, EulerAngle b, double t) {
        Quaternion qA = toQuaternion(a);
        Quaternion qB = toQuaternion(b);
        double dot = dot(qA, qB);
        if (dot < 0.0D) {
            qB.multiply(-1.0D);
            dot = -dot;
        }

        if (dot > 0.9995D) {
            Quaternion result = subtract(qB, qA);
            result = multiply(result, t);
            result = add(qA, result);
            return toEuler(result);
        } else {
            double theta_0 = Math.acos(dot);
            double theta = theta_0 * t;
            double sin_theta = Math.sin(theta);
            double sin_theta_0 = Math.sin(theta_0);
            double sA = Math.cos(theta) - dot * sin_theta / sin_theta_0;
            double sB = sin_theta / sin_theta_0;
            Quaternion rQA = multiply(qA, sA);
            Quaternion rQB = multiply(qB, sB);
            return toEuler(add(rQA, rQB));
        }
    }

    public void multiply(double b) {
        this.w *= b;
        this.vec.multiply(b);
    }

    public void normalize() {
        double norm = Math.sqrt(this.w * this.w + this.vec.getX() * this.vec.getX() + this.vec.getY() * this.vec.getY() + this.vec.getZ() * this.vec.getZ());
        this.w /= norm;
        this.vec.multiply(1.0D / norm);
    }

    public double getW() {
        return this.w;
    }

    public Vector getVec() {
        return this.vec;
    }

    public String toFormula() {
        return "[" + this.w + "+" + this.vec.getX() + "i+" + this.vec.getY() + "j+" + this.vec.getZ() + "k" + "]";
    }

    private static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }
}
