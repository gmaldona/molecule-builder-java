package maldonado.gregory.modeller.parser;

import java.util.HashMap;

public class EquationParser {

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
