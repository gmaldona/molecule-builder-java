package maldonado.gregory.modeller.molecule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Definition of an Atom
 */
public class Atom {

    private double atomicMass;
    private int atomicNumber;
    private int atomicRadius;
    private String color;
    private double density;
    private double electronegativity;
    private String electronicConfiguration;
    private String groupBlock;
    private int ionizationEnergy;
    private String name;
    private int[] oxidationStates;
    private String symbol;

    /**
     *
     * @param data A HashMap that contains JSON data for an Atom
     */
    public Atom(HashMap<String, String> data) {

        this.atomicNumber            = Integer.parseInt(data.get("atomicNumber"));
        this.atomicRadius            = Integer.parseInt(data.get("atomicRadius"));
        this.color                   = "#" + data.get("cpkHexColor");
        this.density                 = Double.parseDouble(data.get("density"));
        this.electronegativity       = Double.parseDouble(data.get("electronegativity"));
        this.electronicConfiguration = data.get("electronicConfiguration");
        this.groupBlock              = data.get("groupBlock");
        this.ionizationEnergy        = Integer.parseInt(data.get("ionizationEnergy"));
        this.name                    = data.get("name");
        this.symbol                  = data.get("symbol");

        try {
            this.atomicMass = Double.parseDouble(data.get("atomicMass"));
        } catch (Exception e) {
            int index = data.get("atomicMass").indexOf('(');
            this.atomicMass = Double.parseDouble(data.get("atomicMass").substring(0, index));
        }

        ArrayList<Integer> states = new ArrayList<>();
        String[] oxidationStatesString = data.get("oxidationStates").split(", ");
        for (String state : oxidationStatesString) {
            states.add(Integer.parseInt(state));
        }

        this.oxidationStates = new int[states.size()];

        for (int i = 0; i < this.oxidationStates.length; i++) {
            this.oxidationStates[i] = states.get(i);
        }

    }

    public double getAtomicMass() { return atomicMass; }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public int getAtomicRadius() {
        return atomicRadius;
    }

    public String getColor() {
        return color;
    }

    public double getDensity() {
        return density;
    }

    public double getElectronegativity() {
        return electronegativity;
    }

    public String getElectronicConfiguration() {
        return electronicConfiguration;
    }

    public String getGroupBlock() {
        return groupBlock;
    }

    public int getIonizationEnergy() {
        return ionizationEnergy;
    }

    public String getName() {
        return name;
    }

    public int[] getOxidationStates() {
        return oxidationStates;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toString() {
        return "Name                   : " + this.name + "\n" +
                "Symbol                 : " + this.symbol + "\n" +
                "Atomic Number          : " + this.atomicNumber + "\n" +
                "Atomic Mass            : " + this.atomicMass + "\n" +
                "Atomic Radius          : " + this.atomicRadius + "\n" +
                "Density                : " + this.density + "\n" +
                "Electronegativity      : " + this.electronegativity + "\n" +
                "Electron Configuration : " + this.electronicConfiguration + "\n" +
                "Group Block            : " + this.groupBlock + "\n" +
                "Ionization Energy      : " + this.ionizationEnergy + "\n" +
                "Oxidation States       : " + Arrays.toString(this.oxidationStates);
    }

    public int getValanceElectrons() {
        String[] orbitals = this.electronicConfiguration.split(" ");
        int outterShell = Integer.parseInt(orbitals[orbitals.length - 1].substring(0, 1));
        int valanceElectrons = 0;
        for (String orbital : orbitals) {
            if (orbital.substring(0, 1).equals(String.valueOf(outterShell))) {
                valanceElectrons = valanceElectrons + Integer.parseInt(orbital.substring(2));
            }
        }
        return valanceElectrons;
    }

}
