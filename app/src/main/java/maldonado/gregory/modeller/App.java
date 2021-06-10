package maldonado.gregory.modeller;

import maldonado.gregory.modeller.GUI.P3.Environment;
import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.molecule.Molecule;
import maldonado.gregory.modeller.util.parser.Parser;
import processing.core.PApplet;

public class App {

    public static Environment environment;

    public static void main(String[] args) {
        //Atom atom = new Atom(Parser.getAtomData("Cl"));
        Molecule molecule = Parser.buildSavedMolecule();
        //System.out.println(atom.getValanceElectrons());
        System.out.println(molecule.getMolecularWeight());
        System.out.println(molecule.toString());

        environment = new Environment();
        //Calls the processing 3D files
        //PApplet.main(new String[]{"maldonado.gregory.modeller.GUI.P3.Environment"});

    }
}
