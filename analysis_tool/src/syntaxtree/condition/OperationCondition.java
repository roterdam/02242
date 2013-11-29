package syntaxtree.condition;

import analysis.lattices.IntervalLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import syntaxtree.BooleanOperation;
import syntaxtree.Symbols;

/**
 * Data representation for boolean condition with a boolean operator
 *
 */
public class OperationCondition extends Condition {

    private Condition cond1;
    private Condition cond2;
    private BooleanOperation bo;

    public OperationCondition(Condition cond1, Condition cond2, BooleanOperation bo) {
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.bo = bo;
    }

    public Condition getCond1() {
        return cond1;
    }

    public void setCond1(Condition cond1) {
        this.cond1 = cond1;
    }

    public Condition getCond2() {
        return cond2;
    }

    public void setCond2(Condition cond2) {
        this.cond2 = cond2;
    }

    public BooleanOperation getBo() {
        return bo;
    }

    public void setBo(BooleanOperation bo) {
        this.bo = bo;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition1: " + cond1.toString() + "\nCondition2: " + cond2.toString() + "\nBoolean Operation: " + bo.toString() + "\n";
    }

    @Override
    public String toString() {
        return cond1 + Symbols.SEPERATOR
                + Symbols.symbolOf(bo) + Symbols.SEPERATOR + cond2;
    }
    @Override
    public SignSet evaluate(SignsLattice lattice) {
        SignSet retval = new SignSet();
        retval.merge(SignSet.empty);
        return retval;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.cond1.hasPotentialUnderFlow(lattice) || this.cond2.hasPotentialUnderFlow(lattice);
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.cond1.isOutOfBounds(lattice) || this.cond2.isOutOfBounds(lattice);
    }
    
}
