package lang.ast;

public interface Visitor<T> {
    T visit(ProgramNode node);
    T visit(DataNode node);
    T visit(DeclNode node);
    T visit(FunNode node);
    T visit(ParamsNode node);
    T visit(ParamNode node);
    T visit(TypeNode node);

    T visit(BTypeNode node);
    T visit(CmdNode node);

    T visit(DefNode node);
    T visit(ExpNode node);
    T visit(ExpsNode node);

    T visit(BinOpNode node);
    T visit(UnOpNode node);

    T visit(BoolNode node);
    T visit(NullNode node);
    T visit(IntNode node);
    T visit(FloatNode node);
    T visit(CharNode node);

    T visit(NewNode node);
    T visit(FuncCallNode node);

    T visit(BlockNode node);
    T visit(IfNode node);
    T visit(IterateNode node);
    T visit(ReadNode node);
    T visit(PrintNode node);
    T visit(ReturnNode node);
    T visit(CallNode node);
    T visit(AssignNode node);
    
    T visit(LValueNode node);
}
