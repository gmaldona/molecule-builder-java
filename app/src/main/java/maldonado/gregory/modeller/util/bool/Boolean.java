package maldonado.gregory.modeller.util.bool;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Special Boolean uses throughout the package
 */
public class Boolean {

    private boolean bool;

    public Boolean(int value) { this.bool = value == 1; }

    public Boolean(boolean bool) { this.bool = bool; }

    public int parseToInt() { return bool ? 1 : 0; }

    public boolean value() { return this.bool; }

    public boolean flip() { return !bool; }
}
