package maldonado.gregory.modeller.molecule;

import maldonado.gregory.modeller.util.parser.Parser;

import java.util.*;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Definition of an Molecule
 * Molecule is a graph of Atoms as vertices and Bonds as edges
 */
public class Molecule {

    private ArrayList<Atom> atoms;
    private ArrayList<Bond> bonds;

    /** Manually defining a Molecule */
    public Molecule(Atom[] atoms) { this.atoms = new ArrayList<>(Arrays.asList(atoms)); }

    public Molecule(ArrayList<Atom> atoms) { this.atoms = atoms; }
    /** Defining a Molecule using the .model file */
    public Molecule(boolean fromSave) {
        if (fromSave) {
            String[] atomNames = Parser.getMoleculeBuildAtoms();
            int[][] adjacencyMatrix = Parser.getMoleculeBuildMatrix();
            this.setBonds(atomNames, adjacencyMatrix);
        }
    }

    public ArrayList<Atom> getAtoms() { return this.atoms; }

    public ArrayList<Bond> getBonds() { return this.bonds; }

    /**
     * This method creates the bonds between each atom based on the adjacency matrix provided
     * @param atomNames A String array of Atom.Name
     * @param adjacencyMatrix A 2D Array that contains an adjacency matrix for the graph
     */
    public void setBonds(String[] atomNames, int[][] adjacencyMatrix) {
        bonds = new ArrayList<>();
        atoms = new ArrayList<>();
        ArrayList<String> bondIndices = new ArrayList<>();

        for (String atomName : atomNames) { atoms.add(new Atom(Parser.getAtomData(atomName))); }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            Atom atom = atoms.get(i);
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (i != j) {
                    if (adjacencyMatrix[i][j] == 1) {
                        Atom otherAtom = atoms.get(j);
                        String flippedIndices = String.valueOf(j) + String.valueOf(i);
                        if (!bondIndices.contains(flippedIndices)) {
                            bondIndices.add(String.valueOf(i) + String.valueOf(j));
                            bonds.add(new Bond(atom, otherAtom));
                        }
                    }
                }
            }
        }
    }

    /**
     * @return The molecular weight of the molecule
     */
    public Double getMolecularWeight() {
        return atoms.stream()
                .map(Atom::getAtomicMass)
                .reduce(Double::sum).get();

    }

}
