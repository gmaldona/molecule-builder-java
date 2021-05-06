package maldonado.gregory.modeller;

import maldonado.gregory.modeller.molecule.Bond;
import maldonado.gregory.modeller.molecule.Molecule;
import maldonado.gregory.modeller.util.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, HashMap<String, String>> json = Parser.getAllAtomData();
        //HashMap<String, Integer> molecule = Parser.getMoleculeData("CH4");

//        ArrayList<Atom> atoms = Builder.buildData(molecule, json);
//        System.out.println(atoms.get(0).toString());
//        System.out.println();
//        System.out.println(atoms.get(1).toString());
//        System.out.println();
//
//        String[] moleculeBuildAtomNames = Parser.getMoleculeBuildAtoms();
//        for (String name : moleculeBuildAtomNames) {
//            System.out.print(name + " ");
//        }
//        System.out.println();
//        int[][] moleculeBuildMatrix = Parser.getMoleculeBuildMatrix();
//        for (int i = 0; i < moleculeBuildMatrix.length; i++) {
//            for (int j = 0; j < moleculeBuildMatrix[i].length; j++) {
//                System.out.print(moleculeBuildMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }

//        String[] names = new String[]{"C", "H", "H", "H", "H"};
//        HashMap<String, Integer> molecule = Parser.getmoleculeData(names);
//        System.out.println(Parser.getAtomData("C").toString());

        Molecule molecule = new Molecule();
        molecule.setBonds(Parser.getMoleculeBuildAtoms(), Parser.getMoleculeBuildMatrix());
        ArrayList<Bond> bonds = molecule.getBonds();
        System.out.println(bonds.size());
        System.out.println();
        for (Bond bond : bonds) {
            System.out.println(bond.toString());
        }

    }
}
