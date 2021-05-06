package maldonado.gregory.modeller.builder;

import maldonado.gregory.modeller.molecule.Atom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Gregory Maldonado
 * @since 2021-05-6
 *
 * Contains builder method for molecules
 */
public class Builder {
    /**
     * @param moleculeData A HashMap that contains Atom symbols as keys and how many times that atom appears in the molecular equation for the value
     * @param atomData A HashMap that contains the JSON data for each atom that exists
     * @return ArrayList of instances of Atom
     */
    public static ArrayList<Atom> buildData(HashMap<String, Integer> moleculeData, HashMap<String, HashMap<String, String>> atomData) {

        ArrayList<Atom> atoms = new ArrayList<>();

        for (String atomName : moleculeData.keySet()) {
            for (int i = 0; i < moleculeData.get(atomName); i++) {
                atoms.add(new Atom (atomData.get(atomName)));
            }
        }

        return atoms;
    }

    // TODO: BuildFromAdjacencyMatrix
    public static void BuildFromAdjacencyMatrix(String[] names, int[][] matrix) {

    }
}
