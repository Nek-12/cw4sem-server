package com.fantastictrio.cw4sem;

import com.fantastictrio.cw4sem.exception.IllegalDecisionException;
import com.fantastictrio.cw4sem.model.Decision;
import com.fantastictrio.cw4sem.model.DecisionRecord;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class DecisionMaker {

    public static DecisionRecord make(Decision decision, List<List<Double>> matrix) {

        var sizes = matrix.stream().mapToInt(List::size).toArray(); // get data source

        if (sizes.length == 0 || //non-empty
                Arrays.stream(sizes).anyMatch((int size) -> size == 0) || // all columns non-empty
                Arrays.stream(sizes).distinct().count() > 1 || //all columns have the same size
                decision.getNatureStatesCount() != matrix.size() || //column numbers match
                sizes[0] != decision.getStrategyList().size() || //rows match
                matrix.stream().anyMatch((List<Double> column) -> column.stream().anyMatch(Objects::isNull))) //no nulls
            throw new IllegalDecisionException("matrix is empty or invalid");

        return new DecisionRecord(waldCriterionMaker(matrix),
                savageCriterionMaker(matrix), gutvitzCriterionMaker(matrix, decision.getPessimismCoefficient()),
                decision, 0, Instant.now());
    }

    private static int waldCriterionMaker(List<List<Double>> matrix) {
        List<Double> maxColumnValue = new ArrayList<>();
        List<List<Double>> transposedMatrix = transpose(matrix);
        for (List<Double> column : transposedMatrix) {
            maxColumnValue.add(Collections.max(column));
        }
        return maxColumnValue.indexOf(Collections.min(maxColumnValue));
    }

    private static int savageCriterionMaker(List<List<Double>> matrix) {
        return waldCriterionMaker(getRiskMatrix(matrix));
    }

    private static List<List<Double>> getRiskMatrix(List<List<Double>> matrix) {
        double min = findMinMatrixValue(matrix);
        return matrix.parallelStream().map(
                (List<Double> column) -> column.stream().map(
                        (Double d) -> d - min).collect(Collectors.toList())).collect(Collectors.toList());
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

    private static double findMinMatrixValue(List<List<Double>> matrix) {
        return matrix.parallelStream().mapToDouble((List<Double> column) -> column.parallelStream().min(Double::compare).orElseThrow()).min().orElseThrow();
    }
}




