package maldonado.gregory.modeller.builder;

import maldonado.gregory.modeller.molecule.Atom;
import maldonado.gregory.modeller.util.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Gregory Maldonado
 * @since 2021-05-6
 *
 * Contains builder method for molecules
 */
public class Builder {
    /**
     * @param moleculeData A HashMap that contains Atom symbols as keys and how many times that atom appears in the molecular equation for the value
     * @param atomData A HashMap that contains the JSON data for each atom that exists
     * @return ArrayList of instances of Atom
     */
    public static ArrayList<Atom> buildData(HashMap<String, Integer> moleculeData, HashMap<String, HashMap<String, String>> atomData) {

        ArrayList<Atom> atoms = new ArrayList<>();

        for (String atomName : moleculeData.keySet()) {
            for (int i = 0; i < moleculeData.get(atomName); i++) {
                atoms.add(new Atom (atomData.get(atomName)));
            }
        }

        return atoms;
    }

//    // TODO: BuildFromAdjacencyMatrix
//    public static void BuildFromAdjacencyMatrix(String[] names, int[][] matrix) {
//
//    }

    /** Save to the file name (creates a new file with that name) */
    public static void save(String filename, String[] atoms, int[][] adjacencyMatrix) {
        final String DIR = "data/saves/";
        String saveStr = Parser.saveMolecule(atoms, adjacencyMatrix);
        try {
            File saveFile = new File(DIR + filename + ".model");
            if (saveFile.createNewFile()) {
                FileWriter filewrite = new FileWriter(DIR + filename + ".model");
                filewrite.write(saveStr);
                filewrite.close();
                System.out.println("Molecule Saved.");
            } else {
                System.out.println("Save already exists.");
            }
        } catch (Exception e) {System.out.println(e);}

    }

    /** Saves to the Molecule.model file */
    public static void save(String[] atoms, int[][] adjacencyMatrix) {
        String saveStr = Parser.saveMolecule(atoms, adjacencyMatrix);
        try {
            FileWriter saveFile = new FileWriter("data/Molecule.model");
            saveFile.write(saveStr);
            saveFile.close();
        } catch(Exception e) {System.out.println(e);}
    }

    public static void save(String saveStr) {
        try {
            FileWriter saveFile = new FileWriter("data/Molecule.model");
            saveFile.write(saveStr);
            saveFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads a molecule from the saves */
    public static void load(String filename) {
        final String DIR = "data/saves/" + filename +".model";
        try {
            StringBuilder saveFile = new StringBuilder();
            Scanner scanner = new Scanner(new File(DIR));
            while (scanner.hasNextLine()) {
                saveFile.append(scanner.nextLine()).append("\n");
            }
            System.out.println(saveFile);
            save(saveFile.toString());
        } catch (Exception e) {System.out.println(e);}
    }
}
