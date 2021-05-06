package maldonado.gregory.modeller.util.parser;

import java.util.HashMap;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Parsing methods for parsing Strings into molecular equations
 */

public class EquationParser {

    /**
     * Iterates over each Atomic Symbol and stores the count
     * @param equation A String Array of Atomic Symbols
     * @return A HashMap that contains the parsed Atomic Symbol with values of the mole count
     */
    protected static HashMap<String, Integer> parse(String[] equation) {

        HashMap<String, Integer> elements = new HashMap<>();

        for (String name : equation) {
            if (elements.containsKey(name)) {
                elements.put(name, elements.get(name) + 1);
                continue;
            }
            elements.put(name, 1);
        }
        return elements;
    }

    /**
     * Steps letter by letter and checks the ASCII value of the character
     * @param equation String that can be parsed into a molecular equation
     * @return A HashMap that contains the parsed Atomic Symbol with values of the mole count
     */
    protected static HashMap<String, Integer> parse(String equation) {

        HashMap<String, Integer> elements = new HashMap<>();
        StringBuilder element = new StringBuilder();
        StringBuilder moles   = new StringBuilder();

        for (int i = 0; i < equation.length(); i++) {
            char currentChar = equation.charAt(i);
            char nextChar    = i + 1  < equation.length() ? equation.charAt(i + 1) : '\0';

            if (Character.isUpperCase(currentChar)) {
                element = new StringBuilder();
                element.append(currentChar);

                if (nextChar > 0) {

                    if (Character.isLowerCase(nextChar)) {
                        element.append(nextChar);
                        i++;
                        if (elements.containsKey(element.toString())) {
                            elements.put(element.toString(), elements.get(element.toString()) + 1);
                            continue;
                        }
                        elements.put(element.toString(), 1);
                    }

                    else if (Character.isUpperCase(nextChar) || Character.isDigit(nextChar)) {
                        if (elements.containsKey(element.toString())) {
                            elements.put(element.toString(), elements.get(element.toString()) + 1);
                            continue;
                        }
                        elements.put(element.toString(), 1);
                    }

                }
            }

            else if (Character.isDigit(currentChar)) {
                moles.append(currentChar);

                if      (nextChar > 0 && Character.isDigit(nextChar)) i++;
                else if (nextChar > 0 && Character.isLetter(nextChar) || nextChar == '\0') {
                    elements.put(element.toString(), elements.get(element.toString()) + Integer.parseInt(moles.toString()) - 1);
                    moles = new StringBuilder();
                }
            }

        }

        return elements;

    }

}
