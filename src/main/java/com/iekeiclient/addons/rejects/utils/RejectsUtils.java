package com.iekeiclient.addons.rejects.utils;

import com.iekeiclient.addons.rejects.IekeiRejectsAddon;
import com.iekeiclient.addons.rejects.utils.seeds.Seeds;
import com.iekeiclient.engine.IekeiClient;
import com.iekeiclient.engine.events.entity.player.PlayerMoveEvent;
import com.iekeiclient.engine.mixininterface.IVec3d;
import com.iekeiclient.engine.systems.modules.Module;
import com.iekeiclient.engine.systems.modules.Modules;
import com.iekeiclient.engine.utils.PostInit;
import com.iekeiclient.engine.utils.Utils;
import com.iekeiclient.engine.utils.player.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

import static com.iekeiclient.engine.IekeiClient.mc;

public class RejectsUtils {
    @PostInit
    public static void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("saving seeds...");
            RejectsConfig.get().save(IekeiClient.FOLDER);
            Seeds.get().save(IekeiClient.FOLDER);
        }));
    }

    public static String getModuleName(String name) {
        int dupe = 0;
        Modules modules = Modules.get();
        if (modules == null) {
            IekeiRejectsAddon.LOG.warn("Module instantiation before Modules initialized.");
            return name;
        }
        for (Module module : modules.getAll()) {
            if (module.name.equals(name)) {
                dupe++;
                break;
            }
        }
        return dupe == 0 ? name : getModuleName(name + "*".repeat(dupe));
    }

    public static String getRandomPassword(int num) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(63);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static boolean inFov(Entity entity, double fov) {
        if (fov >= 360) return true;
        float[] angle = PlayerUtils.calculateAngle(entity.getBoundingBox().getCenter());
        double xDist = MathHelper.angleBetween(angle[0], mc.player.getYaw());
        double yDist = MathHelper.angleBetween(angle[1], mc.player.getPitch());
        double angleDistance = Math.hypot(xDist, yDist);
        return angleDistance <= fov;
    }

    public static float fullFlightMove(PlayerMoveEvent event, double speed, boolean verticalSpeedMatch) {
        if (PlayerUtils.isMoving()) {
            double dir = getDir();

            double xDir = Math.cos(Math.toRadians(dir + 90));
            double zDir = Math.sin(Math.toRadians(dir + 90));

            ((IVec3d) event.movement).iekei$setXZ(xDir * speed, zDir * speed);
        } else {
            ((IVec3d) event.movement).iekei$setXZ(0, 0);
        }

        float ySpeed = 0;

        if (mc.options.jumpKey.isPressed())
            ySpeed += speed;
        if (mc.options.sneakKey.isPressed())
            ySpeed -= speed;
        ((IVec3d) event.movement).iekei$setY(verticalSpeedMatch ? ySpeed : ySpeed / 2);

        return ySpeed;
    }

    private static double getDir() {
        double dir = 0;

        if (Utils.canUpdate()) {
            dir = mc.player.getYaw() + ((mc.player.forwardSpeed < 0) ? 180 : 0);

            if (mc.player.sidewaysSpeed > 0) {
                dir += -90F * ((mc.player.forwardSpeed < 0) ? -0.5F : ((mc.player.forwardSpeed > 0) ? 0.5F : 1F));
            } else if (mc.player.sidewaysSpeed < 0) {
                dir += 90F * ((mc.player.forwardSpeed < 0) ? -0.5F : ((mc.player.forwardSpeed > 0) ? 0.5F : 1F));
            }
        }
        return dir;
    }
}
