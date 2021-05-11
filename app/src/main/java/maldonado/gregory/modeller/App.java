package maldonado.gregory.modeller;

import maldonado.gregory.modeller.builder.Builder;
import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.molecule.Bond;
import maldonado.gregory.modeller.molecule.Molecule;
import maldonado.gregory.modeller.util.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Atom atom = new Atom(Parser.getAtomData("Cl"));
        Molecule molecule = new Molecule(true);
        System.out.println(atom.getValanceElectrons());
        System.out.println(molecule.getMolecularWeight());
    }
}
