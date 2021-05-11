package maldonado.gregory.modeller.util.parser;

import java.util.HashMap;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Static parsing methods for parsing data
 */

public class Parser {

    public static HashMap<String, Integer> getMoleculeData(String equation) {
        return EquationParser.parse(equation);
    }

    public static HashMap<String, Integer> getMoleculeData(String[] equation) { return EquationParser.parse(equation); }

    public static HashMap<String, HashMap<String, String>> getAllAtomData() {
        return JSONParser.parse();
    }

    public static HashMap<String, String> getAtomData(String symbol) { return MoleculeParser.getAtomData(symbol); }

    public static int[][] getMoleculeBuildMatrix() { return MoleculeParser.getAdjacencyMatrix(); }

    public static String[] getMoleculeBuildAtoms() { return MoleculeParser.getAtomNames(); }


}
