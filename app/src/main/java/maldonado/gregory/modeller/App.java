package maldonado.gregory.modeller;

import maldonado.gregory.modeller.builder.Builder;
import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        HashMap<String, HashMap<String, String>> json = Parser.getAtomData();
        HashMap<String, Integer> molecule = Parser.getMoleculeData("CH4");

        ArrayList<Atom> atoms = Builder.Build(molecule, json);
        System.out.println(atoms.get(0).toString());
    }
}
