package maldonado.gregory.modeller.molecule;

import maldonado.gregory.modeller.util.bool.Boolean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Definition of a Bond
 */
public class Bond {

    private Atom atom, otherAtom;
    private Type type;

    public Bond(Atom atom, Atom otherAtom) {
        this.atom = atom;
        this.otherAtom = otherAtom;
    }

    public double getElectronegativity() { return Math.abs(atom.getElectronegativity() - otherAtom.getElectronegativity()); }

    public Atom[] getAtoms() {
        return new Atom[]{this.atom, this.otherAtom};
    }

    /**
     * Gets the type of the bonds
     */
    public void type() {
        String atomGroupStr = atom.getGroupBlock();
        String otherAtomGroupStr = otherAtom.getGroupBlock();

        Group atomGroup;
        Group otherAtomGroup;

        switch (atomGroupStr) {
            case "metal":
                atomGroup = Group.METAL;
            case "nonmetal":
                atomGroup = Group.NONMETAL;
            case "noble gas":
                atomGroup = Group.NOBLEGAS;
            case "metalloid":
                atomGroup = Group.METALLOID;
            default:
                atomGroup = Group.UNDEF;
        }

        switch (otherAtomGroupStr) {
            case "metal":
                otherAtomGroup = Group.METAL;
            case "nonmetal":
                otherAtomGroup = Group.NONMETAL;
            case "noble gas":
                otherAtomGroup = Group.NOBLEGAS;
            case "metalloid":
                otherAtomGroup = Group.METALLOID;
            default:
                otherAtomGroup = Group.UNDEF;
        }

        if (atomGroup == Group.METAL && otherAtomGroup == Group.METAL) {
            type = Type.METALLIC;
        }
        else if (atomGroup == Group.NONMETAL && otherAtomGroup == Group.NONMETAL) {
            if ( this.getElectronegativity() == 0 ) type = Type.NONCOVALENT;
            else type = Type.COVALENT;
        }
        else if ( (atomGroup == Group.METAL && otherAtomGroup == Group.NONMETAL) || (atomGroup == Group.NONMETAL && otherAtomGroup == Group.METAL) ) {
            type = Type.IONIC;
        }
        else type = Type.UNDEF;

    }

    public Type getType() {
        return this.type;
    }

    public String toString() { return this.atom.getName() + " : " + this.otherAtom.getName(); }

    /**
     *
     * @param uniqueAtoms A HashMap of all of the atoms in the molecule. This HashMap can give a unique identification number to each atom in the molecule
     * @return A String of the atom and the otherAtom that are bonded together and contains the unique identification number (if size is greater than 1)
     */
    public String toString(HashMap<String, ArrayList<Atom>> uniqueAtoms) {

                Integer atomID = null;
                Integer otherAtomID = null;

                StringBuilder atomStr = new StringBuilder();
                StringBuilder otherAtomStr = new StringBuilder();

                if (uniqueAtoms.get(atom.getName()).size() != 1 ) {
                    for (Atom atom : uniqueAtoms.get(atom.getName())) {
                        if (this.atom.hashCode() == atom.hashCode()) {
                            atomID = uniqueAtoms.get(atom.getName()).indexOf(atom);
                        }
                    }
                }
                if (uniqueAtoms.get(otherAtom.getName()).size() != 1) {
                    for (Atom atom : uniqueAtoms.get(otherAtom.getName())) {
                        if (this.otherAtom.hashCode() == atom.hashCode()) {
                            otherAtomID = uniqueAtoms.get(atom.getName()).indexOf(atom);
                        }
                    }
        }

        if (atomID == null) {
            atomStr.append(this.atom.getName());
        } else {
            atomStr.append(this.atom.getName()).append("(").append(atomID).append(")");
        }
        if (otherAtomID == null) {
            otherAtomStr.append(this.otherAtom.getName());
        } else {
            otherAtomStr.append(this.otherAtom.getName()).append("(").append(otherAtomID).append(")");
        }

        return atomStr.toString() + " : " + otherAtomStr.toString();

    }
}

enum Type {
    IONIC,
    NONCOVALENT,
    COVALENT,
    METALLIC,
    UNDEF
}

enum Group {
    METAL,
    NONMETAL,
    METALLOID,
    NOBLEGAS,
    UNDEF
}