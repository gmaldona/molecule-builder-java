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
    private HashMap<String, ArrayList<Atom>> uniqueAtoms;

    /** Manually defining a Molecule */
    public Molecule(Atom[] atoms) {
        this.atoms = new ArrayList<>(Arrays.asList(atoms));
        getUniqueAtoms();
    }

    public Molecule(ArrayList<Atom> atoms) {
        this.atoms = atoms;
        getUniqueAtoms();
    }

    /** Building a molecule with the atom names and the bonds to each atom */
    public Molecule(String[] atomNames, int[][] adjacencyMatrix) {
        this.setBonds(atomNames, adjacencyMatrix);
        getUniqueAtoms();
    }

    public ArrayList<Atom> getAtoms() { return this.atoms; }

    public ArrayList<Bond> getBonds() { return this.bonds; }

    private void getUniqueAtoms() {
        uniqueAtoms = new HashMap<>();
        for (Atom atom : atoms) {
            if (uniqueAtoms.containsKey(atom.getName())) {
                uniqueAtoms.get(atom.getName()).add(atom);
            }
            else {
                uniqueAtoms.put(atom.getName(), new ArrayList<>());
                uniqueAtoms.get(atom.getName()).add(atom);
            }
        }
    }
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

    /**
     *
     * @return String representation of the molecular
     */
    public String toString() {
        StringBuilder moleculeStr = new StringBuilder();
        for (int i = 0; i < atoms.size(); i++) {
            moleculeStr.append(atoms.get(i).getName()).append("(").append(i).append(")");
            if (i < atoms.size() - 1) moleculeStr.append(", ");
        }
        moleculeStr.append("\n");
        moleculeStr.append("Molecular Weight: ").append(this.getMolecularWeight());
        return moleculeStr.toString();
    }

    public String bondsToString() {
        StringBuilder bondsStr = new StringBuilder();
        for (Bond bond : bonds) {
            bondsStr.append(bond.toString(uniqueAtoms)).append("\n");
        }
        return bondsStr.toString();
    }


}
