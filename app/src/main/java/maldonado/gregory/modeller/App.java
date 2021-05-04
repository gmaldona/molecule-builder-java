package maldonado.gregory.modeller;

import maldonado.gregory.modeller.builder.Builder;
import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.parser.MoleculeParser;
import maldonado.gregory.modeller.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, HashMap<String, String>> json = Parser.getAtomData();
        HashMap<String, Integer> molecule = Parser.getMoleculeData("S4");

        ArrayList<Atom> atoms = Builder.Build(molecule, json);

        String[] moleculeBuildAtomNames = Parser.getMoleculeBuildAtoms();
        for (String name : moleculeBuildAtomNames) {
            System.out.print(name + " ");
        }
        System.out.println();
        int[][] moleculeBuildMatrix = Parser.getMoleculeBuildMatrix();
        for (int i = 0; i < moleculeBuildMatrix.length; i++) {
            for (int j = 0; j < moleculeBuildMatrix[i].length; j++) {
                System.out.print(moleculeBuildMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
