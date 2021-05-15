package com.fantastictrio.cw4sem;

import com.fantastictrio.cw4sem.model.Decision;
import com.fantastictrio.cw4sem.model.DecisionRecord;

import java.time.Instant;
import java.util.*;

public class DecisionMaker {

    public static DecisionRecord make(Decision decision, List<List<Double>> matrix) {
        List<List<Double>> transposedMatrix = transpose(matrix);
        return new DecisionRecord(waldCriterionMaker(transposedMatrix),
                savageCriterionMaker(transposedMatrix), gutvitzCriterionMaker(transposedMatrix,decision.getPessimismCoefficient()),
                decision, 0, Instant.now());
    }

    private static int waldCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxColumnValue = new ArrayList<>();
        for (List<Double> column : matrix) {
            maxColumnValue.add(Collections.max(column));
        }
        return maxColumnValue.indexOf(Collections.min(maxColumnValue));
    }

    private static int savageCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxRowValue = new ArrayList<>();
        double minValue = findMinMatrixValue(matrix);
        for (List<Double> row : matrix) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i, row.get(i) - minValue);
            }
            maxRowValue.add(Collections.max(row));
        }
        return maxRowValue.indexOf(Collections.min(maxRowValue));
    }

    private static int gutvitzCriterionMaker(List<List<Double>> matrix, double coefficient) {
        List<Double> coefficientValue = new ArrayList<>();
        for (List<Double> list : matrix) {
            coefficientValue.add(coefficient * Collections.min(list) +
                    (1 - coefficient) * Collections.max(list));
        }
        return coefficientValue.indexOf(Collections.min(coefficientValue));
    }



    /* services */

    private static List<List<Double>> transpose(List<List<Double>> matrix) {
        List<List<Double>> transposedMatrix = new ArrayList<>();
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




