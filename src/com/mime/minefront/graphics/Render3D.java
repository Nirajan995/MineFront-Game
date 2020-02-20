package com.mime.minefront.graphics;

import com.mime.minefront.Game;
import com.mime.minefront.input.Controller;
import java.util.Random;

public class Render3D extends Render {

    public double[] zBuffer;
    public double renderDistance = 5000;
    private double forwardGlobal;

    public Render3D(int width, int height) {
        super(width, height);
        zBuffer = new double[width * height];
    }

    public void floor(Game game) {
        double floorPosition = 8;
        double ceilingPosition = 8;//Math.sin(game.time / 10 )// ;
        double forward = game.controls.z; //forward and back movement
        forwardGlobal = forward;
        double right = game.controls.x; //right and left movement
        double up = game.controls.y;

        // Head bob..
        double walking = Math.sin(game.time / 6.0) * 0.5;
        if (Controller.crouchWalk) {
            walking = Math.sin(game.time / 6.0) * 0.25;
        }

        if (Controller.runWalk) {
            walking = Math.sin(game.time / 6.0) * 0.8;
        }

        double rotation = game.controls.rotation;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);

        for (int y = 0; y < height; y++) {
            double ceiling = (y - height / 2.0) / height;

            double z = (floorPosition + up) / ceiling;

            if (Controller.walk) {
                z = (floorPosition + up + walking) / ceiling;
            }

            if (ceiling < 0) {
                z = (ceilingPosition - up) / -ceiling;
                if (Controller.walk) {
                    z = (floorPosition - up - walking) / -ceiling;
                }
            }

            for (int x = 0; x < width; x++) {
                double depth = (x - width / 2.0) / height;
                depth *= z;
                double xx = depth * cosine + z * sine + right;
                double yy = z * cosine - depth * sine + forward;
                int xPix = (int) (xx + right);
                int yPix = (int) (yy + forward);
                zBuffer[x + y * width] = z;
                pixels[x + y * width] = Texture.floor.pixels[(xPix & 7) + (yPix & 7) * 8];

                if (z > 500) {
                    pixels[x + y * width] = 0;
                }
            }
        }

    }

   /* public void walls() {

        Random random = new Random(100);
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble();
            double yy = random.nextDouble();
            double zz = 1.5 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble() - 1;
            double yy = random.nextDouble();
            double zz = 1.5 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble() - 1;
            double yy = random.nextDouble() - 1;
            double zz = 1.5 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble();
            double yy = random.nextDouble() - 1;
            double zz = 1.5 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble();
            double yy = random.nextDouble();
            double zz = 2 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble() - 1;
            double yy = random.nextDouble();
            double zz = 2 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble() - 1;
            double yy = random.nextDouble() - 1;
            double zz = 2 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }
        for (int i = 0; i < 20000; i++) {
            double xx = random.nextDouble();
            double yy = random.nextDouble() - 1;
            double zz = 2 - forwardGlobal / 16;

            int xPixel = (int) (xx / zz * height / 2 + width / 2);
            int yPixel = (int) (yy / zz * height / 2 + height / 2);
            if (xPixel >= 0 && yPixel >= 0 && xPixel < width && yPixel < height) {
                pixels[xPixel + yPixel * width] = 0xfffff;
            }
        }

    }*/

    public void renderDistanceLimiter() {
        for (int i = 0; i < width * height; i++) {
            int color = pixels[i];
            int brightness = (int) (renderDistance / (zBuffer[i]));

            if (brightness < 0) {
                brightness = 0;
            } else if (brightness > 255) {
                brightness = 255;
            }

            int r = (color >> 16) & 0xff;
            int g = (color >> 8) & 0xff;
            int b = (color) & 0xff;

            r = r * brightness / 255;
            g = g * brightness / 255;
            b = b * brightness / 255;

            pixels[i] = r << 16 | g << 8 | b;
        }
    }
}
