package lexer.java8;

import open.FileUtil;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        // 读取文件
        String file = FileUtil.INSTANCE.loadFile("editor/src/main/java/lexer/java8/Main.java");
        CharStream input = CharStreams.fromString(file);
        Java8Lexer lexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        Java8Parser.CompilationUnitContext tree = parser.compilationUnit();
        Java8ParserBaseListener listener = new Java8ParserBaseListener() {
            @Override
            public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
                System.out.println(ctx.methodHeader().methodDeclarator().start.getStartIndex());
                System.out.println(ctx.methodHeader().methodDeclarator().start.getStopIndex());
                System.out.println("method: " + ctx.methodHeader().methodDeclarator().Identifier().getText());
            }

            @Override
            public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
                System.out.println("class: " + ctx.normalClassDeclaration().Identifier().getText());
            }
        };

        ParseTreeWalker.DEFAULT.walk(listener, tree);
    }
}
