package maldonado.gregory.modeller.util.parser;

import maldonado.gregory.modeller.molecule.Molecule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Parsing methods for parsing data to create Molecule classes
 */
public class MoleculeParser {

    /**
     * Opens the Molecule.model file and parses the file into usable data
     * @return String array of Atom names
     */
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

    /**
     *
     * @param symbol An Atomic Symbol
     * @return Data for the Atom with the Atomic Symbol
     */
    protected static HashMap<String, String> getAtomData(String symbol) {
        return Parser.getAllAtomData().get(symbol);
    }

    /**
     * Opens the Molecule.model file and parses the data into an adjacency matrix
     * @return 2D int array that contains an adjacency matrix for a Graph of the molecule
     */
    protected static int[][] getAdjacencyMatrix() {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        int count = 0;
        String index;
        try {
            File model = new File("data/Molecule.model");
            Scanner scanner = new Scanner(model);
            while(scanner.hasNextLine()) {

                index = scanner.nextLine();
                // parses the data based on spaces in the file
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

    /** Defining a Molecule using the .model file */
    protected static Molecule getSavedMolecule() {
        String[] atomNames = Parser.getMoleculeBuildAtoms();
        int[][] adjacencyMatrix = Parser.getMoleculeBuildMatrix();
        return new Molecule(atomNames, adjacencyMatrix);
    }

}
