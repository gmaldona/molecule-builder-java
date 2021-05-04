package maldonado.gregory.modeller.parser;
import maldonado.gregory.modeller.molecule.Molecule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.Character;

public class Parser {

    public static HashMap<String, Integer> getMoleculeData(String equation) {
        return EquationParser.parse(equation);
    }

    public static HashMap<String, HashMap<String, String>> getAtomData() {
        return JSONParser.parse();
    }

    public static int[][] getMoleculeBuildMatrix() { return MoleculeParser.getAdjacencyMatrix(); }

    public static String[] getMoleculeBuildAtoms() { return MoleculeParser.getAtomNames(); }

}
