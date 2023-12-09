package edu.project4;

public interface FunctionProvider {
    AffinFunction[] getAffinFunctions();

    double[] getNonlinearFunction(double x, double y);
}
