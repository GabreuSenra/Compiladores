/*Trabalho 2 - Teoria dos Compiladores (DCC045) 
 * Alunos: Gabriel Pereira Senra Soares - 201935006
 *         Lucas Castro Carvalho - 201835015
*/

package lang.ast;

public abstract class SuperNode {
    private int line;
    private int column;

    public SuperNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public abstract <T> T accept(Visitor<T> visitor);
}