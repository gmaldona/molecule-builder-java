package maldonado.gregory.modeller.molecule;

import maldonado.gregory.modeller.util.bool.Boolean;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Definition of a Bond
 */
public class Bond {

    private Atom atom, otherAtom;

    public Bond(Atom atom, Atom otherAtom) {
        this.atom = atom;
        this.otherAtom = otherAtom;
    }

    public double getElectronegativity() { return Math.abs(atom.getElectronegativity() - otherAtom.getElectronegativity()); }

    public Atom[] getAtoms() {
        return new Atom[]{this.atom, this.otherAtom};
    }

    /**
     * Compares to see if two bonds are equal just with this.atom and this.otherAtom swapped
     * @param otherBond Other instance of a Bond
     * @return 1 if this and otherBond are equal or 0 if this and otherBond are not equal
     */
    public int compareTo(Bond otherBond) {
        return new Boolean(this.atom.hashCode() == otherBond.atom.hashCode() || this.otherAtom.hashCode() == otherBond.otherAtom.hashCode()).parseToInt();
    }

    public String toString() { return this.atom.getName() + " : " + this.otherAtom.getName(); }
}
