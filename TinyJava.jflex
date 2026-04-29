/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 30, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * TinyJava.jflex: Defines lexical rules and tokens for the lexer             *
 ******************************************************************************/

import java_cup.runtime.*;

%%

%class TinyJavaLexer
%unicode
%line
%column
%cupsym Symbol
%cup
%eofval{
    return symbolFactory.newSymbol ("EOF", Symbol . EOF);
%eofval}

%{
    private SymbolFactory symbolFactory;

    public TinyJavaLexer (java.io.Reader input, SymbolFactory sf) {
        this (input);
        symbolFactory = sf;
    }

    private void echo () { System . out . print (yytext ()); }
    
    public int position () { return yycolumn; }
    
    /* Token class name helpers */
    private String kw() { return "(keyword, " + yytext() + ")"; }
    private String pnc() { return "(punctuation, " + yytext() + ")"; }
    private String op() { return "(operator, " + yytext() + ")"; }
    private String integer() { return "(integer, " + yytext() + ")"; }
    private String id() { return "(id, " + yytext() + ")"; }
%}

Whitespace  = [ \t\r\n]+
Identifier  = [[:letter:]][[:letter:][:digit:]]*(_[[:letter:][:digit:]]+)*
Integer     = [[:digit:]]+
Comment     = "//"[^\r\n]*

%%
/* No tokens emitted */
{Whitespace}    { echo(); /* Don't emit a token */ }
{Comment}       { echo(); /* Don't emit a token */ }

/* Punctuation--no quotes this time, thank goodness */
";"     { echo(); return symbolFactory.newSymbol(pnc(), Symbol.SEMICOLON); }
","     { echo(); return symbolFactory.newSymbol(pnc(), Symbol.COMMA); }
"."     { echo(); return symbolFactory.newSymbol(pnc(), Symbol.DOT); }
"{"     { echo(); return symbolFactory.newSymbol(pnc(), Symbol.LBRACE); }
"}"     { echo(); return symbolFactory.newSymbol(pnc(), Symbol.RBRACE); }

/* Operations */
"("     { echo(); return symbolFactory.newSymbol(op(), Symbol.LPAREN); }
")"     { echo(); return symbolFactory.newSymbol(op(), Symbol.RPAREN); }
"["     { echo(); return symbolFactory.newSymbol(op(), Symbol.LBRACKET); }
"]"     { echo(); return symbolFactory.newSymbol(op(), Symbol.RBRACKET); }
"=="    { echo(); return symbolFactory.newSymbol(op(), Symbol.EQ); }
"="     { echo(); return symbolFactory.newSymbol(op(), Symbol.ASSIGN); }
"+"     { echo(); return symbolFactory.newSymbol(op(), Symbol.PLUS); }
"-"     { echo(); return symbolFactory.newSymbol(op(), Symbol.MINUS); }
"!="    { echo(); return symbolFactory.newSymbol(op(), Symbol.NE); }
"!"     { echo(); return symbolFactory.newSymbol(op(), Symbol.NOT); }
"*"     { echo(); return symbolFactory.newSymbol(op(), Symbol.STAR); } /*times*/
"/"     { echo(); return symbolFactory.newSymbol(op(), Symbol.SLASH); }
"<="    { echo(); return symbolFactory.newSymbol(op(), Symbol.LE); }
"<"     { echo(); return symbolFactory.newSymbol(op(), Symbol.LT); }
">="    { echo(); return symbolFactory.newSymbol(op(), Symbol.GE); }
">"     { echo(); return symbolFactory.newSymbol(op(), Symbol.GT); }
"||"    { echo(); return symbolFactory.newSymbol(op(), Symbol.OR); }
"&&"    { echo(); return symbolFactory.newSymbol(op(), Symbol.AND); }

/* Keywords--longest first */
boolean     { echo(); return symbolFactory.newSymbol(kw(), Symbol.BOOLEAN); }
nextInt     { echo(); return symbolFactory.newSymbol(kw(), Symbol.NEXTINT); }
println     { echo(); return symbolFactory.newSymbol(kw(), Symbol.PRINTLN); }
Scanner     { echo(); return symbolFactory.newSymbol(kw(), Symbol.SCANNER); }
import      { echo(); return symbolFactory.newSymbol(kw(), Symbol.IMPORT); }
public      { echo(); return symbolFactory.newSymbol(kw(), Symbol.PUBLIC); }
return      { echo(); return symbolFactory.newSymbol(kw(), Symbol.RETURN); }
static      { echo(); return symbolFactory.newSymbol(kw(), Symbol.STATIC); }
String      { echo(); return symbolFactory.newSymbol(kw(), Symbol.STRING); }
System      { echo(); return symbolFactory.newSymbol(kw(), Symbol.SYSTEM); }
class       { echo(); return symbolFactory.newSymbol(kw(), Symbol.CLASS); }
false       { echo(); return symbolFactory.newSymbol(kw(), Symbol.FALSE); }
while       { echo(); return symbolFactory.newSymbol(kw(), Symbol.WHILE); }
args        { echo(); return symbolFactory.newSymbol(kw(), Symbol.ARGS); }
else        { echo(); return symbolFactory.newSymbol(kw(), Symbol.ELSE); }
java        { echo(); return symbolFactory.newSymbol(kw(), Symbol.JAVA); }
main        { echo(); return symbolFactory.newSymbol(kw(), Symbol.MAIN); }
this        { echo(); return symbolFactory.newSymbol(kw(), Symbol.THIS); }
true        { echo(); return symbolFactory.newSymbol(kw(), Symbol.TRUE); }
util        { echo(); return symbolFactory.newSymbol(kw(), Symbol.UTIL); }
void        { echo(); return symbolFactory.newSymbol(kw(), Symbol.VOID); }
int         { echo(); return symbolFactory.newSymbol(kw(), Symbol.INT); }
new         { echo(); return symbolFactory.newSymbol(kw(), Symbol.NEW); }
out         { echo(); return symbolFactory.newSymbol(kw(), Symbol.OUT); }
if          { echo(); return symbolFactory.newSymbol(kw(), Symbol.IF); }
in          { echo(); return symbolFactory.newSymbol(kw(), Symbol.IN); }

{Integer} {
    echo(); return symbolFactory.newSymbol(integer(), Symbol.INTEGER, yytext());
}
{Identifier} {
    echo(); return symbolFactory.newSymbol(id(), Symbol.ID, yytext());
}

/* Any non-matches fall through and emit an error */
. { echo(); ErrorMessage.print(yychar, "Illegal character"); }