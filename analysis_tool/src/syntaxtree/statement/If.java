package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import java.util.List;
import syntaxtree.StatementList;
import syntaxtree.Symbols;

import syntaxtree.condition.Condition;
import syntaxtree.statement.Statement;
import utilities.Sequencer;

/**
 * Data representation for if statements
 *
 */
public class If extends Statement {

    private Condition cond;
    private StatementList trueBranch;
    private StatementList falseBranch;

    public If(Condition cond, StatementList tBranch, StatementList fBranch) {
        this.cond = cond;
        this.trueBranch = tBranch;
        this.falseBranch = fBranch;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public List<Statement> gettBranch() {
        return trueBranch;
    }

    public void settBranch(StatementList tBranch) {
        this.trueBranch = tBranch;
    }

    public List<Statement> getfBranch() {
        return falseBranch;
    }

    public void setfBranch(StatementList fBranch) {
        this.falseBranch = fBranch;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nTrue branch: " + trueBranch.toString() + "\nFalse branch: " + falseBranch.toString() + "\n";
    }
    
    @Override
    public String toString() {
        return Symbols.IF + Symbols.SEPERATOR + cond + Symbols.SEPERATOR + Symbols.THEN + Symbols.NEWLINE
                + Symbols.INDENTION + trueBranch + Symbols.NEWLINE
                + Symbols.ELSE + Symbols.NEWLINE
                + Symbols.INDENTION + falseBranch
                + Symbols.FI;    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeSet labels() {
        return (new NodeSet())
                .addNode(new Node(this))
                .union(trueBranch.lables())
                .union(falseBranch.lables());
    }

    @Override
    public Node init() {
        return new Node(this);
    }

    @Override
    public void setLabel (Sequencer seq) {
        
        // Initially set my own label.
        super.setLabel(seq);
        
        for(Statement s : this.trueBranch) {
            s.setLabel(seq);
        }
        for(Statement s : this.falseBranch) {
            s.setLabel(seq);
        }
    }
    
    @Override
    public NodeSet finalNodes() {
        return (NodeSet.emptySet
                .union(trueBranch.lables())
                .union(falseBranch.lables()));
    }
}
