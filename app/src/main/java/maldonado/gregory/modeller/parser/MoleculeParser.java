package maldonado.gregory.modeller.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MoleculeParser {

    protected static String[] getAtomNames() {
        String[] atomsNames = new String[]{};
        try {
            File model = new File("data/Molecule.model");
            Scanner scanner = new Scanner(model);
            String[] indexArr = scanner.nextLine().split(" ");
            atomsNames = new String[indexArr.length - 1];
            int counter = 0;
            for (int i = 1; i < indexArr.length; i++) {
                if (Character.isLetter(indexArr[i].charAt(0))) {
                    atomsNames[counter] = indexArr[i];
                    counter ++;
                }
            }
        } catch (Exception e) {}
        return atomsNames;
    }

    protected static int[][] getAdjacencyMatrix() {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        int count = 0;
        String index = "";
        try {
            File model = new File("data/Molecule.model");
            Scanner scanner = new Scanner(model);
            while(scanner.hasNextLine()) {

                index = scanner.nextLine();
                count = index.split(" ").length - 1;
                if (Character.isLetter(index.charAt(0))) {
                    String[] matrixString = index.substring(2).split(" ");
                    adjacencyMatrix.add(new ArrayList<>());
                    for (String num : matrixString) {

                        if (Character.isDigit(num.charAt(0))) {
                            int binaryNumber = Integer.parseInt(num);
                            adjacencyMatrix.get(adjacencyMatrix.size() - 1).add(binaryNumber);
                        }
                    }
                }

            }
        } catch (Exception e) {System.out.println(e.toString());}
        int[][] matrix = new int[adjacencyMatrix.size()][adjacencyMatrix.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = adjacencyMatrix.get(i).get(j);
            }
        }
        return matrix;
    }

}
