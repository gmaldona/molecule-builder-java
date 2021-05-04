package maldonado.gregory.modeller.builder;

import maldonado.gregory.modeller.molecule.Atom;

import java.util.ArrayList;
import java.util.HashMap;

public class Builder {

    public static ArrayList<Atom> Build(HashMap<String, Integer> moleculeData, HashMap<String, HashMap<String, String>> atomData) {

        ArrayList<Atom> atoms = new ArrayList<>();

        for (String atomName : moleculeData.keySet()) {
            for (int i = 0; i < moleculeData.get(atomName); i++) {
                Atom atom = new Atom(atomData.get(atomName));
                atoms.add(atom);
            }
        }

        return atoms;
    }
}
