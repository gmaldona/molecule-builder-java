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

    public String toString() { return this.atom.getName() + " : " + this.otherAtom.getName(); }
}
