package com.fantastictrio.cw4sem;

import com.fantastictrio.cw4sem.exception.IllegalDecisionException;
import com.fantastictrio.cw4sem.model.Decision;
import com.fantastictrio.cw4sem.model.DecisionRecord;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DecisionMaker {

    public static DecisionRecord make(Decision decision, List<List<Double>> matrix) {

        int[] sizes = matrix.stream().mapToInt(List::size).toArray(); // get data source
        if (sizes.length == 0 || //non-empty
                Arrays.stream(sizes).anyMatch((int size) -> size == 0) || // all columns non-empty
                Arrays.stream(sizes).distinct().count() > 1 || //all columns have the same size
                decision.getNatureStatesCount() != matrix.size() || //column numbers match
                sizes[0] != decision.getStrategyList().size() || //rows match
                matrix.stream().anyMatch((List<Double> column) -> column.stream().anyMatch((Double value) ->
                        value == null || value < 0))) //no nulls and negatives
            throw new IllegalDecisionException("matrix is empty or invalid");

        return new DecisionRecord(waldCriterionMaker(matrix),
                savageCriterionMaker(matrix), gutvitzCriterionMaker(matrix, decision.getPessimismCoefficient()),
                decision, 0, Instant.now());
    }

    private static int waldCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxColumnValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> column : transposedMatrix) {
            maxColumnValue.add(Collections.min(column));
        }
        return maxColumnValue.indexOf(Collections.max(maxColumnValue));
    }

    private static int savageCriterionMaker(List<List<Double>> matrix) {
        return savageCount(getRiskMatrix(matrix));
    }

    private static List<List<Double>> getRiskMatrix(List<List<Double>> matrix) {
        return matrix.parallelStream().map(
                (List<Double> column) -> column.stream().map(
                        (Double d) -> Collections.max(column) - d).collect(Collectors.toList())).collect(Collectors.toList());
    }

    private static int savageCount(List<List<Double>> matrix) {
        List<Double> maxColumnValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> column : transposedMatrix) {
            maxColumnValue.add(Collections.max(column));
        }
        return maxColumnValue.indexOf(Collections.min(maxColumnValue));
    }

    private static List<List<Double>> transpose(List<List<Double>> matrix) {
        List<List<Double>> transposedMatrix = new ArrayList<>();
        int noElementsInMatrix = matrix.get(0).size();
        for (int i = 0; i < noElementsInMatrix; i++) {
            List<Double> col = new ArrayList<>();
            for (List<Double> row : matrix) {
                col.add(row.get(i));
            }
            transposedMatrix.add(col);
        }
        return transposedMatrix;
    }

    private static int gutvitzCriterionMaker(List<List<Double>> matrix, double coefficient) {
        List<Double> coefficientValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> list : transposedMatrix) {
            coefficientValue.add(coefficient * Collections.min(list) +
                    (1 - coefficient) * Collections.max(list));
        }
        return coefficientValue.indexOf(Collections.max(coefficientValue));
    }
}




