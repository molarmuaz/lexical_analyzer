import java.io.*;
import java.util.*;

class Token {
    String type;
    String value;

    public Token(String t, String v) {
        this.type = t;
        this.value = v;
    }

    @Override
    public String toString() {
        return "Token[type =" + type + ", value =" + value + "]";
    }
}

class LexicalAnalyzer {
    private String code;
    private List<Token> tokens;

    private final Set<String> keyWords = new HashSet<>(
            Arrays.asList("ginti", "asharia", "haq", "harf", "dikhao", "batao", "sach", "jhoot"));
    private final Set<Character> operations = new HashSet<>(Arrays.asList('+', '-', '*', '/', '%', '=', '>', '<'));
    private final Set<Character> punctuations = new HashSet<>(Arrays.asList(';', '(', ')'));

    public LexicalAnalyzer(String code) {
        this.tokens = new ArrayList<>();
        // Convert all the code to lower case before tokenization is performed
        this.code = code.toLowerCase();
        // replace all whitespace, tabs, lines, etc with a single space character ' '
        this.code.replaceAll("\\s+", " ");
    }

    public List<Token> tokenize() {
        StringBuilder curr = new StringBuilder();
        boolean is_comment = false;
        boolean is_string = false;

        char[] line = code.toCharArray();

        for (int i = 0; i < line.length; i++) {

            // Take each individual character from the code
            char c = line[i];

            // Detect and skip over single line comment
            if (c == '/' && line[i + 1] == '/' && i + 1 < line.length) {
                while (i < line.length && line[i] != '\n') {
                    i++;
                }
                continue;
            }

            // Detect and skip over multi-line comments
            if (c == '/' && i + 1 < line.length && line[i + 1] == '*') {
                is_comment = true;
                i += 2;
                while (i + 1 < line.length && !(line[i] == '*' && line[i + 1] == '/')) {
                    i++;
                }
                i++;
                is_comment = false;
                continue;
            }

            if (is_comment) {continue;}

            // Detect strings
            if (c == '"' && !is_comment) {
                is_string = !is_string;

                if (!is_string) {
                    tokens.add(new Token("STRING", curr.toString()));
                    // Clear the string buffer
                    curr.setLength(0);
                }
                continue;
            }

            if (is_string) {
                curr.append(c);
                continue;
            }

            // Detect keywords and identifiers
            if (Character.isLetter(c)) {
                curr.append(c);
                while (i + 1 < line.length && Character.isLetterOrDigit(line[i + 1])) {
                    curr.append(line[++i]);
                }

                String tkn = curr.toString();
                if (keyWords.contains(tkn)) {
                    tokens.add(new Token("KEYWORD", tkn));
                } 
                else if (tkn.equals(tkn.toUpperCase())) {
                    // All words that are the same as their uppercase version are constants
                    tokens.add(new Token("CONSTANT", tkn)); 
                } 
                else if (tkn == "sach" || tkn == "jhoot") {
                    tokens.add(new Token("BOOLEAN", tkn));
                }
                else {
                    tokens.add(new Token("IDENTIFIER", tkn));
                }
                //Clear the buffer
                curr.setLength(0);
            }
            // Detect numbers (int, float)
            else if (Character.isDigit(c)) {
                curr.append(c);
                boolean isFloat = false;

                while (i + 1 < line.length && (Character.isDigit(line[i + 1]) || line[i + 1] == '.')) {
                    if (line[i + 1] == '.') {
                        isFloat = true;
                        tokens.add(new Token("FLOAT", curr.toString()));
                    }
                    curr.append(line[++i]);
                }

                if(!isFloat) {
                    tokens.add(new Token("INT", curr.toString()));
                }
                curr.setLength(0);
            }
            // Detect char literals
            else if (c == '\'' && i + 2 < line.length && line[i + 2] == '\'') {
                tokens.add(new Token("CHAR", String.valueOf(line[i + 1])));
                i += 2; //SKIP '\''
            }
            // Detect operators and punctuations
            else if (operations.contains(c)) {
                tokens.add(new Token("OPERATOR", String.valueOf(c)));
            } else if (punctuations.contains(c)) {
                tokens.add(new Token("PUNCTUATION", String.valueOf(c)));
            }
            
            // Ignore spaces and line breaks
            else if (c == '\n' || c == '\t' || c == ' '){
                continue;
            }
            // Anything else is treated as unknown and later can be used to identify errors
            else {
                tokens.add(new Token("UNKNOWN", String.valueOf(c)));
            }
        }

        return tokens;
    }

    public void displayTokens() {
        try {
            System.out.println("Total tokens: " + tokens.size());
            List<Token> tokens = tokenize();

            System.out.println("Tokens: ");

            for (Token token : tokens) {
                System.out.println(token);
            } 
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] arg) {
        if (arg.length != 1) {
            System.out.println("Usage: java Main <f_name.mi>");
            return;
        }

        String f_name = arg[0];

        if (!f_name.endsWith(".mi")) {
            System.out.println("Error: Only .mi files are allowed.");
            return;
        }

        try (BufferedReader r = new BufferedReader(new FileReader(f_name))) {
            StringBuilder code = new StringBuilder();
            String ln = r.readLine();
            while (ln != null) {
                ln = r.readLine();
                code.append(ln).append("\n");
            }

            // Pass the read content to LexicalAnalyzer
            LexicalAnalyzer lexer = new LexicalAnalyzer(code.toString());
            lexer.displayTokens();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
