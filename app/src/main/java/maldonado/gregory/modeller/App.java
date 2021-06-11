package maldonado.gregory.modeller;

import maldonado.gregory.modeller.GUI.P3.Environment;
import maldonado.gregory.modeller.builder.Builder;
import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.molecule.Molecule;
import maldonado.gregory.modeller.util.parser.Parser;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static Environment environment;

    public static void main(String[] args) throws Exception {

        Builder.load("CH4");

        Molecule molecule = Parser.buildSavedMolecule();
        //System.out.println(atom.getValanceElectrons());
        System.out.println(molecule.bondsToString());
        System.out.println(molecule.toString());

        environment = new Environment();
        //Calls the processing 3D files
        //PApplet.main(new String[]{"maldonado.gregory.modeller.GUI.P3.Environment"});

    }
}
