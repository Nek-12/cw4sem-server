package com.fantastictrio.cw4sem;

import com.fantastictrio.cw4sem.model.Decision;
import com.fantastictrio.cw4sem.model.DecisionRecord;

import java.time.Instant;
import java.util.*;

public class DecisionMaker {
    /**
     * matrix has rows (outer list) and columns (inner list), it's a matrix of variables needed for making the decision
     * excluding strategy list names and nature states numbers. E.g.
     * nature state-->   1  2  3
     * --------
     * bankrupcy       | 15 22 70 |
     * run away        | 44 11 03 |
     * refuse proposal | 87 22 41 | <-- matrix
     * --------
     **/

    public static DecisionRecord make(Decision decision, List<List<Double>> matrix) {
        return new DecisionRecord(waldCriterionMaker(matrix),
                savageCriterionMaker(matrix), gutvitzCriterionMaker(matrix,decision.getPessimismCoefficient()),
                decision, 0, Instant.now());
    }

    /*public static void main(String... args) {
        List<List<Double>> list = Arrays.asList(Arrays.asList(11.0, 5.0, 13.0), Arrays.asList(1.0, 2.0, 3.0),
                Arrays.asList(9.0, 2.0, 5.0));
    }*/

    private static int waldCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxColumnValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> column : transposedMatrix) {
            maxColumnValue.add(Collections.max(column));
        }
        return maxColumnValue.indexOf(Collections.min(maxColumnValue));
    }

    private static int savageCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxRowValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        double minValue = findMinMatrixValue(matrix);
        for (List<Double> row : transposedMatrix) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i, row.get(i) - minValue);
            }
            maxRowValue.add(Collections.max(row));
        }
        return maxRowValue.indexOf(Collections.min(maxRowValue));
    }

    private static int gutvitzCriterionMaker(List<List<Double>> matrix, double coefficient) {
        List<Double> coefficientValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> list : transposedMatrix) {
            coefficientValue.add(coefficient * Collections.min(list) +
                    (1 - coefficient) * Collections.max(list));
        }
        return coefficientValue.indexOf(Collections.min(coefficientValue));
    }

    /* services */

    private static List<List<Double>> transpose(List<List<Double>> matrix) {
        List<List<Double>> transposedMatrix = new ArrayList<List<Double>>();
        int noElementsInMatrix = matrix.get(0).size();
        for (int i = 0; i < noElementsInMatrix; i++) {
            List<Double> col = new ArrayList<Double>();
            for (List<Double> row : matrix) {
                col.add(row.get(i));
            }
            transposedMatrix.add(col);
        }
        return transposedMatrix;
    }

    private static double findMinMatrixValue(List<List<Double>> matrix) {
        List<Double> minValues = new ArrayList<>();
        for (List<Double> list : matrix) {
            minValues.add(Collections.min(list));
        }
        return Collections.min(minValues);
    }
}




