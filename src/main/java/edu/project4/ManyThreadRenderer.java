package edu.project4;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ManyThreadRenderer implements Renderer {

    private Logger logger = Logger.getLogger("Renderer");
    private final double xMin = -1;
    private final double xMax = 1;
    private final double yMin = -1;
    private final double yMax = 1;
    private final FunctionProvider functionProvider;
    private Pixel[][] pixels;
    private final int threadCount;

    public ManyThreadRenderer(FunctionProvider functionProvider, int threadCount) {
        this.functionProvider = functionProvider;
        this.threadCount = threadCount;
    }

    @Override
    public void render(int numCount, int iterCount, int width, int height, int symmetry, double gammaCorrect) {
        generatePixelsArray(numCount, iterCount, width, height, symmetry);
        pixels = makeGammaCorrection(pixels, gammaCorrect);

        // Показать изображение
        paint(pixels, width, height);
    }

    //перегрузка для сохранения изображения
    @SuppressWarnings("ParameterNumber")
    public void render(
        int numCount, int iterCount, int width, int height, int symmetry,
        double gammaCorrect, ImageFormat format, String title
    ) {
        generatePixelsArray(numCount, iterCount, width, height, symmetry);
        pixels = makeGammaCorrection(pixels, gammaCorrect);

        // Сохранить изображение
        saveImage(pixels, width, height, title, format);
    }

    @SuppressWarnings("MagicNumber")
    private void saveImage(Pixel[][] pixels, int width, int height, String title, ImageFormat format) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(java.awt.Color.BLACK);
        graphic.fillRect(0, 0, width, height);

        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[0].length; ++j) {
                if (pixels[i][j] != null) {
                    int red = pixels[i][j].getRed();
                    int green = pixels[i][j].getGreen();
                    int blue = pixels[i][j].getBlue();
                    int rgb = (red << 16) | (green << 8) | blue;
                    image.setRGB(j, i, rgb);
                }
            }
        }

        try {
            File outputFile = new File(title + "." + format.getFormat());
            ImageIO.write(image, format.getFormat(), outputFile);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    @SuppressWarnings("MagicNumber")
    private void paint(Pixel[][] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(java.awt.Color.BLACK);
        graphic.fillRect(0, 0, width, height);

        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[0].length; ++j) {
                if (pixels[i][j] != null) {
                    int red = pixels[i][j].getRed();
                    int green = pixels[i][j].getGreen();
                    int blue = pixels[i][j].getBlue();
                    int rgb = (red << 16) | (green << 8) | blue;
                    image.setRGB(j, i, rgb);
                }
            }
        }

        JFrame frame = new JFrame("Fractal Flame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    @SuppressWarnings({"CyclomaticComplexity", "MagicNumber", "LambdaBodyLength", "MethodLength", "NestedForDepth"})
    private void generatePixelsArray(int numCount, int iterCount, int width, int height, int symmetry) {
        AffinFunction[] affinFunctions = functionProvider.getAffinFunctions();
        this.pixels = new Pixel[height][width];

        for (Pixel[] line : pixels) {
            Arrays.fill(line, new Pixel(0, 0, 0, 0));
        }

        Thread[] threads = new Thread[threadCount];
        int numCountForThread = numCount / threadCount;

        for (int k = 0; k < threadCount; ++k) {
            threads[k] = new Thread(() -> {
                for (int i = 0; i < numCountForThread; ++i) {
                    double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
                    double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);

                    for (int j = 0; j < iterCount; ++j) {
                        int affinIndex = ThreadLocalRandom.current().nextInt(affinFunctions.length);

                        double newX = x * affinFunctions[affinIndex].a()
                            + y * affinFunctions[affinIndex].b() + affinFunctions[affinIndex].c();
                        double newY = x * affinFunctions[affinIndex].d()
                            + y * affinFunctions[affinIndex].e() + affinFunctions[affinIndex].f();

                        double[] transform = functionProvider.getNonlinearFunction(newX, newY);
                        newX = transform[0];
                        newY = transform[1];

                        // post transform
                        int secondAffin = ThreadLocalRandom.current().nextInt(affinFunctions.length);
                        x = newX * affinFunctions[secondAffin].a()
                            + newY * affinFunctions[secondAffin].b() + affinFunctions[secondAffin].c();
                        y = newX * affinFunctions[secondAffin].d()
                            + newY * affinFunctions[secondAffin].e() + affinFunctions[secondAffin].f();

                        if (j < 20) {
                            continue;
                        }

                        // Симметрия
                        if (symmetry == 0) {
                            int pointX = (int) (x * width / 2 + width / 2);
                            int pointY = (int) (y * height / 2 + height / 2);

                            if (pointX < width && pointY < height && pointX > 0 && pointY > 0) {
                                    if (pixels[pointY][pointX].getHitCount() == 0) {
                                        synchronized (pixels[pointY][pointX]) {
                                            pixels[pointY][pointX] = new Pixel(affinFunctions[affinIndex].red(),
                                                affinFunctions[affinIndex].green(),
                                                affinFunctions[affinIndex].blue(), 1
                                            );
                                        }
                                    } else {
                                        synchronized (pixels[pointY][pointX]) {
                                            pixels[pointY][pointX].hitCountIncrement();
                                            int red =
                                                (pixels[pointY][pointX].getRed()
                                                    + affinFunctions[affinIndex].red()) / 2;
                                            int green = (pixels[pointY][pointX].getGreen()
                                                + affinFunctions[affinIndex].green()) / 2;
                                            int blue =
                                                (pixels[pointY][pointX].getBlue()
                                                    + affinFunctions[affinIndex].blue()) / 2;
                                            pixels[pointY][pointX].setRed(red);
                                            pixels[pointY][pointX].setGreen(green);
                                            pixels[pointY][pointX].setBlue(blue);
                                        }
                                    }
                            }
                        } else if (symmetry == 1) {
                            int pointX = (int) (x * width / 4 + width / 4);
                            int mirrorX = width - pointX - 1;
                            int pointY = (int) (y * height / 2 + height / 2);
                            if (pointX < width && pointY < height && pointX > 0 && pointY > 0) {
                                if (pixels[pointY][pointX].getHitCount() == 0) {
                                    synchronized (pixels[pointY][pointX]) {
                                        pixels[pointY][pointX] = new Pixel(affinFunctions[affinIndex].red(),
                                            affinFunctions[affinIndex].green(),
                                            affinFunctions[affinIndex].blue(), 1);
                                    }

                                    synchronized (pixels[pointY][mirrorX]) {
                                        pixels[pointY][mirrorX] = new Pixel(affinFunctions[affinIndex].red(),
                                            affinFunctions[affinIndex].green(),
                                            affinFunctions[affinIndex].blue(), 1);
                                    }
                                } else {
                                    synchronized (pixels[pointY][pointX]) {
                                        pixels[pointY][pointX].hitCountIncrement();
                                        int red =
                                            (pixels[pointY][pointX].getRed() + affinFunctions[affinIndex].red()) / 2;
                                        int green =
                                            (pixels[pointY][pointX].getGreen()
                                                + affinFunctions[affinIndex].green()) / 2;
                                        int blue =
                                            (pixels[pointY][pointX].getBlue() + affinFunctions[affinIndex].blue()) / 2;
                                        pixels[pointY][pointX].setRed(red);
                                        pixels[pointY][pointX].setGreen(green);
                                        pixels[pointY][pointX].setBlue(blue);
                                    }

                                    synchronized (pixels[pointY][mirrorX]) {
                                        pixels[pointY][mirrorX].setHitCount(pixels[pointY][pointX].getHitCount());
                                        pixels[pointY][mirrorX].setRed(pixels[pointY][pointX].getRed());
                                        pixels[pointY][mirrorX].setGreen(pixels[pointY][pointX].getGreen());
                                        pixels[pointY][mirrorX].setBlue(pixels[pointY][pointX].getBlue());
                                    }
                                }
                            }
                        } else {
                            // Поворот

                            double[] xArr = new double[symmetry];
                            double[] yArr = new double[symmetry];

                            double angle = (2 * Math.PI) / symmetry;
                            double currentAngle = 0;
                            for (int o = 0; o < symmetry; ++o) {
                                xArr[o] = x * Math.cos(currentAngle) - y * Math.sin(currentAngle);
                                yArr[o] = x * Math.sin(currentAngle) + y * Math.cos(currentAngle);
                                currentAngle += angle;
                            }

                            // Заносим в массив реальные точки
                            for (int o = 0; o < xArr.length; ++o) {
                                int pointX = (int) (xArr[o] * width / 2 + width / 2);
                                int pointY = (int) (yArr[o] * height / 2 + height / 2);

                                if (pointX < width && pointY < height && pointX > 0 && pointY > 0) {
                                    if (pixels[pointY][pointX].getHitCount() == 0) {
                                        synchronized (pixels[pointY][pointX]) {
                                            pixels[pointY][pointX] = new Pixel(affinFunctions[affinIndex].red(),
                                                affinFunctions[affinIndex].green(),
                                                affinFunctions[affinIndex].blue(), 1
                                            );
                                        }
                                    } else {
                                        synchronized (pixels[pointY][pointX]) {
                                            pixels[pointY][pointX].hitCountIncrement();
                                            int red =
                                                (pixels[pointY][pointX].getRed()
                                                    + affinFunctions[affinIndex].red()) / 2;
                                            int green = (pixels[pointY][pointX].getGreen()
                                                + affinFunctions[affinIndex].green()) / 2;
                                            int blue =
                                                (pixels[pointY][pointX].getBlue()
                                                    + affinFunctions[affinIndex].blue()) / 2;
                                            pixels[pointY][pointX].setRed(red);
                                            pixels[pointY][pointX].setGreen(green);
                                            pixels[pointY][pointX].setBlue(blue);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Pixel[][] makeGammaCorrection(Pixel[][] pixels, double gamma) {
        double max = 0;
        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[0].length; ++j) {
                if (pixels[i][j] != null) {
                    if (pixels[i][j].getHitCount() != 0) {
                        pixels[i][j].setNormal(Math.log10(pixels[i][j].getHitCount()));
                        if (pixels[i][j].getNormal() > max) {
                            max = pixels[i][j].getNormal();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[0].length; ++j) {
                if (pixels[i][j] != null) {
                    pixels[i][j].setNormal(pixels[i][j].getNormal() / max);
                    double r = pixels[i][j].getRed() * Math.pow(pixels[i][j].getNormal(), 1.0 / gamma);
                    double g = pixels[i][j].getGreen() * Math.pow(pixels[i][j].getNormal(), 1.0 / gamma);
                    double b = pixels[i][j].getBlue() * Math.pow(pixels[i][j].getNormal(), 1.0 / gamma);
                    pixels[i][j].setRed((int) r);
                    pixels[i][j].setGreen((int) g);
                    pixels[i][j].setBlue((int) b);
                }
            }
        }
        return pixels;
    }
}

