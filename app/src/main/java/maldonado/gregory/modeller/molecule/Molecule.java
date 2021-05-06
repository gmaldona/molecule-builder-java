package maldonado.gregory.modeller.molecule;

import maldonado.gregory.modeller.util.parser.Parser;
import maldonado.gregory.modeller.util.bool.Boolean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Definition of an Molecule
 * Molecule is a graph of Atoms as vertices and Bonds as edges
 */
public class Molecule {

    private Atom[] atoms;
    private ArrayList<Bond> bonds;

    /** Manually defining a Molecule */
    public Molecule(Atom[] atoms) {
        this.atoms = atoms;
    }
    /** Defining a Molecule using the .model file */
    public Molecule() { }

    public Atom[] getAtoms() { return this.atoms; }

    public ArrayList<Bond> getBonds() { return this.bonds; }

    /**
     * Defines the molecule using the .model file
     * @param atomNames A String array of Atom.Name
     * @param adjacencyMatrix A 2D Array that contains an adjacency matrix for the graph
     */
    //TODO: Instead of making a new Atom every iteration, init a whole array of Atoms and then iterate over each
    //      index and check if there is a bond between those two Atoms (i and j indices)
    public void setBonds(String[] atomNames, int[][] adjacencyMatrix) {
        bonds = new ArrayList<>();
        atoms = new Atom[atomNames.length];

        HashMap<String, Integer> molecule = Parser.getmoleculeData(atomNames);

       for (int i = 0; i < adjacencyMatrix.length; i++) {
           String atomName = atomNames[i];

           Atom atom = new Atom(Parser.getAtomData(atomName));
           atoms[i] = atom;

           int[] atomBonds = adjacencyMatrix[i];

           for (int bondIndex = 0; bondIndex < atomBonds.length; bondIndex ++) {
               if (bondIndex == 1) {
                   Atom otherAtom = new Atom(Parser.getAtomData(atomNames[bondIndex]));
                   Bond newBond = new Bond(atom, otherAtom);
                   boolean isNew = true;
                   for (Bond bond : bonds) {
                       isNew = new Boolean(bond.compareTo(newBond)).flip();
                       break;
                   }
                   if (isNew) bonds.add(newBond);
               }
           }

       }

    }

}
