package edu.project4;

public interface Renderer {
    void render(int numCount, int iterCount, int width, int height, int symmetry, double gammaCorrect);

    @SuppressWarnings("ParameterNumber")
    void render(
        int numCount, int iterCount, int width, int height, int symmetry,
        double gammaCorrect, ImageFormat format, String title
    );
}
